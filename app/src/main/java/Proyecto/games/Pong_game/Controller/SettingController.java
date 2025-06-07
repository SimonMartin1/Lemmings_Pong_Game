package Proyecto.games.Pong_game.Controller;
import com.entropyinteractive.Mouse;

import Proyecto.games.Pong_game.Model.SettingsModel;
import Proyecto.games.Pong_game.Model.SettingsModel.Settings;
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
            game.setMusicOFF(true);
        }else if(view.isFullScreenClicked(m)){

        }
        else if(view.isFullScreenOffClicked(m)){
            
        }else if(view.isBallSkinClicked(m)){
            int nextBallskin = 1;
                if (game.getBallSkin() == Proyecto.games.Pong_game.View.BallSkins.NORMAL) {
                    nextBallskin = 2;
                } else if (game.getTrack() == Proyecto.games.Pong_game.Model.Track.TRACK2) {
                    nextBallskin = 3;
                }
                game.setBallSkin(nextBallskin);
                view.setDraw("nextBallSkin");
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
                game.setMusicOFF(false);
            }
        }

        if(view.isSaveClicked(m)){
            game.saveSettings();
            game.setIsinsettings();
        }

        if (view.isResetClicked(m)) {
            game.resetSettings();
            view.setDraw("Reset");
        }
        if(view.isCancelClicked(m)){
            game.setMusicOFF(game.getSettings().musicOff);
            switch(game.backupSettings.track){
                case TRACK1 -> {game.setTrack(0);}
                case TRACK2 -> {game.setTrack(1);}
                case TRACK3 -> {game.setTrack(2);}
            }
            switch(game.backupSettings.difficult){
                case HARD -> {game.setDifficult(2);}
                case MEDIUM -> {game.setDifficult(1);}
                case EASY -> {game.setDifficult(0);}
            }
            switch(game.backupSettings.maxPoints){
                case 5 ->{game.setMaxPoints(0);}
                case 10 ->{game.setMaxPoints(1);}
                case 15 ->{game.setMaxPoints(2);}
            }
            game.setTwoPlayers(game.backupSettings.twoPlayers);

            view.drawHard = game.getBackUpSettings(0);
            view.drawMedium = game.getBackUpSettings(1);
            view.drawEasy = game.getBackUpSettings(2);
            view.drawTwoPlayers = game.getBackUpSettings(3);
            view.drawWin5 = game.getBackUpSettings(4);
            view.drawWin10 = game.getBackUpSettings(5);
            view.drawWin15 = game.getBackUpSettings(6);
            view.drawOff = game.getBackUpSettings(7);
            view.drawTrack = game.getBackUpSettings(8);
        }
    }
}

