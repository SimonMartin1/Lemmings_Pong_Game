package Proyecto.Controller;
import Proyecto.Model.MainModel;
import Proyecto.View.MainView;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Proyecto.View.Menu.Main_screen;
import Proyecto.View.Menu.Game_screen.Games_screen;

public class MainController implements ActionListener {
    final private MainModel model;
    final private MainView view;

    public MainController(MainModel model, MainView view) {
        this.model = model;
        this.view = view;
        // Listeners are registered after construction
        view.getMainScreen().games_Button.addActionListener(this);
        view.getMainScreen().config_Button.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks and other actions here
        if(e.getSource() == view.getMainScreen().games_Button){
            view.showScreen("sGames");
            // Handle settings button action
        } 
    }
}
