package controller;

import model.TeaMakerModel;
import view.TeaMakerView;

public class TeaMakerController {

    private TeaMakerModel model;
    private TeaMakerView view;

    public TeaMakerController(TeaMakerModel model){
        this.model = model;

        this.view = new TeaMakerView(this,model);
        view.createView();
    }

    public void fill(int cups){
        model.fill(cups);
    }
    public void start(){
        model.start();
    }
    public void boil(){
        model.boil();
    }
    public void reset(){
        model.reset();
    }


    public int fetchTotalCups() {
        return model.getTotalCupsMonthly();
    }
}
