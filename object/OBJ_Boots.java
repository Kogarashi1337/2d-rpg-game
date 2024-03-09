package object;


import Entity.Entity;
import Main.GamePanel;

public class OBJ_Boots extends Entity{
    


    public OBJ_Boots(GamePanel gp){

        super(gp);

        name="Purple Boots";
        up1 = up2 = left1 = left2 = right1 = right2 = down1 = down2 = setup("/Objects/item_boot1");
    }
    
}
