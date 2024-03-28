/**
 * Name: Kevin Chen
 * Assignment: Words in a Sentence
 * Date: 2/27/23
 * Notes: JUnit tests for the implementation of the Sentence
 */

import org.junit.Test;
import static org.junit.Assert.*;


public class SentenceTest {
    Sentence hello = new Sentence("Hello world!");
    Sentence goodb = new Sentence("Bye. Cruel world");

    @Test public void sentenceTest1() {
        assertEquals(hello.getNumberOfWords(), 2);
        assertEquals(hello.longestWord(), "Hello");
        assertEquals(hello.toString(), "Hello world!");

        assertEquals(goodb.getNumberOfWords(), 3);
        assertEquals(goodb.longestWord(), "Cruel");
        assertEquals(goodb.toString(), "Bye. Cruel world.");
    }

    @Test public void sentenceTest2() {
        assertEquals(hello.clone().toString(), "Hello world!");
        assertEquals(hello, hello.clone());
        assertEquals(hello.merge(hello).toString(), "Hello world! Hello world!");

        assertNotEquals(hello, goodb);
        assertEquals(goodb.merge(hello).toString(), "Bye. Cruel world Hello world!");
        assertEquals(hello.merge(goodb).toString(), "Hello world! Bye. Cruel world.");
        assertEquals(hello.merge(goodb).getNumberOfWords(), 5);
    }
}
