package Proyecto.games.Lemmings_game.Controller;
import Proyecto.games.Lemmings_game.View.GameSettingsView;
import Proyecto.games.Lemmings_game.Lemmings;
public class GameSettingsController {
    public GameSettingsController(GameSettingsView view, Lemmings game){
        if(view.isMusicOnClicked(game.getMouse())){
            game.setMusicOFF(false);
        }else if(view.isMusicOffClicked(game.getMouse())){
            game.setMusicOFF(true);
        }if(view.isFullScreenClicked(game.getMouse())){
            Lemmings.setFullScreen(true);
        }else if(view.isFullScreenOffClicked(game.getMouse())){
            Lemmings.setFullScreen(false);
        }if(view.isSaveClicked(game.getMouse())){
            game.saveSettings();
            game.setIsinsettings();
            System.out.println("Funca");
        }
    }
}
