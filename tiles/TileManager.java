package tiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;

import java.awt.Graphics2D;
// import java.awt.image.BufferedImage;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    ArrayList<String> fileNames=new ArrayList<>();
    ArrayList<String> collisionStatus=new ArrayList<>();
    // ArrayList<String> objectChecker=new ArrayList<>();
    // public static int Coordinates[][];

    public TileManager(GamePanel gp){

        this.gp=gp;
        // int Coordinates[][]=new int[2][10];
        //Read tileData file
        InputStream is=getClass().getResourceAsStream("/Maps/tile_03.txt");
        BufferedReader br=new BufferedReader(new InputStreamReader(is));

        //Getting tile names and collision types from the file
        String line;
        try {
            while((line = br.readLine())!=null){
                fileNames.add(line);
                collisionStatus.add(br.readLine());

              
            }
             br.close(); 
        } catch (IOException e) {
 
            e.printStackTrace();
        }

        //Initialise the file array based on the fileNames size 
        tile=new Tile[fileNames.size()];
        getTileImage();

        //Getting the maxrow and maxcol of the map
        is = getClass().getResourceAsStream("/Maps/map_03.txt");
        br = new BufferedReader(new InputStreamReader(is));

        try {
            
            String line2 = br.readLine();
            String maxTile[]=line2.split(" ");
            // String check;
            gp.maxWorldCol=maxTile.length;
            gp.maxWorldRow=maxTile.length;
            // while ((check = br.readLine())!= null) {
               
            //     objectChecker.add(check);
            // }
            br.close();
        } catch (IOException e) {
            System.out.println("\nException!!!\n");
        }
        
        mapTileNum=new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/Maps/map_03.txt");

        // checkTheObj();
        //loadMap("/Maps/Map_01.txt");
        // loadMap("/Maps/Map_04.txt");
    }

    //Second Layer of tiles
    // public TileManager(GamePanel gp, int layer){

    //     this.gp=gp;
    //     tile=new Tile[2];
    //     mapTileNum=new int[gp.maxWorldCol][gp.maxWorldRow];
    //     getTileImage(layer);
    //     //loadMap("/Maps/Map_01.txt");
    //     loadMap("/Maps/Map_02.txt");
    // }

        // public void checkTheObj(){
        //     int hit=0;
        //     for (int i = 0; i < objectChecker.size(); i++) {
        //         String check;
                
        //         check = objectChecker.get(i);
                
               
        //         String Strikes[]=check.split(" ");
        //         for(int j=0; j < Strikes.length;j++){
                    
        //             if(Strikes[j].equals("13")){
        //                 Coordinates[0][hit]=j;
        //                 Coordinates[1][hit]=i;
        //                 hit++;
        //             }
        //         }
           
        //     }
        // }


    public void getTileImage(){

        for(int i=0; i < fileNames.size(); i++){

            String fileName;
            boolean collision;

            //Getting the name (type of tile) from the filename 
            fileName = fileNames.get(i);
            
            //Chest position detection
            
            //getting the collision type from the file by converting from string type(false/true)
            if(collisionStatus.get(i).equals("true")){
                collision=true;
            
            }else{
                collision=false;
            }
        setup(i,fileName,collision);
        }

        // setup(0, "grass", false);

        // setup(1, "wall", true);
            
        // setup(8, "floor", false);

        // setup(7, "floor_light1", false);

        // setup(6, "floor_door", false);
            
        // setup(9, "floor_door_up", false);

        // setup(2, "water", true);

        // setup(10, "water2", true);

        // setup(3, "earth1", false);
            
        // setup(4, "tree", true);

        // setup(5, "sand", false);
            
        // setup(11, "openChest_s", true);

        // setup(12, "openChest_g", true);
        

       
    }
    //  public void getTileImage(int layer){
    //     setup(0, "empty", false);

    //     setup(1, "grass_high", false);

    // }

    public void setup(int index,String imageName, boolean collision){
        UtilityTool uTool=new UtilityTool();
        try {
            tile[index]= new Tile();
            tile[index].image=ImageIO.read(getClass().getResourceAsStream("/Game_tiles_texture/"+imageName));
            tile[index].image=uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision=collision;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    public void loadMap(String filepath){
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
            int col=0;
            int row=0;
           
            while(col<gp.maxWorldCol && row<gp.maxWorldRow){
                
                String line = br.readLine();
                
                while(col<gp.maxWorldCol){
                    
                    String numbers[]=line.split(" ");

                    int num=Integer.parseInt(numbers[col]);

                    mapTileNum[col][row]=num;
                 

                    col++;
                }

                if(col==gp.maxWorldCol){
                    col=0;
                    row++;
                }

            }
            br.close();
        } catch (Exception e) {
         
       
        }
    
    
    
    }



    public void draw(Graphics2D g2){
       
        int worldCol=0;
        int worldRow=0;
        double nMoves=0;
        while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow){
           
           // g2.drawImage(tile[0].image,x ,y , gp.tileSize,gp.tileSize,null);
            int tileNum=mapTileNum[worldCol][worldRow];

            int worldX=worldCol*gp.tileSize;
            int worldY=worldRow*gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            //   nMoves++;    
            if(worldX + gp.tileSize>gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize<gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize>gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize<gp.player.worldY + gp.player.screenY){
                


                    // if(nMoves>1){
                
                    //     if(tileNum==2){
                    //         tileNum=10;
                    //     }else if(tileNum==10){
                    //         tileNum=2;
                    //     }
                    //     nMoves=0;
                    
                    // }
                    // nMoves++;            
              
                




                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
               
                
                

            
                
       
            }

            worldCol++;
           
           
            if(worldCol==gp.maxWorldCol){
               
                worldCol=0;
                worldRow++;
             
            }

        
        }





     // 
       
    }


}
