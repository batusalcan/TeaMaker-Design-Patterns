package model.states;

import model.TeaMakerModel;
import model.states.State;

public class EmptyState implements State {

    TeaMakerModel teaMakerModel;

    public EmptyState(TeaMakerModel teaMakerModel){
        this.teaMakerModel = teaMakerModel;
    }

    public void fill(){
        teaMakerModel.setMessage("Machine is filled.");
        teaMakerModel.setState(teaMakerModel.getIdleState());
    }
    public void start(){
        teaMakerModel.setMessage("WARNING: You can not start. The Tea Maker is empty.");
    }
    public void boil(){
        teaMakerModel.setMessage("WARNING: You can not boil. The Tea Maker is empty.");
    }
    public void reset(){}
    public void timerExpired(){}
}
