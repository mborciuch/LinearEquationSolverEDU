package solver;

import solver.swapcommand.Command;
import solver.swapcommand.SwapColumnsCommand;
import solver.swapcommand.SwapRowsCommand;

import java.util.ArrayList;
import java.util.List;

public class LinearEquationSolver {
    private Matrix matrix;

    private List<Command> swapColumnsCommands;

    public LinearEquationSolver(Matrix matrix) {
        this.matrix = matrix;
        swapColumnsCommands = new ArrayList<>();
    }

    public String findSolution() {
        manipulateRow();
        prepareUnitMatrix();
        if (!checkIfHasSolution()) {
            return "No solutions";
        }
        if (checkIfHasInfinitySolutions()) {
            return "Infinitely many solutions";
        }
        StringBuilder solution = new StringBuilder();
        for (int i = 0; i < matrix.getVariables(); i++) {
            solution.append(matrix.getRow(i).getValue(matrix.getVariables()));
            solution.append("\n");
        }
        solution.setLength(solution.length() -1);
        return String.valueOf(solution);
    }

    private void manipulateRow() {
        int maxIteration = Math.min(matrix.getEquations(), matrix.getVariables());
        for (int i = 0; i < maxIteration; i++) {
            String result = matrix.setElementToOne(i);
            if (result.equals("Zero value")) {
                int nonZeroElementIndex = matrix.findNonZeroUnderElement(i);
                if (nonZeroElementIndex != -1) {
                    Command swapRowsCommand = new SwapRowsCommand(matrix, i, nonZeroElementIndex);
                    swapRowsCommand.execute();
                } else if (matrix.findNonZeroRighterElement(i,i) == -1) {
                    nonZeroElementIndex = matrix.findNonZeroRighterElement(i,i);
                    Command swapColumnsCommand = new SwapColumnsCommand(matrix,i, nonZeroElementIndex);
                    swapColumnsCommands.add(swapColumnsCommand);
                } else if (matrix.findNonZeroElementInMatrix() != null) {
                    int[] nonZeroElementCoordinates = matrix.findNonZeroElementInMatrix();
                    Command swapRowsCommand = new SwapRowsCommand(matrix, i, nonZeroElementCoordinates[0]);
                    swapRowsCommand.execute();
                    Command swapColumnsCommand = new SwapColumnsCommand(matrix,i, nonZeroElementCoordinates[1]);
                    swapColumnsCommand.execute();
                    swapColumnsCommands.add(swapColumnsCommand);
                } else {
                    return;
                }
            }
        }
    }

    private boolean checkIfHasSolution() {
        for (Row row : matrix.getRows()) {
            double sum = 0;
            for(int i = 0; i < row.getSize() - 1; i++){
                sum += Math.abs(row.getValue(i));
            }
            if (sum == 0 && row.getLastValue() != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIfHasInfinitySolutions() {

        int significantEquations = matrix.getEquations() - matrix.getNumberOfEmptyRows();
        return  significantEquations < matrix.getVariables();

    }

    private void prepareUnitMatrix() {
        int maxIteration = Math.min(matrix.getEquations(), matrix.getVariables());
        for (int i = 0; i < maxIteration; i++) {
            String result = matrix.setElementToOne(i);
            if (!result.equals("")) {
                System.out.println(result);
            }
            int iterations = matrix.getEquations() - i - 1;
            for (int j = i + 1; j < matrix.getEquations(); j++) {
                result = matrix.setValuesOfColumnToZero(i, j);
                if (result.equals("Zero modifier")) {
                    continue;
                }
                System.out.println(result);
            }
        }
        for (int i = maxIteration - 1; i >= 0; i--) {
            for (int j = i; j - 1 >= 0; j--) {
                String result = matrix.setValuesOfColumnToZero(i, j - 1);
                if (result.equals("Zero modifier")) {
                    continue;
                }
                System.out.println(result);
            }
        }
    }

}
