package model.strategies;
import model.TeaMakerModel;
import model.strategies.ProcessStrategy;

import java.util.Timer;
import java.util.TimerTask;

public class WaterBoilingStrategy extends ProcessStrategy {

    public WaterBoilingStrategy(TeaMakerModel model) {
        super(model);
    }

    @Override
    public void executeProcess() {
        System.out.println("Water Boiling mode selected.");
        System.out.println("TIMER-2 has started: Water is boiling ");

        Timer timer = new Timer();
        long duration = 4000;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Water Boiling Finished!");
                model.setMessage("Water has been boiled.");
                model.timerExpired();
                timer.cancel();
            }
        }, duration);
    }
}