package hello.Models;

import java.util.ArrayList;

public class NGram {

    private ArrayList<Gram> grams;
    private Long n;

    public NGram() {
        this.grams = new ArrayList<>();
        this.n = new Long(0);
    }

    public NGram(ArrayList<Gram> grams, Long n) {
        this.grams = grams;
        this.n = n;
    }

    public ArrayList<Gram> getGrams() {
        return grams;
    }

    public void setGrams(ArrayList<Gram> grams) {
        this.grams = grams;
    }

    public Long getN() {
        return n;
    }

    public void setN(Long n) {
        this.n = n;
    }
}
