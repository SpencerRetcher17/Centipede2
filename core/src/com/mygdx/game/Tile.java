package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by sretcher on 4/21/2017.
 */

public class Tile {

    float x,y,width,height;
    Texture texture;



    public Tile(Texture texture,float x, float y, float width, float height)
    {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.texture=texture;
    }

public void render(SpriteBatch batch)
{

    batch.draw(texture,x,y,width,height);

}

}
