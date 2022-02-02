package hu.mmagyar.engine.mouse;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Data
public class MouseScrollState {
    private double xScroll;
    private double yScroll;

    private static final Logger logger = LogManager.getLogger(MouseScrollState.class);

    public void updateScroll(double newXScroll, double newYScroll) {
        this.setXScroll(newXScroll);
        this.setYScroll(newYScroll);
        logger.debug("New cursor scroll: (" + newXScroll + "," + newYScroll + ")");
    }

    public void updateState(MouseScrollState newState) {
        this.xScroll = newState.getXScroll();
        this.yScroll = newState.getYScroll();
    }
}
