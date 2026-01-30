package model.observer;

public interface Subject {
    void registerObserver(TeaMakerObserver o);
    void removeObserver(TeaMakerObserver o);
    void notifyObservers();
}
