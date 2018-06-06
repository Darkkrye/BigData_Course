package hello.GramsUtils;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
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
    public Dataset<Row> loadDataset(String path) {
        Dataset<Row> values = context.read()
                .option("header", true)
                .option("delimiter", ",")
                .option("inferSchema",true)
                .option("encoding", "ISO-8859-1")
                .csv(path);
        return values;
    }
}
