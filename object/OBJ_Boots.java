package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Boots extends SuperObject{
    
    GamePanel gp;

    public OBJ_Boots(GamePanel gp){
        this.gp = gp;
        name ="Purple Boots";
        try {
            image=ImageIO.read(getClass().getResourceAsStream("/Objects/item_boot1.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
    
}
