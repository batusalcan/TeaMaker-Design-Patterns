package model.decorator;

import model.decorator.MessageDecorator;
import model.decorator.MessageDisplay;

public class HealthWarningDecorator extends MessageDecorator {
    MessageDisplay messageDisplay;
    private int currentCups;

    public HealthWarningDecorator(MessageDisplay messageDisplay,int currentCups){
        this.messageDisplay = messageDisplay;
        this.currentCups = currentCups;
    }

    public String getDisplayMessage() {
        return messageDisplay.getDisplayMessage() + " | WARNING: The number of total cups today has reached to " + currentCups + "!";
    }
}
