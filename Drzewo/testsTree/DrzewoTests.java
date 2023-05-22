import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DrzewoTests {

    @Test
    public void shouldFindElement() {
        Drzewo testDrzewo = new Drzewo<Integer>( new WierzcholekDrzewa(5, null, null));
        assertTrue(testDrzewo.czyWartoscWDrzewie(5));
    }

    @Test
    public void shouldAddLeft() {
        Drzewo testDrzewo = new Drzewo<Integer>( new WierzcholekDrzewa(5, null, null));
        testDrzewo.dodajBST(3);
        assertTrue(
                testDrzewo.getKorzen().getLewySyn() != null &&
                        testDrzewo.getKorzen().getPrawySyn() == null
        );
    }

    @Test
    public void shouldAddRight() {
        Drzewo testDrzewo = new Drzewo<Integer>( new WierzcholekDrzewa(5, null, null));
        testDrzewo.dodajBST(6);
        assertTrue(
                testDrzewo.getKorzen().getPrawySyn() != null &&
                        testDrzewo.getKorzen().getLewySyn() == null
        );
    }

    @Test
    public void shouldNotFindElement() {
        Drzewo testDrzewo = new Drzewo<Integer>( new WierzcholekDrzewa(5, null, null));
        assertFalse(testDrzewo.czyWartoscWDrzewie(3));
    }

    @Test
    public void shouldNotBeBalanced() {
        Drzewo testDrzewo = new Drzewo<Integer>( new WierzcholekDrzewa(5, null, null));
        testDrzewo.dodajBST(4);
        testDrzewo.dodajBST(3);
        assertFalse(testDrzewo.sprawdzCzyZrownowazone());
    }

    @Test
    public void shouldBeBalanced() {
        Drzewo testDrzewo = new Drzewo<Integer>( new WierzcholekDrzewa(5, null, null));
        testDrzewo.dodajBST(4);
        testDrzewo.dodajBST(6);
        assertTrue(testDrzewo.sprawdzCzyZrownowazone());
    }

    @Test
    public void shouldBeBalancedAVLLeftLeft() {
        Drzewo testDrzewo = new Drzewo<Integer>( new WierzcholekDrzewa(5, null, null));
        testDrzewo.dodajAVL(4);
        testDrzewo.dodajAVL(3);
        assertTrue(testDrzewo.sprawdzCzyZrownowazone());
    }

    @Test
    public void shouldBeBalancedAVLRightRight() {
        Drzewo testDrzewo = new Drzewo<Integer>( new WierzcholekDrzewa(5, null, null));
        testDrzewo.dodajAVL(6);
        testDrzewo.dodajAVL(7);
        assertTrue(testDrzewo.sprawdzCzyZrownowazone());
    }

    @Test
    public void shouldBeBalancedAVLLeftRight() {
        Drzewo testDrzewo = new Drzewo<Integer>( new WierzcholekDrzewa(5, null, null));
        testDrzewo.dodajAVL(3);
        testDrzewo.dodajAVL(4);
        assertTrue(testDrzewo.sprawdzCzyZrownowazone());
    }

    @Test
    public void shouldBeBalancedAVLRightLeft() {
        Drzewo testDrzewo = new Drzewo<Integer>( new WierzcholekDrzewa(5, null, null));
        testDrzewo.dodajAVL(7);
        testDrzewo.dodajAVL(6);
        assertTrue(testDrzewo.sprawdzCzyZrownowazone());
    }
}
