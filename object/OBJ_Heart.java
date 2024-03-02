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
                
            image=ImageIO.read(getClass().getResourceAsStream("/Objects/Heart_2.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}