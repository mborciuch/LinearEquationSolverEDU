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

        int n = Integer.parseInt(rows.get(0));
        rows.remove(0);

        for (int i = 0; i < n; i++) {
            String[] stringArray = rows.get(i).split(" ");
            Row row = new Row(i, n, stringArray);
            rowsArray.add(row);
        }

        Matrix matrix = new Matrix(rowsArray, n);
        LinearEquationSolver linearEquationSolver = new LinearEquationSolver(matrix);
        System.out.println("Row Manipulations:");
        linearEquationSolver.manipulateRow();

        try (PrintWriter printWriter = new PrintWriter(new File(outFile))) {
            {
                for(Row row :matrix.getRows()){
                    printWriter.println(row.getValue(matrix.getEquations()));
                }
            }

        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}



