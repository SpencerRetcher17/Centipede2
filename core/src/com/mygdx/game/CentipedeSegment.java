package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by sretcher on 4/25/2017.
 */
public class CentipedeSegment extends Sprite {

    enum State{
        LEFT,RIGHT,DOWN
    }

    State currentState,previousState ;

    public static final float DOWN_MOVEMENT=7f;
    public float downMovCounter;
    public float speed;
    public float stateTime;
    TextureRegion image;
    Vector2 position,size;
    Animation<TextureRegion> animation,animation2;
    TextureRegion currentFrame;
    TextureRegion[]frames,frames2;

    Texture tilesImage = new Texture(Gdx.files.internal("tile.png"));
    TextureRegion[][] splitTiles = TextureRegion.split(tilesImage, 8, 8);
    ArrayList<TextureRegion>textures;
    ArrayList<CentipedeSegment>list;
    float delay=0;
CentipedeHead head;
    public CentipedeSegment(TextureRegion[]frames, Vector2 position, Vector2 size,ArrayList<CentipedeSegment>list,CentipedeHead head) {
        super(new TextureRegion(frames[0]));
        this.frames=frames;
        this.list=list;
        this.head=head;
        setPosition(position.x,position.y);
        setSize(size.x,size.y);
        currentState=State.LEFT;
        previousState=State.LEFT;
        speed=6f;






        animation = new Animation(1, frames);
        stateTime=0f;
        currentFrame= animation.getKeyFrame(0);

    }

    public void update(TiledMap map) {


        float delta = Gdx.graphics.getDeltaTime();
        stateTime += Gdx.graphics.getDeltaTime() * 15;
        //      Gdx.app.log(" "+getX()+" "+getY()+" "+delta,"hello");


        if (stateTime > 7) {
            stateTime = 0;
        }


        if (currentState == State.LEFT) {
             setPosition(getX()-speed*delta,getY());



            currentFrame = animation.getKeyFrame(stateTime);

            if (getX() < 0) {
                previousState = currentState;
                currentState = State.DOWN;
            }
        }

        if (currentState == State.RIGHT) {




            currentFrame = animation.getKeyFrame(stateTime + 8);


            setPosition(getX() + speed * delta, getY());




            if (getX() > 19) {
                previousState = currentState;
                currentState = State.DOWN;
            }
        }

        if (currentState == State.DOWN) {
            setPosition(getX(), getY() - 1);
            // if(downMovCounter>DOWN_MOVEMENT){
            //downMovCounter=0;

            //  delay+=Gdx.graphics.getDeltaTime();

            //  if(delay>=0.1) {

            currentState = previousState == State.LEFT ? State.RIGHT : State.LEFT;
            delay = 0;
            //}
        }


        TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get(2);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();


        if (cur.getCell(Math.round(getX()), Math.round(getY())) != null) {

            if (cur.getCell(Math.round(getX()), Math.round(getY())).getTile().getProperties().containsKey("mushroom")) {

                if (cur.getCell(Math.round(getX()), Math.round(getY()-1)).getTile().getProperties().containsKey("mushroom"))
                {
                    previousState = currentState;
                    currentState = State.DOWN;
                }
                else
                {
                    previousState = currentState;
                    currentState = State.DOWN;
                }
/*
       if (getCurrentState() == State.LEFT) {
           for(int i=0;i<frames.length;i++)
           {
                frames[i].flip(true,false);
            }
       } else if (getCurrentState() == State.RIGHT) {
            for (int i = 0; i < frames.length; i++) {
               frames[i].flip(false, false);
          }
        }
*/


            }
        }
    }


    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;
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