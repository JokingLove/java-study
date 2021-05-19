package com.joking.yaml.guava;

public class HaHaEvent implements Event{

    private String haha;

    public HaHaEvent(String haha) {
        this.haha = haha;
    }

    public String getHaha() {
        return haha;
    }

    public void setHaha(String haha) {
        this.haha = haha;
    }

    @Override public String toString() {
        return "HaHaEvent{" +
            "haha='" + haha + '\'' +
            '}';
    }
}
