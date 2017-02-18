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

package com.exorath.plugin.simple1v1.core.map;

import com.exorath.plugin.simple1v1.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by toonsev on 2/6/2017.
 */
public class MapManager {
    private Map gameMap;

    public MapManager() {
        selectMap();
    }

    private void selectMap(){
        File mapContainer = Bukkit.getWorldContainer();
        List<String> mapNames = new ArrayList<>();
        for (File mapDir : mapContainer.listFiles((dir, name) -> new File(dir, name).isDirectory())) {
            File exoDat = new File(mapDir, "exorath.yml");
            if(!exoDat.isFile())
                continue;
            mapNames.add(mapDir.getName());
        }
        if(mapNames.size() == 0){
            System.out.println("No maps found, add a map with an exorath.yml");
            Main.terminate();
        }
        String randomMapName = mapNames.get(new Random().nextInt(mapNames.size()));
        gameMap = new Map(randomMapName);
        if(gameMap.getConfiguration() == null){
            System.out.println("The map " + gameMap.getMapName() + " is not configured. Please add an exorath.yml Shutting down!");
            Bukkit.shutdown();
        }
    }


    public Location getLobbySpawn(){
        ConfigurationSection configuration = gameMap.getConfiguration().getConfigurationSection("lobbyspawn");
        return getLocation(configuration);
    }

    public List<Location> getSpawns(){
        List<Location> spawns = new ArrayList<>();
        ConfigurationSection configuration = gameMap.getConfiguration().getConfigurationSection("spawns");
        for(String spawnKey : configuration.getKeys(false)){
            ConfigurationSection spawnSection = configuration.getConfigurationSection(spawnKey);
            spawns.add(getLocation(spawnSection));
        }
        return new ArrayList<>();
    }

    public Location getLocation(ConfigurationSection section){
        if(section == null || !section.contains("x") || !section.contains("y") || !section.contains("z")) {
            System.out.println("Map " + gameMap.getMapName() + " lobby spawn not configured. Shutting down.");
            Main.terminate();
        }
        return new Location(gameMap.getWorld(), section.getDouble("x"), section.getDouble("y"), section.getDouble("z"));
    }
}
