package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Heart extends SuperObject{
    GamePanel gp;
    
    public OBJ_Heart (GamePanel gp){
        
        this.gp = gp;
        name ="Heart";

        try {
                
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/heart_2.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/Objects/heart_1.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/Objects/heart_0.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}