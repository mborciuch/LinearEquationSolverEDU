package solver;

import java.util.Arrays;

public class Row {

    private String name;
    private int size;
    private double[] values;

    public Row(int index, int size, String[] row) {
        this.name = "R" + (index + 1);
        this.size = size;
        values = Arrays.stream(row).mapToDouble(Double::parseDouble).toArray();
    }

    public Row(Row row) {
        this.name = row.getName();
        this.values = row.getValues();
    }

    public void multiplyRow(double modifier) {
        values = Arrays.stream(values).map(d -> d * modifier).toArray();
    }

    public void addRowToRow(Row row, int size) {
        for (int i = 0; i <= size; i++) {
            values[i] += row.getValue(i);
        }
    }

    public double[] getValues() {
        return values;
    }

    public double getValue(int index) {
        return values[index];
    }

    public String getName() {
        return name;
    }

    public void setValue(int index, double value) {
        values[index] = value;
    }
}
