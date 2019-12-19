package java.vm.game.engine.render;

import org.lwjgl.glfw.GLFW;
import java.vm.game.engine.input.InputHandler;
import java.vm.game.engine.maths.Vector3f;
import java.vm.game.engine.render.graphics.Mesh;
import java.vm.game.engine.render.graphics.Renderer;
import java.vm.game.engine.render.graphics.Vertex;

public class RenderHandler implements Runnable {
    private Window window;
    private int width = 1600;
    private int height = 900;
    private Renderer renderer;
    private Mesh mesh;

    @Override
    public void run() {
        float offX = (float)Math.sin(60);
        float offY = (float)Math.cos(60);
        mesh = new Mesh(new Vertex[] {
            new Vertex(new Vector3f(0.5f, offY, 0f)),
            new Vertex(new Vector3f(0f, 0f, 0f)),
            new Vertex(new Vector3f(1f, 0f, 0f)),
            new Vertex(new Vector3f(offX, offY, 0f)),
            new Vertex(new Vector3f(0f, 2f * offY, 0f)),
            new Vertex(new Vector3f(1f, 2f * offY, 0f)),
            new Vertex(new Vector3f(-offX, offY, 0f))
        }, new int[] {
            0, 1, 2,
            0, 2, 3,
            0, 3, 4,
            0, 4, 5,
            0, 5, 6
        });
        mesh.create();
        renderer = new Renderer();

        window = new Window(width, height, "Viddes Game");
        window.create();
        window.setBackgroundColor(1f, 0f, 1f);
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
        renderer.renderMesh(mesh);
        window.swapBuffers();
    }
}
