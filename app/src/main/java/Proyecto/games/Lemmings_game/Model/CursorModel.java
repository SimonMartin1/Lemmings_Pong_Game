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
                    
                    int offsetX = (int)(0.59f * windowWidth);
            
                    float extraRelWidth = buttonWidth * 0.5f;
                    float extraRelHeight = buttonHeight * 0.4f;
                    // buttonAcelerate

                    
                    float acelRelX = startX;  
                    float acelRelY = 0.72f;       
                    int acelAbsX = (int)(acelRelX * windowWidth) + offsetX;
                    int acelAbsY = (int)(acelRelY * windowHeight);
                    int acelAbsW = (int)(extraRelWidth * windowWidth);
                    int acelAbsH = (int)(extraRelHeight * windowHeight);
            
                    if (x >= acelAbsX && x <= acelAbsX + acelAbsW && y >= acelAbsY && y <= acelAbsY + acelAbsH    ) {
                        for(LemmingModel lemming : currentLemmings){
                            if(lemming.getSpeed() < 4){
                            lemming.setSpeed(lemming.getSpeed() +1 );
                        }
                    }
                    // buttonSlow
                    float slowRelX = startX;         // 0.01f
                    float slowRelY = 0.80f;          // según lo que pusiste antes para slow
                    int slowAbsX = (int)(slowRelX * windowWidth) + offsetX;
                    int slowAbsY = (int)(slowRelY * windowHeight);
                    int slowAbsW = (int)(extraRelWidth * windowWidth);
                    int slowAbsH = (int)(extraRelHeight * windowHeight);
            
                    if (x >= slowAbsX && x <= slowAbsX + slowAbsW && y >= slowAbsY - 8 && y <= slowAbsY + slowAbsH + 8	) {
                        for(LemmingModel lemming : currentLemmings){
                            if(lemming.getSpeed() > 0){
                                lemming.setSpeed(lemming.getSpeed() - 1 );
                            }
                        }
                    }
                }}
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
