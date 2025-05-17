package Proyecto.Controller;
import Proyecto.Model.MainModel;
import Proyecto.View.MainView;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainController implements ActionListener {
    final private MainModel model;
    final private MainView view;

    public MainController(MainModel model, MainView view) {
        this.model = model;
        this.view = view;
        
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
