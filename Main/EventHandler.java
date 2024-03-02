package Main;

import java.awt.Rectangle;

public class EventHandler {
    
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp){

        this.gp = gp;
        eventRect=new Rectangle();
        //Setting the trigger point to be in the center of the tile and to be small to avoid unwanted triggers
        eventRect.x=23;
        eventRect.y=23;
        eventRect.width=2;
        eventRect.height=2;
        eventRectDefaultX=eventRect.x;
        eventRectDefaultY=eventRect.y;

    }
    public void checkEvent(){

    }
    public boolean hit(int eventCol,int eventRow, String reqDirection){
        boolean hit=false;
        gp.player.solidArea.x=gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y=gp.player.worldY + gp.player.solidArea.y;
        eventRect.x=eventCol*gp.tileSize+eventRect.x;
        eventRect.y=eventRow*gp.tileSize+eventRect.y;

        if(gp.player.solidArea.intersects(eventRect)){
            if(gp.player.direction.contentEquals(reqDirection)||reqDirection.equals("any")){
                hit=true;
            }
        }
        gp.player.solidArea.x=gp.player.solidAreaDefaultX;
        gp.player.solidArea.y=gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;
        
        return hit;
    }

}
