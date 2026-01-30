package model.states;

public interface State {

    public void fill();
    public void start();
    public void boil();
    public void reset();
    public void timerExpired();
}
