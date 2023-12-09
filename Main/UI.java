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
    BufferedImage[] itemImage;
    public boolean messageOn = false;
    public String message="";
    public int msgCtr;
    public int msgX;
    public int msgY;
    public float alpha;
    public UI(GamePanel gp,Player pl){
        this.pl=pl;
        this.gp = gp;
        itemImage=new BufferedImage[5];
        arial_40= new Font("Arial", Font.BOLD, 10);
       
        setsImage();
        

    }
    public void setsImage(){
        OBJ_Key key=new OBJ_Key(gp);
        itemImage[0]=key.image;

        OBJ_SilverKey skey=new OBJ_SilverKey(gp);
        itemImage[1]=skey.image;
        
        OBJ_GoldKey gkey=new OBJ_GoldKey(gp);
        itemImage[2]=gkey.image;

        OBJ_Boots boots=new OBJ_Boots(gp);
        itemImage[3]=boots.image;
    }
    
    public void setMessage(String text){
        msgCtr=0;
        message=text;
        messageOn=true;
        
        msgX=38;
        msgY=600;
        alpha=1.0f;
    }
    
    public void draw(Graphics2D g2){
        g2.setFont(arial_40);
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

        if(gp.player.epboots){
             if(gp.player.hasPBoots){
             gp.player.boots="equipped";
            }else{
              gp.player.boots="unequipped";
            }
            g2.drawImage(itemImage[3], gp.tileSize/2, gp.tileSize+(gp.tileSize)-8, gp.tileSize/3,gp.tileSize/3,null);
            g2.drawString(" is "+gp.player.boots, 38, 100);
            
        }

       if(messageOn && pl.disText==true){
        alpha=alpha-(alpha/gp.FPS);
        g2.setColor(new Color(255,255,255,(int) (alpha*255)));
        g2.drawString(message, msgX, msgY--);
        
        msgCtr++;//(60 fps - 120 ticks = 2 seconds)

        if(msgCtr>gp.FPS){
            msgCtr=0;
            pl.disText=false;
            messageOn=false;

        }
       }
        


      //  g2.drawString("SKey = "+gp.player.hasSKey, 0, 14*gp.tileSize);//silv
        //g2.drawString("GKey = "+gp.player.hasGoldKey, 0, 13*gp.tileSize);//gold
    }
    
}
