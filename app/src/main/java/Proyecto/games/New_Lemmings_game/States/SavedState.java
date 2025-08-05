package Proyecto.games.New_Lemmings_game.States;

import Proyecto.games.New_Lemmings_game.LemmingState;
import Proyecto.games.New_Lemmings_game.Lemming_Entity;

public class SavedState implements LemmingState {


        @Override
        public void update(Lemming_Entity lemming, double delta) { }

        @Override
        public void onEnter(Lemming_Entity lemming) {
            lemming.getLevel().sumSavedLemmings();
            lemming.setActivite(false);
        }

        @Override
        public void onExit(Lemming_Entity lemming) { }

    }
