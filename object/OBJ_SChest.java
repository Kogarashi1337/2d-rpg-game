package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_SChest extends Entity{


        public OBJ_SChest(GamePanel gp){

            super(gp);
            name="Silver Chest";
            up1 = up2 = left1 = left2 = right1 = right2 = down1 = down2 = setup("/Objects/Chest_s");

            collision=true;

        }

}
