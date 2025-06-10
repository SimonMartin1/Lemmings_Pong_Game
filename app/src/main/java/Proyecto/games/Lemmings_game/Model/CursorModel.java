package Proyecto.games.Lemmings_game.Model;

import Proyecto.games.Lemmings_game.Utils.Ability;
import Proyecto.games.Lemmings_game.Utils.AbilityClass;
import com.entropyinteractive.Mouse;

import java.util.List;

public class CursorModel {
    private List<LemmingModel> currentLemmings;
    private AbilityClass currentSelectedAbility;
    private Ability currentAbility;
    private Stock stock;
    private Mouse mouse;
    int camX;
    int camY;
    //private int panelWidth = 1366;
    //private int panelHeight = 768;
    private int screenWidth;
    private int screenHeight;
    boolean wasPressedLastFrame = false;

    public CursorModel(Stock stock, Mouse mouse, int screenWidth, int screenHeight){
        this.stock = stock;
        this.mouse = mouse;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void update(){
        checkClick(mouse.getX(), mouse.getY());
    }

    public void checkClick(int x, int y){
        boolean isPressed = mouse.isLeftButtonPressed();
    
        int windowWidth = screenWidth;
        int windowHeight = screenHeight;
    
        float startY = 0.75f;
        float buttonHeight = 0.25f;
        float buttonWidth = 0.13f;
        float espacio = 0.03f;
        float startX = 0.01f;
    
        if(isPressed && !wasPressedLastFrame){
            int absY = (int)(startY * windowHeight);
            int absH = (int)(buttonHeight * windowHeight);
    
            if(y >= absY && y <= absY + absH) {
                for (int i = 0; i < 4; i++) {
                    float relX = startX + i * (buttonWidth + espacio);
                    int absX = (int)(relX * windowWidth);
                    int absW = (int)(buttonWidth * windowWidth);
    
                    if(x >= absX && x <= absX + absW){
                        switch (i) {
                            case 0:
                                if (stock.hasAbility(Ability.DIGGER)) {
                                    currentSelectedAbility = new DigAbility();
                                    currentAbility = Ability.DIGGER;
                                    System.out.println("Habilidad Digger guardada en el cursor");
                                } else {
                                    System.out.println("No hay stock de Digger");
                                }
                                break;
                            case 1:
                                if (stock.hasAbility(Ability.STOP)) {
                                    currentSelectedAbility = new WallAbility();
                                    currentAbility = Ability.STOP;
                                    System.out.println("Habilidad Wall guardada en el cursor");
                                } else {
                                    System.out.println("No hay stock de Wall");
                                }
                                break;
                            case 2:
                                if (stock.hasAbility(Ability.DIGGER)) {
                                    currentSelectedAbility = new DigAbility();
                                    currentAbility = Ability.DIGGER;
                                    System.out.println("Habilidad Build guardada en el cursor");
                                } else {
                                    System.out.println("No hay stock de Build");
                                }
                                break;
                            case 3:
                                if (stock.hasAbility(Ability.CLIMB)) {
                                    currentSelectedAbility = new ClimbAbility();
                                    currentAbility = Ability.CLIMB;
                                    System.out.println("Habilidad Climb guardada en el cursor");
                                } else {
                                    System.out.println("No hay stock de Climb");
                                }
                                break;
                        }
                        break;
                    }
                }
            } else {
                // Click en un lemming
                for(LemmingModel lemming : currentLemmings){
                    //SI SE ROMPE BORRAR LA RESTA DE CAMX
                    System.out.println("camX en cursorModel: " +  camX );
                    if(lemming.isClicked(x, y, camX)){
                        System.out.println("entre al click!!!!");
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
