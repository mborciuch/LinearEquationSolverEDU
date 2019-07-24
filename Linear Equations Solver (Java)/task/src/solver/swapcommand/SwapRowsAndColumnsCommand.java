package solver.swapcommand;

import solver.Matrix;

public class SwapRowsAndColumnsCommand implements Command {

    Matrix matrix;
    int upperRowIndex;
    int lowerRowIndex;
    int firstColumnIndex;
    int secondColumnIndex;

    public SwapRowsAndColumnsCommand(Matrix matrix, int upperRowIndex, int lowerRowIndex, int firstColumnIndex, int secondColumnIndex) {
        this.matrix = matrix;
        this.upperRowIndex = upperRowIndex;
        this.lowerRowIndex = lowerRowIndex;
        this.firstColumnIndex = firstColumnIndex;
        this.secondColumnIndex = secondColumnIndex;
    }

    @Override
    public void execute() {
        matrix.swapRows(upperRowIndex, lowerRowIndex);
        matrix.swapColumns(firstColumnIndex, secondColumnIndex);
    }
}
