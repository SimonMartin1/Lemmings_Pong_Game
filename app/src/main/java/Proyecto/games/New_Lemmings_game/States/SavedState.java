package Proyecto.games.New_Lemmings_game.States;

import Proyecto.games.New_Lemmings_game.Lemming_Entity;
import Proyecto.games.utils.SoundManager;

public class SavedState implements LemmingState {


        @Override
        public void update(Lemming_Entity lemming, double delta, SoundManager soundManager) { }

        @Override
        public void onEnter(Lemming_Entity lemming, SoundManager soundManager) {
            lemming.getLevel().sumSavedLemmings();
            lemming.setActivite(false);
        }

        @Override
        public void onExit(Lemming_Entity lemming, SoundManager soundManager) { }

    }
