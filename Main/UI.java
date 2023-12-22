package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entity.Player;
import object.OBJ_Boots;
import object.OBJ_GoldKey;
import object.OBJ_Key;
import object.OBJ_SilverKey;

public class UI {
    Player pl;
    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
    Font arial_80;
    Font arial_100;
    //BufferedImage[] itemImage;

    public boolean messageOn = false;
    public boolean pMessageOn = false;
    public String message="";
    public String pauseMessage="";
    public int msgCtr;
    public int msgX;
    public int msgY;
    public float alpha;
    public int imgN;


    //Constructor HERE
    public UI(GamePanel gp,Player pl){
        arial_40= new Font("Arial", Font.BOLD, 10);
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
        pMessageOn=true;
        
        msgX=475;
        msgY=300;
        alpha=1.0f;
    }
    public void draw(Graphics2D g2){
        this.g2=g2;
        g2.setFont(getFont(1));
        g2.setColor(Color.white);
        
        //     if(gp.player.hasKey>0){
            
        //     g2.drawImage(itemImage[0], gp.tileSize/2,gp.tileSize/2,gp.tileSize/3,gp.tileSize/3,null);
        //     g2.drawString(" x "+gp.player.hasKey, 38, 35);
        //     }
        //      if(gp.player.hasSKey>0){
        //     g2.drawImage(itemImage[1], gp.tileSize/2,gp.tileSize-8,gp.tileSize/3,gp.tileSize/3,null);
        //     g2.drawString(" x "+gp.player.hasSKey, 38, 51);
        //     }
        //    if(gp.player.hasGoldKey>0){
        //     g2.drawImage(itemImage[2], gp.tileSize/2, gp.tileSize+(gp.tileSize/2)-16, gp.tileSize/3,gp.tileSize/3,null);
        //     g2.drawString(" x "+gp.player.hasGoldKey, 38, 70);
        //    }

        // if(gp.player.epboots){
        //      if(gp.player.hasPBoots){
        //      gp.player.boots="equipped";
        //     }else{
        //       gp.player.boots="unequipped";
        //     }
        //     g2.drawImage(itemImage[3], gp.tileSize/2, gp.tileSize+(gp.tileSize)-8, gp.tileSize/3,gp.tileSize/3,null);
        //     g2.drawString(" is "+gp.player.boots, 38, 100);
            
        // }

        //Pause Menu Message
        // if(pMessageOn){
        //     g2.setFont(arial_80);
        //     messageOn=false;
        //     g2.setColor(new Color(200,200,255,(int) (alpha*255)));
        //     g2.drawString(pauseMessage, msgX, msgY);
            

        // }

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

        if (gp.gameState==gp.pauseState){
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
       // int y = 520/2;
        // System.out.println("\nx ->"+x);
        // System.out.println("\ny ->"+y);
        g2.drawString(text, x, y);
    }
    public int getTextWidth(String text){
        int length=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
       int textWidth=gp.screenWidth/2-length/2;
      // int textWidth=1080/2-length/2;
        return textWidth;
    }
}
