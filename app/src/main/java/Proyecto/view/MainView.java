package Proyecto.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Proyecto.view.Menu.Game_screen.Games_screen;
import Proyecto.view.Menu.Menu_screen;



public class MainView extends JFrame {
    final private CardLayout animation;
    final private Login_screen login_screen;
    final private Games_screen games_screen;
    final private Menu_screen main_screen;
    final private JPanel MainPanel, enter;
    public MainView() {
        this.setTitle("LLS Games");
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        animation = new CardLayout();
        enter = new JPanel();
        enter.setBackground(new Color(0,0,0));
        login_screen = new Login_screen();
        main_screen = new Menu_screen();
        games_screen = new Games_screen();
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
    public Menu_screen getMenuScreen() {
        return main_screen;
    }
    public Menu_screen getMainScreen() {
        return main_screen;
    }
    public Games_screen getGamesScreen() {
        return games_screen;
    }
}


class Login_screen extends JPanel {
    final private Image background;
    public Login_screen() {
        background = new ImageIcon("app\\src\\main\\resources\\Login.png").getImage();
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}










