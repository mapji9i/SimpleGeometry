package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class TopologicalEdge implements Edge{
    private Point firstPoint;
    private Point lastPoint;
    @Override
    public List<Point> getPoints() {
        return List.of(firstPoint, lastPoint);
    }
}
