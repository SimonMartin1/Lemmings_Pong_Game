package Proyecto.games.New_Lemmings_game.States;

import java.awt.Color;

import Proyecto.games.New_Lemmings_game.Lemming_Entity;
import Proyecto.games.New_Lemmings_game.Tile;
import Proyecto.games.New_Lemmings_game.utils.LemmingAnimationState;
import Proyecto.games.utils.SoundManager;
import Proyecto.games.utils.SoundPlayer;

public class ExplodingState implements LemmingState {

    private int tickCounter = 0;
    private final int explosionDuration = 60; // duración de la explosión en ticks


    @Override
    public void update(Lemming_Entity lemmingEntity, double delta, SoundManager soundManager) {
        tickCounter++;

        int tileX = (lemmingEntity.getX()) / 8;
        int tileY = (lemmingEntity.getY()) / 8;
        // Mientras explota, mostrás la animación de explosión
        //lemmingEntity.setCurrentStateAnimation(LemmingAnimationState.NUKE);
        System.out.println("tickCounter");
        // Cuando termina la animación, lo sacás del juego
        if (tickCounter >= explosionDuration) {
            SoundPlayer.playSound("app/src/main/resources/soundEffects/Lemmings_DeadSound.wav");
            createExplosion(lemmingEntity.getLevel().getMap().getMapTiles(), tileX, tileY, 7);
            lemmingEntity.setState(new DeadState());
        }
    }

    private void createExplosion(Tile[][] tiles, int tileX, int tileY, int radius){

        for (int dy = 0; dy <= radius; dy++) { // solo hacia abajo
            int y = tileY + dy;
            int dxMax = (int) Math.sqrt(radius * radius - dy * dy); // círculo

            for (int dx = -dxMax; dx <= dxMax; dx++) {
                int x = tileX + dx;

                if (y >= 0 && y < tiles.length && x >= 0 && x < tiles[0].length) {
                    tiles[y][x].setTileColor(Color.BLACK);
                }
            }
        }

    }
    
    @Override
    public void onEnter(Lemming_Entity lemmingEntity, SoundManager soundManager) {
        tickCounter = 0;
        lemmingEntity.setCurrentStateAnimation(LemmingAnimationState.NUKE);
    }

    @Override
    public void onExit(Lemming_Entity lemmingEntity, SoundManager soundManager) {
        // Por lo general nada, porque lo borrás antes
    }
}
