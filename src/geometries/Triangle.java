package geometries;

import java.util.ArrayList;
import java.util.List;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Triangle extends Geometry implements FlatGeometry {

    // Variables
    private Point3D _p1;
    private Point3D _p2;
    private Point3D _p3;

    // ***************** Constructors ********************** //

// FUNCTION
//   Triangle
// PARAMETERS
//   none
// RETURN VALUE
// none
// MEANING
// This functions builds a Triangle
    public Triangle(){
        _p1 = new Point3D();
        _p2 = new Point3D();
        _p3 = new Point3D();
    }

// FUNCTION
//   Triangle
// PARAMETERS
//   triangle
// RETURN VALUE
// none
// MEANING
// This functions builds a Triangle. It gets a Triangle and copy it

    public Triangle(Triangle triangle){
        _p1 = triangle.getP1();
        _p2 = triangle.getP2();
        _p3 = triangle.getP3();
    }

// FUNCTION
//   Triangle
// PARAMETERS
//   3 Point3D
// RETURN VALUE
// none
// MEANING
// This functions builds a Triangle. It gets a 3 Point3D and use their values to create a Triangle
    public Triangle(Point3D p1, Point3D p2, Point3D p3){
        _p1 = new Point3D(p1);
        _p2 = new Point3D(p2);
        _p3 = new Point3D(p3);
    }

    // ***************** Getters/Setters ********************** //

// FUNCTION
//   getP1
// PARAMETERS
//   none
// RETURN VALUE
// Point3D
// MEANING
// This functions returns a Point3D so that we can use it(because it's protection)
    public Point3D getP1() {
        return new Point3D(_p1);
    }

// FUNCTION
//   getP2
// PARAMETERS
//   none
// RETURN VALUE
// Point3D
// MEANING
// This functions returns a Point3D so that we can use it(because it's protection)
    public Point3D getP2() {
        return new Point3D(_p2);
    }

// FUNCTION
//   getP3
// PARAMETERS
//   none
// RETURN VALUE
// Point3D
// MEANING
// This functions returns a Point3D so that we can use it(because it's protection)
    public Point3D getP3() {
        return new Point3D(_p3);
    }

// FUNCTION
//   setP1
// PARAMETERS
//   Point3D
// RETURN VALUE
// none
// MEANING
// This functions gets a Point3D and copy it's value to one of the point3D of the Triangle
    public void setP1(Point3D p1) {
        this._p1 = new Point3D(p1);
    }

// FUNCTION
//   setP2
// PARAMETERS
//   Point3D
// RETURN VALUE
// none
// MEANING
// This functions gets a Point3D and copy it's value to one of the point3D of the Triangle
    public void setP2(Point3D p2) {
        this._p2 = new Point3D(p2);
    }

// FUNCTION
// setP3
// PARAMETERS
//   Point3D
// RETURN VALUE
// none
// MEANING
// This functions gets a Point3D and copy it's value to one of the point3D of the Triangle
    public void setP3(Point3D p3) {
        this._p3 = new Point3D(p3);
    }

    // ***************** Operations ******************** //

// FUNCTION
//   getNormal
// PARAMETERS
//   Point3D
// RETURN VALUE
// Vector
// MEANING
// This functions gets a Point3D and returns the normal of the Triangle
    @Override
    public Vector getNormal(Point3D point){

        // those r two vector of the triangle
        Vector A = new Vector (_p1, _p2);
        Vector B = new Vector (_p1, _p3);
        // Third new Vector created by the triangle's vectors' crossProduct
        Vector C = new Vector (A.crossProduct(B));
        try{
        C.normalize();}
        catch(Exception e){}
        C.scale(-1);
        return C;
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
        List<Point3D> intersectionPoints = new ArrayList<>(1); // List for the intersection points

        Point3D P0 = ray.getPOO(); // Ray's Head Point3D
        Vector n = getNormal(null); // Triangle's Normal
        Plane plane = new Plane(n, _p3); // Creates new Plane by the triangle's normal & one of the triangle's point3D

        // case there r no intersections returns the list as it
        if (plane.FindIntersections(ray).isEmpty())
            return intersectionPoints;

        // Otherwise
        Point3D intersectionPlane = plane.FindIntersections(ray).get(0);
        Vector P_P0 = new Vector(P0, intersectionPlane);

        // Checks if the intersection point is in the triangle or not

        // those doubles gonna b the results of the dotProducts
        // between -P1 been created & each of the new Vectors we about to create
        double s1, s2, s3;
        s1 = TrianglePart1(P0, P_P0);
        s2 = TrianglePart2(P0, P_P0);
        s3 = TrianglePart3(P0, P_P0);

        // in case the point3D is in the plane add it to the list
        if (((s1 > 0) && (s2 > 0) && (s3 > 0)) || ((s1 < 0) && (s2 < 0) && (s3 < 0)))
            intersectionPoints.add(intersectionPlane);
        return intersectionPoints;
    }

// FUNCTION
//   TrianglePart3
// PARAMETERS
// Point3D, Vector
// RETURN VALUE
// double
// MEANING
// This functions Checks part of the triangle
    private double TrianglePart3(Point3D p0, Vector p_P0) {
        double s3;
        // Creates 2 vectors from the ray's head to two of the triangle's point3Ds
        Vector VC1 = new Vector(p0, this._p3);
        Vector VC2 = new Vector(p0, this._p1);
        // creates new vector from the vectors' crossProduct & normalize it
        Vector N3 = VC1.crossProduct(VC2);
        try{
        N3.normalize();}
        catch(Exception e){}
        s3 = -p_P0.dotProduct(N3);
        return s3;
    }

// FUNCTION
//   TrianglePart2
// PARAMETERS
// Point3D, Vector
// RETURN VALUE
// double
// MEANING
// This functions Checks another part of the triangle
    private double TrianglePart2(Point3D p0, Vector p_P0) {
        double s2;
        // Creates 2 vectors from the ray's head to two of the triangle's point3Ds
        Vector VB1 = new Vector(p0, this._p2);
        Vector VB2 = new Vector(p0, this._p3);
        // creates new vector from the vectors' crossProduct & normalize it
        Vector N2 = VB1.crossProduct(VB2);
        try{
        N2.normalize();}
        catch(Exception e){}
        s2 = -p_P0.dotProduct(N2);
        return s2;
    }

// FUNCTION
//   TrianglePart1
// PARAMETERS
// Point3D, Vector
// RETURN VALUE
// double
// MEANING
// This functions Checks another part of the triangle
    private double TrianglePart1(Point3D p0, Vector p_P0) {
        double s1;// Checks part of the triangle (1 part from 3)
        // Creates 2 vectors from the ray's head to two of the triangle's point3Ds
        Vector VA1 = new Vector(p0, this._p1);
        Vector VA2 = new Vector(p0, this._p2);
        // creates new vector from the vectors' crossProduct & normalize it
        Vector N1 = VA1.crossProduct(VA2);
        try{
        N1.normalize();}
        catch(Exception e){}
        s1 = -p_P0.dotProduct(N1);
        return s1;
    }

}