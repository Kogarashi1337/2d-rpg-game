package Main;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class KeyHandler implements KeyListener{
    public boolean upPressed,downPressed,rightPressed,leftPressed,equipPressed,radioPressedR,radioPressedL,enterPressed;
    public boolean eFlag=false;
    public boolean rFlag=false;
    public boolean timePressed=false;
    public boolean pauseGame;
    public boolean justDialogued=false;
    public boolean configure=false;
    public float sl=0.0f;
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

        //Title State
        if(gp.gameState==gp.titleState){
            
            switch(gp.ui.titleScreenState){

               
                case 0 : //MENU
                
                if(code==KeyEvent.VK_W){
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum<0){
                        gp.ui.commandNum=3;
                    }
                }
                if(code==KeyEvent.VK_S){
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum>3){
                        gp.ui.commandNum=0;
                    }
                }
                if(code==KeyEvent.VK_ENTER){
                    switch(gp.ui.commandNum){
                        case 0:
                        gp.ui.titleScreenState=1;

                        break;
                        case 1:
                        break;
                        case 2:
                        gp.ui.titleScreenState=2;
                        gp.ui.commandNum=0;
                        break;
                        case 3:
                        System.exit(0);
                    }
                }
                break;
                case 1://CLASS SELECTION
                if(code==KeyEvent.VK_W){
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum<0){
                        gp.ui.commandNum=4;
                    }
                }
                if(code==KeyEvent.VK_S){
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum>4){
                        gp.ui.commandNum=0;
                    }
                }
                if(code==KeyEvent.VK_ENTER){
                    switch(gp.ui.commandNum){
                        case 0:
                        System.out.println("\n You chose the Thief Class");
                        gp.ui.commandNum=0;
                        gp.stopMusic();
                        gp.gameState=gp.playState;
                        gp.player.classHealth("Thief");
                        gp.playMusic(6);
                        break;
                        case 1:
                        System.out.println("\n You chose the Mage Class");
                        gp.player.classHealth("Mage");
                        gp.ui.commandNum=0;
                        gp.stopMusic();
                        gp.gameState=gp.playState;
                        gp.playMusic(6);
                        break;
                        case 2:
                        System.out.println("\n You chose the Warrior Class");
                        gp.player.classHealth("Warrior");
                        gp.ui.commandNum=0;
                        gp.stopMusic();
                        gp.gameState=gp.playState;
                        gp.playMusic(6);
                        break;
                        case 3:
                        System.out.println("\n You chose the Elve Class");
                        gp.player.classHealth("Elve");
                        gp.ui.commandNum=0;
                        gp.stopMusic();
                        gp.gameState=gp.playState;
                        gp.playMusic(6);
                        break;
                        case 4:
                        gp.ui.titleScreenState=0;
                        gp.ui.commandNum=0;
                        break;
                    }
                }
                break;
                case 2 : //MENU
                
                if(code==KeyEvent.VK_W){
                    gp.ui.commandNum--;
                    configure=false;
                    if(gp.ui.commandNum<0){
                        gp.ui.commandNum=1;
                    }
                }
                if(code==KeyEvent.VK_S){
                    gp.ui.commandNum++;
                    configure=false;
                    if(gp.ui.commandNum>1){
                        gp.ui.commandNum=0;
                    }
                }
                if(code==KeyEvent.VK_ENTER){
                    switch(gp.ui.commandNum){
                        case 0:
                       configure=true;
                        break;
                        case 1:
                        gp.ui.titleScreenState=0;
                        gp.ui.commandNum=0;
                        break;
                    }
                }
                while(configure){
                    if(code==KeyEvent.VK_D){
                        
                        if(gp.ui.volumeLevel<-60){

                            gp.music.play();
                            gp.ui.volumeLevel=-60;
                            gp.music.setVolume((gp.ui.volumeLevel)/10f);

                        } 
                        else{
                        gp.ui.volumeLevel+=10;
                        gp.music.setVolume((gp.ui.volumeLevel)/10f);
                        }
                        if(gp.ui.volumeLevel>30){
                         gp.ui.volumeLevel=30;
                          
                        }

                        
                    }
                    if(code==KeyEvent.VK_A){

                       
                        if(gp.ui.volumeLevel==-60){
                            gp.ui.volumeLevel=-70;
                            gp.music.stop();
                            
                        }
                        else{
                            gp.ui.volumeLevel-=10;
                            gp.music.setVolume((gp.ui.volumeLevel)/10f);
                             
                        }
 
                        
                    }
                    break;
                }
                
                break;

            }


        }

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
            

            if(code==KeyEvent.VK_S){   
                gp.ui.commandNum++;
                // radioPressed 
                if(gp.ui.commandNum>1){
                    gp.ui.commandNum=0;
                }
            }
            if(code==KeyEvent.VK_W){   
                gp.ui.commandNum--;
                // radioPressed 
                if(gp.ui.commandNum<0){
                    gp.ui.commandNum=1;
                }
            }
            if(code==KeyEvent.VK_ENTER){
                switch(gp.ui.commandNum){
                    case 0:
                   // gp.ui.pauseAnim=false;
                    gp.ui.alpha=1.0f;
                    gp.gameState=gp.titleState;
                    gp.ui.titleScreenState=2;
                    gp.ui.commandNum=0;
                    break;
                    case 1:
                    gp.ui.alpha=1.0f;
                    gp.gameState=gp.titleState;
                    gp.ui.titleScreenState=0;
                    break;
                }
            }
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
                gp.ui.commandNum=0;
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
