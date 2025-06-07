package Proyecto.games.Lemmings_game.Model;

import com.entropyinteractive.Mouse;

import java.util.List;

public class CursorModel {
    private List<LemmingModel> currentLemmings;
    private AbilityModel currentSelectedAbility;
    private Mouse mouse;

    boolean wasPressedLastFrame = false;

    public CursorModel(Mouse mouse){ this.mouse = mouse; }

    public void update(){
        checkClick(mouse.getX(), mouse.getY());
    }

    public void checkClick(int x, int y){
        boolean isPressed = mouse.isLeftButtonPressed();

        if(isPressed && !wasPressedLastFrame){
            // Click en botón de habilidad
            if(y >= 450 && y <= 600) {
                if(x >= 10 && x < 110){
                    currentSelectedAbility = new DigAbility();
                    System.out.println("Estoy cavando!!!");
                } else if(x >= 110 && x < 210){
                    //currentSelectedAbility = new DigAbility();
                    currentSelectedAbility = new WallAbility();
                    System.out.println("Me frené");
                } else if(x >= 210 && x < 310){
                    currentSelectedAbility = new DigAbility();
                    //currentSelectedAbility = new BuildAbility();
                    System.out.println("Construyendo!");
                } else if(x >= 310 && x < 410){
                    currentSelectedAbility = new DigAbility();
                    //currentSelectedAbility = new FlyAbility();
                    System.out.println("Volando!");
                }
            }
            // Click en un lemming
            else {
                for(LemmingModel lemming : currentLemmings){
                    if(lemming.isClicked(x, y)){ // ← implementá esto en LemmingModel
                        if(currentSelectedAbility != null){
                            lemming.assignAbility(currentSelectedAbility);
                            System.out.println("Habilidad asignada al lemming!");
                            currentSelectedAbility = null; // ← se "gasta" la habilidad seleccionada
                        } else {
                            System.out.println("No hay habilidad seleccionada.");
                        }
                        break;
                    }
                }
            }
        }

        wasPressedLastFrame = isPressed;
    }

    public void setCurrentLemmings(List<LemmingModel> currentLemmings){
        this.currentLemmings = currentLemmings;
    }
}
