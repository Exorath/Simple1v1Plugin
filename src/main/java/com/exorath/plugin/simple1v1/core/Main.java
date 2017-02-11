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
import com.exorath.plugin.simple1v1.core.message.MessageManager;
import com.exorath.plugin.simple1v1.core.protection.ProtectionManager;
import com.exorath.plugin.simple1v1.core.start.StartManager;
import com.exorath.plugin.simple1v1.core.state.StateManager;
import com.exorath.plugin.simple1v1.core.teleport.TeleportManager;
import com.exorath.plugin.simple1v1.core.termination.TerminationManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by toonsev on 2/4/2017.
 */
public class Main extends JavaPlugin {
    private static Main instance;
    private static StateManager stateManager;
    private static MapManager mapManager;
    private static MessageManager messageManager;
    private static ProtectionManager protectionManager;
    private static StartManager startManager;
    private static TeleportManager teleportManager;
    private static TerminationManager terminationManager;
    @Override
    public void onEnable() {
        try {
            Main.instance = this;
            Main.stateManager = new StateManager();
            Main.mapManager = new MapManager();
            Main.messageManager = new MessageManager();
            Main.protectionManager = new ProtectionManager();
            Main.startManager = new StartManager();
            Main.teleportManager = new TeleportManager();
            Main.terminationManager = new TerminationManager();
        }catch(Exception e){
            e.printStackTrace();
            terminate();
        }

    }

    public static void terminate(){
        System.out.println("1v1Plugin is terminating...");
        Bukkit.shutdown();
        System.out.println("Termination failed, force exiting system...");
        System.exit(0);
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
