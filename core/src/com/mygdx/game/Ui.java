package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

/**
 * Created by sretcher on 5/8/2017.
 */

public class Ui {

    Stage stage;
    Label label, label2;
    public static Label.LabelStyle style;
    BitmapFont font;
    Image image, image2;

    Texture tilesImage = new Texture(Gdx.files.internal("tile.png"));
    TextureRegion[][] splitTiles = TextureRegion.split(tilesImage, 8, 8);

    int score;
    float highScore=0;
    String scoreString;
    int scoreLength;

   public static Preferences data;

    public Ui(){


        data= Gdx.app.getPreferences("Centipede");


        stage = new Stage();
        font = new BitmapFont(Gdx.files.internal("font.fnt"), false);
        style = new Label.LabelStyle(font, Color.RED);
        score=0;
        scoreString="00";

        label2 = new Label(String.valueOf(getHighScore()), style);
        label2.setPosition((Gdx.graphics.getWidth() / 2), Gdx.graphics.getHeight() - label2.getHeight() + 5);
        label2.setSize(24, 24);
        label2.setAlignment(Align.center);
        stage.addActor(label2);
        image = new Image(splitTiles[10][0]);
        image2 = new Image(splitTiles[10][0]);

        image.setPosition((Gdx.graphics.getWidth()) - (Gdx.graphics.getWidth())+5, Gdx.graphics.getHeight() - (3*image.getHeight()));
        image.setSize(24, 24);
        image.setAlign(Align.topLeft);
        stage.addActor(image);
        image2.setPosition(image.getX()+image.getWidth() , image.getY());
        image2.setSize(24, 24);
        image2.setAlign(Align.topLeft);

        stage.addActor(image2);
        label = new Label(scoreString, style);
        label.setPosition(image2.getX()+image2.getWidth(), image2.getY());
        label.setAlignment(Align.topLeft);

        label.setSize(24, 24);
        stage.addActor(label);




    }


    public void action()
    {
        stage.act();
    }

    public void draw()
    {
        stage.draw();
    }



    public static void setHighScore(int val) {
        data.putInteger("highScore", val);
        data.flush();
    }

    public static int getHighScore() {
        return data.getInteger("highScore");
    }


    public void updateScore()
    {

        //image.setPosition(label.getX()+label.getWidth() + image.getWidth(), label.getY());
        //image2.setPosition(image.getX()+image2.getWidth(), image.getY());


        if (!data.contains("highScore")) {
            data.putInteger("highScore", 0);
        }


        if(score>getHighScore())
        {
            setHighScore(score);
            label2.setText(String.valueOf(getScore()));
        }


        scoreLength=String.valueOf(score).length();

        if(scoreLength==3)
        {
       //   String scoreString1 = String.format("%6d", score);
       //     label.setText(scoreString1);


        }
        else if(scoreLength==4)
        {
      //    String scoreString1 = String.format("%6d", score);
       //     label.setText(scoreString1);


        }
        else {


       // String scoreString1 = String.format("%6d", score);
       //     label.setText(scoreString1);

        }
            label.setText(String.valueOf(getScore()));

    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

  //  public float getHighScore() {
    //    return highScore;
    //}

    public void setHighScore(float highScore) {
        this.highScore = highScore;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Label getLabel2() {
        return label2;
    }

    public void setLabel2(Label label2) {
        this.label2 = label2;
    }

    public static Label.LabelStyle getStyle() {
        return style;
    }

    public static void setStyle(Label.LabelStyle style) {
        Ui.style = style;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage2() {
        return image2;
    }

    public void setImage2(Image image2) {
        this.image2 = image2;
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
}
