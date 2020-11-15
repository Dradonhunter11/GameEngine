/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine.Tiled;

import java.util.HashMap;

/**
 *
 * @author Alexandre Voghel
 */
public class MapManager {
 
    private static MapManager instance;
    
    private HashMap<String, PhysicalMap> registeredMap;
    
    private PhysicalMap currentlyLoadedMap;
    
    public static MapManager getInstance() {
        if(instance == null) {
            instance = new MapManager();
        }
        return instance;
    } 
    
    private MapManager() {
        registeredMap = new HashMap();
    }
    
    public void registerMap(String mapName, PhysicalMap map) {
        registeredMap.put(mapName, map);
    }
    
    public PhysicalMap getCurrentlyLoadMap() {
        return currentlyLoadedMap;
    }
    
    public boolean isMapLoaded(String mapName) {
        if(!registeredMap.containsKey(mapName)) {
            return false;
        }
        return registeredMap.get(mapName).loaded;
    }
    
    public void loadMap(String mapName) throws Exception {
        if(!registeredMap.containsKey(mapName)) {
            throw new Exception("Map does exist");
        }
        registeredMap.get(mapName).load();
    }
    
    public boolean setLoadedMap(String mapName) {
        if(!registeredMap.containsKey(mapName)) {
            return false;
        }
        currentlyLoadedMap = registeredMap.get(mapName);
        return true;
    }   
    
    public boolean setLoadedMap(PhysicalMap mapName) {
        if(!registeredMap.containsKey(mapName)) {
            return false;
        }
        currentlyLoadedMap = registeredMap.get(mapName);
        return true;
    } 
}
