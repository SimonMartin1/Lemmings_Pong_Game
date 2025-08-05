package Proyecto.games.New_Pong_game.views;

import Proyecto.games.New_Pong_game.Pong;
import Proyecto.games.New_Pong_game.utils.*;
import Proyecto.games.utils.GameState;
import Proyecto.view.Menu.Game_screen.Game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.function.BooleanSupplier;

/**
 * Gestiona la vista y la interacción de la pantalla de configuración del juego.
 * Esta versión está refactorizada para mejorar la mantenibilidad y legibilidad
 * utilizando Enums para el estado y Rectangles para el layout de la UI.
 */
public class Settings extends Pong_Screens {
    private boolean ListeningKey, WhantToChangeKeys;
    private PlayersKeys KeyToChange;


    public Settings(int width, int height, Pong game) {
        super(width,height,game);
        ListeningKey =false;
        WhantToChangeKeys =false;

        //implementacion de adaptador keypressed para cambiar los controles en configuracion/opciones
        game.getFrame().addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (ListeningKey) {
                    int lastKeyPressed = e.getKeyCode();
                    switch (KeyToChange) {
                        case PLAYER1_UP -> game.getConfig().setPlayerOneUp(lastKeyPressed);
                        case PLAYER2_DOWN -> game.getConfig().setPlayerTwoDown(lastKeyPressed);
                        case PLAYER2_UP -> game.getConfig().setPlayerTwoUp(lastKeyPressed);
                        default -> game.getConfig().setPlayerOneDown(lastKeyPressed);
                    }
                    ListeningKey=false;
                }
            }
        });
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(3));
        g.drawRoundRect(width/2 -140, 145, 400, 40, 20, 20);
        g.drawRoundRect(width/2-140, 325, 95, 35, 20, 20);
        g.drawRoundRect(width/2-140, 280, 95, 35, 20, 20);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Settings", width/2-50 , 70);
        g.setFont(new Font("Arial", Font.BOLD, 18));

        g.drawString("Music", width/2-250 , 125);
        g.drawString(track(), width/2-120 , 125);
        g.drawString("Off", width/2+20 , 125);

        g.drawString("1 Player", width/2-265 , 170);
        g.setColor(Color.WHITE);

        g.drawString("Difficulty", width/2-120 , 170);
        g.drawString("Hard", width/2 , 170);
        g.drawString("Medium", width/2+90 , 170);
        g.drawString("Easy", width/2+200 , 170);

        g.drawString("2 Players", width/2-265 , 215);
        g.drawString("On", width/2-120 , 215);
        g.drawString("Off", width/2-40 , 215);

        g.drawString("WinPoints", width/2-265 , 260);
        g.drawString("15", width/2-120 , 260);
        g.drawString("10", width/2-60 , 260);
        g.drawString("5", width/2 , 260);

        g.drawString("Pitch Skin", width/2-265 , 305);
        g.drawString(pitchSkin(), width/2-125 , 305);

        g.drawString("Ball Skin", width/2-265 , 350);
        g.drawString(ballSkin(), width/2-125 , 350);

        g.drawString("Full Screen", width/2-265 , 395);
        g.drawString("On", width/2-120 , 395);
        g.drawString("Off", width/2-40 , 395);

        g.drawString("Keys", width/2-265 , 440);
        g.drawString("Player1 Up: "+ KeyEvent.getKeyText(game.getConfig().getPlayerOneUp()) +"  Down: "+ KeyEvent.getKeyText(game.getConfig().getPlayerOneDown()), width/2-200 , 440);
        g.drawString("Change Keys", width/2+100 , 440);

        // OPTIONS
        g.drawString("Save", width-325 , (int) (height * .85) + 10);
        g.drawString("Cancel", width-245 , (int) (height * .85) + 10);
        g.drawString("Reset", width-145 , (int) (height * .85) + 10);

        //dibujar estado actual de la confguracion

        drawCurrentSettings(g);
    }

    private String track(){
        String res = "";
        switch (game.getConfig().getTrack()) {
            case TRACK1 -> res="Track 1";
            case TRACK2 -> res="Track 2";
            case TRACK3 -> res="Track 3";
        }
        return res;
    }

    private String pitchSkin(){
        String res="";
        switch (game.getConfig().getPitchSkin()){
            case DEFAULT -> res="Default";
            case BASKET -> res="Basket";
        }
        return res;
    }

    private String ballSkin(){
        String res="";
        switch (game.getConfig().getBallSkin()){
            case DEFAULT -> res="Default";
            case CRAZY -> res="Crazy";
        }
        return res;
    }
    public void drawCurrentSettings(Graphics2D g){

        //width/2-80, 220, 40, 40
        g.fillRect(width/2-80, 220, 40, 40);
        g.fillRect(width/2-140, 220, 40, 40);
        g.fillRect(width/2-20, 220, 40, 40);

        if(game.getConfig().isVersusIA()){
            switch(game.getConfig().getDifficult()){
                case HARD -> activeButtonEffect(g, width/2, 170,"Hard", width/2-15, 145,70,40,20,20);
                case MEDIUM -> activeButtonEffect(g, width/2+90 , 170,"Medium", width/2+85, 145, 80, 40,20,20);
                default -> activeButtonEffect(g, width/2+200 , 170,"Easy" ,width/2+190, 145, 70, 40,20,20);
            }
        }

        if(!game.getConfig().isVersusIA()){
            activeButtonEffect(g, width/2-120 , 215,"On", width/2-125, 195, 40, 30,20,20);
            g.setColor(Color.WHITE);
            g.drawString("Player2 Up: "+KeyEvent.getKeyText(game.getConfig().getPlayerTwoUp())+"  Down: "+ KeyEvent.getKeyText(game.getConfig().getPlayerTwoDown()), width/2-200 , 485);
        }


        switch (game.getConfig().getMaxPoints()){
            case 3 ->  activeButtonEffect(g,width/2-120, 260,"15",width/2-125, 240, 30, 30, 10, 10);
            case 2 ->  activeButtonEffect(g,width/2-60, 260 ,"10",width/2-65, 240, 30, 30, 10, 10);
            default -> activeButtonEffect(g, width/2, 260,"5", width/2-10, 240, 30, 30,10,10);
        }


        if(game.getConfig().isMusicOff()){
            activeButtonEffect(g,width/2+25, 125,"Off", width/2+20, 105, 40, 30, 10, 10);
        }
        else{
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(3));
            g.drawRoundRect(width/2-140, 100, 100, 40, 20, 20);

        }


        if(game.isFullscreen()){
            activeButtonEffect(g, width/2-120 , 395,"On", width/2-125, 375, 40, 30, 10, 10);
        }else{
            activeButtonEffect(g, width/2-40 , 395,"Off", width/2-45, 375, 40, 30, 10, 10);
        }
        if(WhantToChangeKeys){
            g.setColor(Color.BLACK);
            g.fillRect(width/2+100 ,400, 125,50);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
            g.fillRect(0, 0, width, height);
            g.setColor(Color.WHITE);
            g.fillRoundRect(width/2-70, 125, 150, 40, 20, 20);
            g.fillRoundRect(width/2-80, 185, 65, 40, 20, 20);
            g.fillRoundRect(width/2+20, 185, 65, 40, 20, 20);
            g.fillRoundRect(width/2-70, 250, 150, 40, 20, 20);
            g.fillRoundRect(width/2+20, 310, 65, 40, 20, 20);
            g.fillRoundRect(width/2-80, 310, 65, 40, 20, 20);
            g.fillRoundRect(width/2+75, 405, 150, 40, 20, 20);
            g.setColor(Color.BLACK);
            g.drawString("Cancel", width/2+120 , 430);
            g.drawString("Player 1", width/2-30, 150);
            g.drawString("Player 2", width/2-30 , 275);
            g.drawString("Up", width/2+40 , 210);
            g.drawString("Down", width/2-75 , 210);
            g.drawString("Up", width/2+40 , 335);
            g.drawString("Down", width/2-75 , 335);
        }
        if(ListeningKey){
            g.setColor(Color.BLACK);
            g.fillRect(0 ,0, width,height);
            g.setColor(Color.WHITE);
            g.drawString("Select the new key", width/2-70, 150);
        }

        
    }

    //Dibuja la animacion de boton activo dado un componete grafico, dimensiones y coordenadas en la pantalla

    public void activeButtonEffect(Graphics2D g, int xtext, int ytext, String text ,int xfill,int yfill, int width, int height, int arcx, int arcy){
        g.setColor(Color.WHITE);
        g.fillRoundRect(xfill, yfill, width, height, arcx, arcy);
        g.setColor(new Color(0, 0, 0, 255));
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString(text, xtext , ytext);
    }

    @Override
    public void update(double delta){
        // Detectar eventos y cambiar las config del game correspondientes...
        Map<BooleanSupplier, Runnable> actions = new LinkedHashMap<>();

        actions.put(this::isHardClicked, () -> {
            game.getConfig().setDifficult(Difficult.HARD);
            game.getConfig().setVersusIA(true);
        });

        actions.put(this::isMediumClicked, () -> {
            game.getConfig().setDifficult(Difficult.MEDIUM);
            game.getConfig().setVersusIA(true);
        });

        actions.put(this::isEasyClicked, () -> {
            game.getConfig().setDifficult(Difficult.EASY);
            game.getConfig().setVersusIA(true);
        });

        actions.put(this::isOnClicked, () -> game.getConfig().setVersusIA(false));

        actions.put(this::isWinPoints15Clicked, () -> game.getConfig().setMaxPoints(3));

        actions.put(this::isWinPoints10Clicked, () -> game.getConfig().setMaxPoints(2));

        actions.put(this::isWinPoints5Clicked, () -> game.getConfig().setMaxPoints(1));

        actions.put(this::isOffClicked, () -> game.getConfig().setMusicOff(true));

        actions.put(this::isFullScreenClicked, () -> game.getConfig().setFullscreen(true));

        actions.put(this::isFullScreenOffClicked, () -> game.getConfig().setFullscreen(false));

        actions.put(this::isPitchSkinClicked, () -> {
            PitchSkin nextPitchSkin;
            if(game.getConfig().getPitchSkin().equals(PitchSkin.DEFAULT)) {
                nextPitchSkin=PitchSkin.BASKET;
            }
            else{
                nextPitchSkin=PitchSkin.DEFAULT;
            }
            game.getConfig().setPitchSkin(nextPitchSkin);
        });

        actions.put(this::isBallSkinClicked, () -> {
            BallSkin nextBallSkin;
            if(game.getConfig().getBallSkin().equals(BallSkin.DEFAULT)) {
                nextBallSkin=BallSkin.CRAZY;
            }
            else{
                nextBallSkin=BallSkin.DEFAULT;
            }
            game.getConfig().setBallSkin(nextBallSkin);
        });

        //Cuando selecciona cambiar alguna tecla le muestra las opciones (Cambia al estado "WhantToChangeKeys"),
        // cuando selecciona alguna opcion almacena ese valor y habilita al KeyPressed (Cambia al estado "ListeningKey")
        // el keypressed mapea la tecla y en base a la opcion seleccionada la cambia

        actions.put(this::isChangeKeysClicked, () -> WhantToChangeKeys = true);
        actions.put(this::isCancelSetKeysClicked, () -> WhantToChangeKeys = false);

        actions.put(this::isPlayer1UpClicked, () -> {
            ListeningKey = true;
            KeyToChange=PlayersKeys.PLAYER1_UP;
        });
        actions.put(this::isPlayer1DownClicked, () -> {
            ListeningKey = true;
            KeyToChange=PlayersKeys.PLAYER1_DOWN;
        });
        actions.put(this::isPlayer2UpClicked, () -> {
            ListeningKey = true;
            KeyToChange=PlayersKeys.PLAYER2_UP;
        });
        actions.put(this::isPlayer2DownClicked, () -> {
            ListeningKey = true;
            KeyToChange=PlayersKeys.PLAYER2_DOWN;
        });

        actions.put(this::isTrackNameClicked, () -> {
            if (game.getConfig().isMusicOff()) {
                game.getConfig().setMusicOff(false);
            } else {
                Track nextTrack = switch (game.getConfig().getTrack()) {
                    case TRACK1 -> Track.TRACK2;
                    case TRACK2 -> Track.TRACK3;
                    default -> Track.TRACK1;
                };
                game.getConfig().setTrack(nextTrack);
            }
        });

        actions.put(this::isSaveClicked, () -> game.setGameState(GameState.PRE_MENU));

        actions.put(this::isResetClicked, () -> game.resetConfig());

        actions.put(this::isCancelClicked, game::cancelConfig);

        for (Map.Entry<BooleanSupplier, Runnable> entry : actions.entrySet()) {
            if (entry.getKey().getAsBoolean()) {
                entry.getValue().run();
                break;
            }
        }
    }

    private boolean isMouseOverClickArea(int x, int y, int width, int height){
        int mx = game.getMouse().getX();
        int my = game.getMouse().getY();
        return mx >= x && mx <= x + width && my >= y && my <= y + height && game.getMouse().isLeftButtonPressed();
    }

    public boolean isTrackNameClicked() {
        return isMouseOverClickArea(width/2-120, 85, 105, 25);
    }

    // --- OFF ---
    public boolean isOffClicked() {
        return isMouseOverClickArea(width/2-5, 40, 60, 60);
    }

    // --- HARD ---
    public boolean isHardClicked() {
        return isMouseOverClickArea(width/2-15, 130, 80, 45);
    }

    // --- MEDIUM ---
    public boolean isMediumClicked() {
        return isMouseOverClickArea(width/2+85, 130, 80, 45);
    }

    // --- EASY ---
    public boolean isEasyClicked() {
        return isMouseOverClickArea(width/2+195, 130, 80, 45);
    }

    // --- ON ---
    public boolean isOnClicked() {
        return isMouseOverClickArea(width/2-160, 175, 60, 40);
    }

    // --- WINPOINTS 15 ---
    public boolean isWinPoints15Clicked() {
        return isMouseOverClickArea(width/2-140, 220, 40, 40);
    }

    // --- WINPOINTS 10 ---
    public boolean isWinPoints10Clicked() {
        return isMouseOverClickArea(width/2-80, 220, 40, 40);
    }

    // --- WINPOINTS 5 ---
    public boolean isWinPoints5Clicked() {
        return isMouseOverClickArea(width/2-20, 220, 40, 40);
    }


    public boolean isFullScreenClicked() {
        return isMouseOverClickArea(width/2-120, height/2+50, 30, 30);
    }

    public boolean isFullScreenOffClicked() {
        return isMouseOverClickArea(width/2-60, 375, 60, 30);
    }

    public boolean isPitchSkinClicked() {
        return  isMouseOverClickArea(width/2-140, 260, 45, 35);
    }

    public boolean isBallSkinClicked() {
        return isMouseOverClickArea(width/2-140, 290, 85, 35);
    }

    public boolean isChangeKeysClicked() {
        return isMouseOverClickArea(width/2+100,390,70,30);
    }

    public boolean mouseTrackerSetKeys(int x, int y, int width,int height){
        int mx = game.getMouse().getX();
        int my = game.getMouse().getY();
        return mx >= x && mx <= x + width && my >= y && my <= y + height && game.getMouse().isLeftButtonPressed();
    }

    public boolean isCancelSetKeysClicked() {
        return mouseTrackerSetKeys(width/2+100, 381, 70,45);
    }

    // --- Change Keys Events --

    public boolean isPlayer1UpClicked() {
        return mouseTrackerSetKeys(width/2+20, 170, 65,40);
    }

    public boolean isPlayer1DownClicked() {
        return mouseTrackerSetKeys(width/2-80, 170, 65,40);
    }

    public boolean isPlayer2UpClicked() {
        return mouseTrackerSetKeys(width/2+20, 292, 65,40);
    }

    public boolean isPlayer2DownClicked() {
        return mouseTrackerSetKeys(width/2-80, 292, 65,40);
    }

    // -----------------

    // --- SAVE ---
    public boolean isSaveClicked() {
        return isMouseOverClickArea(width-325, (int) (height * .85) - 20, 30, 30);
    }

    // --- CANCEL ---
    public boolean isCancelClicked() {
        return isMouseOverClickArea(width-245, (int) (height * .85), 60, 30);
    }

    // --- RESET ---
    public boolean isResetClicked() {
        return isMouseOverClickArea(width-145, (int) (height * .85), 60, 30);
    }





}