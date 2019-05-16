package elements;

import java.awt.*;

public abstract class Light {

    protected Color _color;

    // ***************** Constructors **********************
// FUNCTION
//   Light
// PARAMETERS
//   none
// RETURN VALUE
// none
// MEANING
// This functions builds a Light (Equals The color of the parameter)

    public Light() { _color = null; }
// FUNCTION
//   Light
// PARAMETERS
//  color
// RETURN VALUE
// none
// MEANING
// This functions builds a Light. It gets a color and make it's value to the itself light.

    public Light (Color color) { _color = color; }

    // ***************** Getters/Setters **********************
// FUNCTION
//   getIntensity
// PARAMETERS
//   none
// RETURN VALUE
// _color
// MEANING
// This functions returns the strength of the light
    public Color getIntensity() { return _color; }

}
