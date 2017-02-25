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

package com.exorath.plugin.simple1v1.core.message;

import com.exorath.plugin.simple1v1.core.state.State;
import com.exorath.plugin.simple1v1.core.state.StateChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by toonsev on 2/8/2017.
 */
public class MessageManager implements Listener {
    @EventHandler
    public void onStateChange(StateChangeEvent event){
        if(event.getNewState() == State.STARTED){
            Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("The game has started."));
        }else if(event.getNewState() == State.WAITING_FOR_PLAYERS){
            Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("Waiting for more players."));
        }else if(event.getNewState() == State.COUNTING_DOWN){
            Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("Starting countdown:"));
        }else if(event.getNewState() == State.STOPPING){
            Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("Server is stopping."));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.getPlayer().sendMessage("Welcome, simple1v1 running.");
    }
}
