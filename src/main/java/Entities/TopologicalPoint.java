package Entities;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TopologicalPoint implements Point {
    private Double xCoordinate;
    private Double yCoordinate;
    private Double zCoordinate;

    @Override
    public List<Double> getCoords() {
        return List.of(xCoordinate,yCoordinate,zCoordinate);
    }
}
