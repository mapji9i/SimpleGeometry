package Entities.PropertiesUtils;

import Entities.Edge;
import Entities.Point;
import Entities.TopologicalPoint;
import Geometry.EntryPoint;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validations {
    public static boolean checkShapeClosure(List<Edge> edges){

        if(edges==null) return false;

        if(edges.isEmpty()) return false;
        Set<Point> checkSet= new HashSet<>();
        for(Edge edge:edges){

            for (Point point: edge.getPoints()){
                if (!checkSet.contains(point))
                    checkSet.add(point);
                else
                    checkSet.remove(point);
            }
        }
        return checkSet.isEmpty();
    }

    public static boolean checkEdgesLeftOrientation(List<Edge> edges){
        Point middlePoint=getMiddlePoint(edges);
        int numTreads= EntryPoint.NUMBER_OF_THREADS;
        int threadCounter=1;
        int countOfEdgesPerThread= (int) Math.ceil(edges.size()/numTreads);
        boolean result=true;
        Thread[] threads = new checkEdgesThread[numTreads];
        for (int i = 0; i < numTreads; i++) {
            threads[i]=new checkEdgesThread(edges,i*numTreads, (i+1)*numTreads,middlePoint);
            threads[i].start();
        }

        for (int i = 0; i < numTreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            result=result && ((checkEdgesThread)threads[i]).getResult();
        }

        return result;
    }

    //additional inner thread class
    private static class checkEdgesThread extends Thread{
        private List<Edge> edges;
        private int firstIndex, lastIndex;
        boolean result=true;
        Point middlePoint;
        checkEdgesThread(List<Edge> edges, int firstIndex, int lastIndex,Point middlePoint){
            if (lastIndex<edges.size()) lastIndex= edges.size();
            this.firstIndex=firstIndex;
            this.lastIndex=lastIndex;
            this.edges=edges;
            this.middlePoint=middlePoint;
        }

        @Override
        public void run() {
            super.run();
            for(int i=firstIndex;i<lastIndex;i++){
                Edge edge=edges.get(i);
                List<Point> points=edge.getPoints();
                MatrixBuilder matrixBuilder=new MatrixBuilder();
                Double[][] matrix=matrixBuilder.buildThreePointsMatrix(points.get(0),points.get(1),middlePoint);
                DeterminantEvaluator determinantEvaluator=new DeterminantEvaluator();
                Double determinant=determinantEvaluator.getDeterminant(matrix);
                if(determinant>0)
                    result=result && true;
                else
                    result=result && false;
            }
        }
        public boolean getResult(){
            return result;
        }

    }

    public static Point getMiddlePoint(List<Edge> edges){
        if(edges==null) return null;

        if(edges.isEmpty()) return null;
        Double middleCoordX=0.0, middleCoordY=0.0,middleCoordZ=0.0;

        for(Edge edge:edges){
            for(Point point:edge.getPoints()){
            List<Double> coords=point.getCoords();
                middleCoordX+=coords.get(0)/(edges.size()*2);
                middleCoordY+=coords.get(1)/(edges.size()*2);
                middleCoordZ+=coords.get(2)/(edges.size()*2);
            }

        }

        return new TopologicalPoint(middleCoordX,middleCoordY,middleCoordZ);
    }

}
