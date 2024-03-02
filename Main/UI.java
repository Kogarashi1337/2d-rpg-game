package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
// import java.awt.image.BufferedImage;
import java.io.InputStream;

import Entity.Player;

public class UI {
    Player pl;
    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
    Font arial_80;
    Font arial_100;
    Font arial_60;
    Font maruMonica;
    //BufferedImage[] itemImage;

    public boolean messageOn = false;
    public boolean pMessageOn = false;
    public boolean pauseAnim = false;
    public String message="";
    public String pauseMessage="";
    public String currentDialogue="";

    public int commandNum=0;
    public int msgCtr;
    public int msgX;
    public int msgY;
    public int imgN;
    public int volumeLevel=-10;
    public int titleScreenState = 0;// State 0 for menu | State 1 for class selection

    public float alpha;
    
    


    //Constructor HERE
    public UI(GamePanel gp,Player pl){
        arial_40= new Font("Arial", Font.BOLD, 10);
        arial_60= new Font("Arial", Font.BOLD, 20);
        arial_80= new Font("Arial", Font.BOLD, 35);
        arial_100= new Font("Arial", Font.BOLD, 100);
        alpha=1.0f;
       

        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        this.pl=pl;
        this.gp = gp;
        this.imgN = 5;
        // setsImage(imgN);
    }
    
    public Font getFont(int fontSize){
        switch(fontSize){
            case 1:
            return arial_40;
            case 2:
            return arial_80;
            case 3:
            return arial_100;
            case 4:
            return arial_60;
            case 5:
            return maruMonica;
            default:
            return this.arial_40;
        }
      
    }
    public void setsImage(int imgN){

        // itemImage=new BufferedImage[imgN];
        // OBJ_Key key=new OBJ_Key(gp);
        // itemImage[0]=key.image;

        // OBJ_SilverKey skey=new OBJ_SilverKey(gp);
        // itemImage[1]=skey.image;
        
        // OBJ_GoldKey gkey=new OBJ_GoldKey(gp);
        // itemImage[2]=gkey.image;

        // OBJ_Boots boots=new OBJ_Boots(gp);
        // itemImage[3]=boots.image;
    }
    
    public void setMessage(String text){
        msgCtr=0;
        message=text;
        messageOn=true;
        
        msgX=38;
        msgY=600;
        alpha=1.0f;
    }
    public void pauseMessage(String text){
        msgCtr=0;
        pauseMessage=text;

        
        msgX=475;
        msgY=300;
        alpha=1.0f;
    }
    public void draw(Graphics2D g2){
        this.g2=g2;
        g2.setFont(getFont(1));
        g2.setColor(Color.white);
        
        //Item pickup message
        // if(messageOn){
        //     alpha=alpha-(alpha/gp.FPS);
        //      g2.setColor(new Color(255,255,255,(int) (alpha*255)));
        //    g2.drawString(message, msgX, msgY--);
            
        //     msgCtr++;//(60 fps - 120 ticks = 2 seconds)
            
        //     if(msgCtr>gp.FPS){
        //         msgCtr=0;
        //         // pl.disText=false;
        //         messageOn=false;

        //     }
        // }


        //TITLESCREEN
        if(gp.gameState==gp.titleState){
            drawTitleScreen();
        }

        //PLAY 
        if (gp.gameState==gp.playState){
          // pMessageOn=false;
          // msgCtr=0;
        }

        //DIALOGUE
        if(gp.gameState==gp.dialogueState)
        {   
          //  pMessageOn=false;
            drawDialogueScreen();
        }

        //PAUSE
        if (gp.gameState==gp.pauseState){
            
            //  pMessageOn=true;
          drawPauseScreen();  
        }

      //  g2.drawString("SKey = "+gp.player.hasSKey, 0, 14*gp.tileSize);//silv
        //g2.drawString("GKey = "+gp.player.hasGoldKey, 0, 13*gp.tileSize);//gold
    }

