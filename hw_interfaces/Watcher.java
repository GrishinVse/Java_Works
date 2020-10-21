package com.hw_interfaces;

public class Watcher implements IntWatcher {
    public void onChange(StringBuilderWithWatcher stringBuilder) {
        System.out.println("HAVE SOME CHANGES = '" + stringBuilder.toString() + "'");
    }
}
