package hello.Models;

import java.util.ArrayList;

public class Gram {

    private ArrayList<String> words;
    private String next;
    private Long occurances;

    public Gram() {
        this.words = new ArrayList<String>();
        this.next = "";
        this.occurances = new Long(0);
    }

    public Gram(ArrayList<String> words, String next, Long occurances) {
        this.words = words;
        this.next = next;
        this.occurances = occurances;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public Long getOccurances() {
        return occurances;
    }

    public void setOccurances(Long occurances) {
        this.occurances = occurances;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
