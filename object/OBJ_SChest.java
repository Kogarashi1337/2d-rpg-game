package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_SChest extends SuperObject{
        GamePanel gp;

        public OBJ_SChest(GamePanel gp){

            this.gp=gp;
            name="Silver Chest";

            try {
                image=ImageIO.read(getClass().getResourceAsStream("/Objects/Chest_s.png"));
                uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            } catch (IOException e) {
                e.printStackTrace();
            }
            collision=true;

        }

}
