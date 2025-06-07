package Proyecto.games.Pong_game.Controller;
import com.entropyinteractive.Mouse;

import Proyecto.games.Pong_game.Model.SettingsModel;
import Proyecto.games.Pong_game.Pong;
import Proyecto.games.Pong_game.View.GameSettingsView;
public class SettingController {
    public SettingController(GameSettingsView view, SettingsModel model, Mouse m,Pong game) {
        if(view.isHardClicked(m)){
            view.setDraw("Hard");
            game.setDifficult(2);
            game.setTwoPlayers(false);
        } else if(view.isMediumClicked(m)){
            view.setDraw("Medium");
            game.setDifficult(1);
            game.setTwoPlayers(false);
        } else if(view.isEasyClicked(m)){
            view.setDraw("Easy");
            game.setTwoPlayers(false);
            game.setDifficult(0);
        } else if(view.isOnClicked(m)){
            view.setDraw("TwoPlayers");
            game.setTwoPlayers(true);
        } else if(view.isWinPoints15Clicked(m)){
            view.setDraw("Win15");
            game.setMaxPoints(2);
        } else if(view.isWinPoints10Clicked(m)){
            view.setDraw("Win10");
            game.setMaxPoints(1);
        } else if(view.isWinPoints5Clicked(m)){
            view.setDraw("Win5");
            game.setMaxPoints(0);
        } else if(view.isOffClicked(m)){
            view.setDraw("Off");
            game.setMusicOFF();
        }
        
        if (view.isTrackNameClicked(m)) {
            if (view.getDrawTrack()) {
                int nextTrack = 1;
                if (game.getTrack() == Proyecto.games.Pong_game.Model.Track.TRACK1) {
                    nextTrack = 2;
                } else if (game.getTrack() == Proyecto.games.Pong_game.Model.Track.TRACK2) {
                    nextTrack = 3;
                }
                game.setTrack(nextTrack);
                view.setDraw("nextTrack");
            } else {
                view.setDraw("Track");
                game.setMusicOFF();
            }
        }

        if(view.isSaveClicked(m)){
            game.saveSettings();
            game.setIsinsettings();
        }

        if (view.isResetClicked(m)) {
            view.setDraw("Reset");
        }
    }
}

