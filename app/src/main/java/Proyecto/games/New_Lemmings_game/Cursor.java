package Proyecto.games.New_Lemmings_game;

import Proyecto.games.New_Lemmings_game.Abilities.ClimbAbility;
import Proyecto.games.New_Lemmings_game.Abilities.DigAbility;
import Proyecto.games.New_Lemmings_game.Abilities.UmbrellaAbility;
import Proyecto.games.New_Lemmings_game.Abilities.WallAbility;
import Proyecto.games.New_Lemmings_game.States.*;
import Proyecto.games.New_Lemmings_game.utils.Ability;
import Proyecto.games.New_Lemmings_game.Abilities.AbilityClass;
import Proyecto.games.New_Lemmings_game.utils.LemmingAnimationState;
import com.entropyinteractive.Mouse;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Cursor {

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
    //private static final int FULLSCREEN_VERTICAL_OFFSET = 50;
    private int FULLSCREEN_VERTICAL_OFFSET = 0;
    // private static final int WINDOWED_VERTICAL_OFFSET = 0;

    // --- Dependencias y Estado ---
    private List<Lemming_Entity> currentLemmingEntities;
    private AbilityClass currentSelectedAbility;
    private Minimap minimap; 
    private Ability currentAbility; // Mantenemos este campo para restar del stock sin modificar otras clases.
    private Stock stock;
    private final Mouse mouse;
    private final int screenWidth;
    private final int screenHeight;
    private int camX;
    private boolean wasPressedLastFrame = false;
    private boolean wereAllLemmingsGenerated = false;

    private static final Map<Ability, Supplier<AbilityClass>> ABILITY_FACTORY = Map.of(
            Ability.DIGGER, DigAbility::new,
            Ability.STOP, WallAbility::new,
            Ability.UMBRELLA, UmbrellaAbility::new,
            Ability.CLIMB, ClimbAbility::new
    );


    private static final Ability[] ABILITY_BUTTON_ORDER = {
            Ability.DIGGER, Ability.STOP, Ability.UMBRELLA, Ability.CLIMB
    };

    public Cursor(Stock stock, Mouse mouse, int screenWidth, int screenHeight, boolean fullscreen) {
        this.stock = stock;
        this.mouse = mouse;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        if(fullscreen){
            FULLSCREEN_VERTICAL_OFFSET = 0;
        }else{
            FULLSCREEN_VERTICAL_OFFSET = 0;
        }
    }

    public void update() {

        //System.out.println("Entre al update del cursor");
        boolean isPressed = mouse.isLeftButtonPressed();
        if (isPressed && !wasPressedLastFrame) {
            //level.getMinimap().handleClick(mouse.getX(), mouse.getY());
            System.out.println("Clickee el mouse");
            handleMouseClick(mouse.getX(), mouse.getY());
        }

        wasPressedLastFrame = isPressed;
    }

    /**
     * Determina si el clic fue en la UI o en el mundo del juego y delega la acción.
     */
    private void handleMouseClick(int x, int y) {
        int uiStartY = (int) (UI_START_Y_RATIO * screenHeight);

        if (y >= uiStartY - 40 ) {
            handleUiClick(x, y);
            System.out.println("En la ui click");
        } else {
            handleGameWorldClick(x, y);
            System.out.println("CLICK EN MAPA");
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
            System.out.println("CLICKEE ");

            return;
        }

        // Botón Ralentizar
        int slowAbsX = startX + speedButtonOffsetX;
        int slowAbsY = (int) (SLOW_BUTTON_Y_RATIO * screenHeight);
        if (isMouseInBounds(x, y, slowAbsX, slowAbsY + FULLSCREEN_VERTICAL_OFFSET, speedButtonW, speedButtonH, UI_BUTTON_EXTRA_MARGIN)) {
            changeLemmingsSpeed(-1);
            System.out.println("CLICKEE ");

        }

        //Botón nashe
        int nasheAbsX = startX + speedButtonOffsetX;
        int nasheAbsY = (int) (NASHE_BUTTON_Y_RATIO * screenHeight);
        if (isMouseInBounds(x, y, nasheAbsX, nasheAbsY + FULLSCREEN_VERTICAL_OFFSET  , speedButtonW, speedButtonH, UI_BUTTON_EXTRA_MARGIN)) {
            assignNukeLemmings();
            System.out.println("CLICKEE ");
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

        for (Lemming_Entity lemmingEntity : currentLemmingEntities) {
            if (lemmingEntity.isClicked(x, y, camX)) {
                System.out.println("Habilidad asignada al lemming!");
                lemmingEntity.assignAbility(currentSelectedAbility);

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
        if (wereAllLemmingsGenerated) {
            for (Lemming_Entity lemmingEntity : currentLemmingEntities) {
                int currentSpeed = lemmingEntity.getSpeed();
                int newSpeed = currentSpeed + delta;
                if (newSpeed >= 0 && newSpeed <= 4) {
                    lemmingEntity.setSpeed(newSpeed);
                }
            }
            System.out.println("Velocidad de lemmings cambiada.");
        }
    }

    /**
     * asigna nuke a los lemmings
     */
    private void assignNukeLemmings() {
        if (wereAllLemmingsGenerated){
            for (Lemming_Entity lemmingEntity : currentLemmingEntities) {
                lemmingEntity.clearAbility();
                lemmingEntity.setState(new ExplodingState());
                lemmingEntity.setCurrentStateAnimation(LemmingAnimationState.NUKE);
            }
            System.out.println("NUKEEE");
        }
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
    public void setCurrentLemmings(List<Lemming_Entity> currentLemmingEntities) {
        this.currentLemmingEntities = currentLemmingEntities;
    }

    public void setCamX(int camX) {
        this.camX = camX;
    }

    public void setStock(Stock stock){
        this.stock = stock;
    }

    public void setWereAllLemmingsGenerated(boolean wereAllLemmingsGenerated){
        this.wereAllLemmingsGenerated = wereAllLemmingsGenerated;
    }
}