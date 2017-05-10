package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by sretcher on 4/19/2017.
 */

public class Mushroom extends Sprite {

    Vector2 position, size;
    TextureRegion mushroomImage;
    Rectangle bounds;
    ArrayList<TextureRegion> levelOne, levelTwo, levelThree;
    Texture tilesImage = new Texture(Gdx.files.internal("tile.png"));
    TextureRegion[][] splitTiles = TextureRegion.split(tilesImage, 8, 8);
    int level;
    TextureRegion image;
    Ship player;
     int heath;
    ArrayList<Mushroom>mushrooms=new ArrayList<Mushroom>();
TiledMap map;
    public Mushroom(TiledMap map)
    {
this.map=map;
    }

    public Mushroom(TextureRegion image,Ship player,Vector2 position, Vector2 size,int heath) {
        super(new TextureRegion(image));
        this.image=image;
        setPosition(position.x,position.y);
        this.position=position;
        this.size = size;
        this.player=player;
        this.heath=heath;
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

    public void spawnMushrooms(TiledMap map)
    {


        for (int i = 0; i < 20; i++) {
            for (int j = 2; j < 20; j++) {
                TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get(2);
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                int R = (int) ((Math.random() * (9 - 0) + 0));
                if (cur.getCell(i, j) != null) {
                    if (R == 0) {
                        cell = cur.getCell(i, j);
                        cur.setCell(i * 8, j * 8, cell);

                        Mushroom cur4=new Mushroom(splitTiles[0][8],player,new Vector2(i,j),new Vector2(1,1),3);

                        cell.setTile(new StaticTiledMapTile(splitTiles[0][8]));
                        cell.getTile().getProperties().put("mushroom", true);
                        //   bounds.add(new Rectangle(i, j, 1, 1));
                        mushrooms.add(cur4);
                    }

                } else {

                    /*
                    if (R == 0) {
                        cell = cur.getCell(i, j);
                        cur.setCell(i * 8, j * 8, cell);
                        cell.setTile(new StaticTiledMapTile(splitTiles[0][8]));

                        Gdx.app.log(i + "", j + " " + cell.getTile().getId());
                        // mushrooms.add(new Mushroom(new Vector2(i * 8, j * 8), new Vector2(8, 8)));
*/
                    }
                }

            }
        }


/*
    public void damage(int amount,TiledMap map,Vector2 position10)
    {
        Gdx.app.log("DAMGE","DAME");

        setHeath(getHeath()-1);

        if(heath==2)
        {

            TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get(2);
            TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();

            cell=cur.getCell(Math.round(position10.x),Math.round(position10.y));
            cur.setCell(Math.round(position10.x) * 8, Math.round(position10.y) * 8, cell);

            cell.setTile(new StaticTiledMapTile(splitTiles[0][9]));
        }

       else if(heath==1)
        {

            TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get(2);
            TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();

            cell=cur.getCell(Math.round(position10.x),Math.round(position10.y));
            cur.setCell(Math.round(position10.x) * 8, Math.round(position10.y) * 8, cell);

            cell.setTile(new StaticTiledMapTile(splitTiles[0][10]));
        }

       else if(heath==0)
        {

            TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get(2);
            TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();

            cell=cur.getCell(Math.round(position10.x),Math.round(position10.y));
            cur.setCell(Math.round(position10.x) * 8, Math.round(position10.y) * 8, cell);

            cell.setTile(new StaticTiledMapTile(splitTiles[0][11]));
            */





    public void update(ArrayList<Mushroom>mushrooms) {




       /* TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get(2);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();

        for(int i=0;i<player.getBulletList().size();i++) {
            if (cur.getCell(Math.round(player.getBulletList().get(i).getX()), Math.round(player.getBulletList().get(i).getY())).getTile().getProperties().containsKey("mushroom") == true) {



                cell=cur.getCell(Math.round(player.getBulletList().get(i).getX()), Math.round(player.getBulletList().get(i).getY()));
                //cell.getTile().getProperties().put("mushroom", true);
                cur.setCell(Math.round(getPosition().x),Math.round(getPosition().y), cell);
                //player.getBulletList().remove(i);

                switch (heath) {
                    case 3:
                        cell.setTile(new StaticTiledMapTile(splitTiles[0][9]));
                        setHeath(getHeath()-1);
                        //player.getBulletList().remove(i);
                        break;
                    case 2:
                        cell.setTile(new StaticTiledMapTile(splitTiles[0][10]));
                        setHeath(getHeath()-1);

                        //player.getBulletList().remove(i);
                        break;
                    case 1:
                        cell.setTile(new StaticTiledMapTile(splitTiles[0][11]));
                        setHeath(getHeath()-1);

                        //player.getBulletList().remove(i);
                        break;
                    default:
                        cell.setTile(new StaticTiledMapTile(splitTiles[2][11]));
                        cell.getTile().getProperties().remove("mushroom");



                }


            }
        }
        */
    }

   /* public ArrayList<Mushroom> getMushrooms() {
        return mushrooms;
    }

    public void setMushrooms(ArrayList<Mushroom> mushrooms) {
        this.mushrooms = mushrooms;
    }
*/





    public ArrayList<Mushroom> getMushrooms() {
        return mushrooms;
    }

    public void setMushrooms(ArrayList<Mushroom> mushrooms) {
        this.mushrooms = mushrooms;
    }

    public int getHeath() {
        return heath;
    }

    public void setHeath(int heath) {
        this.heath = heath;
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

    public TextureRegion getImage() {
        return image;
    }

    public void setImage(TextureRegion image) {
        this.image = image;
    }
}
