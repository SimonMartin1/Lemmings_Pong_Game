package Proyecto.games.Lemmings_game.Controller;

import com.entropyinteractive.Mouse;

import Proyecto.games.Lemmings_game.Model.MinimapModel;

import javax.swing.*;

public class ButtonController {
    private Mouse mouse;
    private ButtonModel buttonModel;
    private MinimapModel minimapModel;
    boolean wasPressedLastFrame = false;
    //private int screenWidth = 1366;
    //private int screenHeight = 768;
    private int screenWidth;
    private int screenHeight;


    public ButtonController(Mouse mouse, int screenWidth, int screenHeight){
        this.mouse = mouse;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void update(){
        checkClick(mouse.getX(), mouse.getY());
    }

    public void draw(){

    }
    public void checkClick(int x, int y) {
        boolean isPressed = mouse.isLeftButtonPressed();
    
        int windowWidth = screenWidth;
        int windowHeight = screenHeight;
    
        // Valores relativos para botones normales
        float startY = 0.75f;
        float buttonHeight = 0.25f;
        float buttonWidth = 0.13f;
        float espacio = 0.03f;
        float startX = 0.01f;
    
        if (isPressed && !wasPressedLastFrame) {
            // Botones principales
            for (int i = 0; i < 4; i++) {
                float relX = startX + i * (buttonWidth + espacio);
                int absX = (int)(relX * windowWidth);
                int absY = (int)(startY * windowHeight);
                int absW = (int)(buttonWidth * windowWidth);
                int absH = (int)(buttonHeight * windowHeight);
    
                if (x >= absX && x <= absX + absW && y >= absY && y <= absY + absH ) {
                    switch (i) {
                        case 0:
                            System.out.println("Estoy RE NAZI cavando!!!");
                            break;
                        case 1:
                            System.out.println("Me frene");
                            break;
                        case 2:
                            System.out.println("Construyendo!");
                            break;
                        case 3:
                            System.out.println("Volando");
                            break;
                    }
                }
            }
    
            // Minimap (mantiene coordenadas absolutas)
            if (x <= 730 && x >= 480 && y >= 480 && y <= 580) {
                System.out.println("Minimap clickeado!");
                // minimapModel.handleClick(x, y);
            }
        }    
    
        wasPressedLastFrame = isPressed;
    }
    
}