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

package com.exorath.plugin.simple1v1.core.teleport;

import com.exorath.plugin.simple1v1.core.Main;
import com.exorath.plugin.simple1v1.core.state.State;
import com.exorath.plugin.simple1v1.core.state.StateChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

/**
 * Created by toonsev on 2/8/2017.
 */
public class TeleportManager {
    public TeleportManager() {

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Location location = Main.getMapManager().getLobbySpawn();
        if (location == null)
            return;
        event.getPlayer().teleport(location);
    }

    @EventHandler
    public void onStateChange(StateChangeEvent event) {
        if (event.getNewState() == State.STARTED) {
            List<Location> spawns = Main.getMapManager().getSpawns();
            int i = 0;
            int size = Main.getMapManager().getSpawns().size();
            if(size == 0) {
                Bukkit.broadcastMessage("No spawns found, shutting down.");
                Main.getStateManager().setState(State.STOPPING);
                return;
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (i >= size)
                    i = 0;
                player.teleport(Main.getMapManager().getSpawns().get(i));
                i++;
            }
        }
    }
}
