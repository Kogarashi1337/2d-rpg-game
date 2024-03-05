package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_SChest extends Entity{


        public OBJ_SChest(GamePanel gp){

            super(gp);
            name="Silver Chest";
            image = setup("/Objects/Chest_s");

            collision=true;

        }

}
