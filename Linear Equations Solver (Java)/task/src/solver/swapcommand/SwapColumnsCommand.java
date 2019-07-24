package solver.swapcommand;

import solver.Matrix;

public class SwapColumnsCommand implements Command {

    Matrix matrix;
    int firstColumnIndex;
    int secondColumnIndex;

    public SwapColumnsCommand(Matrix matrix, int firstColumnIndex, int secondColumnIndex) {
        this.matrix = matrix;
        this.firstColumnIndex = firstColumnIndex;
        this.secondColumnIndex = secondColumnIndex;
    }

    @Override
    public void execute() {
        matrix.swapColumns(firstColumnIndex, secondColumnIndex);
    }

}
