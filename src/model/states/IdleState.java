package model.states;

import model.strategies.ProcessStrategy;
import model.strategies.TeaBrewingStrategy;
import model.TeaMakerModel;
import model.strategies.WaterBoilingStrategy;

public class IdleState implements State {

    TeaMakerModel teaMakerModel;

    public IdleState(TeaMakerModel teaMakerModel){
        this.teaMakerModel = teaMakerModel;
    }

    public void fill(){


    }
    public void start(){
        teaMakerModel.setState(teaMakerModel.getTeaState());
        ProcessStrategy strategy = new TeaBrewingStrategy(teaMakerModel);
        strategy.executeProcess();
    }
    public void boil(){
        teaMakerModel.setState(teaMakerModel.getBoilingWaterState());
        ProcessStrategy strategy = new WaterBoilingStrategy(teaMakerModel);
        strategy.executeProcess();
    }
    public void reset(){}
    public void timerExpired(){}
}
