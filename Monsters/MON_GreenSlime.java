package Monsters;

import Entity.Entity;
import Main.GamePanel;

public class MON_GreenSlime extends Entity {
    public MON_GreenSlime(GamePanel gp){
        super(gp);
        
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 6;
        solidArea.y = 12;
        solidArea.width = 36;//48-(2x6)
        solidArea.height = 36;//48-12
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
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
        
    }
    
}
