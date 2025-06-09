package Proyecto.games.Lemmings_game.Model;

import com.entropyinteractive.Mouse;

import java.util.List;

public class CursorModel {
    private List<LemmingModel> currentLemmings;
    private AbilityModel currentSelectedAbility;
    private Ability currentAbility;
    private Stock stock;
    private Mouse mouse;
    int camX;
    int camY;

    boolean wasPressedLastFrame = false;

    public CursorModel(Stock stock, Mouse mouse){
        this.stock = stock;
        this.mouse = mouse;
    }

    public void update(){
        checkClick(mouse.getX(), mouse.getY());
    }

    public void checkClick(int x, int y){
        boolean isPressed = mouse.isLeftButtonPressed();

        if(isPressed && !wasPressedLastFrame){
            // Click en botón de habilidad
            if (y >= 450 && y <= 600) {
                if (x >= 10 && x < 110) {
                    if (stock.hasAbility(Ability.DIGGER)) {
                        currentSelectedAbility = new DigAbility();
                        currentAbility = Ability.DIGGER;
                        System.out.println("Habilidad Digger guardada en el cursor");
                    } else {
                        System.out.println("No hay stock de Digger");
                    }

                } else if (x >= 110 && x < 210) {
                    if (stock.hasAbility(Ability.STOP)) {
                        currentSelectedAbility = new WallAbility();
                        currentAbility = Ability.STOP;
                        System.out.println("Habilidad Wall guardada en el cursor");
                    } else {
                        System.out.println("No hay stock de Wall");
                    }

                } else if (x >= 210 && x < 310) {
                    if (stock.hasAbility(Ability.DIGGER)) {
                        currentSelectedAbility = new DigAbility();
                        currentAbility = Ability.DIGGER;
                        System.out.println("Habilidad Build guardada en el cursor");
                    } else {
                        System.out.println("No hay stock de Build");
                    }

                } else if (x >= 310 && x < 410) {
                    if (stock.hasAbility(Ability.CLIMB)) {
                        currentSelectedAbility = new ClimbAbility();
                        currentAbility = Ability.CLIMB;
                        System.out.println("Habilidad Climb guardada en el cursor");
                    } else {
                        System.out.println("No hay stock de Climb");
                    }
                }
            }

            else {
                for(LemmingModel lemming : currentLemmings){

                    if(lemming.isClicked(x, y)){

                        if(currentSelectedAbility != null){
                            System.out.println("Habilidad asignada al lemming!");

                            lemming.assignAbility(currentSelectedAbility);
                            stock.substractAbility(currentAbility);

                            currentAbility = null;
                            currentSelectedAbility = null;
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
    
    public void setCamX(int camX){
        this.camX = camX;
    }
}
