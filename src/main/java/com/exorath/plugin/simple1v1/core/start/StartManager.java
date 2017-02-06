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

package com.exorath.plugin.simple1v1.core.start;

import com.exorath.plugin.simple1v1.core.Main;
import com.exorath.plugin.simple1v1.core.state.State;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by toonsev on 2/6/2017.
 */
public class StartManager implements Listener {
    public StartManager() {

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (Bukkit.getOnlinePlayers().size() > 2)
            event.getPlayer().kickPlayer("Too many players on server.");
        checkCountdown();
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        checkCountdown();
    }

    private void checkCountdown() {
        boolean canStart = Bukkit.getOnlinePlayers().size() == 2;
        if (canStart) {
            if (Main.getStateManager().getState().equals(State.WAITING_FOR_PLAYERS)) {
                Main.getStateManager().setState(State.COUNTING_DOWN);
            }
        } else if (Main.getStateManager().getState().equals(State.COUNTING_DOWN)) {
            Main.getStateManager().setState(State.WAITING_FOR_PLAYERS);
        }
    }

    private class CountdownTask extends BukkitRunnable {
        private int num = 10;

        @Override
        public void run() {
            if(Main.getStateManager().getState() != State.COUNTING_DOWN){
                Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("countdown cancelled."));
                cancel();
                return;
            }
            if(num == 0) {
                Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("Starting now..."));
                Main.getStateManager().setState(State.STARTED);
                cancel();
            }else
                Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("Starting in " + num + " seconds."));
            num--;
        }
    }
}
