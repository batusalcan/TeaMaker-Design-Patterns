package model.decorator;

import model.decorator.MessageDisplay;

public abstract class MessageDecorator implements MessageDisplay {

    public abstract String getDisplayMessage();

}
