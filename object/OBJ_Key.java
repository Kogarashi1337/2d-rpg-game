package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Key extends Entity{

        
        public OBJ_Key(GamePanel gp){
            super(gp);
            
            name ="Door Key";
            down1= setup("/Objects/Key");

        }

}
