package Proyecto.games.New_Lemmings_game;

public class SavedState implements LemmingState{

    @Override
    public void update(Lemming_Entity lemmingEntity, double delta) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onEnter(Lemming_Entity lemmingEntity) {
        lemmingEntity.getLevel().sumSavedLemmings();
    }

    @Override
    public void onExit(Lemming_Entity lemmingEntity) {
        // TODO Auto-generated method stub
    }
    
}
