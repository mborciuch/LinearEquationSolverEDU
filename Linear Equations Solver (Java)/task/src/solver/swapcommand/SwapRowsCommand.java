package solver.swapcommand;

import solver.Matrix;

public class SwapRowsCommand implements Command {

   private Matrix matrix;
   private int firstRowIndex;
   private int secondRowIndex;

    public SwapRowsCommand(Matrix matrix, int firstRowIndex, int secondRowIndex) {
        this.matrix = matrix;
        this.firstRowIndex = firstRowIndex;
        this.secondRowIndex = secondRowIndex;
    }

    @Override
    public void execute() {
        matrix.swapRows(firstRowIndex, secondRowIndex);
    }

    @Override
    public void undo() {
        return;
    }
}
