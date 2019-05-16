package geometries;
//abstract class for all geometries
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.List;

public abstract class Geometry {
    private Material _material = new Material();//reducing factors
    private double _nShininess = 1; //shineness of geometry, much is more
    private Color _emmission = new Color(0, 0, 0); //power of rays that geometry returns from it's surface

    public abstract List<Point3D> FindIntersections (Ray ray);
    public abstract Vector getNormal(Point3D point);

    public double getShininess() { return _nShininess;}
    public Material getMaterial() { return _material;}
    public Color getEmmission() { return  _emmission;}
    public void setShininess(double n) { _nShininess = n;}
    public void setMaterial(Material _material) { this._material = _material;}
    public void setEmmission(Color emmission) { _emmission = emmission;}

    public void setKs(double ks) { _material.setKs(ks); }
    public void setKd(double kd) { _material.setKd(kd); }
    public void setKr(double kr) { _material.setKr(kr); }
    public void setKt(double Kt) { _material.setKt(Kt); }
}
