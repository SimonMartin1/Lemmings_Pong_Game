package Proyecto.view.Menu.Game_screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Proyecto.view.Menu.Buttons.game_Button;
import Proyecto.view.Menu.Buttons.home_Button;
import Proyecto.view.Menu.Buttons.settings_Button;


public class Games_screen extends JPanel {
    final private JPanel games_Panel, hide,title_Panel,home_Panel,game_Panel,setting_Panel,nav_Panel;
    final private JPanel home_Button,game_Button,setting_Button;
    final private JLabel title;
    final private ArrayList<Game> games_Set;

    public Games_screen() {

        home_Panel = new JPanel();
        game_Panel = new JPanel();
        setting_Panel = new JPanel();
        nav_Panel = new JPanel(new GridBagLayout());
        title_Panel = new JPanel();
        title = new JLabel("<html><h2 style='color: white;'>Your Games</h2></html>");
        
        home_Button = new home_Button("app\\src\\main\\resources\\images\\home.png");
        game_Button = new game_Button("app\\src\\main\\resources\\images\\games_white.png");
        setting_Button = new settings_Button("app\\src\\main\\resources\\images\\settings.png");

        
        home_Button.setPreferredSize(new Dimension(25, 25));
        game_Button.setPreferredSize(new Dimension(25, 25));
        setting_Button.setPreferredSize(new Dimension(25, 25));

        home_Panel.add(home_Button);
        home_Panel.setOpaque(false);
        game_Panel.add(game_Button);
        game_Panel.setOpaque(false);
        setting_Panel.add(setting_Button);
        setting_Panel.setOpaque(false);

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
        gbc.insets = new Insets(0, 10, 0, 10);
        nav_Panel.add(setting_Panel, gbc);
        
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;

        nav_Panel.add(Box.createVerticalGlue(), gbc);
        nav_Panel.setBackground(new Color(34, 35, 37));


        games_Panel = new JPanel();
        hide = new JPanel();

        hide.setOpaque(false);
        hide.add(games_Panel);
        
        games_Set = new ArrayList<>();
        games_Panel.setBackground(new Color(14, 15, 17));
        games_Panel.setLayout(new FlowLayout());
        games_Panel.setPreferredSize(getMaximumSize());
        games_Set.add(new Game("app\\\\src\\\\main\\\\resources\\images\\\\Lemmings.jpg", "Lemmings"));
        games_Set.add(new Game("app\\\\src\\\\main\\\\resources\\images\\\\Pong.jpg", "Pong"));
        
        for (Game g : games_Set) {
            games_Panel.add(g);
        }
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(14, 15, 17));
        this.add(nav_Panel, BorderLayout.WEST);
        this.add(hide, BorderLayout.CENTER);
    
    }
        public Game getgame(int i) {
        return games_Set.get(i);
        }


        public JPanel getHome_Button(){
            return home_Panel;
        }

        public JPanel getSettings_Button(){
            return setting_Panel;
        }
}
