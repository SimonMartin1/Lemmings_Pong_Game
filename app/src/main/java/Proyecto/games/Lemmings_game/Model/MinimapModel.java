package Proyecto.games.Lemmings_game.Model;
import Proyecto.games.Lemmings_game.View.LevelView;
import Proyecto.games.Lemmings_game.View.MapView;

public class MinimapModel {
    private MapView mapView;
    private LevelView levelView;
    private LevelModel levelModel;
    private CursorModel cursorModel;
    private int mapWidth = 450 ;
    private int mapHeight = 1536;
    private int minimapWidth = 250;
    private int minimapHeight = 100;
    private int minimapX = 480;
    private int minimapY = 480;

    public MinimapModel(MapView mapView, LevelView levelView, LevelModel levelModel) {
        this.mapView = mapView;
        this.levelView = levelView; 
        this.levelModel = levelModel;
        //this.cursorModel = cursorModel;
    }

    public void setNewCam(int clickX, int clickY){

        float scaleX = (float) mapWidth / minimapWidth;
        float scaleY = (float) mapHeight / minimapHeight;

        int worldX = (int) ((clickX - minimapX) * scaleX);
        //int worldY = (int) ((clickY - minimapY) * scaleY);

        System.out.println("worldX: " + worldX);

        mapView.setCameraPosition(worldX,0);
        levelView.setCamX(worldX);  
        levelModel.setCamX(worldX);
        
        
        //cursorModel.setCamX(worldX);
    }
    
    public void handleClick(int x, int y){
        setNewCam(x, y);
    }

}
