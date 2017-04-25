package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by sretcher on 4/21/2017.
 */

public class PlayScreen implements Screen {


    SpriteBatch batch;
    Texture mario;
    Vector2 position;
    Player player;
    Mushroom mushroom, mushroom1;
    ShapeRenderer sr;
    ArrayList<Mushroom> mushrooms;
    Iterator<Mushroom> mushroomIterator;
    ArrayList<Enemy> enemies;
    Iterator<Enemy> enemyIterator;
    Game game;

    ArrayList<Tile> tiles;
    Iterator<Tile> tilesIterator;

    OrthographicCamera cam;

    Sound sound;
    Music music;

    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    ArrayList<Rectangle> bounds;

    Texture tilesImage = new Texture(Gdx.files.internal("tile.png"));
    TextureRegion[][] splitTiles = TextureRegion.split(tilesImage, 8, 8);

    CentipedeBody ex;
    Sprite image;

    public static int level=0;

    public PlayScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {

        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.setToOrtho(false, 20, 25);
        batch = new SpriteBatch();

        mario = new Texture(Gdx.files.internal("mario.png"));
        sr = new ShapeRenderer();
      //  mushroom = new Mushroom(new Vector2(100, 100), new Vector2(50, 100));
        //mushroom1 = new Mushroom(new Vector2(500, 100), new Vector2(50, 100));

        mushrooms= new ArrayList<Mushroom>();
        enemies = new ArrayList<Enemy>();
        mushrooms.add(mushroom);
        mushrooms.add(mushroom1);


        image=new Sprite(splitTiles[0][0]);

        if (Gdx.files.local("player.dat").exists()) {
            try {
                player = new Player(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2), "mario.png");
                player.setPosition(Player.readPlayer());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            player = new Player(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2), "mario.png");
            try {
                Player.savePlayer(player);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        enemies.add(new Enemy(new Vector2(50, 50), player));
        enemies.add(new Enemy(new Vector2(100, 100), player));

        tiles = new ArrayList<Tile>();

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                int R = (int) ((Math.random() * (5 - 0) + 0));
               /* if(R==0)
                {
                    tiles.add(new Tile(new Texture("mario.png"),i*50,j*50,50,50));

                }*/
                if (R == 1 || R == 2) {
                    tiles.add(new Tile(new Texture("Mush.png"), i * 50, j * 50, 50, 50));
                }
            }
        }

        //  sound=Gdx.audio.newSound(Gdx.files.internal("sound.mp3"));
        //sound=Gdx.audio.newMusic(Gdx.files.internal("sound.mp3"));

