package model.strategies;

import model.TeaMakerModel;

public abstract class ProcessStrategy {

    TeaMakerModel model;

    public ProcessStrategy(TeaMakerModel model){
        this.model = model;
    }
    public abstract void executeProcess();
}
