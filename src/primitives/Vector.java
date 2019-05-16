package primitives;
/**
 * vector in 3D space
 * origin in (0,0,0)
 * */
public class Vector implements Comparable<Vector>{

    private Point3D _head;

    // ***************** Constructors ********************** //

    public Vector() {
        this._head = new Point3D();
    }

    public Vector(Point3D head) {
        this._head = new Point3D(head);
    }

    public Vector(Vector vector) {
        this._head = vector.getHead();
    }

    public Vector(double xHead,  double yHead, double zHead){
        _head = new Point3D(new Coordinate(xHead), new Coordinate(yHead), new Coordinate(zHead));
    }

    public Vector(Point3D p1, Point3D p2){
        this(p2.getX().getCoordinate() - p1.getX().getCoordinate(),
                p2.getY().getCoordinate() - p1.getY().getCoordinate(),
                p2.getZ().getCoordinate() - p1.getZ().getCoordinate());
    }

    // ***************** Getters/Setters ********************** //

    public Point3D getHead() {
        return new Point3D(_head);
    }

    public void setHead(Point3D head) {
        this._head = new Point3D(head);
    }

    // ***************** Administration  ******************** //
    /**
     * compare two vectors by their head's coordinates
     * using comparing of Point3D class
     * @param vector
     * @return 0 if equals
     */
    @Override     // Implements CompareTo (Comparable Function)
    public int compareTo(Vector vector) {
        return this._head.compareTo(vector._head);
    }
    /**
     * prints coordinates of vector's head, 
     * uses toString of Point3D
     * */
    public String toString(){
        return _head.toString();
    }

    // ***************** Operations ******************** //

    /**
     * add two vectors by their head coordinates
     * using add of Point3D
     * @param vector
     */
    public void add(Vector vector ){
        this._head.add(vector);
    }

    /**
     * subtract two vectors by their head coordinates
     * using add of Point3D
     * @param vector
     */
    public void subtract(Vector vector ){
        this._head.subtract(vector);
    }

    /**
     * scales a vector
     * multiplicates it head coordinates by scaling factor
     * @param scalingFactor
     */
    public void scale(double scalingFacor){

        this._head.setX(new Coordinate(scalingFacor * _head.getX().getCoordinate()));
        this._head.setY(new Coordinate(scalingFacor * _head.getY().getCoordinate()));
        this._head.setZ(new Coordinate(scalingFacor * _head.getZ().getCoordinate()));
    }

     /**
     * calculates cross product of two vectors
     * for u = u1i + u2j + u3k, v = v1i + v2k + v3j
     * @return determinant[(i j k), (u1 u2 u3), (v1 v2 v3)]
     * @param vector
     * 
     */
    public Vector crossProduct (Vector vector){

        double x1 = this.getHead().getX().getCoordinate();
        double y1 = this.getHead().getY().getCoordinate();
        double z1 = this.getHead().getZ().getCoordinate();

        double x2 = vector.getHead().getX().getCoordinate();
        double y2 = vector.getHead().getY().getCoordinate();
        double z2 = vector.getHead().getZ().getCoordinate();

        Vector V = new Vector(y1 * z2 - z1 * y2, z1 * x2 - x1 * z2, x1 * y2 - y1 * x2);
        return V;

    }

    /**
     * count length of the vector
     * reuse distance of Point3D, distance from head to origin
     * 
     */
    public double length() {

        double x = this.getHead().getX().getCoordinate();
        double y = this.getHead().getY().getCoordinate();
        double z = this.getHead().getZ().getCoordinate();

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));

    }
    /**
     * normalize vector 
     * divide it by it's length
     * @throws Exception if length = 0
     */
    public void normalize() { // Throws exception if length = 0

        double x = this.getHead().getX().getCoordinate();
        double y = this.getHead().getY().getCoordinate();
        double z = this.getHead().getZ().getCoordinate();

        double length = this.length();

        if (length == 0)
            throw new ArithmeticException();

        // Otherwise
        this.setHead(new Point3D(x/length, y/length,z/length));
    }

    /**
     * count dot product of two vectors
     * for u = (u1, u2, u3), v = (v1, v2, v3)
     * return (u1+v1, u2+v2, u3+v3)
     * @param vector
     * 
     */
    public double dotProduct(Vector vector) {

        double x1 = this.getHead().getX().getCoordinate();
        double y1 = this.getHead().getY().getCoordinate();
        double z1 = this.getHead().getZ().getCoordinate();

        double x2 = vector.getHead().getX().getCoordinate();
        double y2 = vector.getHead().getY().getCoordinate();
        double z2 = vector.getHead().getZ().getCoordinate();

        return x1 * x2 + y1 * y2 + z1 * z2;

    }

}
