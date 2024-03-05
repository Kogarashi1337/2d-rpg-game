package object;


import Entity.Entity;
import Main.GamePanel;

public class OBJ_Boots extends Entity{
    


    public OBJ_Boots(GamePanel gp){

        super(gp);

        name="Purple Boots";
        down1 = setup("/Objects/item_boot1");
    }
    
}
