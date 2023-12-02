package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_SilverKey extends SuperObject{
        GamePanel gp;

    public OBJ_SilverKey(GamePanel gp){

        this.gp=gp;
        name="Silver Key";
        try {
            image=ImageIO.read(getClass().getResourceAsStream("/Objects/Key_Silver.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
          e.printStackTrace();
        }
    }
}
