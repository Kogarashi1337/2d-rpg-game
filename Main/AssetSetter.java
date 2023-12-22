package Main;

import Entity.NPC_OldMan;

// import object.OBJ_GChest;
// import object.OBJ_Boots;
// import object.OBJ_Door;
// import object.OBJ_GoldKey;
// import object.OBJ_Key;
// import object.OBJ_SilverKey;
// import object.OBJ_SChest;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }

    public void setObject(){

        //BETA VERSION
        // gp.obj[0]=new OBJ_SilverKey(gp);
        // gp.obj[0].worldX=43*gp.tileSize;
        // gp.obj[0].worldY=8*gp.tileSize;


        // gp.obj[1]=new OBJ_GoldKey();
        // gp.obj[1].worldX=26*gp.tileSize;
        // gp.obj[1].worldY=10*gp.tileSize;


        // gp.obj[2]=new OBJ_GChest(gp);
        // gp.obj[2].worldX=22*gp.tileSize;
        // gp.obj[2].worldY=19*gp.tileSize;

        // gp.obj[3]=new OBJ_Door(gp);
        // gp.obj[3].worldX=43*gp.tileSize;
        // gp.obj[3].worldY=6*gp.tileSize;

        // gp.obj[4]=new OBJ_Door(gp);
        // gp.obj[4].worldX=22*gp.tileSize;
        // gp.obj[4].worldY=21*gp.tileSize;

        // gp.obj[5]=new OBJ_Door(gp);
        // gp.obj[5].worldX=11*gp.tileSize;
        // gp.obj[5].worldY=4*gp.tileSize;

        // gp.obj[6]=new OBJ_Door(gp);
        // gp.obj[6].worldX=24*gp.tileSize;
        // gp.obj[6].worldY=6*gp.tileSize;
    
        // gp.obj[7]=new OBJ_Door(gp);
        // gp.obj[7].worldX=33*gp.tileSize;
        // gp.obj[7].worldY=12*gp.tileSize;

        // gp.obj[8]=new OBJ_Door(gp);
        // gp.obj[8].worldX=27*gp.tileSize;
        // gp.obj[8].worldY=13*gp.tileSize;

        // gp.obj[9]=new OBJ_SChest(gp);
        // gp.obj[9].worldX=25*gp.tileSize;
        // gp.obj[9].worldY=10*gp.tileSize;

        // gp.obj[10]=new OBJ_Key(gp);
        // gp.obj[10].worldX=6*gp.tileSize;
        // gp.obj[10].worldY=9*gp.tileSize;

        // gp.obj[11]=new OBJ_Key(gp);
        // gp.obj[11].worldX=33*gp.tileSize;
        // gp.obj[11].worldY=7*gp.tileSize;

        // gp.obj[12]=new OBJ_Key(gp);
        // gp.obj[12].worldX=31*gp.tileSize;
        // gp.obj[12].worldY=7*gp.tileSize;

        // gp.obj[13]=new OBJ_Key(gp);
        // gp.obj[13].worldX=25*gp.tileSize;
        // gp.obj[13].worldY=16*gp.tileSize;

        // gp.obj[14]=new OBJ_Key(gp);
        // gp.obj[14].worldX=25*gp.tileSize;
        // gp.obj[14].worldY=19*gp.tileSize;

        // gp.obj[15]=new OBJ_Key(gp);
        // gp.obj[15].worldX=11*gp.tileSize;
        // gp.obj[15].worldY=2*gp.tileSize;

      
        
    
    }
    public void setNPC(){
        gp.npc[0]=new NPC_OldMan(gp);
        gp.npc[0].worldX=gp.tileSize*35;
        gp.npc[0].worldY=gp.tileSize*27;

    }

}
