package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Quadrangle extends Geometry implements FlatGeometry {

    // Variables
    private Triangle _tri1;
    private Triangle _tri2;

    // ***************** Constructors ********************** //

// FUNCTION
// Quadrangle
// PARAMETERS
// 4 Point3D
// RETURN VALUE
// none
// MEANING
// This functions builds a Quadrangle, by creating 2 Triangles
    public Quadrangle (Point3D P1, Point3D P2, Point3D P3, Point3D P4) {
         _tri1 = new Triangle(P1, P2, P4);
         _tri2 = new Triangle(P2, P3, P4);
    }

// FUNCTION
//   FindIntersections
// PARAMETERS
//  Ray ray
// RETURN VALUE
// List<Point3D>
// MEANING
// This functions Find the Intersections of vector on view
    @Override
    public List<Point3D> FindIntersections(Ray ray) {

        List<Point3D> intersection = new ArrayList<Point3D>(1);

        if (!(_tri1.FindIntersections(ray).isEmpty()))
            intersection=_tri1.FindIntersections(ray);

        else if (!(_tri2.FindIntersections(ray).isEmpty()))
            intersection=_tri2.FindIntersections(ray);

        return intersection;
    }

// FUNCTION
//   getNormal
// PARAMETERS
//   Point3D
// RETURN VALUE
// Vector
// MEANING
// This functions gets a Point3D and returns the normal of the Quadrangle
    @Override
    public Vector getNormal(Point3D point) {
        return new Vector(this._tri1.getNormal(point));
    }

}