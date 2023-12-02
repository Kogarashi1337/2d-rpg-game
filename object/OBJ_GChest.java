package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_GChest extends SuperObject{
    GamePanel gp;

    public OBJ_GChest(GamePanel gp){

        this.gp=gp;
        name="Gold Chest";
        try {
            image=ImageIO.read(getClass().getResourceAsStream("/Objects/Chest_g.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
          e.printStackTrace();
        }
        collision=true;
    }
}
