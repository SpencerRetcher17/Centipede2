package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by sretcher on 4/23/2017.
 */

public class CentipedeBody extends Sprite {

    private static final long serialVersionUID = 1L;
    Vector2 position,size;
    String textureLoc;

    private static final int col=4;
    private static final int row=4;

    Animation<TextureRegion> animation;

    Texture playerTexture;
    TextureRegion[] frames;
    TextureRegion currentFrame;
    float stateTime;
    Rectangle bounds;
    Boolean up=false,down=false,right=false,left=false;
    Texture tilesImage = new Texture(Gdx.files.internal("tile.png"));
    TextureRegion[][] splitTiles = TextureRegion.split(tilesImage, 8, 8);
    Sprite body;
    TextureRegion image;
    public CentipedeBody(TextureRegion image,Vector2 position,Vector2 size)  {

      super(new TextureRegion(image));

        this.position = position;
        this.size=size;

        bounds=new Rectangle(position.x,position.y,8,8);
        left=true;

    }




    public void update() {

        bounds.set(getPosition().x,getPosition().y,8,8);


        if (left==true) {
           position.x-=(.5f);
            //body.flip(true,false);
            up=false;
            down=false;
            right=false;
            left=true;
      //      currentFrame= animation.getKeyFrame(12+stateTime);
        }
        if (right==true) {
            position.x+=.5f;
            //body.flip(true,false);
            left=false;
            right=true;
            down=false;
            up=false;
    //        currentFrame= animation.getKeyFrame(4+stateTime);

        }
        if (down==true) {

            position.y-=(.5f);
            right=false;
            left=false;
            down=true;
            up=false;
  //          currentFrame= animation.getKeyFrame(8+stateTime);


            if(position.x<Gdx.graphics.getWidth()-size.x)
            {
                left=false;
               position.y-=(.5f);
                right=true;
            }

        }
        /*
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= 3f;
            down=true;
            up=false;
            right=false;
            left=false;
            currentFrame= animation.getKeyFrame(0+stateTime);


        }

*/
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

   /* public void setAnimation(Animation animation) {
        this.animation = animation;
    }*/

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

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    public Boolean getUp() {
        return up;
    }

    public void setUp(Boolean up) {
        this.up = up;
    }

    public Boolean getDown() {
        return down;
    }

    public void setDown(Boolean down) {
        this.down = down;
    }

    public Boolean getRight() {
        return right;
    }

    public void setRight(Boolean right) {
        this.right = right;
    }

    public Boolean getLeft() {
        return left;
    }

    public void setLeft(Boolean left) {
        this.left = left;
    }

    public Texture getTilesImage() {
        return tilesImage;
    }

    public void setTilesImage(Texture tilesImage) {
        this.tilesImage = tilesImage;
    }

    public TextureRegion[][] getSplitTiles() {
        return splitTiles;
    }

    public void setSplitTiles(TextureRegion[][] splitTiles) {
        this.splitTiles = splitTiles;
    }

    /*
    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    */

    public TextureRegion getImage() {
        return image;
    }

    public void setImage(TextureRegion image) {
        this.image = image;
    }

    public Sprite getBody() {
        return body;
    }

    public void setBody(Sprite body) {
        this.body = body;
    }
}
