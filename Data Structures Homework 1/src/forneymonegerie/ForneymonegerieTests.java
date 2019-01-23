// Jigar Swaminarayan
package forneymonegerie;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ForneymonegerieTests {
    
    // =================================================
    // Test Configuration
    // =================================================
    
    // Used as the basic empty menagerie to test; the @Before
    // method is run before every @Test
    Forneymonegerie fm;
    @Before
    public void init () {
        fm = new Forneymonegerie();
    }

    
    // =================================================
    // Unit Tests
    // =================================================
    
    @Test
    public void testSize() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        assertEquals(2, fm.size());
        fm.collect("Burnymon");
        assertEquals(3, fm.size());
        
        // Added Test:
        fm.collect("Zappymon");
        assertEquals(4, fm.size());
        fm.collect("Burnymon");
        assertEquals(5, fm.size());
    }

    @Test
    public void testTypeSize() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        assertEquals(1, fm.typeSize());
        fm.collect("Burnymon");
        assertEquals(2, fm.typeSize());
        
        // Added Test:
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Zappymon");
        assertEquals(3, fm.typeSize());
        
    }

    @Test
    public void testCollect() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        assertTrue(fm.contains("Dampymon"));
        assertTrue(fm.contains("Burnymon"));
        
        // Added Test:
        fm.collect("Zappymon");
        assertTrue(fm.contains("Zappymon"));
    }

    @Test
    public void testRelease() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        assertEquals(2, fm.size());
        assertEquals(1, fm.typeSize());
        fm.release("Dampymon");
        assertEquals(1, fm.size());
        assertEquals(1, fm.typeSize());
        
        // Added Test:
        fm.collect("Zappymon");
        fm.collect("Zappymon");
        assertEquals(3, fm.size());
        assertEquals(2, fm.typeSize());
        fm.release("Zappymon");
        assertEquals(2, fm.size());
        assertEquals(2, fm.typeSize());
    }

    @Test
    public void testReleaseType() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        assertEquals(3, fm.size());
        assertEquals(2, fm.typeSize());
        fm.releaseType("Dampymon");
        assertEquals(1, fm.size());
        assertEquals(1, fm.typeSize());
        
        // Added Test:
        fm.collect("Zappymon");
        fm.collect("Zappymon");
        assertEquals(3, fm.size());
        assertEquals(2, fm.typeSize());
        fm.releaseType("Zappymon");
        assertEquals(1, fm.size());
        assertEquals(1, fm.typeSize());
    }

    @Test
    public void testCountType() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        assertEquals(2, fm.countType("Dampymon"));
        assertEquals(1, fm.countType("Burnymon"));
        assertEquals(0, fm.countType("forneymon"));
        
        // Added Test:
        fm.collect("Zappymon");
        fm.collect("Zappymon");
        assertEquals(2, fm.countType("Zappymon"));
        assertEquals(0, fm.countType("Leafymon"));
    }

    @Test
    public void testContains() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        assertTrue(fm.contains("Dampymon"));
        assertTrue(fm.contains("Burnymon"));
        assertFalse(fm.contains("forneymon"));
        
        // Added Test:
        fm.collect("Zappymon");
        fm.collect("Leafymon");
        assertTrue(fm.contains("Zappymon"));
        assertTrue(fm.contains("Leafymon"));
        assertFalse(fm.contains("mon"));
    }

    @Test
    public void testNth() {
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Zappymon");
        fm.collect("Dampymon");
        assertEquals("Dampymon", fm.nth(0));
        assertEquals("Dampymon", fm.nth(1));
        assertEquals("Burnymon", fm.nth(2));
        assertEquals("Zappymon", fm.nth(3));
        
        // Added Test:
        fm.collect("Leafymon");
        assertEquals("Leafymon", fm.nth(4));
    }

    @Test
    public void testRarestType() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Zappymon");
        assertEquals("Zappymon", fm.rarestType());
        fm.collect("Zappymon");
        assertEquals("Zappymon", fm.rarestType());
        
        // Added Test:
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        assertEquals("Burnymon", fm.rarestType());
        fm.collect("Burnymon");
        assertEquals("Burnymon", fm.rarestType());
        fm.collect("Burnymon");
        assertEquals("Zappymon", fm.rarestType());
    }

    @Test
    public void testClone() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        Forneymonegerie dolly = fm.clone();
        assertEquals(dolly.countType("Dampymon"), 2);
        assertEquals(dolly.countType("Burnymon"), 1);
        dolly.collect("Zappymon");
        assertFalse(fm.contains("Zappymon"));
        
        // Added Test:
        Forneymonegerie fm2 = new Forneymonegerie();
        fm2.collect("Leafymon");
        fm2.collect("Burnymon");
        fm2.collect("Burnymon");
        Forneymonegerie c2 = fm2.clone();
        assertEquals(c2.countType("Burnymon"), 2);
        assertEquals(c2.countType("Leafymon"), 1);
        c2.collect("Zappymon");
        assertFalse(fm2.contains("Zappymon"));
    }

    @Test
    public void testTrade() {
        Forneymonegerie fm1 = new Forneymonegerie();
        fm1.collect("Dampymon");
        fm1.collect("Dampymon");
        fm1.collect("Burnymon");
        Forneymonegerie fm2 = new Forneymonegerie();
        fm2.collect("Zappymon");
        fm2.collect("Leafymon");
        fm1.trade(fm2);
        assertTrue(fm1.contains("Zappymon"));
        assertTrue(fm1.contains("Leafymon"));
        assertTrue(fm2.contains("Dampymon"));
        assertTrue(fm2.contains("Burnymon"));
        assertFalse(fm1.contains("Dampymon"));
        
        // Added Test:
        Forneymonegerie fm3 = new Forneymonegerie();
        fm3.collect("Zappymon");
        fm3.collect("Zappymon");
        fm3.collect("Burnymon");
        Forneymonegerie fm4 = new Forneymonegerie();
        fm4.collect("Dampymon");
        fm4.collect("Leafymon");
        fm3.trade(fm4);
        assertTrue(fm3.contains("Dampymon"));
        assertTrue(fm3.contains("Leafymon"));
        assertTrue(fm4.contains("Zappymon"));
        assertTrue(fm4.contains("Burnymon"));
        assertFalse(fm3.contains("Burnymon"));
    }

    @Test
    public void testDiffMon() {
        Forneymonegerie fm1 = new Forneymonegerie();
        fm1.collect("Dampymon");
        fm1.collect("Dampymon");
        fm1.collect("Burnymon");
        Forneymonegerie fm2 = new Forneymonegerie();
        fm2.collect("Dampymon");
        fm2.collect("Zappymon");
        Forneymonegerie fm3 = Forneymonegerie.diffMon(fm1, fm2);
        assertEquals(fm3.countType("Dampymon"), 1);
        assertEquals(fm3.countType("Burnymon"), 1);
        assertFalse(fm3.contains("Zappymon"));
        fm3.collect("Leafymon");
        assertFalse(fm1.contains("Leafymon"));
        assertFalse(fm2.contains("Leafymon"));
        
        // Added Test:
        Forneymonegerie newfm1 = new Forneymonegerie();
        newfm1.collect("Leafymon");
        newfm1.collect("Leafymon");
        newfm1.collect("Zappymon");
        Forneymonegerie newfm2 = new Forneymonegerie();
        newfm2.collect("Leafymon");
        newfm2.collect("Dampymon");
        Forneymonegerie newfm3 = Forneymonegerie.diffMon(newfm1, newfm2);
        assertEquals(newfm3.countType("Leafymon"), 1);
        assertEquals(newfm3.countType("Zappymon"), 1);
        assertFalse(newfm3.contains("Dampymon"));
        fm3.collect("Burnymon");
        assertFalse(newfm1.contains("Burnymon"));
        assertFalse(newfm2.contains("Burnymon"));
        
    }

    @Test
    public void testSameForneymonegerie() {
        Forneymonegerie fm1 = new Forneymonegerie();
        fm1.collect("Dampymon");
        fm1.collect("Dampymon");
        fm1.collect("Burnymon");
        Forneymonegerie fm2 = new Forneymonegerie();
        fm2.collect("Burnymon");
        fm2.collect("Dampymon");
        fm2.collect("Dampymon");
        assertTrue(Forneymonegerie.sameCollection(fm1, fm2));
        assertTrue(Forneymonegerie.sameCollection(fm2, fm1));
        fm2.collect("Leafymon");
        assertFalse(Forneymonegerie.sameCollection(fm1, fm2));
    }

}
