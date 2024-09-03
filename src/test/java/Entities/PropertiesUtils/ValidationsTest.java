package Entities.PropertiesUtils;

import Entities.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidationsTest {
    static List<Edge> validEdges;
    static List<Edge> unclosedEdges;

    @BeforeAll
    static void init(){
        Point point1 = new TopologicalPoint(0.0,0.0,0.0);
        Point point2 = new TopologicalPoint(1.0,0.0,0.0);
        Point point3 = new TopologicalPoint(1.0,1.0,0.0);
        Point point4 = new TopologicalPoint(0.0,1.0,0.0);
        Edge edge1 = new TopologicalEdge(point1,point2);
        Edge edge2 = new TopologicalEdge(point2,point3);
        Edge edge3 = new TopologicalEdge(point3,point4);
        Edge edge4 = new TopologicalEdge(point4,point1);
        validEdges=List.of(edge1,edge2,edge3,edge4);
        unclosedEdges=List.of(edge1,edge2,edge3);
    }

    @Test
    void checkShapeClosureTestTrue() {
        assertTrue(Validations.checkShapeClosure(validEdges));

    }

    @Test
    void checkShapeClosureTestFalse() {
        assertFalse(Validations.checkShapeClosure(unclosedEdges));
    }

    @Test
    void getMiddlePointTest() {
        Point actualPoint=Validations.getMiddlePoint(validEdges);
        Point expectedPoint= new TopologicalPoint(0.5,0.5,0.0);
        assertEquals(expectedPoint,actualPoint);
    }
}