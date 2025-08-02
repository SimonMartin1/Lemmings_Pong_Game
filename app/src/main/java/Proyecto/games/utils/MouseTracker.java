package Proyecto.games.utils;

import com.entropyinteractive.Mouse;

public abstract class MouseTracker {


        protected int width,height;
        protected boolean prevMousePressed;
        protected Mouse m;


        protected MouseTracker(int width, int height,Mouse mouse){
            this.width = width;
            this.height = height;
            this.m = mouse;
        }

        protected boolean isMouseJustPressed() {
            boolean justPressed = m.isLeftButtonPressed() && !prevMousePressed;
            prevMousePressed = m.isLeftButtonPressed();
            return  justPressed;
        }

        protected boolean mouseTracker(int x, int y, int width,int height){
            int mx = m.getX();
            int my = m.getY();
            return mx >= x && mx <= x + width && my >= y && my <= y + height && isMouseJustPressed();
        }

}
