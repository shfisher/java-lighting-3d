/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class Point3DTest {
    
    public Point3DTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of compareTo method, of class Point3D.
     */
    @Test
    public void testPoint3DCompareTo() {
        System.out.println("compareTo");
        assertEquals(1, (new Point3D(1,2,3).compareTo(new Point3D(1,2,4))));
        assertEquals(1, (new Point3D(2.23,5.56,8.34).compareTo(new Point3D(2.28,5.51,8.32))));
        assertEquals(0, (new Point3D(0,0,0).compareTo(new Point3D())));
    }

    /**
     * Test of toString method, of class Point3D.
     */
    @Test
    public void testPoint3DToString() {
        System.out.println("toString");
        assertEquals("(2.64, 6.51, 5.50)", (new Point3D(2.639, 6.512, 5.496)).toString());
        assertEquals("(0.00, 0.00, 0.00)", (new Point3D()).toString());
    }

    /**
     * Test of add method, of class Point3D.
     */
    @Test
    public void testPoint3DAdd() {
        System.out.println("add");
        Point3D instance = new Point3D(1,2,4);
        instance.add(new Vector(1,2,1));
        assertEquals(0, instance.compareTo(new Point3D(2,4,5)));
    }

    /**
     * Test of subtract method, of class Point3D.
     */
    @Test
    public void testPoint3DSubtract() {
        System.out.println("subtract");
        Point3D instance = new Point3D(3,3,4);
        instance.subtract(new Vector(1,2,1));
        assertEquals(0, instance.compareTo(new Point3D(2,1,3)));
    }

    /**
     * Test of distance method, of class Point3D.
     */
    @Test
    public void testPoint3DDistance() {
        System.out.println("distance");
        assertEquals(Math.sqrt(3), (new Point3D(1,1,1).distance(new Point3D(2,2,2))), 0.1);
    }
    
}
