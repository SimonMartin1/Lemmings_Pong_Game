package Proyecto.View.Menu.Game_screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;



public class Game extends JPanel {
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

    public Game(String url, String nombre) {
        image = new image_Panel(url, nombre);
        //this.Layout();
        this.setPreferredSize(new Dimension(175, 250)); 
        JPanel image_Panel = new JPanel();
        image_Panel.add(image);
        image_Panel.setOpaque(false);
        this.add(image_Panel, BorderLayout.CENTER);
        this.setOpaque(false);
        cant++;
        this.setBorder(new LineBorder(new Color(0, 0, 0, 0), 2));
        this.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
        Game.this.setBorder(new LineBorder(Color.WHITE, 2)); 
        }
        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
        Game.this.setBorder(new LineBorder(new Color(0, 0, 0, 0), 2)); 
        }
        });

    }
}
