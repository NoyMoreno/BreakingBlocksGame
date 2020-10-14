package animation;
import biuoop.DrawSurface;
/**
 * interface Animation - A contract that all subclass implements must have.
 * @author noy shriki
 */
public interface Animation {
    /**
     * Animation of one frame.
     * @param d - DrawSurface.
     */
    void doOneFrame(DrawSurface d);
    /**
     * @return if the animation need to stop, else otherwise.
     */
    boolean shouldStop();
}
