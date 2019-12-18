package vm.game.engine.render;

import org.lwjgl.glfw.GLFW;
import vm.game.engine.input.InputHandler;

public class RenderHandler implements Runnable {
    private Window window;
    private int width = 1600;
    private int height = 900;

    @Override
    public void run() {
        window = new Window(width, height, "Viddes Game");
        window.create();
        window.setBackgroundColor(0.8f, 0.3f, 0.5f);
        window.setFullscreen(true);
        System.out.println("ASD");
        while (window.shouldClose() == false && InputHandler.isKeyDown(GLFW.GLFW_KEY_ESCAPE) == false) {
            render();
            if (InputHandler.isKeyDown(GLFW.GLFW_KEY_F11)) {
                window.setFullscreen(!window.isFullscreen());
            }
        }
    }

    private void render() {
        // Render
        window.update();
        window.swapBuffers();
    }
}
