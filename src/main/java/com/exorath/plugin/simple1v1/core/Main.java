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

import com.exorath.plugin.simple1v1.core.baseplugin.BasePluginManager;
import com.exorath.plugin.simple1v1.core.map.MapManager;
import com.exorath.plugin.simple1v1.core.message.MessageManager;
import com.exorath.plugin.simple1v1.core.protection.ProtectionManager;
import com.exorath.plugin.simple1v1.core.start.StartManager;
import com.exorath.plugin.simple1v1.core.state.StateManager;
import com.exorath.plugin.simple1v1.core.teleport.TeleportManager;
import com.exorath.plugin.simple1v1.core.termination.TerminationManager;
import com.exorath.plugin.simple1v1.core.visibility.VisibilityManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/**
 * Created by toonsev on 2/4/2017.
 */
public class Main extends JavaPlugin {
    private static Main instance;

    private static HashMap<Class, Object> managers = new HashMap<>();

    @Override
    public void onEnable() {
        try {
            Main.instance = this;
            addManager(new StateManager());
            addManager(new MapManager());
            addManager(new MessageManager());
            addManager(new ProtectionManager());
            addManager(new StartManager());
            addManager(new TeleportManager());
            addManager(new TerminationManager());
            addManager(new BasePluginManager());
            addManager(new VisibilityManager());
        } catch (Exception e) {
            e.printStackTrace();
            terminate();
        }

    }

    public static void addManager(Object manager) {
        if (manager instanceof Listener)
            Bukkit.getPluginManager().registerEvents((Listener) manager, Main.getInstance());
        managers.put(manager.getClass(), manager);
    }

    public static void terminate() {
        System.out.println("1v1Plugin is terminating...");
        Bukkit.shutdown();
        System.out.println("Termination failed, force exiting system...");
        System.exit(0);
    }

    public static <T> T getManager(Class<T> clazz) {
        return (T) managers.get(clazz);
    }

    public static StateManager getStateManager() {
        return getManager(StateManager.class);
    }

    public static MapManager getMapManager() {
        return getManager(MapManager.class);
    }

    public static void refreshVisibility(Player player){
        for(Player viewer : Bukkit.getOnlinePlayers()){
            if(player.equals(viewer))
                continue;
            viewer.hidePlayer(player);
            viewer.showPlayer(player);
        }
    }
    public static Main getInstance() {
        return instance;
    }
}
