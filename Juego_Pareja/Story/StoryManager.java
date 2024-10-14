package Juego_Pareja.Story;

import java.util.*;

import Juego_Pareja.Character;
import Juego_Pareja.Enemy;
import Juego_Pareja.Helpers.GUI;
import Juego_Pareja.Item;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import static Juego_Pareja.Battle.startBattle;
import static Juego_Pareja.CharacterCreation.createCharacter;

public class StoryManager {
    private static List<Dialogue> dialogues;
    private static Dialogue currentDialogue;
    private static double currentDeathPercentage = 25;
    public static boolean finished = false;
    private static Character character;

    private static final HashMap<String, Enemy> enemies = new HashMap<>() {{
        put("furyBattle", new Enemy("The Furies", 50, 1));
        put("cerberusBattle", new Enemy("Cerberus", 75, 3));
        put("aquilesBattle", new Enemy("Aquiles", 100, 5));
        put("demonBattle", new Enemy("Lesser Demon", 125, 7));
        put("chironBattle", new Enemy("Chiron", 150, 8));
        put("odysseusBattle", new Enemy("Odysseus", 175, 9));
        put("luciferBattle", new Enemy("Lucifer", 200, 10));
    }};
    private static final HashMap<String, String> enemyRewards = new HashMap<>() {{
        put("furyBattle", "");
        put("cerberusBattle", "Cerberus' Tooth");
        put("aquilesBattle", "Aquiles' Shield");
        put("demonBattle", "");
        put("chironBattle", "10 Sins");
        put("odysseusBattle", "");
        put("luciferBattle", "");
    }};
    private static final HashMap<String, String> enemyNextId = new HashMap<>() {{
        put("furyBattle", "7");
        put("cerberusBattle", "12");
        put("aquilesBattle", "15");
        put("demonBattle", "17");
        put("chironBattle", "20");
        put("odysseusBattle", "22");
        put("luciferBattle", "23");
    }};

    public static void loadStory(String filePath) throws Exception {
       dialogues = new ArrayList<>();
       File storyFile = new File(filePath);

       // Set up XML parser
       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
       Document doc = dBuilder.parse(storyFile);

       // Normalize so no errors from whitespace and formatting
       doc.getDocumentElement().normalize();

       // list of all items with the dialogue tag
       NodeList dialogueList = doc.getElementsByTagName("dialogue");

        for (int i = 0; i < dialogueList.getLength(); i++) {
            Node dialogueNode = dialogueList.item(i);

            // Check if dialogue i is valid
            if (dialogueNode.getNodeType() == Node.ELEMENT_NODE) {
                Element dialogueElement = (Element) dialogueNode;
                Dialogue dialogue = new Dialogue();

                // Setup dialogue main attributes
                dialogue.setId(dialogueElement.getAttribute("id"));
                dialogue.setText(dialogueElement.getElementsByTagName("text").item(0).getTextContent());

                // Setup options (if there are for this dialogue)
                NodeList optionList = dialogueElement.getElementsByTagName("option");
                if (optionList.getLength() > 0) {
                    List<Option> options = new ArrayList<>();

                    for (int j = 0; j < optionList.getLength(); j++) {
                        Element optionElement = (Element) optionList.item(j);

                        // Setup each option's attributes and add to the list
                        Option option = new Option();
                        option.setDescription(optionElement.getAttribute("description"));
                        option.setNextDialogueId(optionElement.getAttribute("nextDialogueId"));

                        if (!optionElement.getAttribute("objectNeeded").isEmpty()) {
                            option.setObjectNeeded(optionElement.getAttribute("objectNeeded"));
                        }
                        if (!optionElement.getAttribute("alternativeNextId").isEmpty()) {
                            option.setAlternativeNextId(optionElement.getAttribute("alternativeNextId"));
                        }
                        if (!optionElement.getAttribute("reward").isEmpty()) {
                            option.setReward(optionElement.getAttribute("reward"));
                        }

                        options.add(option);
                    }

                    dialogue.setOptions(options);
                } else if (dialogueElement.getElementsByTagName("nextDialogueId").getLength() > 0) {
                    // Setup nextDialogue if there are no options but there is a nextDialogueId
                    dialogue.setNextDialogueId(dialogueElement.getElementsByTagName("nextDialogueId").item(0).getTextContent());
                }

                // Add each created dialogue to the list
                dialogues.add(dialogue);
            }
        }
        // Set first dialogue to current
        currentDialogue = dialogues.get(0);
    }

