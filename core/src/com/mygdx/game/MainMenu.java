package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by sretcher on 4/21/2017.
 */

public class MainMenu implements Screen {

    Stage stage;
    Label label;
    public static Label.LabelStyle style;
    BitmapFont font;

    TextureAtlas buttonAtlas;
    public static TextButton.TextButtonStyle buttonStyle;
    TextButton button, tableButton;
    Skin skin;



    SpriteBatch batch;
    Game game;

public MainMenu(Game game)
{
    this.game=game;
}


    @Override
    public void show() {
        stage=new Stage();
        font=new BitmapFont(Gdx.files.internal("font.fnt"),false);
        style=new Label.LabelStyle(font, com.badlogic.gdx.graphics.Color.WHITE);
        label= new Label("Insert Text Here",style);
        label.setPosition((Gdx.graphics.getWidth()/2)-(label.getWidth()/2),Gdx.graphics.getHeight()-label.getHeight());
        stage.addActor(label);

        skin=new Skin();
        buttonAtlas=new TextureAtlas("buttons/button.pack");
        skin.addRegions(buttonAtlas);

        buttonStyle=new TextButton.TextButtonStyle();

        buttonStyle.up=skin.getDrawable("button");
        buttonStyle.over=skin.getDrawable("buttonpressed");
        buttonStyle.down=skin.getDrawable("buttonpressed");
        buttonStyle.font=font;


        button=new TextButton("Play",buttonStyle);
        button.setWidth(Gdx.graphics.getWidth()/7);
        button.setHeight(Gdx.graphics.getHeight()/7);
        button.setPosition(Gdx.graphics.getWidth()/2-button.getWidth()/2,Gdx.graphics.getHeight()/2);
        stage.addActor(button);

        Gdx.input.setInputProcessor(stage);

        button.addListener(new InputListener() {


            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Gdx.app.log("Clicked","Button");
                game.setScreen(new PlayScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        tableButton=new TextButton("Tables",buttonStyle);
        tableButton.setWidth(Gdx.graphics.getWidth()/7);
        tableButton.setHeight(Gdx.graphics.getHeight()/7);
        tableButton.setPosition(Gdx.graphics.getWidth()/2-tableButton.getWidth()/2,Gdx.graphics.getHeight()/2-(2*tableButton.getHeight()/2));
        stage.addActor(tableButton);

        Gdx.input.setInputProcessor(stage);

        tableButton.addListener(new InputListener() {


            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                Gdx.app.log("Clicked","Button");
                game.setScreen(new TablesScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });






        batch=new SpriteBatch();

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();

        batch.begin();
        stage.draw();
        batch.end();


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
