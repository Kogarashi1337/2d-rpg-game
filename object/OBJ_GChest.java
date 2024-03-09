package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_GChest extends Entity{

    public OBJ_GChest(GamePanel gp){
        super(gp);
        name="Gold Chest";
        up1 = up2 = left1 = left2 = right1 = right2 = down1 = down2 = setup("/Objects/Chest_g");
        collision=true;
    }
}
