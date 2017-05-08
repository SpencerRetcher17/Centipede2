package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.PooledLinkedList;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by sretcher on 4/29/2017.
 */

public class SpawnCentipede {

    LinkedList<CentipedeHead> centipedeList = new LinkedList<CentipedeHead>();
    TextureRegion[] head, segment;
    static float delay = 0;

    public SpawnCentipede(TextureRegion[] segment,TextureRegion[] head) {

        this.head = head;
        this.segment = segment;
    }

            public void fill()
            {
             //   centipedeList.add(0,new CentipedeHead(head, new Vector2(19, 24), new Vector2(1, 1),centipedeList));
            centipedeList.add(new CentipedeHead(segment, new Vector2(19, 24), new Vector2(1, 1),centipedeList));
            }

public void fillHead()
{



    centipedeList.add(new CentipedeHead(head, new Vector2(18, 24), new Vector2(1, 1),centipedeList));
}





              //  if (delay <= 0) {
                  //  delay += 0.5;
                    //batch.draw(centipedeList.get(i),centipedeList.get(i).getX(),centipedeList.get(i).getY(),centipedeList.get(i).getWidth(),centipedeList.get(i).getHeight());
             //   }

               // if (delay <= 0) {
                  //  delay += 0.5;





    ////}




    public LinkedList<CentipedeHead> getCentipedeList() {
        return centipedeList;
    }

    public void LinkedList(LinkedList<CentipedeHead> centipedeList) {
        this.centipedeList = centipedeList;
    }
}


