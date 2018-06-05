package fr.esgi.demo;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SQLContext;

/**
 * All information is stored using CSV. 
 * @author formation
 */
public class CsvLoader {
    /** the sql context to create datasets */
    private SQLContext context; 
    
    /**
     * Constructs a new empty loader with the given context 
     * @param context the context to load sql 
     */
    public CsvLoader(SQLContext context) {
        this.context = context; 
    }
    
    /**
     * Load a "cours" file, in CSV, to make a dataset for that context 
     * @param path the path of the file 
     * @return the dataset matching the file 
     */
    Dataset<Cours> loadDataset(String path) {
        Dataset<Cours> values = context.read()
                .option("header", true)
                .option("delimiter", ";")
                .option("dateFormat", "dd/MM/yy")
                .option("inferSchema",true)
                .csv(path)
                .as(Encoders.bean(Cours.class));
        return values; 
    }
}
