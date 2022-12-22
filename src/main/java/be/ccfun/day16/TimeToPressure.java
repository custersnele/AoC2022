package be.ccfun.day16;

public class TimeToPressure {
    private int time;
    private int pressure;

    public TimeToPressure(int time, int pressure) {
        this.time = time;
        this.pressure = pressure;
    }

    public int getTime() {
        return time;
    }

    public int getPressure() {
        return pressure;
    }

    public TimeToPressure add(TimeToPressure other) {
        return new TimeToPressure(time + other.time, pressure + other.pressure);
    }

    @Override
    public String toString() {
        return pressure + "@" + time;
    }
}
