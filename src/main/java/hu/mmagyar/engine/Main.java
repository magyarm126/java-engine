package hu.mmagyar.engine;

import hu.mmagyar.engine.window.Window;

/***
 * Main class
 */
public class Main {
    /***
     * Entry point of the application
     * @param args Application arguments
     */
    public static void main(String[] args) {
        Window window = Window.getInstance();
        window.run();
    }
}
