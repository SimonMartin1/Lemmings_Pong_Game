package Proyecto.games.New_Lemmings_game.Levels;


import Proyecto.games.New_Lemmings_game.*;
import Proyecto.games.New_Lemmings_game.utils.Ability;
import Proyecto.games.utils.SoundManager;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LoadFromFiles {

    // Assuming 'levels' and 'stock' are defined elsewhere in this class

    public Level loadLevelFromFile(String path, SoundManager soundManager) throws IOException {
        Properties props = new Properties();
        try (FileReader reader = new FileReader(path)) {
            props.load(reader);
        }
    
        // Parsear spawn
        int spawnX = Integer.parseInt(props.getProperty("spawnX"));
        int spawnY = Integer.parseInt(props.getProperty("spawnY"));
        int spawnRate = Integer.parseInt(props.getProperty("spawnRate"));
        Spawn spawn = new Spawn(spawnX, spawnY, spawnRate);
    
        // Parsear exit
        int exitX = Integer.parseInt(props.getProperty("exitX"));
        int exitY = Integer.parseInt(props.getProperty("exitY"));
        int exitWidth = Integer.parseInt(props.getProperty("exitWidth"));
        Exit exit = new Exit(exitX, exitY, exitWidth);
    
        // Map info
        int mapId = Integer.parseInt(props.getProperty("mapId"));
        int mapWidth = Integer.parseInt(props.getProperty("mapWidth"));
        int mapHeight = Integer.parseInt(props.getProperty("mapHeight"));
        Game_Map map = new Game_Map(mapId, mapWidth, null, mapWidth, mapHeight, spawn, exit);
    
        // Stock
        String[] stockParts = props.getProperty("stock").split(",");
        Map<Ability, Integer> stockMap = new HashMap<>();
        for (String part : stockParts) {
            String[] pair = part.split(":");
            stockMap.put(Ability.valueOf(pair[0]), Integer.parseInt(pair[1]));
        }
        Stock stock = new Stock(stockMap);
    
        // Otros datos
        String name = props.getProperty("name");
        int lemmings = Integer.parseInt(props.getProperty("lemmings"));
        int percentajeToWin = Integer.parseInt(props.getProperty("percentajeToWin"));

        return new Level(map, stock, lemmings, (double) percentajeToWin, 0, name, exit, spawnX, spawnY, soundManager);

    }
}
