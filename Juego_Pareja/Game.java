package Juego_Pareja;

import Juego_Pareja.Story.StoryManager;

import static Juego_Pareja.Helpers.GUI.multipleChoice;

public class Game {
    public static void main(String[] args) {
        try {
            StoryManager.loadStory("src/Juego_Pareja/Story/storyline.xml");

            while (!StoryManager.finished) {
                StoryManager.runStory();

                if (StoryManager.finished) {
                    int reset = multipleChoice(new String[]{"Restart", "Exit"}, "Do you wish to restart the game from the beginning?", true);

                    if (reset == 1) {
                        StoryManager.restartGame();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

