package hu.mmagyar.engine.window;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public class MousePositionState {
    private double xPos;
    private double yPos;

    private static final Logger logger = LogManager.getLogger(MousePositionState.class);

    public void updatePosition(double newXPos, double newYPos) {
        this.setXPos(newXPos);
        this.setYPos(newYPos);
        logger.debug("New cursor location: (" + newXPos + "," + newYPos + ")");
    }

    public void updateState(MousePositionState newState) {
        this.xPos = newState.getXPos();
        this.yPos = newState.getYPos();
    }
}
