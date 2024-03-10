package Main;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import Entity.Entity;

import Entity.Player;
// import Entity.Sheep;
// import Entity.Wolf;

import tiles.TileManager;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;


public class GamePanel extends JPanel implements Runnable{
    //screen settings
    final int originalTileSize = 16; // 16x16 tile size
    final int scale = 3;// how much we scale

    public final int tileSize = originalTileSize*scale;// final tile size (48x48)
    
    public final int maxScreenCol = 24; //24
    public final int maxScreenRow = 16; //16  6:4 ratio

    public final int screenWidth = maxScreenCol*tileSize;//(1152 pixels)
    public final int screenHeight = maxScreenRow*tileSize;//(768 pixels)
    
    //WORLD SETTINGS
    public int maxWorldCol = 48;
    public int maxWorldRow = 32;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow; 

    int FPS=60;//running FPS

    // SYSTEM 
    public KeyHandler keyH=new KeyHandler(this);
    TileManager tileM = new TileManager(this);
    
    // TileManager tileM2 = new TileManager(this,0);
    Sound music = new Sound();
    Sound se = new Sound(); 
    public CollisionChecker cChecker=new CollisionChecker(this);
    public AssetSetter aSetter=new AssetSetter(this);
    //EVENT HANDLER
    public EventHandler eHandler=new EventHandler(this);

  
    Thread gameThread;
    
    
    //ENTITY AND OBJECT
    public Player player=new Player(this, keyH);
    public Entity[] obj =new Entity[17];
    //Wolf wolf=new Wolf(this,keyH);
    // Sheep sheep=new Sheep(this,keyH);
    public Entity monster[]=new Entity[20];
    public Entity npc[]=new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();
  //  public NPC_OldMan oldMan=new NPC_OldMan(this);


//UI HERE
  public UI ui=new UI(this,player);



    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    
 
    public GamePanel(){

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        playMusic(8);

       // stopMusic();
        gameState = titleState;
    }

    public void startGameThread(){

        gameThread=new Thread(this);
        gameThread.start();
    }
    @Override
    // public void run() {
    //     double drawInterval = 1000000000/FPS; //drawing every 0.01666 seconds
    //     double nextDrawTime=System.nanoTime()+drawInterval;
    //     while(gameThread != null){

            
    //        // long currentTime = System.nanoTime();
    //         // System.out.println("this is running at :"+currentTime); 
    //         //1. UPDATE: information(character position)
    //          update();
    //         //2. DRAW:   the screen with the updated information
    //          repaint();
             
    //          try {

    //             double remainingTime= nextDrawTime - System.nanoTime();
    //             remainingTime = remainingTime/1000000;

    //             if(remainingTime<0){
    //                 remainingTime=0;
    //             }

    //             Thread.sleep((long) remainingTime);
    //             nextDrawTime+=drawInterval;
            
    //         } catch (InterruptedException e) {
    //            
    //             e.printStackTrace();
    //         }
    //      }

       
    // }

        public void run(){
            double drawInterval =1000000000/FPS;
            double delta = 0;
            long lastTime=System.nanoTime();
            long currentTime;
            long timer=0;
           // int count=0;


            while (gameThread != null){

                currentTime=System.nanoTime();
                delta+= (currentTime-lastTime)/drawInterval;
                 timer+=(currentTime-lastTime);
                lastTime=currentTime;
               
                
                if(delta>=1){
                    update();
                    repaint(); 
                   
                    delta--;  
                   // count++;
                }
                if(timer>=1000000000){
                  // System.out.println("FPS: "+count);
                  //  count=0;
                    timer=0;
                }
                


            } 
        }

    public void update(){


        if(gameState == playState){
            //UPDATE PLAYER
            player.update();

            //UPDATE NPC
            for(int i=0;i<npc.length;i++){
                if(npc[i]!=null){
                    npc[i].update();
                }
            }

            //UPDATE MONSTER
            for(int i=0;i<monster.length;i++){
                if(monster[i]!=null){
                    monster[i].update();
                }
            }
            // wolf.update();
            // sheep.update();
        }
        if(gameState == pauseState){
            //pause nothing happens
        }
        // if(keyH.pauseGame){
        //     switch(gameState){
        //         case playState:
        //         gameState=pauseState;
        //         break;
        //         case pauseState:
        //         gameState=playState;
        //         break;
        //     }
        //     keyH.pauseGame=false;
        // }
        
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        
        Graphics2D g2=(Graphics2D) g;
        
        //DEBUG
        long drawStart=0;
        if(keyH.timePressed){
           drawStart=System.nanoTime();
 
        }
        
        //Title Screen
        if(gameState==titleState){
           
            ui.draw(g2);
        }
        //OTHERS
        else{

            //TILE (map)
            tileM.draw(g2);
            
            entityList.add(player);

            //ADD ENTITIES TO THE LIST
            for(int i = 0; i < npc.length; i++){
                if(npc[i]!= null){
                    entityList.add(npc[i]);
                }
            }

            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
            }

            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    entityList.add(monster[i]);
                }
            }

            //SORT
            Collections.sort(entityList, new Comparator<Entity>(){
                @Override
                public int compare(Entity e1, Entity e2){
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            //DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }
            //EMPTY Entity LIST
            entityList.clear();

            //UI 
            ui.draw(g2);
        }

      
   

       

        // sheep.draw(g2);
      //  wolf.draw(g2);
      
      //Map Overlay
      //  tileM2.draw(g2);
        //LAYER 2
       // tileM2.draw(g2);
     
        
       //DEBUG 2
        if(keyH.timePressed){

            long drawEnd=System.nanoTime();
            long end=drawEnd-drawStart;
            int fps=(int) (1000000000/end);
            
            g2.setFont(ui.getFont(1));
            g2.setColor(Color.white);
            g2.drawString("FPS: "+ fps, 1080, 40);
            System.out.println("Draw time -> " + end);
            System.out.println("FPS -> " + fps);
       } 
       //WAIT FOR INTERACTION
        // if(keyH.justDialogued==true){
        //     long dialTimeS=System.nanoTime();

        //     long dialS=(System.nanoTime()-dialTimeS);
        //     int oneM=(int)(100000000/dialS);
          
        //     System.out.println(oneM);

        //    if(oneM==1000){ 
        //     oneM=0;
        //     System.out.println("E gata tati");
        //       keyH.justDialogued=false;
             
        //    }
         
        // }
       
       
       
        //CLEAR
        g2.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
    public void changeMusic(int z){
        music.stop();
        music.setFile(z);
        music.play();
        music.loop();
    }

}
