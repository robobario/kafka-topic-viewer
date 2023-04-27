package org.example;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;

/**
 * Slow and safe, the kafka consumer and http resource can only access the state
 * serially.
 */
@ApplicationScoped
public class SynchronizedStateStore {

    private final Map<String, Collection<String>> state = new HashMap<>();

    synchronized void append(String key, String value) {
        state.computeIfAbsent(key, s -> new ArrayList<>()).add(value);
    }

    synchronized public Collection<String> get(String key) {
        return state.getOrDefault(key, List.of());
    }
}
