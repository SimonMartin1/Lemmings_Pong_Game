package Proyecto.games.Pong_game.Controller;
import Proyecto.games.Pong_game.Pong;
import com.entropyinteractive.Mouse;
import Proyecto.games.Pong_game.View.GameSettingsView;
import Proyecto.games.Pong_game.Model.SettingsModel;


public class SettingController {
    public SettingController(GameSettingsView view, SettingsModel model,Mouse m, Pong game) {
        
        if(view.isHardClicked(m)){
            view.setDraw();
        }
        if(view.isMediumClicked(m) && m.isLeftButtonPressed()){
            view.setDraw();
        }
        if(view.isEasyClicked(m) && m.isLeftButtonPressed()){
            
        }
        if(view.isTrackNameClicked(m) && view.getTrack()=="Track 1"){
            game.setTrack(2);
        }
        else{
            if(view.isTrackNameClicked(m) && view.getTrack()=="Track 2"){
            game.setTrack(3);
            }
            else{
                    game.setTrack(1);
                }
        }
        }
    }

