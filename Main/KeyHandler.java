package Main;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Entity.Entity;
import Entity.NPC_OldMan;

public class KeyHandler implements KeyListener{
    public boolean upPressed,downPressed,rightPressed,leftPressed,equipPressed,radioPressedR,radioPressedL,enterPressed;
    public boolean eFlag=false;
    public boolean rFlag=false;
    public boolean timePressed=false;
    public boolean pauseGame;
    public boolean justDialogued=false;
    GamePanel gp;

    //Constructor here
    public KeyHandler(GamePanel gp){
        this.gp = gp;

    }
    //Constructor here

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //Play state
        if(gp.gameState==gp.playState){
            if(code==KeyEvent.VK_W){
                upPressed=true;
            }
            if(code==KeyEvent.VK_S){
                downPressed=true;
            }
            if(code==KeyEvent.VK_D){
                rightPressed=true;
            }
            if(code==KeyEvent.VK_A){
                leftPressed=true;
            }

            //for dialogue
            if(code==KeyEvent.VK_ENTER){
              enterPressed=true;
            
            }
            //for ESC
            if(code==KeyEvent.VK_ESCAPE){
             
            }

        }
       
        //Pause state
        else if(gp.gameState==gp.pauseState){

            if(code==KeyEvent.VK_RIGHT){   
                // radioPressed 
            }
            if(code==KeyEvent.VK_LEFT){
                // radioPressed
            }
            //for ESC
            if(code==KeyEvent.VK_ESCAPE){
             
            }


        }

        
        //Dialogue state
        else if(gp.gameState==gp.dialogueState){

            //dialogue 
            if(code==KeyEvent.VK_ENTER){
                
                upPressed=false;
                downPressed=false;
                leftPressed=false;
                rightPressed=false;

                gp.gameState=gp.playState;
               
               
            }
        }


        if(code==KeyEvent.VK_T){
            if(timePressed){
                timePressed=false;
            }else{
                timePressed=true;
            }

        }


    }
           
        
        
    

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        //Play State
        if(gp.gameState==gp.playState){


            //ESC Button
            if(code==KeyEvent.VK_ESCAPE){

                gp.gameState = gp.pauseState;
                gp.ui.pauseMessage("Game Paused");
                
            }
            if(code==KeyEvent.VK_W){
                upPressed=false;
            }
            if(code==KeyEvent.VK_S){
                downPressed=false;
            }
            if(code==KeyEvent.VK_D){
                rightPressed=false;
            }
            if(code==KeyEvent.VK_A){
                leftPressed=false;
            }

            //equipment
            if(code==KeyEvent.VK_E){
             
                if(eFlag){
                equipPressed=false;
                    eFlag=false;
                    
                }
                else{
                    equipPressed=true;
                    eFlag=true;
                }
           
            }

            if(code==KeyEvent.VK_ENTER){
               
            }

        }
       
        //Pause State
        else if(gp.gameState==gp.pauseState){

            if(code==KeyEvent.VK_ESCAPE){
                // pauseGame=true;
                gp.gameState = gp.playState;
               
            }

            //Radio
            if(code==KeyEvent.VK_RIGHT){

                radioPressedR=true;  

            }
            if(code==KeyEvent.VK_LEFT){
                radioPressedL=true;
            }
        }
        //Dialogue state
        else if(gp.gameState==gp.dialogueState){
            
            // if(code==KeyEvent.VK_ENTER){
             
            // }

        }

       


    }
    
}
