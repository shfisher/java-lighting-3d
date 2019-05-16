package primitives;

public class Coordinate implements Comparable<Coordinate>{

    private double _coordinate;

    // ***************** Constructors ********************** //

    public Coordinate() {
        this._coordinate = 0.0;
    }

    public Coordinate(double coordinate) {
        this._coordinate = coordinate;
    }

    public Coordinate(Coordinate coordinate) {
        this._coordinate = coordinate._coordinate;
    }

    // ***************** Getters/Setters ********************** //

    public double getCoordinate() {
        return _coordinate;
    }

    public void setCoordinate(double coordinate) {
        this._coordinate = coordinate;
    }

    // ***************** Administration  ******************** //
    /**
     * compares two coordinates by their _coordinate values
     * implements Comparable
     * 
     * @param coordinate
     * @return 0 if equals
     */
    @Override
    public int compareTo(Coordinate coordinate) {

        return Double.compare(this._coordinate, coordinate._coordinate);
    }

    // ***************** Operations ******************** //
    /**
     * add two coordinates, adding _coordinate values
     * @param coordinate
     */
    public void add (Coordinate coordinate ){

        this._coordinate += coordinate._coordinate;
    }
    /**
     * subtract two coordinates, subtracting _coordinate values
     * @param coordinate
     */
    public void subtract(Coordinate coordinate) {
        this._coordinate -= coordinate._coordinate;
    }

}
