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

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Created by toonsev on 2/6/2017.
 */
public class Map {
    private String mapName;
    private FileConfiguration configuration;

    public Map(String mapName){
        this.mapName = mapName;
        File worldDir = new File(Bukkit.getWorldContainer(), mapName);
        if(!worldDir.isDirectory()){
            System.out.println("1V1PLUGIN ERROR: Tried to initialize world '" + mapName + "' which does not exist.");
            System.exit(1);
        }
        File configFile = new File(worldDir, "exorath.yml");
        if(configFile.isFile())
            configuration = YamlConfiguration.loadConfiguration(configFile);
    }

    public String getMapName() {
        return mapName;
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public World getWorld(){
        return Bukkit.getWorld(mapName);
    }
}
