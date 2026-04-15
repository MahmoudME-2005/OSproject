package mahmoudehabmoheb.osprojectnew;

import javafx.beans.property.*;

public class ProcessModel {
    private final String id;
    private final int burstTime;
    private final DoubleProperty remainingTime;
    private final DoubleProperty progress = new SimpleDoubleProperty(0);

    public ProcessModel(String id, int burstTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.remainingTime = new SimpleDoubleProperty(burstTime);
    }

    // Getters
    public String getId() { return id; }
    public int getBurstTime() { return burstTime; }
    public DoubleProperty remainingTimeProperty() { return remainingTime; }
    public DoubleProperty progressProperty() { return progress; }
}