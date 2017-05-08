package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by sretcher on 5/6/2017.
 */




    /**
     * Created by sretcher on 4/21/2017.
     */

    public class levelTwo implements Screen {


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
        Texture tilesImage2 = new Texture(Gdx.files.internal("tile.png"));
        TextureRegion[][] splitTiles2 = TextureRegion.split(tilesImage2, 8, 8);
        CentipedeHead ex2, ex4, ex5, ex6, ex7;
        SpawnCentipede ex3;
        ArrayList<CentipedeHead> hello;
        public static int level = 0;
        Ship pilot;

        Stage stage;
        Label label, label2;
        public static Label.LabelStyle style;
        BitmapFont font;
        Image image, image2;

        // Bullet ex7, cur;
        double fireDelay=0;
        ArrayList<Bullet> bulletList;
        com.mygdx.game.PlayScreen.State presentState;
        Mushroom cur2;
        TextureRegion[]frames,frames1;

        TiledMapTileLayer cur;
        TiledMapTileLayer.Cell cell;
        SpawnCentipede centipede;
        float delay=0;
        boolean isSpawned;

        public levelTwo(Game game) {
            this.game = game;
        }

        enum State {
            FIRE, NOTFIRING
        }

        @Override
        public void show() {
            bulletList = new ArrayList<Bullet>();
            stage = new Stage();
            font = new BitmapFont(Gdx.files.internal("font.fnt"), false);
            style = new Label.LabelStyle(font, Color.RED);
            label = new Label("00", style);
            label.setPosition((Gdx.graphics.getWidth() / 8) - (label.getWidth()), Gdx.graphics.getHeight() - label.getHeight() + 5);
            label.setSize(24, 24);
            label2 = new Label("00", style);
            label2.setPosition((Gdx.graphics.getWidth() / 2) - (label2.getWidth() / 2), Gdx.graphics.getHeight() - label2.getHeight() + 5);
            label2.setSize(24, 24);
            stage.addActor(label);
            stage.addActor(label2);
            image = new Image(splitTiles[10][0]);
            image2 = new Image(splitTiles[10][0]);

            image.setPosition(label.getX() + 40, label.getY());
            image.setSize(24, 24);
            stage.addActor(image);
            image2.setPosition(image.getX() + 20, label.getY());
            image2.setSize(24, 24);
            stage.addActor(image2);
            batch = new SpriteBatch();
            cur2 = new Mushroom(map);
            hello = new ArrayList<CentipedeHead>();
            frames = new TextureRegion[16];
            frames1 = new TextureRegion[16];

            for (int i = 0; i < 4; i++) {
                frames[i] = splitTiles[2][i];
                frames[i + 4] = splitTiles[3][i];

            }

            for (int i = 8; i < 12; i++) {

                frames[i] = splitTiles2[2][i-8];
                frames[i].flip(true, false);
                frames[i + 4] = splitTiles2[3][i-8];
                frames[i + 4].flip(true, false);


            }
            for (int i = 0; i < 4; i++) {

                frames1[i] = splitTiles[0][i];
                frames1[i + 4] = splitTiles[1][i];
            }

            for (int i = 8; i < 12; i++) {

                frames1[i] = splitTiles2[0][i-8];
                frames1[i].flip(true, false);
                frames1[i + 4] = splitTiles2[1][i-8];
                frames1[i+4].flip(true,false);

            }



        /*

        ex2 = new CentipedeHead(frames, new Vector2(18, 24), new Vector2(1, 1));
        ex4 = new CentipedeHead(frames, new Vector2(17, 24), new Vector2(1, 1));
        ex5 = new CentipedeHead(frames, new Vector2(16, 24), new Vector2(1, 1));
        ex6 = new CentipedeHead(frames, new Vector2(15, 24), new Vector2(1, 1));
        ex7 = new CentipedeHead(frames1, new Vector2(14, 24), new Vector2(1, 1));


        hello.add(ex2);
        hello.add(ex4);
        hello.add(ex5);
        hello.add(ex6);
        hello.add(ex7);
*/

            centipede = new SpawnCentipede(frames, frames1);
            //   centipede.fill();
           pilot = new Ship(batch, cur2.getMushrooms(), centipede.getCentipedeList(), splitTiles[10][0], new Vector2(5, 0), new Vector2(1, 1));
            // ex7 = new Bullet(splitTiles[10][1], pilot, new Vector2(1, 1));
            // cur = new Bullet(splitTiles[10][1], new Vector2(pilot.getX(), pilot.getY()), new Vector2(1, 1));

            cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            cam.setToOrtho(false, 20, 25);
            // ex3=new SpawnCentipede(SpawnCentipede.State.LEVEL1,2,24,true);
            sr = new ShapeRenderer();
            //  mushroom = new Mushroom(new Vector2(100, 100), new Vector2(50, 100));
            //mushroom1 = new Mushroom(new Vector2(500, 100), new Vector2(50, 100));
            mushrooms = new ArrayList<Mushroom>();
            enemies = new ArrayList<Enemy>();
            //    mushrooms.add(mushroom);
            //  mushrooms.add(mushroom1);

            //image=new Sprite(splitTiles[0][0]);

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


            //sound=Gdx.audio.newMusic(Gdx.files.internal("sound.mp3"));

            map = new TmxMapLoader().load("centipedeMap.tmx");
            renderer = new OrthogonalTiledMapRenderer(map, 1 / 8f);
            cur = (TiledMapTileLayer) map.getLayers().get(2);
            cell = new TiledMapTileLayer.Cell();
            Texture tilesImage = new Texture(Gdx.files.internal("tile.png"));
            TextureRegion[][] splitTiles = TextureRegion.split(tilesImage, 8, 8);
            // Texture tilesImage1 = new Texture(Gdx.files.internal("tile2.png"));
            TextureRegion[][] splitTiles1 = TextureRegion.split(tilesImage, 8, 8);
            bounds = new ArrayList<Rectangle>();
            // ex=new CentipedeBody(splitTiles[0][0],new Vector2(0,0),new Vector2(1,1));
            //   ex2 = new CentipedeHead(frames, new Vector2(18, 24), new Vector2(1, 1));
            //  ex4 = new CentipedeHead(frames, new Vector2(17, 24), new Vector2(1, 1));


            cur2.spawnMushrooms(map);




/*

        }
        Texture hello=frames1[5].getTexture();

*/


        }



        @Override
        public void render(float delta) {

            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            renderer.setView(cam);
            renderer.render();
            player.update();


            //  pilot.update(map, pilot);


            //ex.update();
            //cam.position.set(player.getPosition().x + (player.getCurrentFrame().getRegionWidth() / 2), player.getPosition().y + (player.getCurrentFrame().getRegionHeight() / 2), 0);
            batch.setProjectionMatrix(cam.combined);
            cam.update();



            delay+=Gdx.graphics.getDeltaTime();

            if(delay>=0.1){

                if(isSpawned==false) {


                    if(centipede.getCentipedeList().size()==12) {
                        isSpawned=true;
                    }
                    else {

                        centipede.fill();
                        delay = 0;

                    }
                }


            }

            batch.begin();
/*
            if(ex2.getBoundingRectangle().overlaps(bounds.get(i))||cur.getCell(Math.round(ex2.getX()),Math.round(ex2.getY())).getTile().getProperties().containsKey("mushroom"))
            {
                Gdx.app.log("Animation","Collisions");
                ex2.setPreviousState(ex2.getCurrentState());
                ex2.setCurrentState(CentipedeHead.State.DOWN);
                //sound.play();
                //music.isLooping();
                //player.reAdjust();

            }
   */


            //   for(int i=0;i<frames1.length;i++)
            //   {

            //  batch.draw(frames1[i],i,10,1,1);

            //  }


            for (int i = 0; i < centipede.getCentipedeList().size(); i++)
            {


                batch.draw(centipede.getCentipedeList().get(i).getCurrentFrame(),centipede.getCentipedeList().get(i).getX(),centipede.getCentipedeList().get(i).getY(),centipede.getCentipedeList().get(i).getWidth(),centipede.getCentipedeList().get(i).getHeight());
            }

//batch.draw(splitTiles[0][1],5,5,1,1);

            //   delay -= Gdx.graphics.getDeltaTime();
            // if (delay <= 0) {
            //   delay += 0.5;
            //batch.draw(centipede.getCentipedeList().get(i).getCurrentFrame(), centipede.getCentipedeList().get(i).getX(), centipede.getCentipedeList().get(i).getY(), centipede.getCentipedeList().get(i).getWidth(), centipede.getCentipedeList().get(i).getHeight());
            //}




            //   }

            // batch.draw(ex4, ex4.getX(), ex4.getY(), ex4.getWidth(), ex4.getHeight());
            // batch.draw(ex2, ex2.getX(), ex2.getY(), ex2.getWidth(), ex2.getHeight());

            //batch.draw(ex7,ex7.getX(),ex7.getY(),ex7.getWidth(),ex7.getHeight());
            batch.draw(pilot, pilot.getX(), pilot.getY(), pilot.getWidth(), pilot.getHeight());

            for (int i = 0; i < pilot.getBulletList().size(); i++) {
                batch.draw(pilot.getBulletList().get(i), pilot.getBulletList().get(i).getX(), pilot.getBulletList().get(i).getY(), pilot.getBulletList().get(i).getWidth(), pilot.getBulletList().get(i).getHeight());

            }




            batch.end();


            pilot.update(map, pilot);



            for (int x = 0; x < mushrooms.size(); x++) {
                mushrooms.get(x).update(mushrooms);
            }

            for (int i = 0; i < pilot.getBulletList().size(); i++) {
                pilot.getBulletList().get(i).update(map);

            }


            for (int i = 0; i < centipede.getCentipedeList().size(); i++) {


                centipede.getCentipedeList().get(i).update(map);
                // batch.draw(ex4, ex4.getX(), ex4.getY(), ex4.getWidth(), ex4.getHeight());
                // batch.draw(ex2, ex2.getX(), ex2.getY(), ex2.getWidth(), ex2.getHeight());
            }


/*
    //   ex5.update(map);
    // ex7.update(map);
*/


            stage.draw();

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

        }
    }






