package vm.game;

import vm.game.engine.render.Window;

public class Main {
    public static void main(String[] args) {
         Window window = new Window(800, 600, "Viddes First Game");
         window.create();

         while (window.closed() == false) {
             window.update();
             System.out.println("Hey");
             window.swapBuffers();
         }
    }
}
