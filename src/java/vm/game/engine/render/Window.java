package java.vm.game.engine.render;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import java.vm.game.engine.input.InputHandler;
import java.vm.game.engine.maths.Vector3f;

public class Window {
    private int width;
    private int height;
    private int[] windowX;
    private int[] windowY;
    private String title;
    private long window;
    private long currTime;
    private long time;
    private int framesThisSec;
    private InputHandler input;
    private Vector3f background;
    private GLFWWindowSizeCallback sizeCallback;
    private boolean isResized;
    private boolean isFullscreen;
    private GLFWVidMode videoMode;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        background = new Vector3f(0, 0,0);
    }

    public void create() {
        if (!GLFW.glfwInit()) {
            System.err.println("Error: Unable to initialize GFLW");
            return;
        }

        input = new InputHandler();
        window = GLFW.glfwCreateWindow(width, height, title, isFullscreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);

        if (window == 0) {
            System.err.println("Error: Unable to create window");
            return;
        }

        videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        windowX = new int[] {(videoMode.width() - width) / 2};
        windowY = new int[] {(videoMode.height() - height) / 2};
        GLFW.glfwSetWindowPos(window, windowX[0], windowY[0]);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        createCallbacks();

        GLFW.glfwShowWindow(window);
        GLFW.glfwSwapInterval(0);
    }

    private void createCallbacks() {
        sizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int w, int h) {
                width = w;
                height = h;
                isResized = true;
            }
        };

        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
        GLFW.glfwSetScrollCallback(window, input.getMouseScrollCallback());
        GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
    }

    public void update() {
        if (isResized) {
            GL11.glViewport(0, 0, width, height);
            isResized = false;
        }

        GL11.glClearColor(background.getX(), background.getY(), background.getZ(), 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GLFW.glfwPollEvents();
        framesThisSec++;
        currTime = System.currentTimeMillis();
        if (currTime - 1000 > time) {
            GLFW.glfwSetWindowTitle(window, title + " | Fps: " + framesThisSec + " | " + width + "x" + height);
            time = currTime;
            framesThisSec = 0;
        }
    }

    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public void destroy() {
        input.destroy();
        sizeCallback.free();
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public void setBackgroundColor(float r, float g, float b) {
        background = new Vector3f(r, g, b);
    }

    public void setFullscreen(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        isResized = true;
        if (isFullscreen) {
            width = videoMode.width();
            height = videoMode.height();
            GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, width, height, 0);
        } else {
            width = 2000;
            height = 1500;
            GLFW.glfwSetWindowMonitor(window, 0,(videoMode.width() - width) / 2, (videoMode.height()) / 2, width, height, 0);
        }
    }

    public boolean isFullscreen() {
        return isFullscreen;
    }
}
