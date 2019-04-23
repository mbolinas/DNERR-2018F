package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class InfoCardTest {
    InfoCard card1 = new InfoCard("Fishies","lots of info",Type.WHITEPERCH);
    InfoCard card2 = new InfoCard("Little fishes","less info",Type.BLUECATFISH);
    @Test
    public void getTitle() {
        assertEquals("Fishies",card1.getTitle());
        assertEquals("Little fishes",card2.getTitle());
    }

    @Test
    public void getInfo() {
        assertEquals("lots of info",card1.getInfo());
        assertEquals("less info",card2.getInfo());
    }
}