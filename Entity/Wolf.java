package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


import Main.GamePanel;
import Main.KeyHandler;

public class Wolf extends Entity{
 
    KeyHandler keyH;

    public final int petScreenX;
    public final int petScreenY;

    public Wolf(GamePanel gp,KeyHandler keyH){
        super(gp);
        this.keyH = keyH;
        setdefaultValues();
        getPetImage();
        petScreenX = gp.screenWidth/2 ;
        petScreenY = gp.screenHeight/2 ;
    }

 

 public void getPetImage(){
        try{
            up1=ImageIO.read(getClass().getResourceAsStream("/TEXTURES/pet_up_1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("/TEXTURES/pet_up_2.png"));
            down1=ImageIO.read(getClass().getResourceAsStream("/TEXTURES/pet_down_1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("/TEXTURES/pet_down_2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/TEXTURES/pet_left_1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/TEXTURES/pet_left_2.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/TEXTURES/pet_right_1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/TEXTURES/pet_right_2.png"));
        }
        catch(IOException e ){
            e.printStackTrace();

        }
    }
   public void setdefaultValues(){
        this.worldX=gp.tileSize*13;
        this.worldY=gp.tileSize*11;
        this.speed=4;
        direction="up";

    }
    public void update(){
        if(keyH.upPressed==true || keyH.downPressed==true || keyH.leftPressed==true || keyH.rightPressed==true){
            if(keyH.upPressed==true){
            direction="up";
            worldY-=speed;
        }
        else if(keyH.downPressed==true){
            direction="down";
            worldY+=speed;
        }
        else if(keyH.leftPressed==true){
            direction="left";
            worldX-=speed;
        }
        else if(keyH.rightPressed==true){
            direction="right";
            worldX+=speed;
        }
        numMoves++;
        if(numMoves>6){
             
            if(moveType==1){
                moveType=2;
            }else if(moveType==2){
                moveType=1;
            }
            numMoves=0;

        }

        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image =null;
        
        switch(direction){

           case "up": 
           if(moveType==1){
            image=up1;
           }else{
            image=up2;
           }
           break;

           case "down":if(moveType==1){
            image=down1;
           }else{
            image=down2;
           }
           break;

           case "left": if(moveType==1){
            image=left1;
           }else{
            image=left2;
           }
           break;
           
           case "right": if(moveType==1){
            image=right1;
           }else{
            image=right2;
           }
           break;
        }
        g2.drawImage(image,petScreenX, petScreenY, gp.tileSize, gp.tileSize,null);
    }
}
