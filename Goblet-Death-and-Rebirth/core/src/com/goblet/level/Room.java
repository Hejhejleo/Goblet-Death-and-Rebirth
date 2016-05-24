package com.goblet.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.goblet.entities.Direction;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by johan on 5/21/16.
 */
public class Room {
    private HashMap<Direction, Point> doorLocations;
    private Tile[][] tiles;
    String atlasLocation = "assets/tiles/room/room.pack";
    private TextureRegion floorTextureRegion;
    private Map<Direction, WallObject> wallMap;
    private TextureAtlas atlas;
    private Position bottomLeft;
    private Position topRight;
    private boolean doorsOpen;


    public void draw(Batch batch){
        batch.draw(floorTextureRegion, -floorTextureRegion.getRegionWidth()/2, bottomLeft.getY(), floorTextureRegion.getRegionWidth(), floorTextureRegion.getRegionHeight());
        for (WallObject currentWall : wallMap.values()){
            currentWall.draw(batch);
        }

    }

    public void drawFloor(Batch batch){

    }

    public Room(float bottomLeftX, float bottomLeftY, float topRightX, float topRightY){
        bottomLeft = new Position(bottomLeftX, bottomLeftY);
        topRight = new Position(topRightX, topRightY);

        wallMap = new HashMap<Direction, WallObject>();
        tiles = new Tile[16][26];
        atlas = new TextureAtlas(Gdx.files.internal(atlasLocation));

        wallMap.put(Direction.UP, new WallObject(atlas.findRegion("spr_upper_wall"), atlas.findRegion("spr_upper_wall_path"), atlas.findRegion("spr_upper_door"), Direction.UP, bottomLeft, topRight, true));
        wallMap.put(Direction.DOWN, new WallObject(atlas.findRegion("spr_lower_wall"), atlas.findRegion("spr_lower_wall_path"), atlas.findRegion("spr_lower_door"), Direction.DOWN, bottomLeft, topRight, true));
        wallMap.put(Direction.LEFT, new WallObject(atlas.findRegion("spr_left_wall"), atlas.findRegion("spr_left_wall_path"), atlas.findRegion("spr_left_door"), Direction.LEFT, bottomLeft, topRight, true));
        wallMap.put(Direction.RIGHT, new WallObject(atlas.findRegion("spr_right_wall"), atlas.findRegion("spr_right_wall_path"), atlas.findRegion("spr_right_door"), Direction.RIGHT, bottomLeft, topRight, true));

        floorTextureRegion = atlas.findRegion("spr_floor");
    }

    public void openDoors(){
        for (WallObject currentWall : wallMap.values()){
            currentWall.openDoor();
            doorsOpen = true;
        }
    }

    public void closeDoors(){
        for (WallObject currentWall : wallMap.values()){
            currentWall.closeDoor();
            doorsOpen = false;
        }
    }

    public boolean doorsAreOpen(){
        return doorsOpen;
    }


}

