package fr.esgi.demo;

import java.util.List;
import org.apache.spark.sql.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author formation
 */
public class TestCsvLoader {

    private static CsvLoader loader;

    @BeforeClass
    public static void init() {
        final SQLContext context = LocalContexts.get().getSqlSession().sqlContext();
        loader = new CsvLoader(context);
    }

    @Test
    public void testCsvCac40Loader() {
        final String path = LocalContexts.get().getCac40Path();
        Dataset<Cours> values = loader.loadDataset(path);
        List<Cours> cours = values.collectAsList();
        assertNotNull(cours);
        assertTrue(cours.size() >= 200);
        for (Cours element : cours) {
            assertNotNull(element.getIsin());
            assertNotNull(element.getDate());
            assertNotNull(element.getOuverture());
            assertNotNull(element.getCloture());
            assertNotNull(element.getPlushaut());
            assertNotNull(element.getPlusbas());
        }
    }

    @Test
    public void testCsvSGLoader() {
        final String path = LocalContexts.get().getSGPath();
        Dataset<Cours> values = loader.loadDataset(path);
        List<Cours> cours = values.collectAsList();
        assertNotNull(cours);
        assertTrue(cours.size() >= 200);
        for (Cours element : cours) {
            assertNotNull(element.getIsin());
            assertNotNull(element.getDate());
            assertNotNull(element.getOuverture());
            assertNotNull(element.getCloture());
            assertNotNull(element.getPlushaut());
            assertNotNull(element.getPlusbas());
        }
    }

    @AfterClass
    public static void close() {
        LocalContexts.shutdown();
    }
}
