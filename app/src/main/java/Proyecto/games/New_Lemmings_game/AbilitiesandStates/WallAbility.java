package Proyecto.games.New_Lemmings_game.AbilitiesandStates;

import Proyecto.games.New_Lemmings_game.Lemming_Entity;
import Proyecto.games.New_Lemmings_game.Tile;
import Proyecto.games.New_Lemmings_game.utils.Ability;
import Proyecto.games.New_Lemmings_game.utils.AbilityClass;
import Proyecto.games.New_Lemmings_game.utils.LemmingAnimationState;

import java.awt.*;

public class WallAbility extends AbilityClass {

    public WallAbility(){
        super(Ability.STOP);
    }


    @Override
    public void apply(Lemming_Entity lemmingEntity, double delta) {
        int tileX = (lemmingEntity.getX()) / 8;
        int tileY = (lemmingEntity.getY()) / 8;

        lemmingEntity.setSpeed(0);

        //System.out.println(lemming.currentStateAnimation);
        //System.out.println(lemming.state);

        if(!lemmingEntity.getState().equals(new ExplodingState()) && !LemmingAnimationState.NUKE.equals(lemmingEntity.getCurrentStateAnimation())){

            lemmingEntity.setState(new WaitingState()); // cambia la lógica
            lemmingEntity.setCurrentStateAnimation(LemmingAnimationState.STOPING); // cambia cómo se ve
            ;

            //aca le seteo para que no pasen jajaja
            lemmingEntity.getLevel().getMap().getMapTiles()[tileY][tileX].setTileColor(Color.GREEN);
            lemmingEntity.getLevel().getMap().getMapTiles()[tileY - 1][tileX].setTileColor(Color.GREEN);
            lemmingEntity.getLevel().getMap().getMapTiles()[tileY - 2][tileX].setTileColor(Color.GREEN);
            lemmingEntity.getLevel().getMap().getMapTiles()[tileY - 3][tileX].setTileColor(Color.GREEN);
            lemmingEntity.getLevel().getMap().getMapTiles()[tileY - 4][tileX].setTileColor(Color.GREEN);//aca chusmear pq no me acuerdo que estado de animacion iba
        }else if(!lemmingEntity.getState().equals(new ExplodingState())  && LemmingAnimationState.NUKE.equals(lemmingEntity.getCurrentStateAnimation())){
            lemmingEntity.setCurrentStateAnimation(LemmingAnimationState.NUKE);
        }
        else {
            createExplosion(lemmingEntity.getLevel().getMap().getMapTiles(), tileX,tileY, 7);
            lemmingEntity.setState(new DeadState());
            lemmingEntity.clearAbility();
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

}
