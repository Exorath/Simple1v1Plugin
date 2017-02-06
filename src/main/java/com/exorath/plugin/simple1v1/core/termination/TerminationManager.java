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

package com.exorath.plugin.simple1v1.core.termination;

import com.exorath.plugin.simple1v1.core.Main;
import com.exorath.plugin.simple1v1.core.state.State;
import com.exorath.plugin.simple1v1.core.state.StateChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by toonsev on 2/6/2017.
 */
public class TerminationManager implements Listener{
    public TerminationManager() {
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        if(Bukkit.getOnlinePlayers().size() < 2 && Main.getStateManager().getState() == State.STARTED){
            Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("Insufficient players, server shutting down"));
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> Main.getStateManager().setState(State.STOPPING), 60);
        }
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onStateChange(StateChangeEvent event){
        if(event.getNewState().equals(State.STOPPING))
           terminate();
    }

    private void terminate(){
        System.out.println("1v1Plugin is terminating...");
        Bukkit.shutdown();
        System.out.println("Termination failed, force exiting system...");
        System.exit(0);
    }
}
