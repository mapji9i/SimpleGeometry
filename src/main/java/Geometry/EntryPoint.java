package Geometry;

import Entities.Edge;
import Entities.Point;
import Entities.TopologicalEdge;
import Entities.TopologicalPoint;

/**
 * Hello world!
 *
 */
public class EntryPoint
{
    public static final int NUMBER_OF_THREADS=4;
    public static void main( String[] args )
    {
        Point poin1 = new TopologicalPoint(0.0,0.0,0.0);
        Point poin2 = new TopologicalPoint(1.0,0.0,0.0);
        Edge edge = new TopologicalEdge(poin1,poin2);

        System.out.println( edge);
    }
}
