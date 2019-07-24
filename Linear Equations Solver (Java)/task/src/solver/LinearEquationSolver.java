package solver;

import solver.swapcommand.Command;
import solver.swapcommand.SwapColumnsCommand;
import solver.swapcommand.SwapRowsAndColumnsCommand;
import solver.swapcommand.SwapRowsCommand;

import java.util.ArrayList;
import java.util.List;

public class LinearEquationSolver {
    private Matrix matrix;

    private List<Command> commands;

    public LinearEquationSolver(Matrix matrix) {
        this.matrix = matrix;
        commands = new ArrayList<>();
    }

    public void findSolution() {
        manipulateRow();
        evaluateSolutions();
        evaluateStatus();
    }

    private void manipulateRow() {
        for (int i = 0; i < matrix.getEquations(); i++) {
            String result = matrix.setElementToOne(i);
            if (result.equals("Zero Value")) {
                int nonZeroElementIndex = matrix.findNonZeroUnderElement(i);
                if (nonZeroElementIndex != -1) {
                    Command swapRowsCommand = new SwapRowsCommand(matrix, i, nonZeroElementIndex);
                    swapRowsCommand.execute();
                    commands.add(swapRowsCommand);
                } else if (matrix.findNonZeroRighterElement(i,i) == -1) {
                    nonZeroElementIndex = matrix.findNonZeroRighterElement(i,i);
                    Command swapColumnsCommand = new SwapColumnsCommand(matrix,i, nonZeroElementIndex);
                    commands.add(swapColumnsCommand);
                } else if (matrix.findNonZeroElementInMatrix() != null) {
                    int[] nonZeroElementCoordinates = matrix.findNonZeroElementInMatrix();
                    Command swapRowsAndColumnCommand = new SwapRowsAndColumnsCommand(matrix, i, nonZeroElementCoordinates[0], i, nonZeroElementCoordinates[1]);
                    commands.add(swapRowsAndColumnCommand);
                } else {
                    return;
                }
            }
        }
    }

    private void evaluateSolutions() {

        for (int i = 0; i < matrix.getEquations(); i++) {
            String result = matrix.setElementToOne(i);
            if (result != null) {
                System.out.println(result);
            }
            for (int j = i; j < matrix.getEquations() - 1; j++) {
                System.out.println(matrix.setValuesOfColumnToZero(i, j + 1));
            }
        }
        for (int i = matrix.getEquations() - 1; i > 0; i--) {
            for (int j = i; j > 0; j--) {
                System.out.println(matrix.setValuesOfColumnToZero(i, j - 1));
            }
        }
    }

    private void evaluateStatus() {

    }

    private void checkIfHasSolution() {

    }
}
