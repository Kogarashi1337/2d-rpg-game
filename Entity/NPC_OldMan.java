package Entity;

import java.util.Random;

import Main.GamePanel;


public class NPC_OldMan extends Entity{
    
    public NPC_OldMan(GamePanel gp,String name){
       super(gp);
       direction="down";
       speed=1;
       this.name=name;
       getImage();
       setDialogue();
    }
        
 
    public void getImage(){
       
        up1=setup("/npc/old_man_up_1");
        up2=setup("/npc/old_man_up_2");
        down1=setup("/npc/old_man_down_1");
        down2=setup("/npc/old_man_down_2");
        left1=setup("/npc/old_man_left_1");
        left2=setup("/npc/old_man_left_2");
        right1=setup("/npc/old_man_right_1");
        right2=setup("/npc/old_man_right_2");

    
    }public void setDialogue(){
        dialogues[0]="Hello bruv! My name's "+this.name;
    }

    public void setAction(){
        Random random=new Random();
        int i=random.nextInt(100)+1;
        actionLockCounter++;

        if(actionLockCounter == 120){

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
    public void speak(){
        gp.ui.currentDialogue=dialogues[0];
    }
    


}
