/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometries;

import elements.Camera;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 *
 * @author user
 */
public class QuadrangleTest {
    

    @Test
    public void testFindIntersections() {
        final int WIDTH  = 3;
        final int HEIGHT = 3;

        Ray[][] rays = new Ray [HEIGHT][WIDTH];

        Camera camera = new Camera(new Point3D(0.0 ,0.0 ,0.0),
                new Vector (0.0, 1.0, 0.0),
                new Vector (0.0, 0.0, -1.0));

        Quadrangle q1 = new Quadrangle(new Point3D(2,2,2), new Point3D(3,3,2), new Point3D(4,4,2), new Point3D(5,5,2));
        Triangle t1 = new Triangle(new Point3D(2,2,2), new Point3D(3,3,2), new Point3D(5,5,2));
        Triangle t2 = new Triangle(new Point3D(3,3,2), new Point3D(4,4,2), new Point3D(5,5,2));
        List<Point3D> intersectionPointsT1 = new ArrayList<>();
        List<Point3D> intersectionPointsT2 = new ArrayList<>();
        List<Point3D> intersectionPointsQ = new ArrayList<>();

        System.out.println("Camera:\n" + camera);

        for (int i = 0; i < HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++){

                rays[i][j] = camera.constructRayThroughPixel(
                        WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);

                List<Point3D> rayIntersectionPoints   = t1.  FindIntersections(rays[i][j]);
                List<Point3D> rayIntersectionPoints2  = t2. FindIntersections(rays[i][j]);
                List<Point3D> rayIntersectionPoints3  = q1. FindIntersections(rays[i][j]);
                for (Point3D iPoint: rayIntersectionPoints)
                    intersectionPointsT1.add(iPoint);

                for (Point3D iPoint: rayIntersectionPoints2)
                    intersectionPointsT2.add(iPoint);
                for (Point3D iPoint: rayIntersectionPoints3)
                    intersectionPointsQ.add(iPoint);
            }
        }

        assertTrue(intersectionPointsQ. size() == intersectionPointsT1.size() + intersectionPointsT2.size());
        

        System.out.println("Intersection Points:");
        for (Point3D iPoint: intersectionPointsQ){
            System.out.println(iPoint);
        }
    }

    /**
     * Test of getNormal method, of class Quadrangle.
     */
    @Test
    public void testGetNormal() {
        Vector A = new Vector (2,1,4);
        Vector B = new Vector (5,3,-1);
        Vector c = new Vector (A.crossProduct(B));
        c.normalize();
        c.scale(-1);
        Vector check = new Vector(-13,22,1);
        check.normalize();
        check.scale(-1);
        assertEquals(c.compareTo(check),0);
    }
    
}
