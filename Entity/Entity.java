package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;

public class Entity {
    public int worldX,worldY;
    public int speed;
    public int actionLockCounter;
    public int dialogueIndex=0;
    public boolean movementOn=false;
    public String name;

    //moved from superobj
    public BufferedImage image, image2, image3;

    public boolean collision = false;
    
    public BufferedImage up1,up2,up3,up4,up5,up6,up7,up8,down1,down2,down3,down4,down5,down6,down7,down8,left1,left2,left3,left4,left5,left6,left7,left8,right1,right2,right3,right4,right5,right6,right7,right8,down1b,down2b,left1b,left2b,right1b,right2b,up1b,up2b, xu1,xu2,xd1,xd2,xl1,xl2,xr1,xr2,obj1;
    public String direction = "down";
    public String entityDNA ="";
    String dialogues[]=new String[20];

    public int numMoves=0;
    public int numMoves8=0;//sprite Counter
    public int moveType=1;//sprite Number
    public int animTime=120;//rate of change in direction
 
    public Rectangle solidArea=new Rectangle(0,0,48,48);
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn=true;
    public boolean movement8=false;
    //CHARACTER STATUS
    public int maxLife;
    public int life;


    GamePanel gp;


    //CONSTRUCTOR
    public Entity(GamePanel gp){
        this.gp = gp;
   
   
   
    }

    public void setAction(){


    }
    public void classHealth(String chooseClass){
        switch (chooseClass) {
            case "Thief":
                gp.player.maxLife=4;
                break;

                case "Mage":
                gp.player.maxLife=6;
                break;

                case "Warrior":
                gp.player.maxLife=10;
                break;
                
                case "Elve":
                gp.player.maxLife=8;
                
                break;
                default:
                break;
        }
        life=maxLife;
    }

    public void speak(){

        if(dialogues[dialogueIndex]==null){
            dialogueIndex=0;
        }
        gp.ui.currentDialogue=dialogues[dialogueIndex];
        dialogueIndex++;
        //direction when speaking
        switch(gp.player.direction){
            case "up":
            direction = "down";
            break;
            case "down":
            direction = "up";
            break;
            case "left":
            direction = "right";
            break;
            case "right":
            direction = "left";
            break;

        }
        //go to next line of dialogue

    }

    public void update(){

        setAction();
        
        collisionOn=false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);

        //if collision is false , player can move
      if(collisionOn==false){

            switch(direction){
                case"up":  worldY-=speed;break;
                case"down": worldY+=speed;break;
                case"left":worldX-=speed;break;
                case"right":worldX+=speed;break;
            }
        }


        numMoves++;
        numMoves8++;
        moveCount(numMoves);
        if(this.entityDNA.equals("monster")){
        moveCount8(numMoves8);
        }
        
    }

    public void moveCount(int numMoves){
        if(numMoves>10){
                
            if((moveType==1)){
                moveType=2;
            }else if(moveType==2){
                moveType=1;
            }   
           this.numMoves=0;

        }  
    }
    public void moveCount8(int numMoves8){
        if(numMoves8>10){
            moveType++;   
            if(moveType>8){
                moveType=1;
            }
               
           this.numMoves8=0;

        }  
    }

    public void draw(Graphics2D g2){

        BufferedImage image =null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        //   nMoves++;    
        if(worldX + gp.tileSize>gp.player.worldX - gp.player.screenX &&
           worldX - gp.tileSize<gp.player.worldX + gp.player.screenX &&
           worldY + gp.tileSize>gp.player.worldY - gp.player.screenY &&
           worldY - gp.tileSize<gp.player.worldY + gp.player.screenY){
            // SUPREME ONE
                switch(direction){
                         
                        case "up": 
                        if((moveType==1)&&(collisionOn==false)){
                        image=up1;
                        }else{
                        image=up2;
                        }
                        break;
            
                        case "down":if((moveType==1)&&(collisionOn==false)){
                        image=down1;
                        }else{
                        image=down2;
                        }
                        break;
            
                        case "left": if((moveType==1)&&(collisionOn==false)){
                        image=left1;
                        }else{
                        image=left2;
                        }
                        break;
                        
                        case "right": if((moveType==1)&&(collisionOn==false)){
                        image=right1;
                        }else{
                        image=right2;
                        }
                        break;
                    
                }

                if(this.entityDNA.equals("monster")){
                   if(collisionOn==false){
                     switch(moveType){
                        case 1:image=up1;this.speed=1;break;
                        case 2:image=up2;this.speed=2;break;
                        case 3:image=up3;this.speed=3;break;
                        case 4:image=up4;this.speed=2;break;
                        case 5:image=up5;this.speed=3;break;
                        case 6:image=up6;this.speed=2;break;
                        case 7:image=up7;this.speed=1;break;
                        case 8:image=up8;this.speed=0;break;
                    }
                   }
                    else if(collisionOn){
                        switch(moveType){
                        case 1:image=up1;break;
                        case 2:image=up2;break;
                        case 3:image=up1;break;
                        case 4:image=up2;break;
                        case 5:image=up1;break;
                        case 6:image=up2;break;
                        case 7:image=up1;break;
                        case 8:image=up2;break;
                        }
                    }
                }
                    //END SLIME ONE

                    // case "up": 
                    // if((moveType==1)&&(collisionOn==false)){
                    // image=up1;
                    // }
                    // else if((collisionOn==true)){
                    //     image=down1;
                    // }
                    // else{
                    // image=up2;
                    // }
                    // break;
        
                    // case "down":if((moveType==1)&&(collisionOn==false)){
                    // image=down1;
                    // }else if((collisionOn==true)){
                    // image=down1;
                    // }
                    // else{
                    // image=down2;
                    // }
                    // break;
        
                    // case "left": if((moveType==1)&&(collisionOn==false)){
                    // image=left1;
                    // }
                    // else if((collisionOn==true)){
                    //     image=down1;
                    // }
                    // else{
                    // image=left2;
                    // }
                    // break;
                    
                    // case "right": if((moveType==1)&&(collisionOn==false)){
                    // image=right1;
                    // }
                    // else if((collisionOn==true)){
                    //     image=down1;
                    //     }
                    // else{
                    // image=right2;
                    // }
                    // break;
                    // default:
                    
                    // image=down1;  

        }         
                  
            
            
        g2.drawImage(image, screenX, screenY, gp.tileSize,gp.tileSize,null);

        // g2.drawImage(obj1, screenX, screenY,gp.tileSize,gp.tileSize,null);
    }
    

    public BufferedImage setup(String imagePath){
        UtilityTool uTool=new UtilityTool();
        BufferedImage image=null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }


    

} 
