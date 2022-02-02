package hu.mmagyar.engine.window;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.lwjgl.glfw.GLFW.*;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public class CursorHandler {

    private double xPos;
    private double lastXPos;
    private double yPos;
    private double lastYPos;
    private boolean dragging;
    private MouseButtonState currentMouseButtonState = new MouseButtonState();
    private MouseButtonState lastMouseButtonState = new MouseButtonState();

    private static final Logger logger = LogManager.getLogger(CursorHandler.class);

    public CursorHandler(long windowAddress) {
        this.init(windowAddress);
    }

    protected void init(long windowAddress) {
        initCursorCallback(windowAddress);
        initMouseButtonCallback(windowAddress);
    }

    protected void initCursorCallback(long windowAddress) {
        var setCallback = glfwSetCursorPosCallback(windowAddress, (window, xPos, yPos) -> updatePosition(xPos, yPos));
        if (setCallback == null) {
            throw new IllegalStateException("Could not set cursor position callback");
        }
    }

    protected void initMouseButtonCallback(long windowAddress) {
        var mouseButtonCallback = glfwSetMouseButtonCallback(windowAddress, (window, button, action, mods) -> updateMouseButtonState(button, action));
        if (mouseButtonCallback == null) {
            throw new IllegalStateException("Could not set mouse button callback");
        }
    }

    protected void updateMouseButtonState(int button, int action) {
        if (action == GLFW_PRESS) {
            this.currentMouseButtonState.setAtIndex(button, true);
        } else if (action == GLFW_RELEASE) {
            this.currentMouseButtonState.setAtIndex(button, false);
        } else {
            throw new IllegalArgumentException("Unexpected mouse press action encountered with value:" + action);
        }
    }

    protected void updatePosition(double newXPos, double newYPos) {
        this.setLastXPos(this.xPos);
        this.setLastYPos(this.yPos);
        this.setXPos(newXPos);
        this.setYPos(newYPos);
        logger.debug("New cursor location: (" + newXPos + "," + newYPos + ")");
    }

}
