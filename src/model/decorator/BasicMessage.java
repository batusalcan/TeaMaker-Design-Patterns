package model.decorator;

import model.decorator.MessageDisplay;

public class BasicMessage implements MessageDisplay {

    String day;
    String date;
    String state;

    public BasicMessage(String day, String date, String state){
        this.day = day;
        this.date = date;
        this.state = state;
    }
    public String getDisplayMessage(){
        return "Day: " + day + " | Date: " + date + " | State: " + state;
    }
}
