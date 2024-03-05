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
        if(hit(25,23,"down")==true){healingPool(25,23,gp.dialogueState,gp.player.getLastHeal());}
        if(hit(25,25,"down")==true){damagePit(25,25,gp.dialogueState);}
    }
    //EVENT COLLISION CHECK
    public boolean hit(int col,int row, String reqDirection){
        boolean hit=false;
        gp.player.solidArea.x=gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y=gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x=col*gp.tileSize+eventRect[col][row].x;
        eventRect[col][row].y=row*gp.tileSize+eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone==false){
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
    public void damagePit(int col, int row, int gameState){
        gp.gameState=gameState;
        gp.ui.currentDialogue = "You fall into a pit!!";
        gp.player.life-=1;
        eventRect[col][row].eventDone=true;
    }
    public void healingPool(int col, int row, int gameState, long lastHeal){
        if(gp.keyH.enterPressed==true){
            long cooldown=10000;
            gp.gameState=gameState;
            
            long healTime= System.currentTimeMillis();
            if(healTime- lastHeal >= cooldown){
            gp.ui.currentDialogue="You drank some holy water!\n You have recovered!";
            gp.player.life=gp.player.maxLife;
            gp.player.setLastHeal(healTime);    
            }else{
                gp.ui.currentDialogue="You need to wait "+ (cooldown-(healTime-lastHeal))/1000+"sec ";
            }
        }

    }
    public void changeMap(int gameState){
        
        gp.gameState=gameState;
        gp.ui.currentDialogue="You drank some holy water!\n You have recovered!";
        gp.player.life=gp.player.maxLife;
        
    }
}
