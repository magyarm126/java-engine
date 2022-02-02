package hu.mmagyar.engine.window;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;

@Data
public class MouseHandler {

    private MousePositionState oldPosition = new MousePositionState();
    private MousePositionState currentPosition = new MousePositionState();

    private MouseButtonState oldButtons = new MouseButtonState();
    private MouseButtonState currentButtons = new MouseButtonState();

    private static final Logger logger = LogManager.getLogger(MouseHandler.class);

    public MouseHandler(long windowAddress) {
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
        this.oldButtons.setNewState(this.currentButtons);
        this.currentButtons.setAtIndex(button, action);
    }

    protected void updatePosition(double newXPos, double newYPos) {
        this.getOldPosition().updateState(this.getCurrentPosition());
        this.getCurrentPosition().updatePosition(newXPos, newYPos);
    }

}
