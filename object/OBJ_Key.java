package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Key extends Entity{

        
        public OBJ_Key(GamePanel gp){
            super(gp);
            
            name ="Door Key";
            up1 = up2 = left1 = left2 = right1 = right2 = down1 = down2 = setup("/Objects/Key");

        }

}
