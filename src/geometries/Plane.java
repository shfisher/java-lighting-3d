package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Plane extends Geometry implements FlatGeometry{

    // Variables
    private Vector _normal;
    private Point3D _Q;

    // ***************** Constructors ********************** //

// FUNCTION
//   Plane
// PARAMETERS
//   none
// RETURN VALUE
// none
// MEANING
// This functions builds a Plane (Equals The vector to 0)
    public Plane(){
        _normal = new Vector();
        _Q = new Point3D();
    }

// FUNCTION
//   Plane
// PARAMETERS
//  Plane plane
// RETURN VALUE
// none
// MEANING
// This functions builds a Plane. It gets a Plane and and make its value to the itself Plane.
    public Plane (Plane plane){
        _normal = plane.getNormal(null);
        _Q = plane.getQ();
    }

// FUNCTION
//   Plane
// PARAMETERS
//  Vector normal and Point3D q
// RETURN VALUE
// none
// MEANING
// This functions builds a Plane. It gets a normal and point 3D and copy their values to the itself Plane.
    public Plane (Vector normal, Point3D q){
        _normal = new Vector(normal);
        try{
        _normal.normalize();}
        catch(Exception e){}
        _Q = new Point3D(q);
    }

    // ***************** Getters/Setters ********************** //

// FUNCTION
//   getNormal
// PARAMETERS
//   point 3D
// RETURN VALUE
// vector
// MEANING
// This functions returns the Normal of the plane
    public Vector getNormal(Point3D point) {
        return new Vector(_normal);
    }

// FUNCTION
//   getQ
// PARAMETERS
// none
// RETURN VALUE
// point 3D
// MEANING
// This functions returns the q of the plane
    public Point3D getQ() {
        return new Point3D(_Q);
    }

// FUNCTION
//   setNormal
// PARAMETERS
//  Vector the normal
// RETURN VALUE
// none
// MEANING
// This functions sets the normal of the plane
    public void setNormal(Vector normal) {
        this._normal = new Vector(normal);
    }

// FUNCTION
//   setQ
// PARAMETERS
//   point 3D the q
// RETURN VALUE
// none
// MEANING
// This functions sets the q of the plane
    public void setQ(Point3D d) {
        this._Q = new Point3D(d);
    }

    // ***************** Operations ******************** //

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

        List<Point3D> intersectionPoint = new ArrayList<>(1);

        // Vectors
        Vector N = this.getNormal(null); // Plane's Normal
        Vector V = ray.getDirection(); // Ray's Direction

        // Point3Ds
        Point3D P0 = ray.getPOO(); // Ray's Head/Start Point3D
        Point3D Q0 = this.getQ(); // Plane's Point3D

        Vector v = new Vector(Q0, P0); // Creates vector from the plane's point3D to the Ray's Head
        double t = (N.dotProduct(v) * -1) / N.dotProduct(V);

        if (t >= 0){
            V.scale(t);
            P0.add(V);
            intersectionPoint.add(P0);
        }

        return intersectionPoint;
    }

}