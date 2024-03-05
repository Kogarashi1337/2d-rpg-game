package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_GChest extends Entity{

    public OBJ_GChest(GamePanel gp){
        super(gp);
        name="Gold Chest";
        down1 = setup("/Objects/Chest_g");
        collision=true;
    }
}
