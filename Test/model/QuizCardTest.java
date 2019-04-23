package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuizCardTest {
    QuizCard card1 = new QuizCard("one","info one", Type.WHITEPERCH);
    QuizCard card2 = new QuizCard("two","info two",Type.BLUECATFISH);
    @Test
    public void getCorrectAnswerIndex() {
        assertEquals(1,card1.getCorrectAnswerIndex());
        assertEquals(1,card2.getCorrectAnswerIndex());
    }

    @Test
    public void getAnswerOne() {
        assertEquals("...",card1.getAnswerOne());
        assertEquals("...",card1.getAnswerOne());
    }

    @Test
    public void getAnswerTwo() {
        assertEquals("...",card1.getAnswerTwo());
        assertEquals("...",card2.getAnswerTwo());
    }
}