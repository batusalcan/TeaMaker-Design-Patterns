package model.strategies;

import model.TeaMakerModel;
import model.strategies.ProcessStrategy;

import java.util.Timer;
import java.util.TimerTask;

public class TeaBrewingStrategy extends ProcessStrategy {

    public TeaBrewingStrategy(TeaMakerModel model){
        super(model);
    }

    @Override
    public void executeProcess() {
        System.out.println("Tea Brewing mode selected.");
        System.out.println("TIMER-1 has started: Tea is brewing. ");

        Timer timer = new Timer();
        long duration = 5000;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Tea Brewing Finished!");
                model.setMessage("Tea is ready! Enjoy!");
                model.timerExpired();
                timer.cancel();
            }
        }, duration);
    }
    }


