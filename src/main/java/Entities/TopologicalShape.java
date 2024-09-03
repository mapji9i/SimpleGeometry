package Entities;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
@EqualsAndHashCode
@ToString
public class TopologicalShape implements Shape{
    private List<Edge> edges;
    @Override
    public List<Edge> getEdges() {
        return edges;
    }

    public TopologicalShape(List<Edge> edges) {
        this.edges = edges;
    }
}
