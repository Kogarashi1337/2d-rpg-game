package Main;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    public boolean upPressed,downPressed,rightPressed,leftPressed,equipPressed,radioPressedR,radioPressedL;
    public boolean eFlag=false;
    public boolean rFlag=false;
    public boolean timePressed=false;
    public boolean pauseGame;
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
        
        if(code==KeyEvent.VK_RIGHT){
            
        }

        if(code==KeyEvent.VK_LEFT){
            // radioPressed=true;
        }
        
        if(code==KeyEvent.VK_T){
            if(timePressed){
                timePressed=false;
            }else{
                timePressed=true;
            }

        }

        if(code==KeyEvent.VK_E){
          
        }

        // if(code==KeyEvent.VK_ESCAPE){

            // switch(gp.gameState){
            //         case gp.playState:
            //         gp.gameState=gp.pauseState;
            //         break;
            //         case gp.pauseState:
            //         gp.gameState=gp.playState;
            //         break;
            //     }
            // pauseGame=false;
            // if(gp.gameState == gp.playState){
            //     gp.gameState = gp.pauseState;
                
            // }
            // else if(gp.gameState == gp.pauseState){
            //     gp.gameState = gp.playState;
            // }
        // }
        }
           
        
        
    

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
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
        if(code==KeyEvent.VK_ESCAPE){
         // pauseGame=true;
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
                gp.ui.pauseMessage("Game Paused");
            }
            else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
                gp.ui.pMessageOn=false;
            }
        }
        if(code==KeyEvent.VK_RIGHT){

          radioPressedR=true;  
            // if(rFlag){
            //     radioPressed=false;
            //     rFlag=false;
            // }
            // else{
            //     radioPressed=true;
            //     rFlag=true;
            // }
        }
        if(code==KeyEvent.VK_LEFT){
            radioPressedL=true;
        }
    }
    
}
