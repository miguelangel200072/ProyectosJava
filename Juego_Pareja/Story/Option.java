package Juego_Pareja.Story;

public class Option {
    private String description;
    private String nextDialogueId;
    private String objectNeeded;
    private String alternativeNextId;
    private String reward;

    public String getDescription() {
        return description;
    }

    public String getNextDialogueId() {
        return nextDialogueId;
    }

    public String getObjectNeeded() {
        return objectNeeded;
    }

    public String getAlternativeNextId() {
        return alternativeNextId;
    }

    public String getReward() {
        return reward;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNextDialogueId(String nextDialogueId) {
        this.nextDialogueId = nextDialogueId;
    }

    public void setObjectNeeded(String objectNeeded) {
        this.objectNeeded = objectNeeded;
    }

    public void setAlternativeNextId(String alternativeNextId) {
        this.alternativeNextId = alternativeNextId;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }
}
