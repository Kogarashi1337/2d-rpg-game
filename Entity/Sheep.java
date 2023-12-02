package Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;
import Main.KeyHandler;

public class Sheep extends Entity {
      GamePanel gp ;
    KeyHandler keyH;

    public Sheep(GamePanel gp,KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setdefaultValues();
    }

    public void setdefaultValues(){
        this.x=550;
        this.y=150;
        this.speed=16;
    }

    public void update(){
        if(keyH.upPressed==true){
            y-=speed;
        }
        else if(keyH.downPressed==true){
            y+=speed;
        }
        else if(keyH.leftPressed==true){
            x-=speed;
        }
        else if(keyH.rightPressed==true){
            x+=speed;
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
