package Proyecto.games.Lemmings_game.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class config_screen extends JPanel{

    final private JCheckBox fullScreeen,activateSound;
    final private JComboBox<String> musicTrack,characterSkin;
    final private JButton saveBtn, cancelBtn, resetBtn;
    final private JPanel main_Panel,bottom_Panel,checkbox_Panel,music_Panel,char_Panel,teclas_Panel;
    final private JLabel teclas;
    final Image background;

    public config_screen() {
        saveBtn = new JButton("Save");
        cancelBtn = new JButton("Cancel");
        resetBtn = new JButton("Reset");
        main_Panel = new JPanel();
        bottom_Panel = new JPanel();
        fullScreeen = new JCheckBox("FullScreen");
        activateSound = new JCheckBox("Activate Sound");
        musicTrack = new JComboBox<>(new String[]{"505 - Artic Monkeys"});
        characterSkin = new JComboBox<>(new String[]{"Fire Skin","Ice Skin"});
        checkbox_Panel = new JPanel();
        char_Panel = new JPanel();
        music_Panel = new JPanel();
        teclas_Panel = new JPanel();
        teclas = new JLabel("<html><span style='color: white;'>Q - Sound Effects        W - Background Music     Space - Pause <br> [1-8] Habilities            E - Accelerate Game      R - Self-Destruction</span></html>");
        
        background = new ImageIcon("app\\src\\main\\resources\\Config.jpg").getImage();
        
        this.setLayout(new BorderLayout()); 
        this.add(main_Panel, BorderLayout.CENTER); 
        main_Panel.setLayout(new GridLayout(7, 1, 5, 5));
        main_Panel.setOpaque(false); 
        JPanel empty = new JPanel();
        main_Panel.add(empty);
        empty.setOpaque(false);

        checkbox_Panel.setSize(300, 10); 
        checkbox_Panel.setOpaque(false);
        checkbox_Panel.setBackground(Color.BLUE);
        checkbox_Panel.add(fullScreeen,BorderLayout.SOUTH);
        checkbox_Panel.add(activateSound,BorderLayout.SOUTH);
        main_Panel.add(checkbox_Panel);
        
        char_Panel.setOpaque(false); 
        characterSkin.setPreferredSize(new Dimension(300, 30)); 
        char_Panel.add(characterSkin);
        main_Panel.add(char_Panel);
        music_Panel.setOpaque(false);
        musicTrack.setPreferredSize(new Dimension(300, 30)); 
        music_Panel.add(musicTrack);
        main_Panel.add(music_Panel);

        teclas_Panel.add(teclas);
        teclas_Panel.setOpaque(false);
        main_Panel.add(teclas_Panel);
        main_Panel.add(bottom_Panel);
        
        fullScreeen.setOpaque(false);
        fullScreeen.setForeground(Color.WHITE);
        activateSound.setOpaque(false);
        activateSound.setForeground(Color.WHITE);
        
        bottom_Panel.setLayout(new FlowLayout());
        bottom_Panel.setOpaque(false);
        bottom_Panel.add(saveBtn);
        bottom_Panel.add(cancelBtn);
        bottom_Panel.add(resetBtn);

        saveBtn.setBackground(new Color(255, 255, 255, 100));
        cancelBtn.setBackground(new Color(255, 255, 255, 100));
        resetBtn.setBackground(new Color(255, 255, 255, 100));
    
    fullScreeen.setFocusPainted(false);
    activateSound.setFocusPainted(false);

    saveBtn.setBackground(Color.WHITE);
    cancelBtn.setBackground(Color.WHITE);
    resetBtn.setBackground(Color.WHITE);
    saveBtn.setFocusPainted(false);
    cancelBtn.setFocusPainted(false);
    resetBtn.setFocusPainted(false);
    }
            @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

