package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;
    /**
     * Contractor.
     * @param sensor keySensor
     * @param key string 
     * @param animation animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.sensor = sensor;
        this.key = key;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.isAlreadyPressed && this.sensor.isPressed(key)) {
            this.stop = true;
        } else if (!this.sensor.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
        animation.doOneFrame(d);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
