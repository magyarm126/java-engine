package hu.mmagyar.engine.mouse;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.lwjgl.glfw.GLFW.*;

@Data
public class MouseHandler {

    private MousePositionState oldPosition = new MousePositionState();
    private MousePositionState currentPosition = new MousePositionState();

    private MouseButtonState oldButtons = new MouseButtonState();
    private MouseButtonState currentButtons = new MouseButtonState();

    private MouseScrollState oldScroll = new MouseScrollState();
    private MouseScrollState currentScroll = new MouseScrollState();

    private static final Logger logger = LogManager.getLogger(MouseHandler.class);

    public MouseHandler(long windowAddress) {
        this.init(windowAddress);
    }

    protected void init(long windowAddress) {
        initCursorCallback(windowAddress);
        initMouseButtonCallback(windowAddress);
        initMouseScrollCallback(windowAddress);
    }

    protected void initCursorCallback(long windowAddress) {
        var setCallback = glfwSetCursorPosCallback(windowAddress, (window, xPos, yPos) -> this.updatePosition(xPos, yPos));
        if (setCallback == null) {
            throw new IllegalStateException("Could not set cursor position callback");
        }
    }

    protected void initMouseButtonCallback(long windowAddress) {
        var setCallback = glfwSetMouseButtonCallback(windowAddress, (window, button, action, mods) -> this.updateMouseButtonState(button, action));
        if (setCallback == null) {
            throw new IllegalStateException("Could not set mouse button callback");
        }
    }

    protected void initMouseScrollCallback(long windowAddress) {
        var setCallback = glfwSetScrollCallback(windowAddress, (window, xOffset, yOffset) -> this.updateScroll(xOffset, yOffset));
        if (setCallback == null) {
            throw new IllegalStateException("Could not set mouse scroll callback");
        }
    }

    protected void updateMouseButtonState(int button, int action) {
        this.oldButtons.setNewState(this.currentButtons);
        this.currentButtons.setAtIndex(button, action);
    }

    protected void updatePosition(double newXPos, double newYPos) {
        this.getOldPosition().updateState(this.getCurrentPosition());
        this.getCurrentPosition().updatePosition(newXPos, newYPos);
    }

    protected void updateScroll(double xOffset, double yOffset) {
        this.getOldScroll().updateState(this.getCurrentScroll());
        this.getCurrentScroll().updateScroll(xOffset, yOffset);
    }

}
