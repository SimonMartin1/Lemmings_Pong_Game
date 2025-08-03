package Proyecto.games.utils;

import com.entropyinteractive.Keyboard;
import com.entropyinteractive.Mouse;

import java.awt.event.KeyEvent;

public abstract class InputEventsTracker {


        protected int width,height;
        protected boolean prevMousePressed;
        protected Mouse m;
        protected Keyboard k;


        protected InputEventsTracker(int width, int height, Mouse mouse, Keyboard k){
            this.width = width;
            this.height = height;
            this.m = mouse;
            this.k = k;
            prevMousePressed = false;
        }


        protected boolean isMouseOverClickArea(int x, int y, int width, int height){
            int mx = m.getX();
            int my = m.getY();
            return mx >= x && mx <= x + width && my >= y && my <= y + height && m.isLeftButtonPressed();
        }

        protected boolean detecPlay(int x, int y, int width,int height){
            return isMouseOverClickArea(width / 2 - 100, 300, 200, 60);
        }

        protected boolean detecSettings(int x, int y, int width,int height){
            return isMouseOverClickArea(width - 250, height - 110, 150, 80);
        }

        protected boolean detectPlayKeyboard(){
         return k.isKeyPressed(KeyEvent.VK_ENTER);
        }

        protected boolean detectSettingsKeyboard(){
        return k.isKeyPressed(KeyEvent.VK_C);
        }

        protected boolean detectPauseKeyboard(){
        return k.isKeyPressed(KeyEvent.VK_P);
        }

}
