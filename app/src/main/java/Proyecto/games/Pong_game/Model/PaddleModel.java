package Proyecto.games.Pong_game.Model;

public class PaddleModel extends Entity {
    private final int PADDLE_HEIGHT = 100;
    private final int WINDOW_HEIGHT = 600;
    private final int MOVE_AMOUNT = 10;
    private final int PADDING = 10;


    public PaddleModel(int initialY) {
        this.y = initialY;
    }

    public void moveUp() {
        if (y - MOVE_AMOUNT >= 0) {
            y -= MOVE_AMOUNT;
        } else {
            y = PADDING;
        }
    }

    public void moveDown() {
        if (y + PADDLE_HEIGHT + MOVE_AMOUNT <= WINDOW_HEIGHT ) {
            y += MOVE_AMOUNT;
        } else {
            y = WINDOW_HEIGHT - PADDLE_HEIGHT - PADDING;
        }
    }

    public int getY() {
        return y;
    }
}
