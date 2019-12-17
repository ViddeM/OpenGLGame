package vm.game.engine.render;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class Window {
    private int width;
    private int height;
    private String title;
    private long window;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void create() {
        if (!GLFW.glfwInit()) {
            System.err.println("Error: Couldn't initialize GLFW");
            System.exit(-1);
        }

        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);

        window = GLFW.glfwCreateWindow(width, height, title, 0, 0);

        if (window == 0) {
            System.err.println("Error: Window couldn't be created");
            System.exit(-1);
        }

        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);

        GLFW.glfwShowWindow(window);
    }

    public boolean closed() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public void update() {
        GLFW.glfwPollEvents();
    }

    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }
}
