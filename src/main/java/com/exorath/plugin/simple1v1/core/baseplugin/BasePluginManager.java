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

package com.exorath.plugin.simple1v1.core.baseplugin;

import com.exorath.plugin.base.ExoBaseAPI;
import com.exorath.plugin.simple1v1.core.Main;
import com.exorath.service.connector.res.BasicServer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.net.UnknownHostException;

/**
 * Created by toonsev on 2/11/2017.
 */
public class BasePluginManager implements Listener{
    private ExoBaseAPI exoBaseAPI;

    public BasePluginManager(){
        exoBaseAPI = ExoBaseAPI.getInstance();
        try {
            exoBaseAPI.setupGame(new BasicServer("simple1v1", "map1", "normal"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Main.terminate();
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        exoBaseAPI.onGameJoin(event.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        exoBaseAPI.onGameLeave(event.getPlayer());
    }
}
