package linked_forneymonegerie;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedForneymonegerieTests {
    
    // =================================================
    // Test Configuration
    // =================================================
	static int possible = 0, passed = 0;
    // Used as the basic empty menagerie to test; the @Before
    // method is run before every @Test
    LinkedForneymonegerie fm;
    @Before
    public void init () {
        fm = new LinkedForneymonegerie();
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
    }
    
    @Test
    public void testSize_t1() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        assertEquals(fm.size(), 3);
    }
    
    @Test
    public void testSize_t2() {
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Zappymon");
        assertEquals(fm.size(), 3);
    }

    @Test
    public void testTypeSize() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        assertEquals(1, fm.typeSize());
        fm.collect("Burnymon");
        assertEquals(2, fm.typeSize());
    }
    
    @Test
    public void testTypeSize_t2() {
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Zappymon");
        assertEquals(fm.typeSize(), 3);
    }
    
    @Test
    public void testTypeSize_t3() {
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.release("Burnymon");
        assertEquals(fm.typeSize(), 1);
        fm.release("Dampymon");
        assertEquals(fm.typeSize(), 0);
    }

    @Test
    public void testCollect() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        assertTrue(fm.contains("Dampymon"));
        assertTrue(fm.contains("Burnymon"));
    }
    
    @Test
    public void testCollect_t1() {
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        assertTrue(fm.contains("Burnymon"));
        assertTrue(fm.contains("Dampymon"));
        assertEquals(fm.size(), 3);
        assertEquals(fm.typeSize(), 2);
    }
    
    @Test
    public void testCollect_t2() {
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.release("Dampymon");
        assertTrue(fm.contains("Burnymon"));
        assertFalse(fm.contains("Dampymon"));
        assertEquals(fm.size(), 2);
        assertEquals(fm.typeSize(), 1);
    }
    
    @Test
    public void testCollect_t4() {
        for (int i = 0; i < 1000; i++) {
            fm.collect("" + i);
        }
        assertEquals(fm.size(), 1000);
        assertEquals(fm.typeSize(), 1000);
    }
    
    @Test
    public void testCollect_t5() {
        for (int i = 0; i < 1000; i++) {
            fm.collect("SAMESIES");
        }
        assertEquals(fm.size(), 1000);
        assertEquals(fm.typeSize(), 1);
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
    }
    
    @Test
    public void testRelease_t1() {
        fm.collect("Dampymon");
        boolean released = fm.release("Dampymon");
        assertEquals(fm.size(), 0);
        assertTrue(released);
        assertFalse(fm.contains("Dampymon"));
        released = fm.release("Dampymon");
        assertFalse(released);
    }
    
    @Test
    public void testRelease_t3() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.release("Dampymon");
        assertEquals(fm.size(), 4);
        assertEquals(fm.typeSize(), 2);
        fm.collect("Zappymon");
        fm.collect("Zappymon");
        boolean released = fm.release("Burnymon");
        assertTrue(released);
        released = fm.release("Burnymon");
        assertTrue(released);
        assertEquals(fm.size(), 4);
        assertEquals(fm.typeSize(), 2);
        assertFalse(fm.contains("Burnymon"));
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
    }
    
    @Test
    public void testReleaseAll_t1() {
        fm.releaseType("Dampymon");
        fm.collect("Dampymon");
        fm.releaseType("Dampymon");
        assertEquals(fm.size(), 0);
        assertFalse(fm.contains("Dampymon"));
    }
    
    @Test
    public void testReleaseAll_t3() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Zappymon");
        fm.releaseType("Burnymon");
        assertEquals(fm.size(), 3);
        assertEquals(fm.typeSize(), 2);
        assertFalse(fm.contains("Burnymon"));
        fm.releaseType("Dampymon");
        assertEquals(fm.size(), 1);
        assertEquals(fm.typeSize(), 1);
        fm.releaseType("Zappymon");
        assertEquals(fm.size(), 0);
        assertEquals(fm.typeSize(), 0);
    }

    @Test
    public void testCountType() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        assertEquals(2, fm.countType("Dampymon"));
        assertEquals(1, fm.countType("Burnymon"));
        assertEquals(0, fm.countType("forneymon"));
    }
    
    @Test
    public void testCount_t1() {
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        assertEquals(fm.countType("Burnymon"), 3);
        fm.releaseType("Burnymon");
        assertEquals(fm.countType("Burnymon"), 0);
    }
    
    @Test
    public void testCount_t2() {
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        assertEquals(fm.countType("Burnymon"), 2);
        assertEquals(fm.countType("Dampymon"), 2);
        fm.releaseType("Burnymon");
        assertEquals(fm.countType("Burnymon"), 0);
        fm.release("Dampymon");
        assertEquals(fm.countType("Dampymon"), 1);
    }

    @Test
    public void testContains() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        assertTrue(fm.contains("Dampymon"));
        assertTrue(fm.contains("Burnymon"));
        assertFalse(fm.contains("forneymon"));
    }

    @Test
    public void testRarestType() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Zappymon");
        assertEquals("Zappymon", fm.rarestType());
        fm.collect("Zappymon");
        assertEquals("Zappymon", fm.rarestType());
    }
    
    public void testGetRarest_t1() {
        assertEquals(fm.rarestType(), null);
    }
    
    @Test
    public void testGetRarest_t2() {
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Zappymon");
        assertEquals("Zappymon", fm.rarestType());
    }

    @Test
    public void testClone() {
        fm.collect("Dampymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        LinkedForneymonegerie dolly = fm.clone();
        assertEquals(dolly.countType("Dampymon"), 2);
        assertEquals(dolly.countType("Burnymon"), 1);
        dolly.collect("Zappymon");
        assertFalse(fm.contains("Zappymon"));
    }
    
    @Test
    public void testClone_t1() {
        LinkedForneymonegerie dolly = fm.clone();
        fm.collect("Dampymon");
        assertFalse(dolly.contains("Dampymon"));
    }
    
    @Test
    public void testClone_t2() {
        fm.collect("Dampymon");
        LinkedForneymonegerie dolly = fm.clone();
        dolly.collect("Burnymon");
        LinkedForneymonegerie superDolly = dolly.clone();
        superDolly.collect("Zappymon");
        assertTrue(superDolly.contains("Dampymon"));
        assertTrue(superDolly.contains("Burnymon"));
        assertFalse(dolly.contains("Zappymon"));
    }
    

    @Test
    public void testTrade() {
        LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
        fm1.collect("Dampymon");
        fm1.collect("Dampymon");
        fm1.collect("Burnymon");
        LinkedForneymonegerie fm2 = new LinkedForneymonegerie();
        fm2.collect("Zappymon");
        fm2.collect("Leafymon");
        fm1.trade(fm2);
        assertTrue(fm1.contains("Zappymon"));
        assertTrue(fm1.contains("Leafymon"));
        assertTrue(fm2.contains("Dampymon"));
        assertTrue(fm2.contains("Burnymon"));
        assertFalse(fm1.contains("Dampymon"));
    }
    
    @Test
    public void testTrade_t1() {
        LinkedForneymonegerie fm2 = new LinkedForneymonegerie();
        fm.collect("Dampymon");
        fm2.trade(fm);
        assertTrue(fm.empty());
        assertFalse(fm2.empty());
    }
    
    @Test
    public void testTrade_t2() {
        LinkedForneymonegerie fm2 = new LinkedForneymonegerie();
        LinkedForneymonegerie fm3 = new LinkedForneymonegerie();
        fm2.collect("Dampymon");
        fm.collect("Burnymon");
        fm2.trade(fm);
        fm3.trade(fm2);
        assertTrue(fm2.empty());
        assertTrue(fm.contains("Dampymon"));
        assertTrue(fm3.contains("Burnymon"));
        fm.collect("Zappymon");
        assertFalse(fm2.contains("Zappymon"));
    }
    
    @Test
    public void testTrade_t3() {
        fm.collect("Dampymon");
        fm.trade(fm);
        assertTrue(fm.contains("Dampymon"));
        assertEquals(fm.size(), 1);
    }
    
    @Test
    public void testTrade_t4() {
        LinkedForneymonegerie fm2 = new LinkedForneymonegerie();
        fm.trade(fm2);
        assertTrue(fm.empty());
        assertTrue(fm2.empty());
    }

    @Test
    public void testDiffMon() {
        LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
        fm1.collect("Dampymon");
        fm1.collect("Dampymon");
        fm1.collect("Burnymon");
        LinkedForneymonegerie fm2 = new LinkedForneymonegerie();
        fm2.collect("Dampymon");
        fm2.collect("Zappymon");
        LinkedForneymonegerie fm3 = LinkedForneymonegerie.diffMon(fm1, fm2);
        assertEquals(fm3.countType("Dampymon"), 1);
        assertEquals(fm3.countType("Burnymon"), 1);
        assertFalse(fm3.contains("Zappymon"));
        fm3.collect("Leafymon");
        assertFalse(fm1.contains("Leafymon"));
        assertFalse(fm2.contains("Leafymon"));
    }
    
    @Test
    public void testDiffMon_t1() {
        LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
        fm1.collect("Dampymon");
        fm1.collect("Burnymon");
        LinkedForneymonegerie fm2 = LinkedForneymonegerie.diffMon(fm, fm1);
        assertEquals(fm2.size(), 0);
        assertFalse(fm2.contains("Dampymon"));
    }
    
    @Test
    public void testDiffMon_t2() {
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
        fm1.collect("Dampymon");
        fm1.collect("Dampymon");
        LinkedForneymonegerie fm2 = LinkedForneymonegerie.diffMon(fm, fm1);
        assertEquals(fm2.size(), 1);
        assertFalse(fm2.contains("Dampymon"));
    }
    
    @Test
    public void testDiffMon_t5() {
    	LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
    	LinkedForneymonegerie diff = LinkedForneymonegerie.diffMon(fm, fm1);
        assertTrue(diff.empty());
    }

    @Test
    public void testSameForneymonegerie() {
        LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
        fm1.collect("Dampymon");
        fm1.collect("Dampymon");
        fm1.collect("Burnymon");
        LinkedForneymonegerie fm2 = new LinkedForneymonegerie();
        fm2.collect("Burnymon");
        fm2.collect("Dampymon");
        fm2.collect("Dampymon");
        assertTrue(LinkedForneymonegerie.sameCollection(fm1, fm2));
        assertTrue(LinkedForneymonegerie.sameCollection(fm2, fm1));
        fm2.collect("Leafymon");
        assertFalse(LinkedForneymonegerie.sameCollection(fm1, fm2));
    }
    
    @Test
    public void testSameCollection_t1() {
    	LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
        assertTrue(LinkedForneymonegerie.sameCollection(fm, fm1));
        assertTrue(LinkedForneymonegerie.sameCollection(fm1, fm1));
        fm1.collect("Dampymon");
        assertTrue(LinkedForneymonegerie.sameCollection(fm1, fm1));
    }
    
    @Test
    public void testSameCollection_t2() {
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Burnymon");
        LinkedForneymonegerie fm1 = new LinkedForneymonegerie();
        fm1.collect("Burnymon");
        fm1.collect("Dampymon");
        assertFalse(LinkedForneymonegerie.sameCollection(fm, fm1));
        fm.releaseType("Burnymon");
        fm1.releaseType("Burnymon");
        assertTrue(LinkedForneymonegerie.sameCollection(fm, fm1));
    }
    
    @Test
    public void testToString() {
        possible--; // For bonus' sake
        fm.collect("Burnymon");
        assertEquals("[ \"Burnymon\": 1 ]", fm.toString());
    }
    
    @Test
    public void testToString_t1() {
        possible--;
        fm.collect("Burnymon");
        fm.collect("Dampymon");
        fm.collect("Burnymon");
        fm.collect("Leafymon");
        fm.collect("Zappymon");
        fm.collect("Leafymon");
        assertEquals("[ \"Burnymon\": 2, \"Dampymon\": 1, \"Leafymon\": 2, \"Zappymon\": 1 ]", fm.toString());
    }
    
    @Test
    public void testIteratorBasics() {
        fm.collect("Andrewmon");
        fm.collect("Andrewmon");
        fm.collect("Andrewmon");
        fm.collect("Baddymon");
        LinkedForneymonegerie.Iterator it = fm.getIterator();

        // Test next()
        LinkedForneymonegerie dolly = fm.clone();
        while (true) {
            String gotten = it.getType();
            assertTrue(dolly.contains(gotten));
            dolly.release(gotten);
            if (it.hasNext()) {it.next();} else {break;}
        }
        assertTrue(dolly.empty());
        assertFalse(it.hasNext());
        
        // Test prev()
        dolly = fm.clone();
        while (true) {
            String gotten = it.getType();
            assertTrue(dolly.contains(gotten));
            dolly.release(gotten);
            if (it.hasPrev()) {it.prev();} else {break;}
        }
        // If we've seen every Forneymon that was in fm, and removed
        // that from dolly (a copy), then dolly should be empty by now
        assertTrue(dolly.empty());
        assertFalse(it.hasPrev());
        
        int countOfReplaced = fm.countType(it.getType());
        it.replaceAll("Mimicmon");
        assertEquals(countOfReplaced, fm.countType("Mimicmon"));
        assertTrue(it.isValid());
        
        fm.collect("Cooliomon");
        assertFalse(it.isValid());
    }

}
