/*
 * Course: SWE2410-121
 * Fall 2025-2026
 * File header contains class Beach
 * Name: mirzaa and aubryg
 * Created 10/9/2025
 */
package coconuts;

// the beach catches (hits) coconuts and increases the coconut score
// This is a domain class; do not introduce JavaFX or other GUI components here
public class Beach extends HittableIslandObject {
    public Beach(OhCoconutsGameManager game, int skyHeight, int islandWidth) {
        super(game, 0, skyHeight, islandWidth, null);
    }

    @Override
    public void step() { /* do nothing for now. */ }

    @Override
    public boolean isGroundObject() { return true; }


}

