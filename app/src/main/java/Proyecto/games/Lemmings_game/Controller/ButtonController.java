package Proyecto.games.Lemmings_game.Controller;

import com.entropyinteractive.Mouse;

import javax.swing.*;

public class ButtonController {
    private Mouse mouse;
    private ButtonModel buttonModel;
    boolean wasPressedLastFrame = false;


    public ButtonController(Mouse mouse){
        this.mouse = mouse;
    }

    public void update(){
        checkClick(mouse.getX(), mouse.getY());
    }

    public void draw(){

    }

    public void checkClick(int x, int y){
        boolean isPressed = mouse.isLeftButtonPressed();

        
        if(isPressed && !wasPressedLastFrame){
            // Esto solo se ejecuta UNA VEZ cuando se presiona el botón
            if(x<=110 && x >= 10 && y>=450 && y<= 600){
                //new DigAbility(le)
                System.out.println("Estoy cavando!!!");
            } else if(x<=210 && x >= 110 && y>=450 && y<= 600){
                System.out.println("Me frene");
            } else if(x<=310 && x >= 210 && y>=450 && y<= 600){
                System.out.println("Construyendo!");
            } else if(x<=410 && x >= 310 && y>=450 && y<= 600){
                System.out.println("Volando");
            } else if (x <= 730 && x >= 480 && y >= 480 && y <= 580) {
                System.out.println("Minimap clickeado!");
                //minimapModel.handleClick(x, y);
            }
        }

        wasPressedLastFrame = isPressed;
    }
}
