package hu.mmagyar.engine.window;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;

import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;

public class CursorHandler {

    @Getter
    private double xPos;

    @Getter
    private double yPos;

    private static final Logger logger = LogManager.getLogger(CursorHandler.class);

    public CursorHandler(long windowAddress) {
        glfwSetCursorPosCallback(windowAddress, this.cursorCallback());
    }

    private GLFWCursorPosCallbackI cursorCallback() {
        return (window, xPos, yPos) -> updatePosition(xPos, yPos);
    }

    private void updatePosition(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        logger.debug("New cursor location: (" + xPos + "," + yPos + ")");
    }

}
