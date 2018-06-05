package fr.esgi.demo;

import java.util.List;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLContext;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author formation
 */
public class TestCsvQueries {

    private static CsvLoader loader;

    @BeforeClass
    public static void init() {
        final SQLContext context = LocalContexts.get().getSqlSession().sqlContext();
        loader = new CsvLoader(context);
    }

    @Test
    public void testCsvCac40Loader() throws AnalysisException {
        final String path = LocalContexts.get().getCac40Path();
        Dataset<Cours> values = loader.loadDataset(path);
        //values.show();
        values.createOrReplaceTempView("COURS");
        Double avg = values.sqlContext().sql("select AVG(OUVERTURE) from COURS").first().getDouble(0);
        assertTrue(avg >= 4000.0D);
        assertTrue(avg <= 6000.0D);
    }

    @AfterClass
    public static void close() {
        LocalContexts.shutdown();
    }
}
