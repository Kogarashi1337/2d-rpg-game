package object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Heart extends Entity{
   
    
    public OBJ_Heart (GamePanel gp){
        
        super(gp);
        name ="Heart";
        image = setup("/Objects/heart_2");
        image2 = setup("/Objects/heart_1");
        image3 = setup("/Objects/heart_0");

    }
    
}