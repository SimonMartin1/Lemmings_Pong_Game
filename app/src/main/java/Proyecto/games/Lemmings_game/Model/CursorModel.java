package Proyecto.games.Lemmings_game.Model;

import Proyecto.games.Lemmings_game.Utils.Ability;
import Proyecto.games.Lemmings_game.Utils.AbilityClass;
import Proyecto.games.Lemmings_game.Utils.LemmingAnimationState;
import Proyecto.games.Lemmings_game.Utils.LemmingState;

import com.entropyinteractive.Mouse;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class CursorModel {

    // --- Constantes de la Interfaz de Usuario (UI) ---
    private static final float UI_START_Y_RATIO = 0.75f;
    private static final float ABILITY_BUTTON_WIDTH_RATIO = 0.12f;
    private static final float ABILITY_BUTTON_HEIGHT_RATIO = 0.25f;
    private static final float ABILITY_BUTTON_SPACING_RATIO = 0.03f;
    private static final float ABILITY_BUTTON_START_X_RATIO = 0.01f;

    private static final float SPEED_BUTTON_OFFSET_X_RATIO = 0.59f;
    private static final float SPEED_BUTTON_WIDTH_RATIO = 0.05f;  // 0.10f * 0.5f
    private static final float SPEED_BUTTON_HEIGHT_RATIO = 0.052f; // 0.13f * 0.4f
    private static final float ACCEL_BUTTON_Y_RATIO = 0.76f;
    private static final float SLOW_BUTTON_Y_RATIO = 0.83f;
    private static final float NASHE_BUTTON_Y_RATIO = 0.69f; 
    
    private static final int UI_BUTTON_EXTRA_MARGIN = 10;
    
    // Offset vertical para botones, ajustable para diferentes modos de pantalla.
    private static final int FULLSCREEN_VERTICAL_OFFSET = 50;
    // private static final int WINDOWED_VERTICAL_OFFSET = 0;

    // --- Dependencias y Estado ---
    private List<LemmingModel> currentLemmings;
    private AbilityClass currentSelectedAbility;
    private Ability currentAbility; // Mantenemos este campo para restar del stock sin modificar otras clases.
    private final Stock stock;
    private final Mouse mouse;
    private final int screenWidth;
    private final int screenHeight;
    private int camX;
    private boolean wasPressedLastFrame = false;

    // --- Mapa de Habilidades (Patrón Factory) ---
    private static final Map<Ability, Supplier<AbilityClass>> ABILITY_FACTORY = Map.of(
        Ability.DIGGER, DigAbility::new,
        Ability.STOP, WallAbility::new,
        Ability.UMBRELLA, UmbrellaAbility::new,
        Ability.CLIMB, ClimbAbility::new
    );
    
    // Define el orden de los botones de habilidad en la UI.
    private static final Ability[] ABILITY_BUTTON_ORDER = {
        Ability.DIGGER, Ability.STOP, Ability.UMBRELLA, Ability.CLIMB
    };

    public CursorModel(Stock stock, Mouse mouse, int screenWidth, int screenHeight) {
        this.stock = stock;
        this.mouse = mouse;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    /**
     * Método principal de actualización, llamado en cada frame del juego.
     */
    public void update() {
        boolean isPressed = mouse.isLeftButtonPressed();
        
        if (isPressed && !wasPressedLastFrame) {
            handleMouseClick(mouse.getX(), mouse.getY());
        }
        
        wasPressedLastFrame = isPressed;
    }

    /**
     * Determina si el clic fue en la UI o en el mundo del juego y delega la acción.
     */
    private void handleMouseClick(int x, int y) {
        int uiStartY = (int) (UI_START_Y_RATIO * screenHeight);
        
        if (y >= uiStartY) {
            handleUiClick(x, y);
        } else {
            handleGameWorldClick(x, y);
        }
    }

    /**
     * Procesa los clics que ocurren dentro del área de la interfaz de usuario (botones).
     */
    private void handleUiClick(int x, int y) {
        // --- Comprobación de Botones de Habilidad ---
        int absButtonY = (int) (UI_START_Y_RATIO * screenHeight);
        int absButtonH = (int) (ABILITY_BUTTON_HEIGHT_RATIO * screenHeight);

        if (y >= absButtonY && y <= absButtonY + absButtonH) {
            for (int i = 0; i < ABILITY_BUTTON_ORDER.length; i++) {
                float relX = ABILITY_BUTTON_START_X_RATIO + i * (ABILITY_BUTTON_WIDTH_RATIO + ABILITY_BUTTON_SPACING_RATIO);
                int absX = (int) (relX * screenWidth);
                int absW = (int) (ABILITY_BUTTON_WIDTH_RATIO * screenWidth);

                if (isMouseInBounds(x, y, absX, absButtonY, absW, absButtonH)) {
                    selectAbility(ABILITY_BUTTON_ORDER[i]);
                    return; 
                }
            }
        }
        
        // --- Comprobación de Botones de Velocidad ---
        handleSpeedButtonsClick(x, y);
    }
    
    /**
     * Maneja específicamente los clics en los botones de acelerar y ralentizar.
     */
    private void handleSpeedButtonsClick(int x, int y) {
        int speedButtonOffsetX = (int) (SPEED_BUTTON_OFFSET_X_RATIO * screenWidth);
        int speedButtonW = (int) (SPEED_BUTTON_WIDTH_RATIO * screenWidth);
        int speedButtonH = (int) (SPEED_BUTTON_HEIGHT_RATIO * screenHeight);
        int startX = (int) (ABILITY_BUTTON_START_X_RATIO * screenWidth);

        // Botón Acelerar
        int accelAbsX = startX + speedButtonOffsetX;
        int accelAbsY = (int) (ACCEL_BUTTON_Y_RATIO * screenHeight);
        if (isMouseInBounds(x, y, accelAbsX, accelAbsY + FULLSCREEN_VERTICAL_OFFSET, speedButtonW, speedButtonH, UI_BUTTON_EXTRA_MARGIN)) {
            changeLemmingsSpeed(1);
            return;
        }

        // Botón Ralentizar
        int slowAbsX = startX + speedButtonOffsetX;
        int slowAbsY = (int) (SLOW_BUTTON_Y_RATIO * screenHeight);
        if (isMouseInBounds(x, y, slowAbsX, slowAbsY + FULLSCREEN_VERTICAL_OFFSET, speedButtonW, speedButtonH, UI_BUTTON_EXTRA_MARGIN)) {
            changeLemmingsSpeed(-1);
        }

        //Botón nashe
        int nasheAbsX = startX + speedButtonOffsetX;
        int nasheAbsY = (int) (NASHE_BUTTON_Y_RATIO * screenHeight);
        if (isMouseInBounds(x, y, nasheAbsX, nasheAbsY + FULLSCREEN_VERTICAL_OFFSET, speedButtonW, speedButtonH, UI_BUTTON_EXTRA_MARGIN)) {
            assignNukeLemmings();
        }
    }

    /**
     * Procesa los clics que ocurren en el área de juego para asignar habilidades a los lemmings.
     */
    private void handleGameWorldClick(int x, int y) {
        if (currentSelectedAbility == null) {
            System.out.println("No hay habilidad seleccionada.");
            return;
        }

        for (LemmingModel lemming : currentLemmings) {
            if (lemming.isClicked(x, y, camX)) {
                System.out.println("Habilidad asignada al lemming!");
                lemming.assignAbility(currentSelectedAbility);
                
                // Usamos el campo 'currentAbility' que guardamos, tal como en el código original.
                stock.substractAbility(currentAbility); 

                // Limpiamos ambas variables
                currentSelectedAbility = null;
                currentAbility = null;
                break; 
            }
        }
    }
    
    /**
     * Intenta seleccionar una habilidad, actualizando las dos variables de estado.
     */
    private void selectAbility(Ability ability) {
        if (stock.hasAbility(ability)) {
            this.currentSelectedAbility = ABILITY_FACTORY.get(ability).get();
            this.currentAbility = ability; // Almacenamos el tipo de habilidad aquí
            System.out.println("Habilidad " + ability.name() + " guardada en el cursor");
        } else {
            System.out.println("No hay stock de " + ability.name());
        }
    }
    
    /**
     * Cambia la velocidad de todos los lemmings actuales.
     */
    private void changeLemmingsSpeed(int delta) {
        for (LemmingModel lemming : currentLemmings) {
            int currentSpeed = lemming.getSpeed();
            int newSpeed = currentSpeed + delta;
            if (newSpeed >= 0 && newSpeed <= 4) {
                lemming.setSpeed(newSpeed);
            }
        }
        System.out.println("Velocidad de lemmings cambiada.");
    }

    /**
     * asigna nuke a los lemmings
     */
    private void assignNukeLemmings() {
        for (LemmingModel lemming : currentLemmings) {
            lemming.setAbility(new WallAbility());
            lemming.setStateLemming(LemmingState.WAITING);
            lemming.setCurrentLeemingState(LemmingAnimationState.NUKE);
        }
        System.out.println("NUKEEE");
    }


    /**
     * Helper para verificar si el cursor está dentro de un rectángulo.
     */
    private boolean isMouseInBounds(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
    
    /**
     * Sobrecarga de isMouseInBounds para incluir un margen adicional.
     */
    private boolean isMouseInBounds(int mouseX, int mouseY, int x, int y, int width, int height, int margin) {
        return isMouseInBounds(mouseX, mouseY, x - margin, y - margin, width + (2 * margin), height + (2 * margin));
    }

    // --- Setters ---
    public void setCurrentLemmings(List<LemmingModel> currentLemmings) {
        this.currentLemmings = currentLemmings;
    }

    public void setCamX(int camX) {
        this.camX = camX;
    }
}