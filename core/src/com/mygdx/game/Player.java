package com.mygdx.game;

/**
 * Created by sretcher on 4/19/2017.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;


/**
 * Created by sretcher on 4/18/2017.
 */


public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    Vector2 position;
    String textureLoc;

    private static final int col=4;
    private static final int row=4;

    Animation<TextureRegion>animation;

    Texture playerTexture;
    TextureRegion[] frames;
    TextureRegion currentFrame;
    float stateTime;
    Rectangle bounds;
    Boolean up,down,right,left=false;

    public Player(Vector2 position, String textureLoc) {
        this.position = position;
        // this.texture = new Texture(Gdx.files.internal(textureLoc));
        playerTexture=new Texture(Gdx.files.internal("Red.png"));
        TextureRegion[][] tmp=TextureRegion.split(playerTexture,playerTexture.getWidth()/col,playerTexture.getHeight()/row);
        frames= new TextureRegion[col*row];

        int index=0;

        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                frames[index++]=tmp[i][j];
            }
        }

        animation = new Animation(1, frames);
        stateTime=0f;
        currentFrame= animation.getKeyFrame(0);
        bounds=new Rectangle(position.x,position.y,currentFrame.getRegionWidth(),currentFrame.getRegionHeight());

    }



    public void update() {

        bounds.set(position.x,position.y,currentFrame.getRegionWidth(),currentFrame.getRegionHeight());



        stateTime+=Gdx.graphics.getDeltaTime()*4;


        if(stateTime>4) {
            stateTime = 0;
        }


        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += 3f;
            up=true;
            down=false;
            right=false;
            left=false;
            currentFrame= animation.getKeyFrame(12+stateTime);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= 3f;
            left=true;
            right=false;
            down=false;
            up=false;
            currentFrame= animation.getKeyFrame(4+stateTime);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += 3f;
            right=true;
            left=false;
            down=false;
            up=false;
            currentFrame= animation.getKeyFrame(8+stateTime);


        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= 3f;
            down=true;
            up=false;
            right=false;
            left=false;
            currentFrame= animation.getKeyFrame(0+stateTime);


        }


    }

    public void reAdjust()
    {

        if(up==true)
        {
            position.y-=4f;
        }
        if(down==true)
        {
            position.y+=4f;
        }
        if(right==true)
        {
            position.x-=4f;
        }
        if(left==true)
        {
            position.x+=4f;
        }




    }


    public static void savePlayer(Player playerPosition) throws IOException {

        FileHandle file = Gdx.files.local("player.dat");
        OutputStream out = null;

        try {

            file.writeBytes(serialize(playerPosition.getPosition()), false);

        } catch (Exception ex) {
            //ex.tostring
        } finally {

            if (out != null) {
                try {
                    out.close();
                } catch (Exception ex) {

                }
            }
        }

    }


    public static Vector2 readPlayer() throws IOException, ClassNotFoundException {
        Vector2 playerPosition=null;

        FileHandle file = Gdx.files.local("player.dat");
        playerPosition = (Vector2)deserialize(file.readBytes());

        return playerPosition;
    }


    //Turns Player into Byte Array to store it

    @SuppressWarnings("unused")
    private static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }

    //Allows us to get the Player which is a Byte Array

    private static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

  public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;
    }

    public TextureRegion[] getFrames() {
        return frames;
    }

    public void setFrames(TextureRegion[] frames) {
        this.frames = frames;
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayerTexture(Texture playerTexture) {
        this.playerTexture = playerTexture;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public String getTextureLoc() {
        return textureLoc;
    }

    public void setTextureLoc(String textureLoc) {
        this.textureLoc = textureLoc;
    }

    public static int getCol() {
        return col;
    }

    public static int getRow() {
        return row;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

/*
    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    */
}