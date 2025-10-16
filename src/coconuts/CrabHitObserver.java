/*
 * Course: SWE2410-121
 * Fall 2025-2026
 * File header contains class CrabHitObserver
 * Name: aubryg and mirzaa
 * Created 10/9/2025
 */
package coconuts;

/**
 * Course SWE2410-121
 * Fall 2025-2026
 * Class CrabHitObserver Purpose: Observer for when a coconut hits the crab
 *
 * @author aubryg
 * @version created on 10/9/2025 1:40 PM
 */
public class CrabHitObserver implements Observer {
    private final OhCoconutsGameManager gameManager;
    private final Crab crab;
    public CrabHitObserver(OhCoconutsGameManager gameManager, Crab crab) {
        this.crab = crab;
        this.gameManager = gameManager;
    }

    @Override
    public void update(HitEvent hit) {
        IslandObject hitting = hit.getHittingObject();
        HittableIslandObject hittable = hit.getHittableObject();

        // Only kill crab if the hittable IS the crab
        if (hitting.isFalling() && hittable == crab) {
            gameManager.killCrab();
            gameManager.scheduleForDeletion(hittable);
            gameManager.scheduleForDeletion(hitting);
        }
    }
}