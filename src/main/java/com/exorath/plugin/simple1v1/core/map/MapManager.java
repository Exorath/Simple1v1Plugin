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
        selectGameMap();
    }

    private void selectGameMap(){
        File mapContainer = Bukkit.getWorldContainer();
        List<String> mapNames = new ArrayList<>();
        for (File mapDir : mapContainer.listFiles((dir, name) -> new File(dir, name).isDirectory())) {
            File levelDat = new File(mapDir, "level.dat");
            if(!levelDat.isFile())
                continue;
            mapNames.add(mapDir.getName());
        }
        String randomMapName = mapNames.get(new Random().nextInt(mapNames.size()));
        gameMap = new Map(randomMapName);
    }
}
