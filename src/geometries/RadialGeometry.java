package geometries;
//father class for geometries with radius (sphere, cylinder)
public abstract class RadialGeometry extends Geometry {
    protected double _radius;

    // ***************** Constructors ********************** //

// FUNCTION
//   RadialGeometry
// PARAMETERS
//   none
// RETURN VALUE
// none
// MEANING
// This functions builds a RadialGeometry (Equals The Radius to 0)

    public RadialGeometry() {
        _radius = 0;
    }

// FUNCTION
//   RadialGeometry
// PARAMETERS
//   double - represents radius
// RETURN VALUE
// none
// MEANING
// This functions builds a RadialGeometry. It gets a radius and and make it's value to the itself radius.

    public RadialGeometry(double radius) {
        _radius = radius;
    }

    // ***************** Getters/Setters ********************** //

// FUNCTION
//   getRadius
// PARAMETERS
//   none
// RETURN VALUE
// double - The radius
// MEANING
// This functions returns the radius of the RadialGeometry

    public double getRadius(){
        return _radius;
    }

// FUNCTION
//   setRadius
// PARAMETERS
//   double - the radius
// RETURN VALUE
// none
// MEANING
// This functions sets the radius of the RadialGeometry

    public void setRadius(double radius){
        _radius = radius;
    }
}
