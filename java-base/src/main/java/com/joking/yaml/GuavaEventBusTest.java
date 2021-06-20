package com.joking.yaml;

import com.google.common.eventbus.EventBus;
import com.joking.yaml.guava.HaHaEvent;
import com.joking.yaml.guava.HaHaEventSubscriber;

public class GuavaEventBusTest {

    public static void main(String[] args) {
        EventBus bus = new EventBus();
        bus.register(new HaHaEventSubscriber());

        bus.post(new HaHaEvent("womendoushihaohaizi"));
    }
}