        map = new TmxMapLoader().load("centipedeMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1/8f);
       Texture tilesImage = new Texture(Gdx.files.internal("tile.png"));
        TextureRegion[][] splitTiles = TextureRegion.split(tilesImage, 8, 8);
        bounds = new ArrayList<Rectangle>();
        ex=new CentipedeBody(splitTiles[0][0],new Vector2(19,24),new Vector2(1,1));


        /*TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get(1);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();

        cell = cur.getCell(19, 19);
        cell.setTile(new StaticTiledMapTile(splitTiles[0][12]));
        Gdx.app.log(19 + "", 0 + " " + cell.getTile().getId());
        bounds.add(new Rectangle(19 * 64, 0 * 64, 64, 64));
*/
       for (int i = 0; i < 20; i++) {
            for (int j = 2; j < 23; j++) {
                TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get(2);
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                int R = (int) ((Math.random() * (11 - 0) + 0));
                if (cur.getCell(i, j)!= null) {
                   if(R==0) {
                       cell = cur.getCell(i, j);
                       cur.setCell(i * 8, j * 8, cell);
                       cell.setTile(new StaticTiledMapTile(splitTiles[0][8]));
                       bounds.add(new Rectangle(i * 8, j * 8, 8, 8));
                       mushrooms.add(new Mushroom(new Vector2(i*8,j*8),new Vector2(8,8)));
                   }

                }
                else
               {
                   if(R==0) {
                       cell = cur.getCell(i, j);
                       cur.setCell(i * 8, j * 8, cell);
                       cell.setTile(new StaticTiledMapTile(splitTiles[0][8]));

                       Gdx.app.log(i + "", j + " " + cell.getTile().getId());
                       bounds.add(new Rectangle(i* 19, j * 8, 8, 8));
                       mushrooms.add(new Mushroom(new Vector2(i*8,j*8),new Vector2(8,8)));

                   }
               }

     }
        }
/*
        TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get(0);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        TiledMapTileSet tileSet = map.getTileSets().getTileSet("terrainMinecraft.png");

        cell.setTile(tileSet.getTile(57));
            Gdx.app.log(0 + "", 0 + " " + cell.getTile().getId());
            bounds.add(new Rectangle(0 * 64, 0 * 64, 64, 64));
        cur.setCell(0, 0, cell); // 32 and 64 being x and y coordinates
*/
/*
       Texture player3=new Texture(Gdx.files.internal("terrainMinecraft.png"));
        TextureRegion[][] tmp=TextureRegion.split(player3,player3.getWidth()/16,player3.getHeight()/16);
        TextureRegion[] frames1= new TextureRegion[16*16];
/*

 /*
        TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get(1);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        TiledMapTileSet tileSet = map.getTileSets().getTileSet("terrainMinecraft.png");*/
        /*
       int index=0;
        for(int i=0;i<16;i++)
        {
            for(int j=0;j<16;j++)
            {
                frames1[index++]=tmp[i][j];
            }
        }
        Texture hello=frames1[5].getTexture();
        */
        /*
        cell.setTile((TiledMapTile)frames1[5]);
        Gdx.app.log(0 + "", 0 + " " + cell.getTile().getId());
        bounds.add(new Rectangle(0 * 64, 0 * 64, 64, 64));
        cur.setCell(0, 0, cell); // 32 and 64 being x and y coordinates

*/
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(cam);
        renderer.render();
        player.update();
        ex.update();
        //cam.position.set(player.getPosition().x + (player.getCurrentFrame().getRegionWidth() / 2), player.getPosition().y + (player.getCurrentFrame().getRegionHeight() / 2), 0);
        batch.setProjectionMatrix(cam.combined);
        cam.update();

/*
        Texture player3=new Texture(Gdx.files.internal("terrainMinecraft.png"));
        TextureRegion[][] tmp=TextureRegion.split(player3,player3.getWidth()/16,player3.getHeight()/16);
        TextureRegion[] frames1= new TextureRegion[16*16];
/*
        TiledMapTileLayer cur = (TiledMapTileLayer) map.getLayers().get(1);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        TiledMapTileSet tileSet = map.getTileSets().getTileSet("terrainMinecraft.png");*/
       /*
        int index=0;
        for(int i=0;i<16;i++)
        {
            for(int j=0;j<16;j++)
            {
                frames1[index++]=tmp[i][j];
            }
        }
        Texture hello=frames1[5].getTexture();

*/

        batch.begin();

        tilesIterator = tiles.iterator();
/*
        while(tilesIterator.hasNext())
        {
            Tile cur=tilesIterator.next();
            cur.render(batch);
        }
*/
        //batch.draw(player.getTexture(),player.getPosition().x,player.getPosition().y);
//        batch.draw(player.getCurrentFrame(), player.getPosition().x, player.getPosition().y);
  /*
        treeIterator=trees.iterator();
        while(treeIterator.hasNext())
        {
            Tree cur=treeIterator.next();
            cur.draw(batch);
            cur.update();


            if(player.getBounds().overlaps(cur.getBounds()))
            {
                Gdx.app.log("Animation","Collisions");
                //sound.play();
                //music.isLooping();
                player.reAdjust();
            }
        }
*/
        /*

        enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy cur = enemyIterator.next();
            cur.update();
            batch.draw(cur.getEnemyTexture(), cur.getPosition().x, cur.getPosition().y, 25, 25);
            if (player.getBounds().overlaps(cur.getBounds())) {
                Gdx.app.log("Animation", "Collisions");
            }
        }

        player.update();


        for (int i = 0; i < bounds.size(); i++) {
            if (bounds.get(i).overlaps(player.getBounds())) {
                Gdx.app.log("Animation", "Collisions");
                player.reAdjust();
            }
        }
*/

        batch.draw(image,2,2,1,1);
        batch.draw(ex,ex.getPosition().x,ex.getPosition().y,ex.getSize().x,ex.getSize().y);

        batch.end();

        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(com.badlogic.gdx.graphics.Color.BLUE);
        sr.rect(player.getPosition().x, player.getPosition().y, player.getCurrentFrame().getRegionWidth(), player.getCurrentFrame().getRegionHeight());
        player.update();
        sr.setColor(com.badlogic.gdx.graphics.Color.GREEN);

        sr.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        try {
            Player.savePlayer(player);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
