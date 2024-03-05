package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_SilverKey extends Entity{
 

    public OBJ_SilverKey(GamePanel gp){

        super(gp);
        name="Silver Key";
        image = setup("/Objects/Key_Silver");
    }
}
