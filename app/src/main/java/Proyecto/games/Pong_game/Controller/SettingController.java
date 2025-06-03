package Proyecto.games.Pong_game.Controller;

import javax.swing.text.View;
import com.entropyinteractive.Mouse;
import Proyecto.games.Pong_game.View.GameSettingsView;
import Proyecto.games.Pong_game.Model.SettingsModel;


public class SettingController {
    public SettingController(GameSettingsView view, SettingsModel model,int width, int height, Mouse m) {
        
        if(view.selectDifficulty()==1 && m.isLeftButtonPressed()){

        }
    }

    
}
