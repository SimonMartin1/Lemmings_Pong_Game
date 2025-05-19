package Proyecto.view.Menu.Game_screen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;


public class Games_screen extends JPanel {
    final private JPanel games_Panel, hide,top_Panel, title_Panel,home_Panel,game_Panel,setting_Panel,nav_Panel;
    final private JPanel home_Button,game_Button,setting_Button;
    final private JLabel title;
    final private ArrayList<Game> games_Set;

    public Games_screen() {

        top_Panel = new JPanel();
        home_Panel = new JPanel();
        game_Panel = new JPanel();
        setting_Panel = new JPanel();
        nav_Panel = new JPanel();
        title_Panel = new JPanel();
        title = new JLabel("<html><h2 style='color: white;'>Your Games</h2></html>");
        
        home_Button = new home_Button();
        game_Button = new game_Button();
        setting_Button = new setting_Button();

        nav_Panel.setLayout(new FlowLayout());
        nav_Panel.add(home_Panel);
        nav_Panel.add(game_Panel);
        nav_Panel.add(setting_Panel);
        nav_Panel.setOpaque(false);
        
        top_Panel.setBackground(new Color(25, 25, 25));
        top_Panel.setLayout(new BorderLayout());
        top_Panel.add(nav_Panel, BorderLayout.WEST);

        home_Button.setPreferredSize(new Dimension(20, 20));
        home_Panel.setBorder(new MatteBorder(0, 0, 0, 2, new Color(35, 35, 35)));
        home_Panel.add(home_Button);
        home_Panel.setOpaque(false);

        game_Button.setPreferredSize(new Dimension(24, 20));
        game_Panel.setBorder(new MatteBorder(0, 0, 0, 2, new Color(35, 35, 35)));
        game_Panel.add(game_Button);
        game_Panel.setOpaque(false);

        setting_Button.setPreferredSize(new Dimension(20, 20));
        setting_Panel.add(setting_Button);
        setting_Panel.setOpaque(false);
        
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
        this.add(top_Panel, BorderLayout.NORTH);
        this.add(hide, BorderLayout.CENTER);
    
    }
        public Game getgame(int i) {
        return games_Set.get(i);
        }


        public JPanel getHome_Button(){
            return home_Button;
        }

        public JPanel getSettings_Button(){
            return setting_Button;
        }
}
class home_Button extends JPanel{
    final private Image background;
    public home_Button(){
        background = new ImageIcon("app\\src\\main\\resources\\images\\home.png").getImage();
        this.setOpaque(false); 
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}

class game_Button extends JPanel{
    final private Image background;
    public game_Button(){
        background = new ImageIcon("app\\src\\main\\resources\\images\\games_white.png").getImage();
        this.setOpaque(false); 
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}

class setting_Button extends JPanel{
    final private Image background;
    public setting_Button(){
        background = new ImageIcon("app\\src\\main\\resources\\images\\settings.png").getImage();
        this.setOpaque(false); 
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}