package solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix {
    private List<Row> rows;
    private int variables;
    private int equations;

    public Matrix(List<Row> rows, int variables, int equations) {
        this.rows = rows;
        this.variables = variables;
        this.equations = equations;
    }


    public String setElementToOne(int index) {
        Row rowToTransform = rows.get(index);
        double valueToTransform = rowToTransform.getValue(index);
        if (valueToTransform == 1) {
            return "";
        }
        if (valueToTransform == 0) {
            return "Zero value";
        }
        double modifier = getModifierForTransformingToOne(valueToTransform);
        rowToTransform.multiplyRow(modifier);
        return String.format("%f * %s -> %s", modifier, rowToTransform.getName(), rowToTransform.getName());
    }

    public String setValuesOfColumnToZero(int upperRowIndex, int lowerRowIndex) {
        Row upperRow = rows.get(upperRowIndex);
        Row lowerRow = rows.get(lowerRowIndex);

        double modifier = getModifierForTransformingOtherRow(lowerRow.getValue(upperRowIndex));
        if (modifier == 0.0 || modifier == -0.0) {
            return "Zero modifier";
        }
        Row tempRow = new Row(upperRow);
        tempRow.multiplyRow(modifier);

        lowerRow.addRowToRow(tempRow, variables);
        return String.format("%f * %s + %s -> %s", modifier, upperRow.getName(), lowerRow.getName(), lowerRow.getName());
    }

    public String swapRows(int upperRowIndex, int lowerRowIndex) {
        Row upperRow = rows.get(upperRowIndex);
        Row lowerRow = rows.get(lowerRowIndex);
        Row tempRow = new Row(upperRow);

        rows.set(upperRowIndex, lowerRow);
        rows.set(lowerRowIndex, tempRow);

        return String.format("%s <-> %s", upperRow.getName(), lowerRow.getName());
    }
    public String swapColumns(int firstColumnIndex, int secondColumnIndex){
        double tempValue;
        for (Row row : rows) {
            tempValue = row.getValue(firstColumnIndex);
            row.setValue(firstColumnIndex, row.getValue(secondColumnIndex));
            row.setValue(secondColumnIndex, tempValue);
        }
        return String.format("C%s <-> C%s", firstColumnIndex + 1, secondColumnIndex + 1);
    }

    public int findNonZeroUnderElement(int index) {
        List<Row> sliceRow = new ArrayList(rows);
        sliceRow = sliceRow.subList(index, rows.size());
        for (Row row : sliceRow) {
            if ((row.getValue(index) != 0)) {
                return rows.indexOf(row);
            }
        }
        return -1;
    }
    public int findNonZeroRighterElement(int indexRow, int columnIndex){
        double[] values = rows.get(indexRow).getValues();
        double[] valuesToIterate = Arrays.copyOfRange(values,columnIndex ,values.length);
        for (Double value : values) {
            if (value != 0) {
                Arrays.asList(values).indexOf(value);
            }
        }
        return -1;
    }

    public int[] findNonZeroElementInMatrix(){
        double[] values;
        for(Row row : rows){
            values = row.getValues();
            for( double value : values) {
                if ((value == 0)) {
                    return new int[]{rows.indexOf(row), Arrays.asList(values).indexOf(value)};
                }
            }
        }
        return null;
    }

    private double getModifierForTransformingToOne(double value) {
        return 1 / value;
    }

    private double getModifierForTransformingOtherRow(double value) {
        return -value;
    }

    public List<Row> getRows() {
        return rows;
    }

    public Row getRow(int index) {
        return rows.get(index);
    }

    public int getEquations() {
        return equations;
    }

    public int getVariables() {
        return variables;
    }

    public int getNumberOfEmptyRows() {
        int emptyRows = rows.size();
        for (Row row : rows) {
            for (double value : row.getValues()) {
                if (value != 0.0 && value != -0.0) {
                    emptyRows--;
                    break;
                }
            }
        }
        return emptyRows;
    }
}