    public static void runStory() {
        while (currentDialogue != null) {
            // Check Sin Points and give object
            if (character != null && character.getSinPoints() >= 25) {
                character.addItem(new Item("Heavy Sins", "Reward", 1));
            }

            if (currentDialogue.getId() != null && Objects.equals(currentDialogue.getId(), "Character")) {
                // Character creation Screen
                character = createCharacter();
                currentDialogue = getNextDialogueById(currentDialogue.getNextDialogueId());
            } else if (Objects.equals(currentDialogue.getId(), "DEATH") || Objects.equals(currentDialogue.getId(), "WIN")) {
                GUI.showStory(currentDialogue.getText(), false);
                currentDialogue = null;
                finished = true;
            } else if (currentDialogue.getOptions() == null || currentDialogue.getOptions().isEmpty()) {
                // Text without options - go to next established dialogue
                GUI.showStory(currentDialogue.getText(), true);
                if (currentDialogue.getNextDialogueId().contains("Battle")) {
                    boolean battleOutcome = startBattle(character,enemies.get(currentDialogue.getNextDialogueId()));
                    if (battleOutcome) {
                        if (!enemyRewards.get(currentDialogue.getNextDialogueId()).isEmpty()) {
                            String enemyReward = enemyRewards.get(currentDialogue.getNextDialogueId());
                            if (enemyReward.contains("Sins")) {
                                enemyReward = enemyRewards.get(currentDialogue.getNextDialogueId()).replaceAll("[^0-9]", "");
                                if (!enemyReward.isEmpty()) {
                                    character.setSinPoints(Integer.parseInt(enemyReward));
                                }
                            }
                            // Add items to inventory
                            else {
                                character.addItem(new Item(enemyReward,"Reward", 1));
                                if (enemyReward.equals("Aquiles' Shield")) {
                                    character.setHealth(character.getHealth() + 3);
                                }
                            }
                        }
                        currentDialogue = getNextDialogueById(enemyNextId.get(currentDialogue.getNextDialogueId()));
                    } else {
                        currentDialogue = getNextDialogueById("DEATH");
                    }
                } else {
                    currentDialogue = getNextDialogueById(currentDialogue.getNextDialogueId());
                }
            } else {
                List<Option> availableOptions = new ArrayList<>();

                for (Option option : currentDialogue.getOptions()) {
                    // If the character has the required item or the option does not require one add it to the list
                    if (option.getObjectNeeded() == null || character.hasItem(option.getObjectNeeded())) {
                        availableOptions.add(option);
                    }
                }

                // Add the options to an array
                String[] options = availableOptions.stream().map(Option::getDescription).toArray(String[]::new);

                // Show dialogue and get selection
                int choice = GUI.multipleChoice(options, currentDialogue.getText(), true);

                // Find selected option and go to the next established dialogue
                Option selectedOption = availableOptions.get(choice - 1);

                // Random Outcomes, loss percentage is greater referring to character's sin points
                if (selectedOption.getAlternativeNextId() != null && !selectedOption.getAlternativeNextId().isEmpty()) {
                    Random random = new Random();
                    int randNum = random.nextInt(0,100);
                    double deathPercentage = currentDeathPercentage + (double) character.getSinPoints() / 2;

                    if (randNum <= deathPercentage) {
                        currentDialogue = getNextDialogueById(selectedOption.getAlternativeNextId());
                        currentDeathPercentage = deathPercentage;
                    } else {
                        currentDialogue = getNextDialogueById(selectedOption.getNextDialogueId());
                    }
                } else if (selectedOption.getNextDialogueId() != null && selectedOption.getNextDialogueId().contains("Battle")) 
                {
                    boolean battleOutcome = startBattle(character,enemies.get(selectedOption.getNextDialogueId()));

                    if (battleOutcome) {
                        if (!enemyRewards.get(selectedOption.getNextDialogueId()).isEmpty()) {
                            String enemyReward = enemyRewards.get(selectedOption.getNextDialogueId());
                            if (enemyReward.contains("Sins")) {
                                enemyReward = enemyRewards.get(selectedOption.getNextDialogueId()).replaceAll("[^0-9]", "");
                                if (!enemyReward.isEmpty()) {
                                    character.setSinPoints(Integer.parseInt(enemyReward));
                                }
                            }
                            // Add items to inventory
                            else {
                                character.addItem(new Item(enemyReward,"Reward", 1));
                                if (enemyReward.equals("Aquiles' Shield")) {
                                    character.setHealth(character.getHealth() + 3);
                                }
                            }
                        }
                        currentDialogue = getNextDialogueById(enemyNextId.get(selectedOption.getNextDialogueId()));
                    } else {
                        currentDialogue = getNextDialogueById("DEATH");
                    }
                } else if (Objects.equals(selectedOption.getNextDialogueId(), "18")) {
                    character.removeItem("Cerberus' Tooth");
                } else {
                    currentDialogue = getNextDialogueById(selectedOption.getNextDialogueId());
                }

                // Handle Rewards
                if (selectedOption.getReward() != null && !selectedOption.getReward().isEmpty()) {
                    String reward = selectedOption.getReward();

                    // Add Sin Points
                    if (reward.contains("Sins")) {
                        reward = reward.replaceAll("[^0-9]", "");
                        if (!reward.isEmpty()) {
                            character.setSinPoints(Integer.parseInt(reward));
                        }
                    }
                    // Add items to inventory
                    else {
                        character.addItem(new Item(reward,"Reward", 1));
                    }
                }



            }
        }
    }

    private static Dialogue getNextDialogueById(String id) {
        // Search the list of dialogues for the id otherwise return null
        return dialogues.stream().filter(dialogue -> dialogue.getId().equals(id)).findFirst().orElse(null);
    }

    public static void restartGame() {
        // Reset game variables to restart
        currentDeathPercentage = 25;
        finished = false;
        character = null; // Delete current character

        // Reload the first dialogue
        currentDialogue = dialogues.get(0);
    }
}
