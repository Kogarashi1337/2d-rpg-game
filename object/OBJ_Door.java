package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Door extends Entity{


    public OBJ_Door(GamePanel gp){
        super(gp);
        name="Door";
        
        collision=true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        down1 = setup("/Objects/Door_1");
      //  update();
    }
}
