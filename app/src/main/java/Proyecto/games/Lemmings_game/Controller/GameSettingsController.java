package Proyecto.games.Lemmings_game.Controller;
import Proyecto.games.Lemmings_game.View.GameSettingsView;
import Proyecto.utils.SoundPlayer;
import Proyecto.games.Lemmings_game.Lemmings;
public class GameSettingsController {
    public GameSettingsController(GameSettingsView view, Lemmings game){
        if(view.isMusicOnClicked(game.getMouse())){
            view.setDraw("On");
            game.setMusicOFF(false);
        }else if(view.isMusicOffClicked(game.getMouse())){
            view.setDraw("Off");
            game.setMusicOFF(true);
            game.playTrack();
            SoundPlayer.stopSound();
        }if(view.isFullScreenClicked(game.getMouse())){
            view.setDraw("fullscreen");
            Lemmings.setFullScreen(true);
        }else if(view.isFullScreenOffClicked(game.getMouse())){
            view.setDraw("fullscreenOff");
            Lemmings.setFullScreen(false);
        }if(view.isSaveClicked(game.getMouse())){
            game.saveSettings();
            game.setIsinsettings();
        }if(view.isResetClicked(game.getMouse())){
            view.setDraw("reset");
            game.setMusicOFF(false);
            Lemmings.setFullScreen(false);
        }if(view.isCancelClicked(game.getMouse())){
            if(game.getbackUp().musicOff){
                view.setDraw("Off");
            }
            else{
                view.setDraw("On");
            }
            if(game.getbackUp().fullScreen){
                view.setDraw("fullscreen");
            }
            else{
                view.setDraw("fullscreenOff");
            }
            game.setMusicOFF(game.getbackUp().musicOff);
            Lemmings.setFullScreen(game.getbackUp().fullScreen);
        }
    }
}
