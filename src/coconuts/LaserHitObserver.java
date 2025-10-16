/*
 * Course: SWE2410-121
 * Fall 2025-2026
 * File header contains class LaserHitObserver
 * Name: aubryg and mirzaa
 * Created 10/9/2025
 */
package coconuts;

/**
 * Course SWE2410-121
 * Fall 2025-2026
 * Class LaserHitObserver Purpose: Observes when a laser hits the coconut
 *
 * @author aubryg
 * @version created on 10/9/2025 1:39 PM
 */
public class LaserHitObserver implements Observer {
    private final OhCoconutsGameManager gameManager;
    public LaserHitObserver(OhCoconutsGameManager gameManager) {
        this.gameManager = gameManager;
    }
    @Override
    public void update(HitEvent hit) {
        IslandObject hitting = hit.getHittingObject();
        HittableIslandObject hittable = hit.getHittableObject();

        // Laser hits coconut
        if (hitting.isLaser() && hittable.isFalling()) {
            gameManager.scheduleForDeletion(hittable);
            gameManager.scheduleForDeletion(hitting);
            gameManager.coconutDestroyed();
        }
    }

}