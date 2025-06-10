package Proyecto.games.Lemmings_game.Model;

import Proyecto.games.Lemmings_game.Utils.Ability;
import Proyecto.games.Lemmings_game.Utils.AbilityClass;
import Proyecto.games.Lemmings_game.Utils.LemmingAnimationState;
import Proyecto.games.Lemmings_game.Utils.LemmingState;

import java.awt.Color;

public class WallAbility extends AbilityClass {

    public WallAbility(){
        super(Ability.STOP);
    }

    @Override
    public void apply(LemmingModel lemming, double delta) {
        int tileX = (lemming.getX() + lemming.getCamX()) / 8;
        int tileY = (lemming.getY()) / 8;

        lemming.setSpeed(0);

        System.out.println(lemming.currentStateAnimation);
        System.out.println(lemming.state);

        if(!lemming.state.equals(LemmingState.EXPLOTING) && !LemmingAnimationState.NUKE.equals(lemming.getCurrentStateAnimation())){

            lemming.setCurrentLeemingState(LemmingAnimationState.STOPING);
            lemming.setStateLemming(LemmingState.WAITING);

            //aca le seteo para que no pasen jajaja
            lemming.getMap().getMapTiles()[tileY][tileX].setTileColor(Color.GREEN);
            lemming.getMap().getMapTiles()[tileY - 1][tileX].setTileColor(Color.GREEN);
            lemming.getMap().getMapTiles()[tileY - 2][tileX].setTileColor(Color.GREEN);
            lemming.getMap().getMapTiles()[tileY - 3][tileX].setTileColor(Color.GREEN);
            lemming.getMap().getMapTiles()[tileY - 4][tileX].setTileColor(Color.GREEN);
        }else if(!lemming.state.equals(LemmingState.EXPLOTING) && LemmingAnimationState.NUKE.equals(lemming.getCurrentStateAnimation())){
            lemming.setCurrentLeemingState(LemmingAnimationState.NUKE);
        }
        else {
            createExplosion(lemming.getMap().getMapTiles(), tileX,tileY, 7);

            lemming.state = LemmingState.DEAD;
            lemming.setAbility(null);
        }


    }

    private void createExplosion(TileModel[][] tiles, int tileX, int tileY, int radius){

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
