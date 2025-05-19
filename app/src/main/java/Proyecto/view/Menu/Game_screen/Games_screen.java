package Proyecto.view.Menu.Game_screen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;


public class Games_screen extends JPanel {
    final private JPanel games_Panel, hide,top_Panel, title_Panel, home_Button,home_Panel,game_Panel;
    final private JLabel title;
    final private ArrayList<Game> games_Set;

    
    public Games_screen() {
        
        top_Panel = new JPanel();
        home_Panel = new JPanel();
        game_Panel = new JPanel();
        title_Panel = new JPanel();
        title = new JLabel("<html><h2 style='color: white;'>Your Games</h2></html>");
        
        top_Panel.setOpaque(false);
        top_Panel.setBackground(new Color(16, 44, 85));
        top_Panel.setLayout(new BorderLayout());
        top_Panel.add(home_Panel, BorderLayout.WEST);
        top_Panel.add(game_Panel, BorderLayout.WEST);
        this.add(top_Panel, BorderLayout.NORTH);
        home_Panel.setLayout(new FlowLayout());
        
        home_Button = new home_Button();
        
        home_Button.setPreferredSize(new Dimension(25, 25));
        home_Panel.setBorder(new MatteBorder(0, 0, 0, 2, Color.WHITE));
        home_Panel.add(home_Button);
        home_Panel.setBackground(new Color(16, 44, 85));
        home_Panel.setOpaque(false);
        title_Panel.setOpaque(false);
        title_Panel.add(title);

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(8, 38, 82));
        
        games_Panel = new JPanel();
        hide = new JPanel();

        hide.setOpaque(false);
        hide.add(games_Panel);
        this.add(hide, BorderLayout.CENTER);
        
        games_Set = new ArrayList<>();
        games_Panel.setBackground(new Color(16, 44, 85));
        games_Panel.setLayout(new FlowLayout());
        games_Panel.setPreferredSize(getMaximumSize());
        games_Set.add(new Game("app\\\\src\\\\main\\\\resources\\images\\\\Lemmings.jpg", "Lemmings"));
        games_Set.add(new Game("app\\\\src\\\\main\\\\resources\\images\\\\Pong.jpg", "Pong"));
        
        for (Game g : games_Set) {
            games_Panel.add(g);
        }
    
    }
        public Game getgame(int i) {
        return games_Set.get(i);
        }

        public void closeGame(){
        closeGameFrame close = new closeGameFrame();
        }
        public JPanel getBack_Button(){
            return home_Button;
        }
    }

class closeGameFrame extends JFrame{
private JPanel main_Panel;
final private JLabel title;
   public closeGameFrame(){
        this.setTitle("LLS Games");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        main_Panel.setOpaque(false);
        title = new JLabel("<html><h2 style='color: white;'>Close The Game Before</h2></html>");
        this.add(main_Panel, BorderLayout.CENTER);
        main_Panel.add(title);
        main_Panel.setBackground(new Color(16, 44, 85));
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
        background = new ImageIcon("app\\src\\main\\resources\\images\\games.png").getImage();
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
        background = new ImageIcon("app\\src\\main\\resources\\images\\games.png").getImage();
        this.setOpaque(false); 
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}