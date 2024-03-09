package object;


import Entity.Entity;
import Main.GamePanel;

public class OBJ_GoldKey extends Entity{
   
    
    public  OBJ_GoldKey(GamePanel gp){

        super(gp);
        name="Gold Key";
        
        up1 = up2 = left1 = left2 = right1 = right2 = down1 = down2 = setup("/Objects/Key_Gold");
    }

}
