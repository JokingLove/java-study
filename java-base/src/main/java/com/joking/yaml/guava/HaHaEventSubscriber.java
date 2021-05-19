package com.joking.yaml.guava;

import com.google.common.eventbus.Subscribe;

public class HaHaEventSubscriber {

    @Subscribe
    public void haha(HaHaEvent event) {
        System.out.println(event);
    }
}
