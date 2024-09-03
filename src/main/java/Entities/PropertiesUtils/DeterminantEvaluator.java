package Entities.PropertiesUtils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DeterminantEvaluator {
    public double getDeterminant(Double[][] matrix){
        int sizeOfMatrix = matrix.length;
        double result=0;
        if (sizeOfMatrix==1)
            return matrix[0][0];

        Double[][] tmpMtx=new Double[sizeOfMatrix-1][sizeOfMatrix-1];
        int sign=1;
        for(int i=0; i<sizeOfMatrix;i++){
            getCofactor(matrix,tmpMtx,0,i,sizeOfMatrix);
            result+=sign*matrix[0][i]*getDeterminant(tmpMtx);
            sign*=-1;
        }
        return result;
    }

    private void getCofactor(Double[][] matrix, Double[][] tmpMtx, int p,int q, int n) {
        int i=0, j=0;
        for (int row=0; row<n;row++){
            for (int col=0; col<n;col++){
                if(row!=p && col!=q){
                    tmpMtx[i][j++]=matrix[row][col];
                    if(j==n-1){
                        j=0;
                        i++;
                    }
                }
            }
        }
    }
}
