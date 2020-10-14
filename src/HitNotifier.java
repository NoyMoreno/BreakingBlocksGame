package game;
/**
 * interface HitNotifier - using observer pattern - notify.
 * @author noy shriki
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl - some object to add as listener.
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl - some object to remove from listener.
     */
    void removeHitListener(HitListener hl);
}
