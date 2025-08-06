package Proyecto.games.New_Lemmings_game.Abilities;

import Proyecto.games.New_Lemmings_game.States.DeadState;
import Proyecto.games.New_Lemmings_game.Lemming_Entity;
import Proyecto.games.New_Lemmings_game.States.WaitingState;
import Proyecto.games.New_Lemmings_game.States.WalkingState;
import Proyecto.games.New_Lemmings_game.utils.Ability;
import Proyecto.games.New_Lemmings_game.utils.LemmingAnimationState;

import java.awt.*;

public class DigAbility extends AbilityClass {
    

    public DigAbility(){
        super(Ability.DIGGER);
    }

    @Override
    public void apply(Lemming_Entity lemmingEntity, double delta) {

        lemmingEntity.setCurrentStateAnimation(LemmingAnimationState.DIGGING);

        int tileX = ((lemmingEntity.getX()) / 8);
        int tileY = (lemmingEntity.getY()) / 8;


        try {

            if (!Color.BLACK.equals(lemmingEntity.getLevel().getMap().getMapTiles()[tileY + 3][tileX].getColor())) {

                lemmingEntity.getLevel().getMap().getMapTiles()[tileY][tileX].setTileColor(Color.BLACK);
                lemmingEntity.getLevel().getMap().getMapTiles()[tileY + 1][tileX].setTileColor(Color.BLACK);

                lemmingEntity.getLevel().getMap().getMapTiles()[tileY][tileX + 1].setTileColor(Color.BLACK);
                lemmingEntity.getLevel().getMap().getMapTiles()[tileY + 1][tileX + 1].setTileColor(Color.BLACK);

                lemmingEntity.setY(lemmingEntity.getY() + 1); // baja el lemming un poco
            } else {

                lemmingEntity.getLevel().getMap().getMapTiles()[tileY][tileX].setTileColor(Color.BLACK);
                lemmingEntity.getLevel().getMap().getMapTiles()[tileY + 1][tileX].setTileColor(Color.BLACK);

                lemmingEntity.getLevel().getMap().getMapTiles()[tileY][tileX + 1].setTileColor(Color.BLACK);
                lemmingEntity.getLevel().getMap().getMapTiles()[tileY + 1][tileX + 1].setTileColor(Color.BLACK);

                lemmingEntity.getLevel().getMap().getMapTiles()[tileY + 1][tileX].setTileColor(Color.BLACK);
                lemmingEntity.getLevel().getMap().getMapTiles()[tileY + 1][tileX + 1].setTileColor(Color.BLACK);

                lemmingEntity.getLevel().getMap().getMapTiles()[tileY + 2][tileX].setTileColor(Color.BLACK);
                lemmingEntity.getLevel().getMap().getMapTiles()[tileY + 2][tileX + 1].setTileColor(Color.BLACK);

                lemmingEntity.setAbility(null);
            }
        }
        catch (Exception e) {
            lemmingEntity.setState(new DeadState());
        }
    }

    @Override
    public boolean canUseAbility(Lemming_Entity lemming) {
        return lemming.getState() instanceof WalkingState;
    }
}