    public void drawTitleScreen(){
        if(titleScreenState==0){


        //MENU SCREEN
            g2.setColor(new Color(3,4,94,(int) (alpha*200)));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            if(pauseAnim==false){
            alpha=alpha-(alpha/60);  
            }
            if(pauseAnim){
                alpha=alpha+(alpha/60);
            }

            msgCtr++;

            if(msgCtr>100){
                if(pauseAnim){
                    pauseAnim=false;
                }
                else{
                    pauseAnim=true;
                }
                msgCtr=0;
            }

            g2.setFont(getFont(5).deriveFont(Font.BOLD,96F));
            String text = "Lost Estharia";
            int x = getTextWidth(text);
            int y = gp.tileSize*3;

            g2.setColor(new Color(0,180,216));
            g2.drawString(text, x+4, y+4);

            g2.setColor(new Color(173,232,244));
            g2.drawString(text, x, y);

            //Mithras Trismegistus Image
            x = gp.screenWidth/2-(gp.tileSize*2)/2;
            y += gp.tileSize*2;
            g2.drawImage(gp.player.down1,x, y, gp.tileSize*2,gp.tileSize*2,null);

            //NEW GAME
            g2.setColor(new Color(3,4,94));
            g2.setFont(getFont(5).deriveFont(Font.BOLD,48F));
            text="New Game";
            x = getTextWidth(text);
            y += gp.tileSize*3;
            if(commandNum==0){
                
                g2.setColor(new Color(173,232,244));
                g2.drawString("=>", x-gp.tileSize, y);
                g2.setColor(new Color(0,180,216));
            }
            g2.drawString(text, x, y);
            

            //LOAD GAME
            g2.setColor(new Color(3,4,94));
            g2.setFont(getFont(5).deriveFont(Font.BOLD,48F));
            text="Load Game";
            x = getTextWidth(text);
            y += gp.tileSize;
            if(commandNum==1){
                
                g2.setColor(new Color(173,232,244));
                g2.drawString("=>", x-gp.tileSize, y);
                g2.setColor(new Color(0,180,216));
            }
            g2.drawString(text, x, y);
        

            //SETTINGS
            g2.setColor(new Color(3,4,94));
            g2.setFont(getFont(5).deriveFont(Font.BOLD,48F));
            text="Settings";
            x = getTextWidth(text);
            y += gp.tileSize;
            if(commandNum==2){
                
                g2.setColor(new Color(173,232,244));
                g2.drawString("=>", x-gp.tileSize, y);
                g2.setColor(new Color(0,180,216));
            }
            g2.drawString(text, x, y);
        

            //EXIT
            g2.setColor(new Color(3,4,94));
            g2.setFont(getFont(5).deriveFont(Font.BOLD,48F));
            text="Exit";
            x = getTextWidth(text);
            y += gp.tileSize;
            if(commandNum==3){
                
                g2.setColor(new Color(173,232,244));
                g2.drawString("=>", x-gp.tileSize, y);
                g2.setColor(new Color(0,180,216));
            }
            g2.drawString(text, x, y);
        }
        else if(titleScreenState==1){
            //Background
            g2.setColor(new Color(0,0,0,(int) (alpha*255)));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            //Title
            g2.setColor(new Color(173,232,244));
            g2.setFont(getFont(5).deriveFont(Font.BOLD,96F));
            String text="Choose your Class";
            int x = getTextWidth(text);
            int y = gp.tileSize*3; 
            g2.drawString(text, x, y);

            //THIEF CLASS
            g2.setColor(new Color(3,4,94));
            g2.setFont(getFont(5).deriveFont(Font.BOLD,48F));
            text="Thief";
            x = getTextWidth(text);
            y += gp.tileSize*3;
            if(commandNum==0){
                
                g2.setColor(new Color(173,232,244));
                g2.drawString("=>", x-gp.tileSize, y);
                g2.setColor(new Color(0,180,216));
            }
            g2.drawString(text, x, y);

            //MAGE CLASS
            g2.setColor(new Color(3,4,94));
            g2.setFont(getFont(5).deriveFont(Font.BOLD,48F));
            text="Mage";
            x = getTextWidth(text);
            y += gp.tileSize;
            if(commandNum==1){
                
                g2.setColor(new Color(173,232,244));
                g2.drawString("=>", x-gp.tileSize, y);
                g2.setColor(new Color(0,180,216));
            }
            g2.drawString(text, x, y);

            //WARRIOR CLASS
            g2.setColor(new Color(3,4,94));
            g2.setFont(getFont(5).deriveFont(Font.BOLD,48F));
            text="Warrior";
            x = getTextWidth(text);
            y += gp.tileSize;
            if(commandNum==2){
                
                g2.setColor(new Color(173,232,244));
                g2.drawString("=>", x-gp.tileSize, y);
                g2.setColor(new Color(0,180,216));
            }
            g2.drawString(text, x, y);

            //ELVE CLASS
            g2.setColor(new Color(3,4,94));
            g2.setFont(getFont(5).deriveFont(Font.BOLD,48F));
            text="Elve";
            x = getTextWidth(text);
            y += gp.tileSize;
            if(commandNum==3){
                
                g2.setColor(new Color(173,232,244));
                g2.drawString("=>", x-gp.tileSize, y);
                g2.setColor(new Color(0,180,216));
            }
            g2.drawString(text, x, y);

            //Back to Menu button
            g2.setColor(new Color(3,4,94));
            g2.setFont(getFont(5).deriveFont(Font.BOLD,48F));
            text="Back";
            x = getTextWidth(text);
            y += gp.tileSize*2;
            if(commandNum==4){
                
                g2.setColor(new Color(173,232,244));
                g2.drawString("=>", x-gp.tileSize, y);
                g2.setColor(new Color(0,180,216));
            }
            g2.drawString(text, x, y);
            
        }
        else if(titleScreenState==2){
            //Background
            g2.setColor(new Color(0,0,0,(int) (alpha*255)));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            //Title
            g2.setColor(new Color(173,232,244));
            g2.setFont(getFont(5).deriveFont(Font.BOLD,96F));
            String text="Game Settings";
            int x = getTextWidth(text);
            int y = gp.tileSize*3; 
            g2.drawString(text, x, y);

            //Sound Volume
            g2.setColor(new Color(3,4,94));
            g2.setFont(getFont(5).deriveFont(Font.BOLD,48F));
            text="Sound Volume";
            x = getTextWidth(text)/2;
            y += gp.tileSize*3;
            if(commandNum==0){
                
                g2.setColor(new Color(173,232,244));
                g2.drawString("=>", x-gp.tileSize, y);
                g2.setColor(new Color(0,180,216));
            }
            g2.drawString(text, x, y);
            //Sound Bar
            g2.setColor(new Color(3,4,94));
            g2.setFont(getFont(5).deriveFont(Font.BOLD,48F));
            text="||||||||||";
            x = getTextWidth(text)+getTextWidth(text)/2;
            String volm =text;
            g2.drawString(text, x, y);
            switch(volumeLevel){
                case -60:
                volm="|";
                g2.setColor(new Color(0,180,216));
                break;

                case -50:
                volm="||";
                g2.setColor(new Color(0,180,216));
                break;

                case -40:
                volm="|||";
                g2.setColor(new Color(0,180,216));
                break;

                case -30:
                volm="||||";
                g2.setColor(new Color(0,180,216));
                break;

                case -20:
                volm="|||||";
                g2.setColor(new Color(0,180,216));
                break;

                case -10:
                volm="||||||";
                g2.setColor(new Color(0,180,216));
                break;

                case 0:
                volm="|||||||";
                g2.setColor(new Color(0,180,216));
                break;

                case 10:
                volm="||||||||";
                g2.setColor(new Color(0,180,216));
                break;

                case 20:
                volm="|||||||||";
                g2.setColor(new Color(0,180,216));
                break;

                case 30:
                volm="||||||||||";
                g2.setColor(new Color(0,180,216));
                break;
                default:
                g2.setColor(new Color(3,4,94));
                break;
                // g2.setColor(new Color(173,232,244));
                // g2.drawString("=>", x-gp.tileSize, y);
            }
            g2.drawString(volm, x, y);

            //Back to Menu button
            g2.setColor(new Color(3,4,94));
            g2.setFont(getFont(5).deriveFont(Font.BOLD,48F));
            text="Back";
            x = getTextWidth(text);
            y += gp.tileSize*2;
            if(commandNum==1){
                
                g2.setColor(new Color(173,232,244));
                g2.drawString("=>", x-gp.tileSize, y);
                g2.setColor(new Color(0,180,216));
            }
            g2.drawString(text, x, y);

            
        }

      

    }

