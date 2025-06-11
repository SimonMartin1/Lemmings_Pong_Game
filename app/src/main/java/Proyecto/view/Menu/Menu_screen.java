package Proyecto.view.Menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Proyecto.view.Menu.Buttons.game_Button;
import Proyecto.view.Menu.Buttons.home_Button;


public class Menu_screen extends JPanel {
    final private JPanel main_Panel,nav_Panel,title_Panel,about_Panel,home_Panel,game_Panel, settings_Panel;
    final public JPanel home_Button,games_Button;
    final private JLabel title,about;
    public Menu_screen() {
        main_Panel = new JPanel(new BorderLayout());
        about_Panel = new JPanel();
        nav_Panel = new JPanel(new GridBagLayout());
        title_Panel = new JPanel();
        home_Panel = new JPanel();
        game_Panel = new JPanel();
        settings_Panel = new JPanel();
        home_Button = new home_Button("app\\src\\main\\resources\\images\\home_white.png");
        games_Button = new game_Button("app\\src\\main\\resources\\images\\games_blue.png");
        title = new JLabel("<html><h2 style='color: white;'>About</h2></html>");
        about = new JLabel("<html><h2 style='color: white;'>This app is a project for the POO <br> subjetc of engineering unlpam &copy </h2></html>");

        home_Button.setPreferredSize(new Dimension(25, 25));
        games_Button.setPreferredSize(new Dimension(27, 25));

        home_Panel.add(home_Button);
        home_Panel.setOpaque(false);
        game_Panel.add(games_Button);
        game_Panel.setOpaque(false);        
        settings_Panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTH; 
        
        gbc.gridy = 0;
        gbc.insets = new Insets(60, 10, 40, 10); 
        nav_Panel.add(home_Panel, gbc);
        
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 40, 10);
        nav_Panel.add(game_Panel, gbc);

        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;

        nav_Panel.add(Box.createVerticalGlue(), gbc);
        nav_Panel.setBackground(new Color(34, 35, 37));
        
        title_Panel.add(title);
        title_Panel.setOpaque(false);
        
        main_Panel.add(title_Panel, BorderLayout.NORTH);
        main_Panel.setOpaque(false);
        main_Panel.add(about_Panel, BorderLayout.CENTER);

        about_Panel.add(about);
        about_Panel.setOpaque(false);

        this.setLayout(new BorderLayout());
        this.add(nav_Panel, BorderLayout.WEST);
        this.add(main_Panel, BorderLayout.CENTER);
        this.setBackground(new Color(14, 15, 17));


        
    }
    public JPanel getSettings_Button() {
        return settings_Panel;
    }
    public JPanel getGames_Button() {
        return game_Panel;
    }
}
