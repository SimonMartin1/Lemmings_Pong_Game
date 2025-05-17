package Proyecto.controller;
import Proyecto.model.MainModel;
import Proyecto.view.MainView;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainController implements ActionListener {
    final private MainModel model;
    final private MainView view;

    public MainController(MainModel model, MainView view) {
        this.model = model;
        this.view = view;
        // Listeners are registered after construction
        view.getGamesButton().addActionListener(this);
        view.getConfigButton().addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks and other actions here
        if(e.getSource() == view.getGamesButton()){
            view.showScreen("sGames");
            // Handle settings button action
        } 
    }
}
