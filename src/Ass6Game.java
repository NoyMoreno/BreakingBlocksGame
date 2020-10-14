package game;

import java.util.ArrayList;
import java.util.List;

import animation.AnimationRunner;
import biuoop.GUI;
import levels.LevelDirectHit;
import levels.LevelFinalFour;
import levels.LevelGreen3;
import levels.LevelInformation;
import levels.LevelWideEasy;
/**
 * Class Ass3Game- main class to start the game.
 * @author noy shriki
 */
public class Ass6Game {
    /**
     * Create a game object, initializes and runs it.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanorid", 800, 800);
        AnimationRunner animation = new  AnimationRunner(gui, 60);
        List<LevelInformation> allLevels = new ArrayList<>();
        allLevels.add(new LevelDirectHit());
        allLevels.add(new LevelWideEasy());
        allLevels.add(new LevelGreen3());
        allLevels.add(new LevelFinalFour());
        GameFlow gameFlow = new GameFlow(animation, gui.getKeyboardSensor());
        gameFlow.runLevels(allLevels);
     }
}
