package Juego_Pareja.Story;

import java.util.List;

public class Dialogue {
    private String id;
    private String text;
    private List<Option> options;
    private String nextDialogueId;

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<Option> getOptions() {
        return options;
    }

    public String getNextDialogueId() {
        return nextDialogueId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public void setNextDialogueId(String nextDialogueId) {
        this.nextDialogueId = nextDialogueId;
    }
}
