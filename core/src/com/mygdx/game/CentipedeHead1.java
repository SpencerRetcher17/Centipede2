package com.mygdx.game;

/**
 * Created by sretcher on 5/1/2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by sretcher on 4/25/2017.
 */
public class CentipedeHead1 extends Sprite {

    enum State {
        LEFT, RIGHT, DOWN
    }

    State currentState, previousState;

    public static final float DOWN_MOVEMENT = 7f;
    public float downMovCounter;
    public float speed;
    public float stateTime;
    TextureRegion image;
    Vector2 position, size;
    Animation<TextureRegion> animation;
    TextureRegion currentFrame;
    TextureRegion[] frames;

    public CentipedeHead1(TextureRegion image, Vector2 position, Vector2 size) {
        super(new TextureRegion(image));
        setPosition(position.x, position.y);
        setSize(size.x, size.y);
        currentState = State.LEFT;
        previousState = State.LEFT;
        speed = 8f;

//        frames=new TextureRegion[20];
/*
        for(int i=0;i<5;i++)
        {
            frames[i]=splitTiles[0][i];
        }

        animation = new Animation(1, frames);
        stateTime=0f;
        currentFrame= animation.getKeyFrame(0);
*/
    }

    public void update(TiledMap map) {

        float delta = Gdx.graphics.getDeltaTime();
        //      stateTime+=Gdx.graphics.getDeltaTime()*4;
        Gdx.app.log("" + delta, "hello");
/*
        if(stateTime>4) {
            stateTime = 0;
        }
*/

        if (currentState == State.LEFT) {
            // setX(getX()-speed);
            setPosition(getX() - speed * delta, getY());
            //          currentFrame= animation.getKeyFrame(4+stateTime);

            if (getX() < 0) {
                previousState = currentState;
                currentState = State.DOWN;
                setFlip(true, false);
            }
        }

        if (currentState == State.RIGHT) {
            setPosition(getX() + speed * delta, getY());
            if (getX() > 19) {
                previousState = currentState;
                currentState = State.DOWN;
                setFlip(false, false);

            }
        }

        if (currentState == State.DOWN) {
            setPosition(getX(), getY() - Math.round(speed * delta + 0.5));
            //   downMovCounter++;
            // if(downMovCounter>DOWN_MOVEMENT){
            downMovCounter = 0;
            currentState = previousState == State.LEFT ? State.RIGHT : State.LEFT;
            // }

        }


        TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get(2);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();


        if (cur.getCell(Math.round(getX()), Math.round(getY())).getTile().getProperties().containsKey("mushroom")) {


            if (getCurrentState() == State.LEFT) {
                setFlip(true, false);

            } else if (getCurrentState() == State.RIGHT) {
                setFlip(false, false);
            }

            previousState = currentState;
            currentState = State.DOWN;


        }
    }


    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDownMovCounter() {
        return downMovCounter;
    }

    public void setDownMovCounter(float downMovCounter) {
        this.downMovCounter = downMovCounter;
    }

    public static float getDownMovement() {
        return DOWN_MOVEMENT;
    }

    public State getPreviousState() {
        return previousState;
    }

    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public TextureRegion getImage() {
        return image;
    }

    public void setImage(TextureRegion image) {
        this.image = image;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }


}
