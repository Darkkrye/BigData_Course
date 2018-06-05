package fr.esgi.demo;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.stat.Statistics;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLContext;

/**
 *
 * @author formation
 */
public class Main {

    public static void main(String[] args) {
        final SQLContext context = LocalContexts.get().getSqlSession().sqlContext();
        final CsvLoader loader = new CsvLoader(context);
        Dataset<Cours> coursCAC40 = loader.loadDataset(LocalContexts.get().getCac40Path());
        Dataset<Cours> coursSG = loader.loadDataset(LocalContexts.get().getSGPath());
        JavaRDD<Double> clotureCAC40 = coursCAC40.javaRDD().map((Cours c) -> c.getCloture());
        JavaRDD<Double> clotureSG = coursSG.javaRDD().map((Cours c) -> c.getCloture());
        double correlations = Statistics.corr(clotureSG, clotureCAC40);
        System.out.println(correlations);
        LocalContexts.shutdown();
    }
}
