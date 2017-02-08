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

package com.exorath.plugin.simple1v1.core;

import com.exorath.plugin.simple1v1.core.map.MapManager;
import com.exorath.plugin.simple1v1.core.state.StateManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by toonsev on 2/4/2017.
 */
public class Main extends JavaPlugin {
    private static Main instance;
    private static StateManager stateManager;
    private static MapManager mapManager;

    @Override
    public void onEnable() {
        Main.instance = this;
        Main.stateManager = new StateManager();
        Main.mapManager = new MapManager();
    }

    public static StateManager getStateManager() {
        return stateManager;
    }

    public static MapManager getMapManager() {
        return mapManager;
    }

    public static Main getInstance() {
        return instance;
    }
}
