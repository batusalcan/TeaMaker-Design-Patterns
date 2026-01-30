package model.states;

import model.TeaMakerModel;
import model.states.State;

public class BoilingWaterState implements State {

    TeaMakerModel teaMakerModel;

    public BoilingWaterState(TeaMakerModel teaMakerModel){
        this.teaMakerModel = teaMakerModel;
    }

    public void fill(){
    }
    public void start(){}
    public void boil(){}
    public void reset(){}
    public void timerExpired(){
        teaMakerModel.processFinished();
        teaMakerModel.setState(teaMakerModel.getDoneState());
    }
}
