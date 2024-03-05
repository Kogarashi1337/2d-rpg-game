package object;


import Entity.Entity;
import Main.GamePanel;

public class OBJ_GoldKey extends Entity{
   
    
    public  OBJ_GoldKey(GamePanel gp){

        super(gp);
        name="Gold Key";
        
        down1 = setup("/Objects/Key_Gold");
    }

}
