package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_GoldKey extends SuperObject{
    GamePanel gp;

    public  OBJ_GoldKey(GamePanel gp){

        this.gp=gp;
        name="Gold Key";
        try {
            image=ImageIO.read(getClass().getResourceAsStream("/Objects/Key_Gold.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

}
