package Proyecto.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Proyecto.model.MainModel;
import Proyecto.view.MainView;


public class MainController implements ActionListener {
    final private MainModel model;
    final private MainView view;

    public MainController(MainModel model, MainView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    public void initController() {
        view.getMainScreen().games_Button.addActionListener(this);
        view.getMainScreen().config_Button.addActionListener(this);

        view.getGamesScreen().getgame(0).addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                if(model.getRuntimegame() == null){
                model.runGame(0);
                }
                else{
                    notifyCloseGame();
                }
            }
        });
        view.getGamesScreen().getgame(1).addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(model.getRuntimegame() == null){
                    model.runGame(1);
                }
                else{
                    notifyCloseGame();
                }
            }
        });
    }

    public void notifyCloseGame(){
        view.getGamesScreen().closeGame();
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
