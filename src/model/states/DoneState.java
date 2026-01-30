package model.states;

import model.TeaMakerModel;
import model.states.State;

public class DoneState implements State {

    TeaMakerModel teaMakerModel;

    public DoneState(TeaMakerModel teaMakerModel){
        this.teaMakerModel = teaMakerModel;
    }

    public void fill(){

    }
    public void start(){}
    public void boil(){}
    public void reset(){
        teaMakerModel.setMessage("Tea Maker reset.");
        teaMakerModel.setState(teaMakerModel.getEmptyState());
    }
    public void timerExpired(){}
}
