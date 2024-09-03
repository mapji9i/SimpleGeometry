package Entities.PropertiesUtils;

import Entities.Point;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class MatrixBuilder {
    @Getter
    private Double[][] value=null;
    public Double [][] buildThreePointsMatrix(Point point1, Point point2, Point point3){
        List<Double> point1Coords=point1.getCoords();
        List<Double> point2Coords=point2.getCoords();
        List<Double> point3Coords=point3.getCoords();
        Double[][] result = new Double[3][3];
        result[0]=point1Coords.toArray(new Double[3]);
        result[1]=point2Coords.toArray(new Double[3]);
        result[2]=point3Coords.toArray(new Double[3]);
        value=result;
        return result;
    }
}
