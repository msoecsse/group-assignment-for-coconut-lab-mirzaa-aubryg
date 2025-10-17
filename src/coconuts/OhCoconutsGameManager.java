/*
 * Course: SWE2410-121
 * Fall 2025-2026
 * File header contains class OhCoconutsGameManager
 * Name: aubryg and mirzaa
 * Created 10/9/2025
 */
package coconuts;

// https://stackoverflow.com/questions/42443148/how-to-correctly-separate-view-from-model-in-javafx

import javafx.scene.layout.Pane;

import java.util.Collection;
import java.util.LinkedList;


/**
 * Course SWE2410-121
 * Fall 2025-2026
 * Class OhCoconutsGameManager Purpose: This class manages the game, including tracking all island objects and detecting when they hit
 *
 * @editors aubryg and mirzaa
 * @version edited on 10/10/2025 12:20 PM
 */
public class OhCoconutsGameManager {
    private final Collection<IslandObject> allObjects = new LinkedList<>();
    private final Collection<HittableIslandObject> hittableIslandSubjects = new LinkedList<>();
    private final Collection<IslandObject> scheduledForRemoval = new LinkedList<>();
    private final int height, width;
    private final int DROP_INTERVAL = 10;
    private final int MAX_TIME = 100;
    private final HitEvent subjectHitEvent = new HitEvent();
    private Pane gamePane;
    private Crab theCrab;
    private Beach theBeach;
    /* game play */
    private int coconutsInFlight = 0;
    private int gameTick = 0;
    private GameController gameController;

    public OhCoconutsGameManager(int height, int width, Pane gamePane, GameController gameController) {
        this.height = height;
        this.width = width;
        this.gamePane = gamePane;
        this.gameController = gameController;

        this.theCrab = new Crab(this, height, width);
        registerObject(theCrab);
        gamePane.getChildren().add(theCrab.getImageView());

        this.theBeach = new Beach(this, height, width);
        registerObject(theBeach);
        if (theBeach.getImageView() != null)
            System.out.println("Unexpected image view for beach");
        subjectHitEvent.attach(new BeachHitObserver(this, theBeach));
        subjectHitEvent.attach(new CrabHitObserver(this, theCrab));
        subjectHitEvent.attach(new LaserHitObserver(this));
        subjectHitEvent.attach(new Scoreboard(this));

    }

    private void registerObject(IslandObject object) {
        allObjects.add(object);
        if (object.isHittable()) {
            HittableIslandObject asHittable = (HittableIslandObject) object;
            hittableIslandSubjects.add(asHittable);
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void coconutDestroyed() {
        coconutsInFlight -= 1;
    }

    public void tryDropCoconut() {
        if (gameTick % DROP_INTERVAL == 0 && theCrab != null) {
            coconutsInFlight += 1;
            Coconut c = new Coconut(this, (int) (Math.random() * width));
            registerObject(c);
            gamePane.getChildren().add(c.getImageView());
        }
        gameTick++;
    }

    public Crab getCrab() {
        return theCrab;
    }

    public void killCrab() {
        theCrab = null;
    }

    public void advanceOneTick() {
        for (IslandObject o : allObjects) {
            o.step();
            o.display();
        }
        // see if objects hit; the hit itself is something you will add
        // you can't change the lists while processing them, so collect
        //   items to be removed in the first pass and remove them later
        scheduledForRemoval.clear();
        for (IslandObject thisObj : allObjects) {
            for (HittableIslandObject hittableObject : hittableIslandSubjects) {
                if (thisObj.canHit(hittableObject) && thisObj.isTouching(hittableObject)) {
                    // TODO: add code here to process the hit
                    HitEvent hit = new HitEvent(hittableObject, thisObj);
                    subjectHitEvent.notifyAll(hit);
                    if (!hittableObject.isGroundObject())  {
                        scheduledForRemoval.add(hittableObject);
                        gamePane.getChildren().remove(hittableObject.getImageView());
                    }                }
            }
        }
        // actually remove the objects as needed
        for (IslandObject thisObj : scheduledForRemoval) {
            gamePane.getChildren().remove(thisObj.getImageView());
            allObjects.remove(thisObj);
            if (thisObj.isHittable()) {
                hittableIslandSubjects.remove((HittableIslandObject) thisObj);
            }
        }
        scheduledForRemoval.clear();
    }

    public void shootLaser() {
        if (theCrab != null) {
            LaserBeam laser = new LaserBeam(this, height, theCrab.getMidX());
            registerObject(laser);
            gamePane.getChildren().add(laser.getImageView());
        }
    }

    public void scheduleForDeletion(IslandObject islandObject) {
        scheduledForRemoval.add(islandObject);
    }

    public boolean done() {
        return coconutsInFlight == 0 && gameTick >= MAX_TIME;
    }

    public void coconutMiss(int missed) {
        gameController.setCoconutMissed(missed);
    }
    public void coconutHit(int hit) {
        gameController.setCoconutDestroyed(hit);
    }
}
