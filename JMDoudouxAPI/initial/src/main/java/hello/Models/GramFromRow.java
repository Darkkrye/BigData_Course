package hello.Models;

import java.io.Serializable;

public class GramFromRow implements Serializable {

    private String COL1;
    private String COL2;
    private String COL3;
    private String COL4;

    private int nb;

    public Gram toGram() {
        return new Gram();
    }
}
