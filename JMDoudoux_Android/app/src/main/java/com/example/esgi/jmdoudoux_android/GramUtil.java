package com.example.esgi.jmdoudoux_android;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GramUtil {

    public ArrayList<NGram> ngrams;

    public GramUtil(ArrayList<NGram> ngrams) {
        this.ngrams = ngrams;
    }

    public ArrayList<String> getNextWords(String word, String word2) {
        if (word2 == null) {
            NGram ng = this.getGram(new Long(2));
            ArrayList<Gram> grams = new ArrayList<>();
            ArrayList<String> nexts = new ArrayList<>();

            for (Gram g: ng.getGrams()) {
                if (g.getWords().get(0) == word) {
                    grams.add(g);
                }
            }

            Collections.sort(grams, (gram, t1) -> gram.getOccurances() - t1.getOccurances());

            for (int i = 0; i < 3; i++) {
                nexts.add(grams.get(i).getNext());
            }

            return nexts;
        } else {
            NGram ng = this.getGram(new Long(3));
            ArrayList<Gram> grams = new ArrayList<>();
            ArrayList<String> nexts = new ArrayList<>();

            for (Gram g: ng.getGrams()) {
                if (g.getWords().get(0) == word && g.getWords().get(1) == word2) {
                    grams.add(g);
                }
            }

            Collections.sort(grams, (gram, t1) -> gram.getOccurances() - t1.getOccurances());

            for (int i = 0; i < 3; i++) {
                nexts.add(grams.get(i).getNext());
            }

            return nexts;
        }
    }

    private NGram getGram(Long n) {
        for (NGram ng: this.ngrams) {
            if (ng.getN() == n) {
                return ng;
            }
        }
        return null;
    }
}
