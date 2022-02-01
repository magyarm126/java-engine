package hu.mmagyar.engine.window;

import lombok.Getter;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;


import java.util.logging.Level;
import java.util.logging.Logger;

import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;

public class CursorHandler {

    @Getter
    private double xPos;

    @Getter
    private double yPos;

    private static final Logger logger = Logger.getLogger(CursorHandler.class.getName());

    public CursorHandler(long windowAddress) {
        glfwSetCursorPosCallback(windowAddress, this.cursorCallback());
    }

    private GLFWCursorPosCallbackI cursorCallback() {
        return (window, xPos, yPos) -> updatePosition(xPos, yPos);
    }

    private void updatePosition(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        logger.log(Level.INFO, "New cursor location: (" + xPos + "," + yPos + ")");
    }

}
