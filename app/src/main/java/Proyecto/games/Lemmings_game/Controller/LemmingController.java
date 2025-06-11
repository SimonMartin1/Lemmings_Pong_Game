
// haceme el inicio
package Proyecto.games.Lemmings_game.Controller;

import Proyecto.games.Lemmings_game.Controller.LemmingController;
import Proyecto.games.Lemmings_game.Model.*;

public class LemmingController {

    private LemmingModel lemmingModel;

    public LemmingController(LemmingModel lemmingModel) {
        this.lemmingModel = lemmingModel;
    }

    public boolean isLemmingClicked(int clickX, int clickY) {
        int lemmingX = lemmingModel.getX();
        int lemmingY = lemmingModel.getY();
        
        int halfSize = 8; // radio de sensibilidad al click

        System.out.println("lemming " + lemmingX + lemmingY + " clickeado");
        return clickX >= lemmingX - halfSize && clickX <= lemmingX + halfSize &&
            clickY >= lemmingY - halfSize && clickY <= lemmingY + halfSize;
    }
}
