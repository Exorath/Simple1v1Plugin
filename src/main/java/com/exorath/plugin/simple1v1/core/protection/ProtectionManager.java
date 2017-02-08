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

package com.exorath.plugin.simple1v1.core.protection;

import com.exorath.plugin.simple1v1.core.Main;
import com.exorath.plugin.simple1v1.core.state.State;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

/**
 * Created by toonsev on 2/8/2017.
 */
public class ProtectionManager implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onMobSpawnEvent(EntitySpawnEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event){
        if(Main.getStateManager().getState() != State.STARTED)
            event.setCancelled(true);
    }
}
