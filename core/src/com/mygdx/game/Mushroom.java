package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by sretcher on 4/19/2017.
 */

public class Mushroom {

    Vector2 position, size;
    TextureRegion mushroomImage;
    Rectangle bounds;
    ArrayList<TextureRegion> levelOne, levelTwo, levelThree;
    Texture tilesImage = new Texture(Gdx.files.internal("tile.png"));
    TextureRegion[][] splitTiles = TextureRegion.split(tilesImage, 8, 8);
    int level;


    public Mushroom()
    {

    }

    public Mushroom(Vector2 position, Vector2 size) {

        this.position = position;
        this.size = size;
        this.level=level;

   //     bounds = new Rectangle(position.x, position.y, size.x, size.y);
     //   boundaries.add(bounds);

       /*
        switch (level) {
            case 1:
                mushroomImage = splitTiles[0][8];
                break;
            case 2:
                mushroomImage = splitTiles[0][8];
                break;
            case 3:
                mushroomImage = splitTiles[0][8];
                break;

        }
*/

    }

    public void update() {
        bounds.set(position.x, position.y, size.x, size.y);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(mushroomImage, position.x, position.y, size.x, size.y);
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

    public TextureRegion getMushroomImage() {
        return mushroomImage;
    }

    public void setTree(TextureRegion mushroomImage) {
        this.mushroomImage = mushroomImage;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }


    public TextureRegion[][] getSplitTiles() {
        return splitTiles;
    }

    public void setSplitTiles(TextureRegion[][] splitTiles) {
        this.splitTiles = splitTiles;
    }
}
