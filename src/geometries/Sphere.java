package geometries;

import java.util.ArrayList;
import java.util.List;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Sphere extends RadialGeometry{

    private Point3D _center;

    // ***************** Constructors ********************** //

// FUNCTION
//   Sphere
// PARAMETERS
//   none
// RETURN VALUE
// none
// MEANING
// This functions builds a Sphere (Equals The vector of the center ant the radius to 0)
    public Sphere(){
        super(0.0);
        _center = new Point3D();
    }

// FUNCTION
//   Sphere
// PARAMETERS
//  Sphere
// RETURN VALUE
// none
// MEANING
// This functions builds a Sphere. It gets a Sphere and and make it's value to the itself Sphere.
    public Sphere (Sphere sphere){
        super(sphere._radius);
        _center = sphere.getCenter();
    }

// FUNCTION
//   Sphere
// PARAMETERS
//  double radius and Point3D center
// RETURN VALUE
// none
// MEANING
// This functions builds a Sphere. It gets a radius and Point3D center and make it's value to the itself Sphere.
    public Sphere(double radius, Point3D center){
        super(radius);
        _center = new Point3D(center);
    }

    // ***************** Getters/Setters ********************** //

// FUNCTION
//   getCenter
// PARAMETERS
//   none
// RETURN VALUE
// _center
// MEANING
// This functions returns the center of the sphere
    public Point3D getCenter(){
        return new Point3D(_center);
    }

// FUNCTION
    //   setCenter
// PARAMETERS
//  Point3D Center
// RETURN VALUE
// none
// MEANING
// This functions sets the Center of the sphere
    public void setCenter(Point3D center) { this._center = new Point3D(center); }

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

        List<Point3D> intersectionPoints= new ArrayList<>(2);

        Vector L = new Vector(ray.getPOO(), this.getCenter()); //vector from poo to the center
        double tm = L.dotProduct(ray.getDirection()); // vector from poo to the point on the sphere
        double d = Math.sqrt(Math.pow(L.length(), 2) - Math.pow(tm, 2)); // find the Pithagoras between L and tm

        // if it is true the ray is in the sphere
        if (d > this.getRadius())
            return intersectionPoints;

        double th, t1, t2;

        th = Math.sqrt(Math.pow(this.getRadius(), 2) - Math.pow(d, 2));
        t1 = tm - th; //the vector from poo to the first point
        t2 = tm + th; //the vector from the first point to the last point

        intersectionPoint1(ray, intersectionPoints, t1);
        intersectionPoint2(ray, intersectionPoints, t2);

        return intersectionPoints;

    }

// FUNCTION
//  intersectionPoint1
// PARAMETERS
//  Ray, List<Point3D>, double
// RETURN VALUE
// none
// MEANING
// This functions checks if there is an intersectionPoint & if there is - adds it to the List
    private void intersectionPoint1(Ray ray, List<Point3D> intersectionPoints, double t1) {
        if (t1 >= 0){
            Vector V = ray.getDirection();
            V.scale(t1);
            Point3D P1 = ray.getPOO(); // Ray's Head/Start Point3D
            P1.add(V); // adds to the head the vector V
            intersectionPoints.add(P1);
        }
    }

// FUNCTION
//  intersectionPoint2
// PARAMETERS
//  Ray, List<Point3D>, double
// RETURN VALUE
// none
// MEANING
// This functions checks if there is an intersectionPoint & if there is - adds it to the List
    private void intersectionPoint2(Ray ray, List<Point3D> intersectionPoints, double t2) {
        if (t2 >= 0){
            Vector V = ray.getDirection();
            V.scale(t2);
            Point3D P2 = ray.getPOO(); // Ray's Head/Start Point3D
            P2.add(V); // adds to the head the vector V
            intersectionPoints.add(P2);
        }
    }

// FUNCTION
//  getNormal
// PARAMETERS
//  Point3D point
// RETURN VALUE
// none
// MEANING
// This functions get the Normal from 2 points one is the center and the another on the sphere
    @Override
    public Vector getNormal(Point3D point) {

        Vector N = new Vector (_center, point);
        try{
        N.normalize();}
        catch(Exception e){}
        return N;

    }
}
