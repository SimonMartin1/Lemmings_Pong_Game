package Proyecto.games.Lemmings_game.Controller;
import Proyecto.games.Lemmings_game.View.GameSettingsView;
import Proyecto.games.Lemmings_game.Lemmings;
public class GameSettingsController {
    public GameSettingsController(GameSettingsView view, Lemmings game){
        if(view.isMusicOnClicked(game.getMouse())){
            game.setMusicOFF(false);
        }else if(view.isMusicOffClicked(game.getMouse())){
            game.setMusicOFF(true);
        }
    }
}
