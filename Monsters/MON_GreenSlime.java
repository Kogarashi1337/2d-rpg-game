package Monsters;

import java.util.Random;

import Entity.Entity;
import Main.GamePanel;

public class MON_GreenSlime extends Entity {
    public MON_GreenSlime(GamePanel gp){
        super(gp);
        type=2;
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        this.entityDNA="monster";
        solidArea.x = 6;
        solidArea.y = 12;
        solidArea.width = 36;//48-(2x6)
        solidArea.height = 36;//48-12
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){
        up1= setup("/monster/tslime_1");
        up2= setup("/monster/tslime_2");
        up3= setup("/monster/tslime_3");
        up4= setup("/monster/tslime_4");
        up5= setup("/monster/tslime_5");
        up6= setup("/monster/tslime_6");
        up7= setup("/monster/tslime_7");
        up8= setup("/monster/tslime_8");  
        
        // right1=left1=down1=up1= setup("/monster/tslime_1");
        // right2=left2=down2=up2= setup("/monster/tslime_2");
        // right3=left3=down3=up3= setup("/monster/tslime_3");
        // right4=left4=down4=up4= setup("/monster/tslime_4");
        // right5=left5=down5=up5= setup("/monster/tslime_5");
        // right6=left6=down6=up6= setup("/monster/tslime_6");
        // right7=left7=down7=up7= setup("/monster/tslime_7");
        // right8=left8=down8=up8= setup("/monster/tslime_8");  
    }
    public void setAction(){
        int animTime=240;
        Random random=new Random();
        int i=random.nextInt(100)+1;
        actionLockCounter++;
        

        if(actionLockCounter == animTime){

            if(i <= 25){
            direction="left";
            }
            if((i > 25)&&(i<=50)){
            direction="right";
            }
            if((i > 50)&&(i <= 75)){
            direction="down";
            }
            if(i > 75){
            direction="up";
            }
            actionLockCounter=0; 
        }
    }
}
