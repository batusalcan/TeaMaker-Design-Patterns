package model;

import model.decorator.BasicMessage;
import model.decorator.HealthWarningDecorator;
import model.decorator.MessageDisplay;
import model.observer.Subject;
import model.observer.TeaMakerObserver;
import model.states.*;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TeaMakerModel implements Subject {

    private ArrayList<TeaMakerObserver> observers;

    State boilingWaterState;
    State doneState;
    State emptyState;
    State idleState;
    State teaState;
    State state;
    String message;
    private int totalCupsMonthly = 0;
    private int totalCupsDaily = 0;
    private int currentFillAmount = 0;

    public TeaMakerModel(){
        observers = new ArrayList<>();

        boilingWaterState = new BoilingWaterState(this);
        doneState = new DoneState(this);
        emptyState = new EmptyState(this);
        idleState = new IdleState(this);
        teaState = new TeaState(this);

        state = emptyState;
        updateTotals();

    }

    public void registerObserver(TeaMakerObserver o){
        observers.add(o);
    }

    public void removeObserver(TeaMakerObserver o){
        observers.remove(o);
    }
    public void notifyObservers(){
        for(TeaMakerObserver observer: observers){
            observer.updateView();
        }
    }
    public void incrementTotalCups(){

        this.totalCupsMonthly += currentFillAmount;
    }


    public void fill(int cups){
        this.currentFillAmount = cups;
        state.fill();
    }

    public void fill(){
        state.fill();
    }

    public void start(){
        state.start();
    }
    public void boil(){
        state.boil();
    }
    public void reset(){
        state.reset();
    }
    public void timerExpired(){
        state.timerExpired();
    }

    public void setState(State state){
        this.state = state;
        notifyObservers();
    }

    public void processFinished() {
        incrementTotalCups();
        saveBrewingRecord();
    }

    public State getState(){
        return state;
    }

    public State getEmptyState(){
        return emptyState;
    }
    public State getIdleState(){
        return idleState;
    }
    public State getBoilingWaterState(){
        return boilingWaterState;
    }

    public State getTeaState(){
        return teaState;
    }
    public State getDoneState(){
        return doneState;
    }

    public void setMessage(String message){
        this.message = message;
        notifyObservers();

    }

    public String getMessage(){
        String currentState = state.getClass().getSimpleName().replace("State","");

        LocalDate date = LocalDate.now();
        String day = date.getDayOfWeek().toString();
        String dateStr = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        MessageDisplay message = new BasicMessage(day, dateStr, currentState);

        if(this.totalCupsDaily > 10){
            message = new HealthWarningDecorator(message, totalCupsDaily);
        }


        return message.getDisplayMessage() + " | " + (this.message != null ? this.message : "");
    }


    public void saveBrewingRecord() {
        DatabaseManager.getInstance().insertLog(this.currentFillAmount);
        updateTotals();
    }


    public void updateTotals() {

        this.totalCupsMonthly = DatabaseManager.getInstance().getTotalCupsMonthly();
        this.totalCupsDaily = DatabaseManager.getInstance().getTotalCupsDaily();

        notifyObservers();
    }



    public int getTotalCupsMonthly() {
        return totalCupsMonthly;
    }
}
