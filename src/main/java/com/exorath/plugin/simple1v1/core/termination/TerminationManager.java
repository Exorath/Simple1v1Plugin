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
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.util.HashMap;

/**
 * Created by toonsev on 2/6/2017.
 */
public class TerminationManager implements Listener {
    private HashMap<Player, Player> lastDamagerByPlayer = new HashMap<>();

    public TerminationManager() {
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (Bukkit.getOnlinePlayers().size() < 2 && Main.getStateManager().getState() == State.STARTED) {
            Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("Insufficient players, server shutting down"));
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> Main.getStateManager().setState(State.STOPPING), 60);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onStateChange(StateChangeEvent event) {
        if (event.getNewState().equals(State.STOPPING))
            Main.terminate();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.isCancelled())
            return;
        if (event.getEntity() instanceof Player) {
            if (event.getDamager() instanceof Player) {
                lastDamagerByPlayer.put((Player) event.getEntity(), (Player) event.getDamager());
            } else if (event.getDamager() instanceof Projectile) {
                ProjectileSource src = ((Projectile) event.getDamager()).getShooter();
                if (src instanceof Player)
                    lastDamagerByPlayer.put((Player) event.getEntity(), (Player) event.getDamager());
            }
        }
    }

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> Main.terminate(), 60l);
        event.getEntity().sendMessage(ChatColor.RED  + "You died and lost the game.");
        Player lastDamager = lastDamagerByPlayer.get(event.getEntity());
        if(lastDamager == null)
            return;
        lastDamager.sendMessage(ChatColor.GREEN + "Woohoo! You won the game!");
        Bukkit.broadcastMessage("Game is terminating.");
    }

}
