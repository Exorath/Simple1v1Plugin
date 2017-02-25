/*
 * Copyright 2017 Exorath
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.exorath.plugin.simple1v1.core.monitor;

import com.exorath.plugin.simple1v1.core.Main;

import java.text.NumberFormat;

/**
 * Created by toonsev on 2/25/2017.
 */
public class MonitorManager {
    public MonitorManager(){
        new Lag().runTaskTimer(Main.getInstance(), 20, 1);

        new Thread(() -> {
            while(true) {
                Runtime runtime = Runtime.getRuntime();
                NumberFormat format = NumberFormat.getInstance();
                StringBuilder sb = new StringBuilder();
                long maxMemory = runtime.maxMemory();
                long allocatedMemory = runtime.totalMemory();
                long freeMemory = runtime.freeMemory();
                sb.append("free memory: " + format.format(freeMemory / 1024) + "<br/>");
                sb.append("allocated memory: " + format.format(allocatedMemory / 1024) + "<br/>");
                sb.append("max memory: " + format.format(maxMemory / 1024) + "<br/>");
                sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + "<br/>");
                System.out.println(sb.toString());
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
