package Proyecto.controller;
import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;

import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import Proyecto.model.MainModel;
import Proyecto.view.MainView;





public class MainController {
    final private MainModel model;
    final private MainView view;

    public MainController(MainModel model, MainView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    public void initController() {
        
        //Menu screen
        view.getMenuScreen().getGames_Button().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                view.showScreen("Games");
            }
            // @Override
            // public void mouseEntered(java.awt.event.MouseEvent evt) {
                
            // }
            // @Override
            // public void mouseExited(java.awt.event.MouseEvent evt) {
            
            //     }
        });

        view.getMenuScreen().getSettings_Button().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                view.showScreen("Settings");
            }
            // @Override
            // public void mouseEntered(java.awt.event.MouseEvent evt) {
                
            // }
            // @Override
            // public void mouseExited(java.awt.event.MouseEvent evt) {
            
            //     }
        });


        // Games screen

        view.getGamesScreen().getHome_Button().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
            view.showScreen("Home");
            }
        });

        view.getGamesScreen().getSettings_Button().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
            view.showScreen("Settings");
            }
        });

        view.getGamesScreen().getgame(0).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {     
                SwingUtilities.invokeLater(() -> {
                        model.runGame(0);
                });
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
                    SwingUtilities.invokeLater(() -> {
                        model.runGame(1);
                    });
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


        //Settinngs screen
        view.getSettingsScreen().getHome_Button().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                view.showScreen("Home");
            }
        });

        view.getSettingsScreen().getGames_Button().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                view.showScreen("Games");
            }
            // @Override
            // public void mouseEntered(java.awt.event.MouseEvent evt) {
                
            // }
            // @Override
            // public void mouseExited(java.awt.event.MouseEvent evt) {
            
            //     }
        });
        
    }

}
