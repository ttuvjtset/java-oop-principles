package kt08_mocking.tests;

import kt08_mocking.WordPacker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class WordPackerTest {
    private WordPacker wordPacker;

    @Before
    public void setUp() throws Exception {
        wordPacker = new WordPacker();
    }

    @Test
    public void testIfLongSentencePacksRight() throws Exception {

        assertEquals("vstsm  lvndlg, sndd n kllsd j skvd lld",
                wordPacker.pack("Avastasime uue elevandiliigi, isendid on kollased ja oskavad laulda"));
    }

    @Test
    public void testIfWordPacksRight1() throws Exception {

        assertEquals("rn", wordPacker.pack("Aaron"));
    }

    @Test
    public void testIfWordPacksRight2() throws Exception {

        assertEquals("HRRY", wordPacker.pack("HARRY"));
    }

    @Test
    public void testIfWordPacksRight3() throws Exception {

        assertEquals("Tmll", wordPacker.pack("Timolol"));
    }

    @Test
    public void testIfWordsPackRight() throws Exception {

        assertEquals("T t TTÜ", wordPacker.pack("IT at TTÜ"));
    }

    @Test
    public void testIfAllSmallLettersPackRight() throws Exception {

        assertEquals("qqwwrrttyypp[[]]'';;llkkjjhhggffddsszzxxccvvbbnnmm,,..//",
                wordPacker.pack("qqwweerrttyyuuiioopp[[]]'';;llkkjjhhggffddssaazzxxccvvbbnnmm,,..//"));
    }

    @Test
    public void testIfDigitsPackRight() throws Exception {

        assertEquals("00112233445566778899",
                wordPacker.pack("00112233445566778899"));
    }

    @Test
    public void testIfAllBigLettersPackRight() throws Exception {

        assertEquals("QQWWRRTTYYPP[[]]'';;LLKKJJHHGGFFDDSSZZXXCCVVBBNNMM,,..//",
                wordPacker.pack("QQWWEERRTTYYUUIIOOPP[[]]'';;LLKKJJHHGGFFDDSSAAZZXXCCVVBBNNMM,,..//"));
    }
}