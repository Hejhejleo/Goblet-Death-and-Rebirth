package com.goblet.gameEngine;

import com.goblet.entities.Enemy;
import com.goblet.level.Position;
import com.goblet.level.SpawnPoint;

import java.io.FileNotFoundException;

/**
 * Created by Simoido on 2016-05-23.
 */
public class EnemyConstructor {
    EnemyParser ep;

    public EnemyConstructor(){
        ep = new EnemyParser("enemies.json");
    }

    public Enemy createEnemy(String name, float xPos, float yPos){
        String atlasLocation = name +"/"+ name;
        Enemy enemy;
        Position position = new Position(xPos,yPos);
        Box box;

        try{
            box =  new Box(position,ep.getHitboxWidth(name),ep.getHitboxHeight(name),ep.getHitboxOffsetX(name),ep.getHitboxOffsetY(name));
        }catch (FileNotFoundException ex){
            box = null;
        }

        try{
            enemy = new Enemy(position,atlasLocation,ep.getMoveFrames(name),ep.getAttackFrames(name),(float) ep.getMoveSpeed(name)
                    ,ep.getHealth(name),ep.getAttackRange(name),ep.getMoveType(name),ep.getAttackType(name),box,(float) ep.getMoveAnimationSpeed(name),
                    (float) ep.getAttackAnimationSpeed(name));
        }catch (FileNotFoundException ex){
            return null;
        }

        return enemy;
    }
}
