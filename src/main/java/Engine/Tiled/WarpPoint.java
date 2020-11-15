/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine.Tiled;

import Engine.Entity.Collision.CollisionRegistery;
import Engine.Entity.EntityType.BaseEntity;
import Engine.Entity.EntityType.PlayerEntity;
import Engine.Graphics.Buffer;
import Engine.Networking.ArrayListNetwork;
import Engine.Utils.ObjectCaster;
import Engine.Utils.Vector2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexandre Voghel
 */
public class WarpPoint extends BaseEntity {
    private Vector2D position;
    private Vector2D positionToWarp;
    private int tileWidth;
    private int tileHeight;
    private String map;
    
    public WarpPoint(Vector2D position, Vector2D positionToWarp, int tileWidth, int tileHeight, String referenceMap) {
        this.position = position.Multiply(32);
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.map = referenceMap;
        this.positionToWarp = positionToWarp.Multiply(32);
    }
    
    /* NEED TO BE ADAPTED */
    public PhysicalMap warp(PlayerEntity player) {
        PhysicalMap previousMap = MapManager.getInstance().getCurrentlyLoadMap();
        if(map != null) {
            CollisionRegistery.getInstance().clearList();
            try {
                if(!MapManager.getInstance().isMapLoaded(map)) {
                    MapManager.getInstance().loadMap(map);
                }
            }
            catch (Exception ex) {
                System.out.print(ex.toString());
                return null;
            }
            MapManager.getInstance().setLoadedMap(map);
        }       
        player.setPosition(positionToWarp);
        return null;
    }
    
    @Override
    public Rectangle getHitbox() {
        return ObjectCaster.Rectangle(position, tileWidth * 32, tileHeight * 32);
    }

    @Override
    public void draw(Buffer buffer) {
        System.out.println("draw");
        buffer.drawRectangle(position, tileWidth * 32, tileHeight * 32, Color.red);
    }

    @Override
    public void syncData(ArrayListNetwork data) {
        
    }

    @Override
    public void receiveData(ArrayListNetwork data) {
        
    }
            
}
