package Main;

import java.awt.Rectangle;

public class EventHandler {
    
    GamePanel gp;
    EventRect eventRect[][];

    public EventHandler(GamePanel gp){

        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while((col < gp.maxWorldCol)&&(row<gp.maxWorldRow)){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
            col++;
            if(col==gp.maxWorldCol){
                col=0;
                row++;
            }
            

        }

    }
    public void checkEvent(){
        //event happens
        //if(hit(17,24,"any")==true){changeMap(gp.dialogueState);}
        if(hit(25,23,"down")==true){healingPool(gp.dialogueState);}
        if(hit(25,25,"down")==true){damagePit(gp.dialogueState);}
    }
    //EVENT COLLISION CHECK
    public boolean hit(int col,int row, String reqDirection){
        boolean hit=false;
        gp.player.solidArea.x=gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y=gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x=col*gp.tileSize+eventRect[col][row].x;
        eventRect[col][row].y=row*gp.tileSize+eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row])){
            if(gp.player.direction.contentEquals(reqDirection)||reqDirection.equals("any")){
                hit=true;
            }
        }
        gp.player.solidArea.x=gp.player.solidAreaDefaultX;
        gp.player.solidArea.y=gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }
    //EVENTS
    public void damagePit(int gameState){
        gp.gameState=gameState;
        gp.ui.currentDialogue = "You fall into a pit!!";
        gp.player.life-=1;
    }
    public void healingPool(int gameState){
        if(gp.keyH.enterPressed==true){
            gp.gameState=gameState;
            gp.ui.currentDialogue="You drank some holy water!\n You have recovered!";
            gp.player.life=gp.player.maxLife;
        }
    }
    public void changeMap(int gameState){
        
        gp.gameState=gameState;
        gp.ui.currentDialogue="You drank some holy water!\n You have recovered!";
        gp.player.life=gp.player.maxLife;
        
    }
}
