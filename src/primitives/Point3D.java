package primitives;
/**
 * point on three-dimensional space
 * */
public class Point3D extends Point2D {

    private Coordinate _z;

    // ***************** Constructors ********************** //

    public Point3D(){
        _z = new Coordinate();
    }

    public Point3D(Coordinate x, Coordinate y, Coordinate z){
        super(x, y);
        _z = new Coordinate(z);
    }

    public Point3D(double x, double y, double z){
        super(new Coordinate(x), new Coordinate(y));
        _z = new Coordinate(z);
    }

    public Point3D(Point3D point3D){
        super(point3D._x, point3D._y);
        _z = point3D.getZ();
    }

    // ***************** Getters/Setters ********************** //

    public Coordinate getZ()        {
        return new Coordinate(_z);
    }

    public void setZ(Coordinate _z) {
        this._z = new Coordinate(_z);
    }

    // ***************** Administration  ******************** //
    /**
     * compares two 3d points by their coordinates
     * @param point3D
     * @return 0 if equals
     */
    public int compareTo(Point3D point3D) {
        if (((Point2D)this).compareTo((Point2D)point3D) == 0)
            if (this._z.compareTo(point3D._z) == 0)
                return 0;
        return 1;
    }
    /**
     * print coordinates to string with 0.01 precision
     * @return
     */
    @Override // Implements print ()
    public String toString(){
        return String.format("(%.2f, %.2f, %.2f)", _x.getCoordinate(), _y.getCoordinate(), _z.getCoordinate());
    }

    // ***************** Operations ********************

    // Add A Vector To A Point3D
    public void add(Vector vector) {

        this._x.add(vector.getHead().getX());
        this._y.add(vector.getHead().getY());
        this._z.add(vector.getHead().getZ());

    }

    // Subtract A Vector From Point3D
    public void subtract(Vector vector) {

        this._x.subtract(vector.getHead().getX());
        this._y.subtract(vector.getHead().getY());
        this._z.subtract(vector.getHead().getZ());

    }

    // Calculate The Distance Between 2 Point3D by pythagoras
    public double distance(Point3D point){
        return Math.sqrt(Math.pow(this._x.getCoordinate() - point.getX().getCoordinate(), 2) +
                Math.pow(this._y.getCoordinate() - point.getY().getCoordinate(), 2) +
                Math.pow(this._z.getCoordinate() - point.getZ().getCoordinate(), 2));
    }

}
