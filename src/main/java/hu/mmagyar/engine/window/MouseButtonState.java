package hu.mmagyar.engine.window;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_RIGHT;

public class MouseButtonState {

    private final boolean[] buttonsPressed;

    public MouseButtonState() {
        this.buttonsPressed = new boolean[8];
        this.clear();
    }

    public boolean getAtIndex(int index) {
        assertIndexBounds(index);
        return buttonsPressed[index];
    }

    public void setAtIndex(int index, boolean newValue) {
        assertIndexBounds(index);
        buttonsPressed[index] = newValue;
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

    public void clear() {
        Arrays.fill(buttonsPressed, false);
    }
}