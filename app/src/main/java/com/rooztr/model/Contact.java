package com.rooztr.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by anandsuresh on 8/1/16.
 */
public class Contact {

    private String id;
    private String name;
    private Set<String> numbers;

    public Contact(){
        numbers = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(Set<String> numbers) {
        this.numbers = numbers;
    }

    public String toString() {
        return name;
    }
}
