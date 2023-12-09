package Entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;
import object.OBJ_Boots;
import object.OBJ_GoldKey;

public class Player extends Entity {
 
    GamePanel gp ;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey=0;
    public int hasSKey=0;
    public int hasGoldKey=0;
    public int radio=0;


    public  boolean hasGKey=false;
    public  boolean hasPBoots=false;
    public String boots="unequipped";
    public boolean epboots=false;
    public boolean disText=false;

    public Player(GamePanel gp,KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
    
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x=10;//8
        solidArea.y=16;//16
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=30;
        solidArea.height=30;

        setdefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage(){
       
        up1=setup("boy_up1");
        up2=setup("boy_up2");
        down1=setup("boy_down1");
        down2=setup("boy_down2");
        left1=setup("boy_left1");
        left2=setup("boy_left2");
        right1=setup("boy_right1");
        right2=setup("boy_right2");

        //Boots
        down1b=setup("boy_bdown1");
        down2b=setup("boy_bdown2");
        left1b=setup("boy_bleft1");
        left2b=setup("boy_bleft2");
        right1b=setup("boy_bright1");
        right2b=setup("boy_bright2");
        up1b=setup("boy_bup1");
        up2b=setup("boy_bup2");

        //switch 
                    xd1=down1;
                    xd2=down2;
                    xl1=left1;
                    xl2=left2;
                    xr1=right1;
                    xr2=right2;
                    xu1=up1;
                    xu2=up2;
    }
    public void setdefaultValues(){
        this.worldX = gp.tileSize*39;
        this.worldY = gp.tileSize*29;
        this.speed=4;
        direction="down";
    }

    public BufferedImage setup(String imageName){
        UtilityTool uTool=new UtilityTool();
        BufferedImage image=null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/TEXTURES/"+imageName+".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update(){

    //    while(keyH.pauseGame){
    //         if(gp.gameState==gp.playState){
    //             gp.gameState=gp.pauseState;
    //             keyH.pauseGame=false;
    //             break;
    //         }
    //         if(gp.gameState==gp.pauseState){
    //             gp.gameState=gp.playState;
    //             keyH.pauseGame=false;
    //            break;
    //         }

    //     }

        if(keyH.radioPressedR){
            switch(radio){
                case 0: radio = 1;
                break;
                case 1:radio = 6;
                break;
                case 6:radio = 7;
                break;
                case 7:radio = 0;
                break;
            }
            keyH.radioPressedR=false;
            gp.changeMusic(radio);

        }else if(keyH.radioPressedL){
             switch(radio){
                case 0: radio = 7;
                break;
                case 7:radio = 6;
                break;
                case 6:radio = 1;
                break;
                case 1:radio = 0;
                break;
            }
            keyH.radioPressedL=false;
            gp.changeMusic(radio);

        }


        if(epboots==true){
            if(keyH.equipPressed){
            
                
                hasPBoots=true;
                speed=6;
            
               
                    down1=down1b;
                    down2=down2b;
                    left1=left1b;
                    left2=left2b;
                    right1=right1b;
                    right2=right2b;
                    up1=up1b;
                    up2=up2b;
                 
            
            }else if(keyH.equipPressed==false){

                    down1=xd1;
                    down2=xd2;
                    left1=xl1;
                    left2=xl2;
                    right1=xr1;
                    right2=xr2;
                    up1=xu1;
                    up2=xu2;
                
                hasPBoots=false;
                speed=4;
                
            }
        }
        if(keyH.upPressed==true || keyH.downPressed==true || keyH.leftPressed==true || keyH.rightPressed==true ){
            
            if(keyH.upPressed==true){
                direction="up";
            
            }
            else if(keyH.downPressed==true){
                direction="down";
            
            }
            else if(keyH.leftPressed==true){
                direction="left";
                
            }
            else if(keyH.rightPressed==true){
                direction="right";
                
            }
            
            //check tile collision
            collisionOn=false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
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
            if(numMoves>10){
                
                if(moveType==1){
                    moveType=2;
                }else if(moveType==2){
                    moveType=1;
                }
                numMoves=0;

            }

        }
        
    }
    public void pickUpObject(int i){
        if(i!=999){

            String objectName=gp.obj[i].name;

            switch(objectName){
                case "Door Key":
                gp.playSE(5);
                gp.obj[i]=null;
                disText=true;
                gp.ui.setMessage("Ai gasit o cheie");
               // System.out.println("Ai gasit un key!");
                hasKey++;
                break;

                case "Silver Key":
                gp.playSE(5);
                gp.obj[i]=null;
                disText=true;
                gp.ui.setMessage("Ai gasit un Silver key!");
                hasSKey++;
                break;

                case "Gold Key":
                gp.playSE(5);
                gp.obj[i]=null;
                hasGoldKey++;
                disText=true;
                gp.ui.setMessage("Ai gasit un Gold key!");
                hasGKey=true;
                
                break;
                case "Silver Chest":
                if(hasSKey>0){
                    gp.playSE(4);
                    gp.obj[i]=null;
                    disText=true;
                    gp.ui.setMessage("Ai deschis un Silver Chest!!!");
                    hasSKey--;
                    if(hasGKey==false)
                    gp.obj[1]=new OBJ_GoldKey(gp);
                    gp.obj[1].worldX=26*gp.tileSize;
                    gp.obj[1].worldY=10*gp.tileSize;
                }
                break;
                case "Gold Chest":
                if(hasGKey||hasGoldKey>0){
                    gp.playSE(4);
                    gp.obj[i]=null;
                    disText=true;
                    gp.ui.setMessage("Ai deschis un Gold Chest!!!");
                    hasGKey=false;
                    // hasPBoots=true;
                    hasGoldKey--;
                    if(hasPBoots==false){
                    gp.obj[16]=new OBJ_Boots(gp);
                    gp.obj[16].worldX=23*gp.tileSize;
                    gp.obj[16].worldY=19*gp.tileSize; 
                    }
                    


                }
                break;
                case "Door":
                
                if(hasKey>0){
                    gp.playSE(2);
                    gp.obj[i]=null;
                    disText=true;
                    System.out.println("Ai deschis o usa!");
                    hasKey--;
                }
                break;
                case "Purple Boots":
                
                    gp.playSE(3);
                    gp.obj[i]=null;
                    disText=true;
                    gp.ui.setMessage("Ai gasit o pereche de boots! (speed +1)");
                    
                   
                    // down1=down1b;
                    // down2=down2b;
                    // left1=left1b;
                    // left2=left2b;
                    // right1=right1b;
                    // right2=right2b;
                    // up1=up1b;
                    // up2=up2b;
                    
                    epboots=true;
                    
                   
                
                break;





            }
            
        }

    }

    public void draw(Graphics2D g2){
        // g2.setColor(Color.red);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
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
           
          



        g2.drawImage(image,screenX, screenY,null);
    }
}