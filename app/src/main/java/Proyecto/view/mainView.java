package Proyecto.View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;




public class MainView extends JFrame {
    final private CardLayout animation;
    final private login_screen login_screen;
    final private games_screen games_screen;
    final private main_screen main_screen;
    final private JPanel MainPanel, enter;
    public MainView() {
        this.setTitle("LLS Games");
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        animation = new CardLayout();
        enter = new JPanel();
        enter.setBackground(new Color(0,0,0));
        login_screen = new login_screen();
        main_screen = new main_screen();
        games_screen = new games_screen();
        MainPanel = new JPanel(animation);
        MainPanel.add(enter, "Enter");
        MainPanel.add(login_screen, "sLogin");
        MainPanel.add(main_screen, "sMain");
        MainPanel.add(games_screen, "sGames");
        this.add(MainPanel);
        this.setVisible(true);

        Timer timer1 = new Timer(2200, e -> {
            this.showScreen("sLogin");
            // After 2 seconds, show the main screen
            Timer timer2 = new Timer(2200, e2 -> this.showScreen("sMain"));
            timer2.setRepeats(false);
            timer2.start();
        });
        timer1.setRepeats(false);
        timer1.start();
    }
    public void showScreen(String screenName) {
        animation.show(MainPanel, screenName);
    }
    public JButton getGamesButton() {
        return main_screen.getGamesButton();
    }
    public JButton getConfigButton() {
        return main_screen.getConfigButton();
    }

}


class login_screen extends JPanel {
    final private Image background;
    public login_screen() {
        background = new ImageIcon("C:\\Users\\MARTIN\\Documents\\Facultad\\3 Año\\1 Cuatrimestre\\POO\\Proyecto\\app\\src\\main\\resources\\login.png").getImage();
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}

class main_screen extends JPanel {
    final private JPanel main_Panel, games_Panel, config_Panel;
    final private JButton games_Button, config_Button;
    public main_screen() {
        main_Panel = new JPanel();
        games_Panel = new JPanel();
        config_Panel = new JPanel();
        games_Button = new JButton("Games");
        config_Button = new JButton("Configuration");

        this.setLayout(new BorderLayout());
        this.add(main_Panel,BorderLayout.CENTER);
        main_Panel.setLayout(new GridLayout(3,1,5,5));
        main_Panel.add(new JPanel());
        main_Panel.add(games_Panel);
        main_Panel.add(config_Panel);
        games_Panel.add(games_Button);
        games_Panel.setPreferredSize(new Dimension(50, 50));
        config_Panel.add(config_Button);
        
        games_Button.setOpaque(false);
        games_Button.setContentAreaFilled(false);
        games_Button.setBorderPainted(false);
        games_Button.setFocusPainted(false);

        config_Button.setOpaque(false);
        config_Button.setContentAreaFilled(false);
        config_Button.setBorderPainted(false);
        config_Button.setFocusPainted(false);

        games_Button.setForeground(Color.BLACK); // normal color
        games_Button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                games_Button.setForeground(Color.WHITE); // hover color
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                games_Button.setForeground(Color.BLACK); // normal color
            }
        });

        config_Button.setForeground(Color.BLACK);
        config_Button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                config_Button.setForeground(Color.WHITE);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                config_Button.setForeground(Color.BLACK);
            }
        });
        
    }
    public JButton getGamesButton() {
        return games_Button;
    }
    public JButton getConfigButton() {
        return config_Button;
    }
}



class games_screen extends JPanel {
    final private JPanel games_Panel, hide,top_Panel, title_Panel;
    final private JLabel title;
    final private ArrayList<game> games_Set;

    public games_screen() {
        games_Panel = new JPanel();
        hide = new JPanel();
        top_Panel = new JPanel();
        title_Panel = new JPanel();
        title = new JLabel("<html><h2 style='color: white;'>Tus Juegos</h2></html>");
        games_Set = new ArrayList<>();
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(8, 38, 82)); 
        top_Panel.setOpaque(false);
        this.add(top_Panel, BorderLayout.NORTH);
        top_Panel.setLayout(new BorderLayout());
        top_Panel.add(title_Panel, BorderLayout.WEST);
        title_Panel.setOpaque(false);
        title_Panel.add(title);
        hide.setOpaque(false);
        games_Panel.setBackground(new Color(16, 44, 85));
        hide.add(games_Panel);
        this.add(hide, BorderLayout.CENTER);
        games_Panel.setLayout(new FlowLayout());
        games_Panel.setPreferredSize(getMaximumSize()); 
        games_Set.add(new game("c:\\\\Users\\\\MARTIN\\\\Documents\\\\Facultad\\\\3 Año\\\\1 Cuatrimestre\\\\POO\\\\Practica\\\\Practico6\\\\Lemmings.jpg", "Lemmings"));
        games_Set.add(new game("c:\\\\Users\\\\MARTIN\\\\Documents\\\\Facultad\\\\3 Año\\\\1 Cuatrimestre\\\\POO\\\\Practica\\\\Practico6\\\\pong.jpg", "Pong"));
        for (game g : games_Set) {
            games_Panel.add(g);
        }
    }
    
}

//Game class represents a game in the game menu, that allows extensibility
//Each game has a text and a image
class game extends JPanel {
    public static int cant = 0;
    final private JPanel image;
    private JLabel name_Label;
    
    private class image_Panel extends JPanel {
        final private Image icono;
        final private JPanel text_Panel;
        image_Panel(String url, String nombre) {
            text_Panel = new JPanel();
            name_Label = new JLabel(nombre);
            name_Label.setForeground(Color.WHITE);
            icono = new ImageIcon(url).getImage();
            this.setLayout(new BorderLayout());
            this.setPreferredSize(new Dimension(150, 175));
            this.setOpaque(false);
            this.add(text_Panel,BorderLayout.SOUTH);
            text_Panel.setBackground(new Color(8, 38, 82));
            text_Panel.add(name_Label);
            text_Panel.setPreferredSize(new Dimension(150, 50));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(icono, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public game(String url, String nombre) {
        image = new image_Panel(url, nombre);
        //this.Layout();
        this.setPreferredSize(new Dimension(175, 250)); 
        JPanel image_Panel = new JPanel();
        image_Panel.add(image);
        image_Panel.setOpaque(false);
        this.add(image_Panel, BorderLayout.CENTER);
        this.setOpaque(false);
        cant++;
    }

    // public void Layout() {
    //     this.setPreferredSize(new Dimension(175, 250)); 
    //     JPanel image_Panel = new JPanel();
    //     image_Panel.add(image);
    //     image_Panel.setOpaque(false);
    //     this.add(image_Panel, BorderLayout.CENTER);
    //     this.setOpaque(false);
    // }
}









