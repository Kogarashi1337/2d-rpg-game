package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Key extends SuperObject{
        GamePanel gp;

        public OBJ_Key(GamePanel gp){
            
            this.gp=gp;
            name ="Door Key";

            try {
                
                image=ImageIO.read(getClass().getResourceAsStream("/Objects/Key.png"));
                uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
