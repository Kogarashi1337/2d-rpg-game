package Entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX,worldY;
    public int speed;

    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2,down1b,down2b,left1b,left2b,right1b,right2b,up1b,up2b, xu1,xu2,xd1,xd2,xl1,xl2,xr1,xr2;
    public String direction;

    public int numMoves=0;//sprite Counter
    public int moveType=1;//sprite Number
 
    public Rectangle solidArea;
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn=false;
} 
