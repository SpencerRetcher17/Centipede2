package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by sretcher on 4/25/2017.
 */
public class Ship extends Sprite implements InputProcessor {

    enum State{
        FIRE,NOTFIRING
    }

    State currentState,previousState ;

    public float speed;
    public float stateTime;
    TextureRegion image;
    Vector2 position,size;
    Animation<TextureRegion> animation;
    TextureRegion currentFrame;
    TextureRegion[]frames;
    Texture tilesImage = new Texture(Gdx.files.internal("tile.png"));
    TextureRegion[][] splitTiles = TextureRegion.split(tilesImage, 8, 8);
    SpriteBatch batch;
    Bullet cur;
    float fireDelay;
    ArrayList<Bullet> bulletList;
    ArrayList<Mushroom> mushroomsList;
    LinkedList<CentipedeHead>list;
    Ui userInterFace;

    Sound shoot=Gdx.audio.newSound(Gdx.files.internal("shoot.wav"));

    public Ship(SpriteBatch batch,ArrayList<Mushroom>mushroomsList,LinkedList<CentipedeHead>list,TextureRegion image, Vector2 position, Vector2 size,Ui userInterface) {
        super(new TextureRegion(image));
        setPosition(position.x,position.y);
        setSize(size.x,size.y);
        currentState=State.NOTFIRING;
        previousState=State.NOTFIRING;
        speed=8f;
        this.batch=batch;
        fireDelay=0;
this.mushroomsList=mushroomsList;
        bulletList=new ArrayList<Bullet>();
this.list=list;
        this.userInterFace=userInterface;
    }

    public void update(TiledMap map,Ship player) {

        Gdx.input.setInputProcessor(this);


        if (Gdx.input.isTouched())
        {

            setCurrentState(State.FIRE);

        }

        

        float delta = Gdx.graphics.getDeltaTime();

            if(currentState==State.FIRE)
            {



                        fireDelay -= Gdx.graphics.getDeltaTime();
                        if (fireDelay <= 0) {
                            bulletList.add(new Bullet(splitTiles[10][1],list,player,new Vector2(1,1),userInterFace));
                            shoot.play();
                            fireDelay += 0.2;
                        }
                        else
                        {
                            currentState=State.NOTFIRING;
                        }
                    }

            }




    public ArrayList<Mushroom> getMushroomsList() {
        return mushroomsList;
    }

    public void setMushroomsList(ArrayList<Mushroom> mushroomsList) {
        this.mushroomsList = mushroomsList;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {




      return false;
    }



    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        OrthographicCamera  cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.setToOrtho(false, 20, 25);

        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX()-11, Gdx.input.getY()-70, 0);
        cam.unproject(touchPos);
        setPosition(touchPos.x,touchPos.y);

        if(touchPos.x<0)
        {
            setPosition(0,touchPos.y);

        }
        else if(touchPos.x>19)
        {
            setPosition(19,touchPos.y);
        }
        else if(touchPos.y<0)
        {
            setPosition(touchPos.x,0);
        }
        else if(touchPos.y>3)
        {
            setPosition(touchPos.x,3);
        }

       return true;
        }



    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public ArrayList<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(ArrayList<Bullet> bulletList) {
        this.bulletList = bulletList;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
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
