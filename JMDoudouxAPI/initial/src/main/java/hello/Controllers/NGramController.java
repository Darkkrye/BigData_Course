package hello.Controllers;

import hello.Models.NGram;
import hello.Repositories.NGramRepository;
import org.apache.spark.sql.AnalysisException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class NGramController {

    private static final String template = "Hello, %s !";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/ngrams")
    public ArrayList<NGram> ngram() throws IOException, AnalysisException {
        return NGramRepository.getInstance().loadNGram();
    }
}
