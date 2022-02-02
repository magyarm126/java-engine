package hu.mmagyar.engine.mouse;

import lombok.Data;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;

@Data
public final class MouseButtonState {

    @Getter
    private boolean[] buttonsPressed;

    private static final Logger logger = LogManager.getLogger(MouseButtonState.class);

    public MouseButtonState() {
        this.buttonsPressed = new boolean[8];
        this.clear();
    }

    public void setNewState(MouseButtonState newState) {
        this.buttonsPressed = Arrays.copyOf(newState.getButtonsPressed(), this.buttonsPressed.length);
    }

    public boolean getAtIndex(int index) {
        this.assertIndexBounds(index);
        return this.buttonsPressed[index];
    }

    public void setAtIndex(int buttonIndex, boolean isPressed) {
        this.assertIndexBounds(buttonIndex);
        this.buttonsPressed[buttonIndex] = isPressed;
        logger.debug("New pressed button state at (index,state): (" + buttonIndex + "," + isPressed + ")");
    }

    public void setAtIndex(int buttonIndex, int actionIndex) {
        this.assertActionInBounds(actionIndex);
        this.setAtIndex(buttonIndex, actionIndex == GLFW_PRESS);
    }

    public boolean getLeftButton() {
        return this.getAtIndex(GLFW_MOUSE_BUTTON_LEFT);
    }

    public void setLeftButton(boolean newValue) {
        this.setAtIndex(GLFW_MOUSE_BUTTON_LEFT, newValue);
    }

    public boolean getMiddleButton() {
        return this.getAtIndex(GLFW_MOUSE_BUTTON_MIDDLE);
    }

    public void setMiddleButton(boolean newValue) {
        this.setAtIndex(GLFW_MOUSE_BUTTON_MIDDLE, newValue);
    }

    public boolean getRightButton() {
        return this.getAtIndex(GLFW_MOUSE_BUTTON_RIGHT);
    }

    public void setRightButton(boolean newValue) {
        this.setAtIndex(GLFW_MOUSE_BUTTON_RIGHT, newValue);
    }

    private void assertIndexBounds(int buttonIndex) {
        if (buttonIndex < 0 || 7 < buttonIndex) {
            throw new IllegalArgumentException("Mouse button index is not within bounds [0,7], actual value:" + buttonIndex);
        }
    }

    private void assertActionInBounds(int actionIndex) {
        if (actionIndex == GLFW_PRESS || actionIndex == GLFW_RELEASE) {
            return;
        }
        throw new IllegalArgumentException("Unexpected mouse press action encountered with value:" + actionIndex);
    }

    public void clear() {
        Arrays.fill(buttonsPressed, false);
    }
}