package vm.game.engine.render;

import org.lwjgl.glfw.GLFW;
import vm.game.engine.input.InputHandler;
import vm.game.engine.maths.Vector3f;
import vm.game.engine.render.graphics.Mesh;
import vm.game.engine.render.graphics.Renderer;
import vm.game.engine.render.graphics.Vertex;


public class RenderHandler implements Runnable {
    private Window window;
    private int width = 1600;
    private int height = 900;
    private Renderer renderer;
    private Mesh mesh;

    private void init() {
        window = new Window(width, height, "Viddes Game");
        renderer = new Renderer();
        window.setBackgroundColor(1f, 0f, 1f);
        window.create();

        mesh = createHexagon(0.2f);
        mesh.create();
    }

    @Override
    public void run() {
        init();
        while (window.shouldClose() == false && InputHandler.isKeyDown(GLFW.GLFW_KEY_ESCAPE) == false) {
            update();
            render();
            if (InputHandler.isKeyDown(GLFW.GLFW_KEY_F11)) {
                window.setFullscreen(!window.isFullscreen());
            }
        }
        window.destroy();
    }

    private void update() {
        window.update();
    }

    private void render() {
        // Render
        renderer.renderMesh(mesh);
        window.swapBuffers();
    }

    private Mesh createHexagon(float scale) {
        float sideLength = scale;
        float height = (float)Math.sqrt(3) * scale;

        return new Mesh(new Vertex[] {
            new Vertex(new Vector3f(0f, 0f, 0f)),
            new Vertex(new Vector3f(-sideLength / 2, -height / 2, 0f)),
            new Vertex(new Vector3f(sideLength / 2, -height / 2, 0f)),
            new Vertex(new Vector3f(sideLength, 0f, 0f)),
            new Vertex(new Vector3f(sideLength / 2, height / 2, 0f)),
            new Vertex(new Vector3f(-sideLength / 2, height / 2, 0f)),
            new Vertex(new Vector3f(-sideLength, 0f, 0f))
        }, new int[] {
            0,1,2,
            0,2,3,
            0,3,4,
            0,4,5,
            0,5,6,
            0,6,1
        });
    }
}
