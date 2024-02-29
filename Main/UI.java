package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
// import java.awt.image.BufferedImage;

import Entity.Player;

public class UI {
    Player pl;
    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
    Font arial_80;
    Font arial_100;
    Font arial_60;
    //BufferedImage[] itemImage;

    public boolean messageOn = false;
    public boolean pMessageOn = false;
    public boolean pauseAnim = false;
    public String message="";
    public String pauseMessage="";
    public String currentDialogue="";

    public int msgCtr;
    public int msgX;
    public int msgY;
    public int imgN;
    public float alpha;
    
    


    //Constructor HERE
    public UI(GamePanel gp,Player pl){
        arial_40= new Font("Arial", Font.BOLD, 10);
        arial_60= new Font("Arial", Font.BOLD, 20);
        arial_80= new Font("Arial", Font.BOLD, 35);
        arial_100= new Font("Arial", Font.BOLD, 100);
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
       // int y = 520/2;
        // System.out.println("\nx ->"+x);
        // System.out.println("\ny ->"+y);
        
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

        g2.setFont(getFont(4));
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
