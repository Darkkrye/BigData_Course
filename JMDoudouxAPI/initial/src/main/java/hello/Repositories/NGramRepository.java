package hello.Repositories;

import hello.GramsUtils.CorpusReader;
import hello.GramsUtils.CsvLoader;
import hello.GramsUtils.LocalContexts;
import hello.Models.Gram;
import hello.Models.NGram;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NGramRepository {

    String path = "";
    Dataset<Row> initGram;
    ArrayList<NGram> ngrams = new ArrayList<NGram>();

    private static NGramRepository instance;

    public static NGramRepository getInstance() throws IOException, AnalysisException {
        if (instance == null) {
            instance = new NGramRepository();
            instance.createCsvFile();
            instance.initDataSet();
            instance.initGrams();
        }

        return instance;
    }

    private NGramRepository() {}

    public ArrayList<NGram> loadNGram() {
        return this.ngrams;
    }

    private void createCsvFile() throws IOException {
        StringBuilder str = new StringBuilder();
        String oldWord = "", middleWord = "", newWord = "", grandpaWord="";
        CorpusReader reader = new CorpusReader();
        final SQLContext context = LocalContexts.get().getSqlSession().sqlContext();
        final CsvLoader loader = new CsvLoader(context);

        String path = getClass().getClassLoader().getResource("corpus.txt").getPath() + "resultat.csv";
        this.path = path.replace("corpus.txt", "");

        File f = new File(this.path);
        if (!f.exists() && !f.isDirectory()) {
            str.append("COL1,COL2,COL3,COL4\n");
            //Création du fichier initiale
            while (reader.hasNext()) {
                if (newWord.equals("")) {
                    grandpaWord = reader.next();
                    oldWord = reader.next();
                    middleWord = reader.next();
                } else {
                    grandpaWord = oldWord;
                    oldWord = middleWord;
                    middleWord = newWord;
                }
                newWord = reader.next();

                str.append(grandpaWord)
                        .append(",")
                        .append(oldWord)
                        .append(",")
                        .append(middleWord)
                        .append(",")
                        .append(newWord)
                        .append("\n");
            }

            str.append(newWord).append(",");

            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            fw.write(str.toString());  // écrire une ligne dans le fichier resultat.txt

            fw.close(); // fermer le fichier à la fin des traitements
        }
    }

    private void initDataSet() throws AnalysisException {
        final SQLContext context = LocalContexts.get().getSqlSession().sqlContext();
        final CsvLoader loader = new CsvLoader(context);

        this.initGram = loader.loadDataset(this.path);
        this.initGram.createTempView("TEXTS");
    }

    private void initGrams() {
        //this.initGram0();
        this.initGram1();
        this.initGram2();
        this.initGram3();
    }

    private void initGram0() {
        List<Row> rows = this.initGram.sqlContext().sql("select COL1, count(*) as nb from TEXTS group by COL1 order by nb DESC").collectAsList();
        NGram ngram = new NGram();
        ngram.setN(new Long(0));

        int i = 0;

        for (Row r : rows) {
            Gram g = new Gram();
            g.setNext("");
            g.setWords(new ArrayList<String>() {{
                add((String) r.get(0));
            }});
            g.setOccurances((Long) r.get(1));

            ngram.getGrams().add(g);

            if (i == 10)
                break;

            i++;
        }

        this.ngrams.add(ngram);
    }

    private void initGram1() {
        List<Row> rows = this.initGram.sqlContext().sql("select COL1, COL2, count(*) as nb from TEXTS group by COL1, COL2 order by nb DESC").collectAsList();
        NGram ngram = new NGram();
        ngram.setN(new Long(1));

        for (Row r : rows) {
            Gram g = new Gram();
            g.setNext((String) r.get(1));
            g.setWords(new ArrayList<String>() {{
                add((String) r.get(0));
            }});
            g.setOccurances((Long) r.get(2));

            ngram.getGrams().add(g);
        }

        this.ngrams.add(ngram);
    }

    private void initGram2() {
        List<Row> rows = this.initGram.sqlContext().sql("select COL1, COL2,COL3, count(*) as nb from TEXTS group by COL1, COL2,COL3 order by nb DESC").collectAsList();
        NGram ngram = new NGram();
        ngram.setN(new Long(2));

        for (Row r : rows) {
            Gram g = new Gram();
            g.setNext((String) r.get(2));
            g.setWords(new ArrayList<String>() {{
                add((String) r.get(0));
                add((String) r.get(1));
            }});
            g.setOccurances((Long) r.get(3));

            ngram.getGrams().add(g);
        }

        this.ngrams.add(ngram);
    }

    private void initGram3() {
        List<Row> rows = this.initGram.sqlContext().sql("select COL1, COL2,COL3, COL4, count(*) as nb from TEXTS group by COL1, COL2,COL3, COL4  order by nb DESC").collectAsList();
        NGram ngram = new NGram();
        ngram.setN(new Long(3));

        for (Row r : rows) {
            Gram g = new Gram();
            g.setNext((String) r.get(3));
            g.setWords(new ArrayList<String>() {{
                add((String) r.get(0));
                add((String) r.get(1));
                add((String) r.get(2));
            }});
            g.setOccurances((Long) r.get(4));

            ngram.getGrams().add(g);
        }

        this.ngrams.add(ngram);
    }
}
