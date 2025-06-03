package Proyecto.games.Pong_game.Controller;
import Proyecto.games.Pong_game.Pong;
import javax.swing.text.View;
import com.entropyinteractive.Mouse;
import Proyecto.games.Pong_game.View.GameSettingsView;
import Proyecto.games.Pong_game.Model.SettingsModel;


public class SettingController {
    public SettingController(GameSettingsView view, SettingsModel model,Mouse m, Pong juego) {
        
        if(view.selectDifficulty()==1 && m.isLeftButtonPressed()){
            
        }
        if(view.selectDifficulty()==2 && m.isLeftButtonPressed()){
            
        }
        if(view.selectDifficulty()==3 && m.isLeftButtonPressed()){
            
        }
    }

    
}
