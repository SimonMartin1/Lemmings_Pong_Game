package Proyecto.controller;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

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

        view.getGamesScreen().getgame(0).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                
                if(model.getRuntimegame() == null){
                model.runGame(0);
                }
                else{
                    notifyCloseGame();
                }
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                view.getGamesScreen().getgame(0).getImagePanel().setBorder(new LineBorder(Color.WHITE, 2));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
            view.getGamesScreen().getgame(0).getImagePanel().setBorder(new LineBorder(new Color(0,0,0,0), 2));
                }
        });

        view.getGamesScreen().getgame(1).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if(model.getRuntimegame() == null){
                    model.runGame(1);
                }
                else{
                    notifyCloseGame();
                }
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                view.getGamesScreen().getgame(1).getImagePanel().setBorder(new LineBorder(Color.WHITE, 2));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
            view.getGamesScreen().getgame(1).getImagePanel().setBorder(new LineBorder(new Color(0,0,0,0), 2));
                }
        });

        view.getGamesScreen().getBack_Panel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                view.showScreen("sMain");
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
