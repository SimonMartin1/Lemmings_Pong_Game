package Proyecto.games.Lemmings_game.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameWinView {
    private final int width;
    private final int height;
    private String playerName = "";
    private boolean nameEntered = false;
    private boolean backToMenu = false;

    // Botón "Volver al menú"
    private final int btnX, btnY, btnW = 220, btnH = 50;

    public GameWinView(int width, int height) {
        this.width = width;
        this.height = height;
        this.btnX = width / 2 - btnW / 2;
        this.btnY = height / 2 + 70;
    }

    // Llama a este método en tu ciclo de eventos de teclado
    public void keyPressed(KeyEvent e) {
        if (!nameEntered) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_BACK_SPACE && playerName.length() > 0) {
                playerName = playerName.substring(0, playerName.length() - 1);
            } else if (key == KeyEvent.VK_ENTER && playerName.length() > 0) {
                nameEntered = true;
            } else {
                char c = e.getKeyChar();
                if (Character.isLetterOrDigit(c) && playerName.length() < 12) {
                    playerName += c;
                }
            }
        } else {
            // Si ya ingresó el nombre, Enter también vuelve al menú
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                backToMenu = true;
            }
        }
    }

    // Llama a este método en tu ciclo de eventos de mouse
    public void mousePressed(MouseEvent e) {
        if (nameEntered) {
            int mx = e.getX();
            int my = e.getY();
            if (mx >= btnX && mx <= btnX + btnW && my >= btnY && my <= btnY + btnH) {
                backToMenu = true;
            }
        }
    }

    public boolean isNameEntered() {
        return nameEntered;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean wantsBackToMenu() {
        return backToMenu;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("You Win!", width / 2 - 90, height / 2 - 60);

        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Enter your name:", width / 2 - 110, height / 2);

        g.setColor(Color.YELLOW);
        g.setFont(new Font("Consolas", Font.BOLD, 28));
        g.drawString(playerName + (nameEntered ? "" : "_"), width / 2 - 80, height / 2 + 40);

        if (nameEntered) {
            // Botón "Volver al menú"
            g.setColor(new Color(30, 144, 255));
            g.fillRect(btnX, btnY, btnW, btnH);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 22));
            g.drawString("Back to Menu", btnX + 30, btnY + 33);
        }
    }
}