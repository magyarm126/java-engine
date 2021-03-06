package hu.mmagyar.engine.window;

import hu.mmagyar.engine.mouse.MouseHandler;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private final int width;
    private final int height;
    private final String title;

    private long windowMemoryAddress;

    private MouseHandler mouseHandler;

    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "Test engine";
    }

    private static final class InstanceHolder {
        private static final Window instance = new Window();
    }

    public static Window getInstance() {
        return InstanceHolder.instance;
    }

    public void run() {
        System.out.print("Hello LWJGL" + Version.getVersion());
        init();
        loop();
        cleanup();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to init glfw!");
        }
        preConfigure();
        createWindow();
        postConfigure();
    }

    private void preConfigure() {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);
    }

    private void createWindow() {
        this.windowMemoryAddress = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (this.windowMemoryAddress == NULL) {
            throw new IllegalStateException("Failed to create the window!");
        }
    }

    private void postConfigure() {
        glfwMakeContextCurrent(this.windowMemoryAddress);

        //Enable V-Sync
        glfwSwapInterval(1);

        glfwShowWindow(this.windowMemoryAddress);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        registerCursorHandler();
    }

    private void registerCursorHandler() {
        this.mouseHandler = new MouseHandler(this.windowMemoryAddress);
    }

    private void loop() {
        while (!glfwWindowShouldClose(this.windowMemoryAddress)) {
            glfwPollEvents();

            glClearColor(0.0f, 0.3f, 0.0f, 0.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(this.windowMemoryAddress);
        }
    }

    private void cleanup() {
        glfwFreeCallbacks(this.windowMemoryAddress);
        glfwDestroyWindow(this.windowMemoryAddress);
    }
}
