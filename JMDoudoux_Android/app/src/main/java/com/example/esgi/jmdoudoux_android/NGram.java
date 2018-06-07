package com.example.esgi.jmdoudoux_android;

import java.util.ArrayList;

public class NGram {

    private ArrayList<Gram> grams;
    private int n;

    public NGram() {
        this.grams = new ArrayList<>();
        this.n = 0;
    }

    public NGram(ArrayList<Gram> grams, int n) {
        this.grams = grams;
        this.n = n;
    }

    public ArrayList<Gram> getGrams() {
        return grams;
    }

    public void setGrams(ArrayList<Gram> grams) {
        this.grams = grams;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}