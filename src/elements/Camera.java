
package elements;

import java.util.Map;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Camera {

    // Variables
    // Eye point of the camera
    private Point3D _P0;
    private Vector _vUp;
    private Vector _vTo;

    // Should be calculated as the cross product if vUp and vTo
    private Vector _vRight;

    // ***************** Constructors ********************** //

// FUNCTION
//   Camera
// PARAMETERS
//   none
// RETURN VALUE
// none
// MEANING
// This functions builds a Camera (it's point is (0,0,0) & the vectors r: (1,0,0), (0,0,-1)
    public Camera(){

        _P0 = new Point3D(0, 0, 10);
        _vUp = new Vector(1.0, 0.0, 0.0);
        _vTo = new Vector(0.0, 0.0, -1.0);

        _vRight = _vUp.crossProduct(_vTo);
    }

// FUNCTION
//   Camera
// PARAMETERS
//   Camera
// RETURN VALUE
// none
// MEANING
// This functions builds a Camera by make the camera's values to it's values
    public Camera (Camera camera){
        _P0     = camera.getP0();
        _vUp    = camera.get_vUp();
        _vTo    = camera.get_vTo();
        _vRight = camera.get_vRight();
    }

// FUNCTION
//   Camera
// PARAMETERS
//   Point3D, Vector, Vector
// RETURN VALUE
// none
// MEANING
// This functions builds a Camera by make the vectors' & the camera's values to it's values
    public Camera (Point3D P0, Vector vUp, Vector vTo){

        _P0 = new Point3D(P0);
        _vUp = new Vector(vUp);
        _vTo = new Vector(vTo);

        _vRight = _vUp.crossProduct(_vTo);
        _vUp = _vTo.crossProduct(_vRight);

        // Vectors' Normalization
        try{
        _vUp.normalize();
        _vTo.normalize();
        _vRight.normalize();}
        catch (Exception e){}

    }

    // ***************** Getters/Setters ********************** //

// FUNCTION
//   get_vUp
// PARAMETERS
//   none
// RETURN VALUE
// Vector
// MEANING
// This functions returns the vector _vUp
    public Vector get_vUp() {
        return new Vector(_vUp);
    }

// FUNCTION
//   get_vTo
// PARAMETERS
//   none
// RETURN VALUE
// Vector
// MEANING
// This functions returns the vector _vTo
    public Vector get_vTo() {
        return new Vector(_vTo);
    }

// FUNCTION
//   get_vRight
// PARAMETERS
//   none
// RETURN VALUE
// Vector
// MEANING
// This functions returns the vector _vRight
    public Vector get_vRight() {
        return new Vector(_vRight);
    }

// FUNCTION
//   get_P0
// PARAMETERS
//   none
// RETURN VALUE
// Point3D
// MEANING
// This functions returns the Point3D _P0
    public Point3D getP0() {
        return new Point3D(_P0);
    }

// FUNCTION
//   set_vUp
// PARAMETERS
//   Vector
// RETURN VALUE
// none
// MEANING
// This functions sets the vector(_vUp)'s value
    public void set_vUp(Vector vUp) {
        this._vUp = new Vector(vUp);
    }

// FUNCTION
//   set_vTo
// PARAMETERS
//   Vector
// RETURN VALUE
// none
// MEANING
// This functions sets the vector(_vTo)'s value
    public void set_vTo(Vector vTo) {
        this._vTo = new Vector(vTo);
    }

// FUNCTION
//   setP0
// PARAMETERS
//   Point3D
// RETURN VALUE
// none
// MEANING
// This functions sets the Point3D(_P0)'s value
    public void setP0(Point3D P0) {
        this._P0  = new Point3D(P0);
    }


    // ***************** Administration ********************** //

// FUNCTION
//   toString
// PARAMETERS
//   none
// RETURN VALUE
// String
// MEANING
// This functions prints the camera's values
    @Override
    public String toString(){
        return "Vto: "   + _vTo + "\n" + "Vup: "   + _vUp + "\n" + "Vright:" + _vRight + ".";
    }

    // ***************** Operations ******************** //

// FUNCTION
//   constructRayThroughPixel
// PARAMETERS
//  2 int, 5 double
// RETURN VALUE
// Ray
// MEANING
// This functions creates a ray from the camera's center through the image's pixel
    public Ray constructRayThroughPixel (int Nx, int Ny,
                                         double x, double y, double screenDist, double screenWidth, double screenHeight){

        Vector vTo = new Vector(_vTo);
        Vector vRight = new Vector(_vRight);
        Vector vUp = new Vector(_vUp);

        Point3D Pc = imageCenter(screenDist, vTo);

        // Calculate x-y ratios pixels
        double Rx = screenWidth  / Nx;
        double Ry = screenHeight / Ny;

        Point3D P = IntersectionPoint_Calculation(Nx, Ny, x, y, vRight, vUp, Pc, Rx, Ry);

        // construct a ray between the intersection point & P0
        Vector ray = new Vector(_P0, P);
        try{
        ray.normalize();}
        catch (Exception e){}
        return new Ray(P, ray);
    }

// FUNCTION
//   imageCenter
// PARAMETERS
// double, vector
// RETURN VALUE
// Point3D
// MEANING
// This functions Calculates & creates new Point3D (the center of the image)
    private Point3D imageCenter(double screenDist, Vector vTo) {
        vTo.scale(screenDist);
        Point3D Pc = new Point3D(_P0);
        Pc.add(vTo);
        return Pc;
    }

// FUNCTION
//   IntersectionPoint_Calculation
// PARAMETERS
//  2 int, 4 double 3 vector
// RETURN VALUE
// Point3D
// MEANING
// This functions Calculates the intersection point
    private Point3D IntersectionPoint_Calculation(int Nx, int Ny,
                                                  double x, double y, Vector vRight, Vector vUp, Point3D pc, double rx, double ry) {

        vRight.scale(((x - (Nx/2.0)) * rx + 0.5 * rx));
        vUp.scale(((y - (Ny/2.0)) * ry + 0.5 * ry));
        vRight.subtract(vUp);
        pc.add(vRight);
        return new Point3D(pc);
    }

}