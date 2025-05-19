package Proyecto.view.Menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import Proyecto.view.Menu.Buttons.game_Button;
import Proyecto.view.Menu.Buttons.home_Button;
import Proyecto.view.Menu.Buttons.settings_Button;

public class Menu_screen extends JPanel {
    final private JPanel main_Panel, games_Panel;
    final public JPanel home_Button,games_Button, settings_Button;
    public Menu_screen() {
        main_Panel = new JPanel(new GridBagLayout());
        games_Panel = new JPanel();
        home_Button = new home_Button("app\\src\\main\\resources\\images\\home_white.png");
        games_Button = new game_Button("app\\src\\main\\resources\\images\\games.png");
        settings_Button = new settings_Button("app\\src\\main\\resources\\images\\settings.png");

        this.setLayout(new BorderLayout());
        this.add(main_Panel, BorderLayout.WEST);
        this.setBackground(new Color(34, 35, 37));


        
    }
    public JPanel getConfig_Button() {
        return settings_Button;
    }
    public JPanel getGames_Button() {
        return games_Button;
    }
}

        // games_Panel.setOpaque(false);
        // config_Panel.setOpaque(false);
        // main_Panel.setOpaque(false);

        // games_Panel.add(games_Button);
        // config_Panel.add(config_Button);

        // games_Button.setOpaque(false);
        // games_Button.setContentAreaFilled(false);
        // games_Button.setBorderPainted(false);
        // games_Button.setFocusPainted(false);

        // config_Button.setOpaque(false);
        // config_Button.setContentAreaFilled(false);
        // config_Button.setBorderPainted(false);
        // config_Button.setFocusPainted(false);

        // games_Button.setForeground(Color.BLACK);
        

        // java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        // gbc.gridx = 0;
        // gbc.fill = java.awt.GridBagConstraints.NONE;
        // gbc.anchor = java.awt.GridBagConstraints.NORTH; 
        // gbc.insets = new java.awt.Insets(60, 0, 40, 0); 

        // gbc.gridy = 0;
        // main_Panel.add(games_Panel, gbc);
        // gbc.gridy = 1;
        // gbc.insets = new java.awt.Insets(0, 0, 0, 0);
        // main_Panel.add(config_Panel, gbc);

        // gbc.gridy = 2;
        // gbc.weighty = 1.0;
        // gbc.fill = java.awt.GridBagConstraints.VERTICAL;
        // main_Panel.add(javax.swing.Box.createVerticalGlue(), gbc);