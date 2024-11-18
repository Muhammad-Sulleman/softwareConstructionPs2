package poet;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class GraphPoetTest {

    @Test
    public void testSingleLineCorpus() throws IOException {
        GraphPoet poet = new GraphPoet(new File("test/poet/single-line.txt"));
        String result = poet.poem("Hello world");
        assertEquals("Hello there world", result);
    }

    @Test
    public void testMultiLineCorpus() throws IOException {
        GraphPoet poet = new GraphPoet(new File("test/poet/multi-line.txt"));
        String result = poet.poem("The sound system is amazing");
        assertEquals("The sound of the system is amazing", result);
    }

    @Test
    public void testNoBridgeWord() throws IOException {
        GraphPoet poet = new GraphPoet(new File("test/poet/unrelated.txt"));
        String result = poet.poem("This input has no bridge words");
        assertEquals("This input has no bridge words", result);
    }

    @Test
    public void testCaseInsensitiveMatching() throws IOException {
        GraphPoet poet = new GraphPoet(new File("test/poet/case-insensitive.txt"));
        String result = poet.poem("HELLO goodbye");
        assertEquals("HELLO and goodbye", result);
    }

    @Test
    public void testEmptyCorpus() throws IOException {
        GraphPoet poet = new GraphPoet(new File("test/poet/empty.txt"));
        String result = poet.poem("No bridges here");
        assertEquals("No bridges here", result);
    }

    @Test
    public void testCorpusWithPunctuation() throws IOException {
        GraphPoet poet = new GraphPoet(new File("test/poet/punctuation.txt"));
        String result = poet.poem("This is fine");
        assertEquals("This is totally fine", result);
    }
}