    public void drawPauseScreen(){
        g2.setFont(getFont(2));
        String text = "PAUSED";
        
        int x = getTextWidth(text);
        int y = gp.screenHeight/2;
          //  g2.setColor(new Color(255,255,255,(int) (alpha*255)));
            g2.drawString(text, x, y);
        
            // if(pMessageOn){
            //     if(pauseAnim==false){
            //         alpha=alpha-(alpha/60);   
            //     }
            //     else{
            //         alpha=alpha+(alpha/60);
            //     }
                   
            //      msgCtr++;

            //     if(msgCtr>60){
            //         if(pauseAnim){
            //                pauseAnim=false;
            //         }
            //         else{
            //                pauseAnim=true;
            //         }
                       
            //         msgCtr=0;
            //     }
       
            // }
        
    }



    public int getTextWidth(String text){
        int length=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
       int textWidth=gp.screenWidth/2-length/2;
      // int textWidth=1080/2-length/2;
        return textWidth;
    }


    
    public void drawDialogueScreen(){
        //window
        int x=gp.tileSize*2;
        int y=gp.tileSize/2;
        int width=gp.screenWidth-(gp.tileSize*4);
        int height=gp.tileSize*4;

        drawSubWindow(x, y, width, height);

        g2.setFont(getFont(5).deriveFont(Font.PLAIN,32F));
        x +=gp.tileSize;
        y +=gp.tileSize;
        //String text = "Hello, my name is "+gp.npc[i].name();

        for(String line: currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y+=40;
        }
        
        
    }


    public void drawSubWindow(int x,int y, int width,int height){
        Color c=new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect( x, y, width, height, 35, 35);

        //white outline
        c=new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
}
