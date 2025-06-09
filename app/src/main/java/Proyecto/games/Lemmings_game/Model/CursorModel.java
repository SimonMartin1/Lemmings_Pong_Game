package Proyecto.games.Lemmings_game.Model;

import com.entropyinteractive.Mouse;

import java.util.List;

public class CursorModel {
    private List<LemmingModel> currentLemmings;
    private AbilityModel currentSelectedAbility;
    private Mouse mouse;
    int camX;
    int camY;
    private int panelWidth = 1366;
    private int panelHeight = 768;
    boolean wasPressedLastFrame = false;

    public CursorModel(Mouse mouse){ this.mouse = mouse; }

    public void update(){
        checkClick(mouse.getX(), mouse.getY());
    }

    public void checkClick(int x, int y){
        boolean isPressed = mouse.isLeftButtonPressed();
    
        int windowWidth = panelWidth;
        int windowHeight = panelHeight;
    
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
                                currentSelectedAbility = new DigAbility();
                                System.out.println("Estoy cavando!!!");
                                break;
                            case 1:
                                currentSelectedAbility = new WallAbility();
                                System.out.println("Me frené");
                                break;
                            case 2:
                                currentSelectedAbility = new DigAbility(); // Cambialo si usás otra
                                System.out.println("Construyendo!");
                                break;
                            case 3:
                                currentSelectedAbility = new DigAbility(); // Cambialo si usás otra
                                System.out.println("Volando!");
                                break;
                        }
                        break;
                    }
                }
            } else {
                // Click en un lemming
                for(LemmingModel lemming : currentLemmings){
                    System.out.println("camX en cursorModel: " +  camX );
                    if(lemming.isClicked(x, y, camX)){
                        System.out.println("entre al click!!!!");
                        if(currentSelectedAbility != null){
                            lemming.assignAbility(currentSelectedAbility);
                            System.out.println("Habilidad asignada al lemming!");
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
