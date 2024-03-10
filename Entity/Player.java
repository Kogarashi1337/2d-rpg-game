package Entity;
// import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;




import Main.GamePanel;
import Main.KeyHandler;

// import object.OBJ_Boots;
// import object.OBJ_GoldKey;

public class Player extends Entity {
 
 
    KeyHandler keyH;
    private long lastHeal;
    public final int screenX;
    public final int screenY;

    //ALPHA VERSION ITEMS
    // public int hasKey=0;
    // public int hasSKey=0;
    // public int hasGoldKey=0;
    // public  boolean hasGKey=false;
    // public  boolean hasPBoots=false;
    // public String boots="unequipped";
    // public boolean epboots=false;
    
    public int radio=0;



 
    public boolean disText=false;

    public Player(GamePanel gp,KeyHandler keyH){
        
        super(gp);
        lastHeal=0;

        this.keyH = keyH;
    
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x=8;//8
        solidArea.y=16;//16
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=30;
        solidArea.height=30;

        setdefaultValues();
        getPlayerImage();
    }
    public long getLastHeal(){
        return lastHeal;
    }
    public void setLastHeal(long lastHeal){
        this.lastHeal=lastHeal;
    }

    public void getPlayerImage(){
       
        up1=setup("/TEXTURES/boy_up1");
        up2=setup("/TEXTURES/boy_up2");
        down1=setup("/TEXTURES/boy_down1");
        down2=setup("/TEXTURES/boy_down2");
        left1=setup("/TEXTURES/boy_left1");
        left2=setup("/TEXTURES/boy_left2");
        right1=setup("/TEXTURES/boy_right1");
        right2=setup("/TEXTURES/boy_right2");

        //Boots
        // down1b=setup("/TEXTURES/boy_bdown1");
        // down2b=setup("/TEXTURES/boy_bdown2");
        // left1b=setup("/TEXTURES/boy_bleft1");
        // left2b=setup("/TEXTURES/boy_bleft2");
        // right1b=setup("/TEXTURES/boy_bright1");
        // right2b=setup("/TEXTURES/boy_bright2");
        // up1b=setup("/TEXTURES/boy_bup1");
        // up2b=setup("/TEXTURES/boy_bup2");

        //switch 
                    // xd1=down1;
                    // xd2=down2;
                    // xl1=left1;
                    // xl2=left2;
                    // xr1=right1;
                    // xr2=right2;
                    // xu1=up1;
                    // xu2=up2;
    }




    public void setdefaultValues(){
        this.worldX = gp.tileSize*39;
        this.worldY = gp.tileSize*29;
        this.speed=4;
        direction="down";

        //PLAYER STATUS


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

        //BETA VERSION ITEMS
        // if(epboots==true){
        //     if(keyH.equipPressed){
            
                
        //         hasPBoots=true;
        //         speed=6;
            
               
        //             down1=down1b;
        //             down2=down2b;
        //             left1=left1b;
        //             left2=left2b;
        //             right1=right1b;
        //             right2=right2b;
        //             up1=up1b;
        //             up2=up2b;
                 
            
        //     }else if(keyH.equipPressed==false){

        //             down1=xd1;
        //             down2=xd2;
        //             left1=xl1;
        //             left2=xl2;
        //             right1=xr1;
        //             right2=xr2;
        //             up1=xu1;
        //             up2=xu2;
                
        //         hasPBoots=false;
        //         speed=4;
                
        //     }
        // }
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

            //CHECK NPC COLLISION
            int npcIndex=gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);

            //CHECK EVENT
            gp.eHandler.checkEvent();
            gp.keyH.enterPressed=false;

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

            // BETA VERSION ITEMS
            // String objectName=gp.obj[i].name;

            // switch(objectName){
            //     case "Door Key":
            //     gp.playSE(5);
            //     gp.obj[i]=null;
            //     // disText=true;
            //     gp.ui.setMessage("Ai gasit o cheie");
            //    // System.out.println("Ai gasit un key!");
            //     hasKey++;
            //     break;

            //     case "Silver Key":
            //     gp.playSE(5);
            //     gp.obj[i]=null;
            //     // disText=true;
            //     gp.ui.setMessage("Ai gasit un Silver key!");
            //     hasSKey++;
            //     break;

            //     case "Gold Key":
            //     gp.playSE(5);
            //     gp.obj[i]=null;
            //     hasGoldKey++;
            //     // disText=true;
            //     gp.ui.setMessage("Ai gasit un Gold key!");
            //     hasGKey=true;
                
            //     break;
            //     case "Silver Chest":
            //     if(hasSKey>0){
            //         gp.playSE(4);
            //         gp.obj[i]=null;
            //         // disText=true;
            //         gp.ui.setMessage("Ai deschis un Silver Chest!!!");
            //         hasSKey--;
            //         if(hasGKey==false)
            //         gp.obj[1]=new OBJ_GoldKey(gp);
            //         gp.obj[1].worldX=26*gp.tileSize;
            //         gp.obj[1].worldY=10*gp.tileSize;
            //     }
            //     break;
            //     case "Gold Chest":
            //     if(hasGKey||hasGoldKey>0){
            //         gp.playSE(4);
            //         gp.obj[i]=null;
            //         // disText=true;
            //         gp.ui.setMessage("Ai deschis un Gold Chest!!!");
            //         hasGKey=false;
            //         // hasPBoots=true;
            //         hasGoldKey--;
            //         if(hasPBoots==false){
            //         gp.obj[16]=new OBJ_Boots(gp);
            //         gp.obj[16].worldX=23*gp.tileSize;
            //         gp.obj[16].worldY=19*gp.tileSize; 
            //         }
                    


            //     }
            //     break;
            //     case "Door":
                
            //     if(hasKey>0){
            //         gp.playSE(2);
            //         gp.obj[i]=null;
            //         // disText=true;
            //         System.out.println("Ai deschis o usa!");
            //         hasKey--;
            //     }
            //     break;
            //     case "Purple Boots":
                
            //         gp.playSE(3);
            //         gp.obj[i]=null;
            //         // disText=true;
            //         gp.ui.setMessage("Ai gasit o pereche de boots! (speed +1)");
                    
                   
            //         // down1=down1b;
            //         // down2=down2b;
            //         // left1=left1b;
            //         // left2=left2b;
            //         // right1=right1b;
            //         // right2=right2b;
            //         // up1=up1b;
            //         // up2=up2b;
                    
            //         epboots=true;
                    
                   
                
            //     break;





            // }
            
        }

    }

    //NPC COLLISION INTERACTION
    public void interactNPC(int i){
        if(i!=999){
            if(gp.keyH.enterPressed==true){

                gp.gameState=gp.dialogueState;
                gp.npc[i].speak();  
            }
           
            
        }
        
    }
    
    //TIMER BETWEEN COLLISION INTERACTIONS
    // public void setDialogueTimer(){
    //     int i;
    //     for(i=0;i<1200;i++){
    //         System.out.println("Chat timer countdown: "+(1200-i));
          
          
    //     }

    // }

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
