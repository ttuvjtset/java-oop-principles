package kt09_streams.destination;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DestinationTest {

    @Test
    public void testIfDestinationParsingStringWorksRight() {
        Destination destination = new Destination("Albania,Candidate,,,,,,,Lek,ALL,Albanian,2892302,28748,101" +
                ",10267,11398,3945");

        assertEquals(destination.getCountry(), "Albania");
        assertEquals(destination.getMemberOfEU(), "Candidate");
        assertEquals(destination.getAverageTemperature(), 299.0, 1);
        assertEquals(destination.getAccessionYear(), "");
        assertEquals(destination.getCurrency(), "Lek");
        assertEquals(destination.getCurrencyCode(), "ALL");
        assertEquals(destination.getArea(), "28748");
        assertEquals(destination.getGdp(), "3945");
    }

    @Test
    public void testIfDestinationParsingStringWorksRight2() {
        Destination destination = new Destination("Estonia,Member,2004,4,6,,Member,Member,Euro,EUR,Estonian" +
                ",1314870,45227,29,20252,22459,17119");

        assertEquals(destination.getCountry(), "Estonia");
        assertEquals(destination.getMemberOfEU(), "Member");
        assertEquals(293.04, destination.getAverageTemperature(), 0.001);
        assertEquals(destination.getAccessionYear(), "2004");
        assertEquals(destination.getCurrency(), "Euro");
        assertEquals(destination.getCurrencyCode(), "EUR");
        assertEquals(destination.getArea(), "45227");
        assertEquals(destination.getGdp(), "17119");
    }

    @Test
    public void testIfDestinationParsingStringWorksRight3() {
        Destination destination = new Destination("Germany,Member,1958,29,96,,Member,Member,Euro,EUR,German" +
                ",81197537,357021,227,3032820,3363447,41313");

        assertEquals(destination.getCountry(), "Germany");
        assertEquals(destination.getMemberOfEU(), "Member");
        assertEquals(292.58, destination.getAverageTemperature(), 0.001);
        assertEquals(destination.getAccessionYear(), "1958");
        assertEquals(destination.getCurrency(), "Euro");
        assertEquals(destination.getCurrencyCode(), "EUR");
        assertEquals(destination.getArea(), "357021");
        assertEquals(destination.getGdp(), "41313");
    }

    @Test
    public void testIfDestinationParsingStringWorksRight4() {
        Destination destination = new Destination("Cyprus,Member,2004,4,6,,Member,Member,Euro," +
                "EUR,\"Greek, Turkish\",847008,9251,92,17637,19560,23243");

        assertEquals(destination.getCountry(), "Cyprus");
        assertEquals(destination.getMemberOfEU(), "Member");
        assertEquals(293.04, destination.getAverageTemperature(), 0.001);
        assertEquals(destination.getAccessionYear(), "2004");
        assertEquals(destination.getCurrency(), "Euro");
        assertEquals(destination.getCurrencyCode(), "EUR");
        assertEquals(destination.getArea(), "9251");
        assertEquals(destination.getGdp(), "23243");
    }

    @Test
    public void testIfDestinationParsingStringWorksRight5() {
        Destination destination = new Destination("Liechtenstein,,,,,Member,Member,,Franc,CHF,German," +
                "37366,160,234,,,");

        assertEquals(destination.getCountry(), "Liechtenstein");
        assertEquals(destination.getMemberOfEU(), "");
        assertEquals(299.0, destination.getAverageTemperature(), 1);
        assertEquals(destination.getAccessionYear(), "");
        assertEquals(destination.getCurrency(), "Franc");
        assertEquals(destination.getCurrencyCode(), "CHF");
        assertEquals(destination.getArea(), "160");
        assertEquals(destination.getGdp(), "");
    }
}