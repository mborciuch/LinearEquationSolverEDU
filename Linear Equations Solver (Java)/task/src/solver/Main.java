package solver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        String inFile = args[1];
        String outFile = args[3];

        System.out.println("Starting solving the equation");

        List<String> rows = Files.readAllLines(Paths.get(inFile));

        List<Row> rowsArray = new ArrayList<>();

        String[] parameters = rows.get(0).split(" ");
        int variables = Integer.parseInt(parameters[0]);
        int equations = Integer.parseInt(parameters[1]);
        rows.remove(0);

        for (int i = 0; i < equations; i++) {
            String[] stringArray = rows.get(i).split(" ");
            Row row = new Row(i, variables + 1, stringArray);
            rowsArray.add(row);
        }

        Matrix matrix = new Matrix(rowsArray, variables, equations);
        LinearEquationSolver linearEquationSolver = new LinearEquationSolver(matrix);
        System.out.println("Row Manipulations:");
        String solution = linearEquationSolver.findSolution();
        try (PrintWriter printWriter = new PrintWriter(new File(outFile))) {
            {
                printWriter.print(solution);
            }

        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}



