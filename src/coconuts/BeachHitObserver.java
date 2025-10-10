/*
 * Course: SWE2410-121
 * Fall 2025-2026
 * File header contains class BeachHitObserver
 * Name: aubryg and mirzaa
 * Created 10/9/2025
 */
package coconuts;

/**
 * Course SWE2410-121
 * Fall 2025-2026
 * Class BeachHitObserver Purpose: Observer for coconuts that reached the beach
 *
 * @author aubryg
 * @version created on 10/9/2025 1:41 PM
 */
public class BeachHitObserver implements Observer {
    private final OhCoconutsGameManager gameManager;
    private final Beach beach;
    public BeachHitObserver(OhCoconutsGameManager gameManager, Beach beach) {
        this.gameManager = gameManager;
        this.beach = beach;
    }
    @Override
    public void update(HitEvent hit) {

    }
}