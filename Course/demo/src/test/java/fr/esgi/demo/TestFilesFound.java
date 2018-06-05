package fr.esgi.demo;

import java.io.File;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Just to ensure that, for all the files we need, the path was set correctly
 * @author formation
 */
public class TestFilesFound {

    @Test
    public void testFindCac40File() {
        File file = new File(LocalContexts.get().getCac40Path());
        assertTrue(file.exists());
    }
    
    @Test
    public void testFindSGFile() {
        File file = new File(LocalContexts.get().getSGPath());
        assertTrue(file.exists());        
    }
}
