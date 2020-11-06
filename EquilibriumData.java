import java.io.*;


public class EquilibriumData {

    public double[][][] data; // index 1 is Venture, index 2 is X(0) or Y(1) data, index 3 is values

    public EquilibriumData() {
       String filename = "EquilibriumDataFile";
       this.data = new double[3][2][12]; // because we know the size of the data list otherwise would need to use a list
       File file = new File(filename);
       try {
           BufferedReader reader = new BufferedReader(new FileReader(filename));
            String[] elements;
            String line;
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 2; ++j) {
                    line = reader.readLine();
                    elements = line.split("\t");
                    for (int k = 0; k < 12; ++k) {
                        data[i][j][k] = Double.parseDouble(elements[k]);
                        }
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
       }
    }

    public EquilibriumData(EquilibriumData source) {
        int size1 = source.data.length;
        int size2 = source.data[0].length;
        int size3 = source.data[0][0].length;
        this.data = new double[size1][size2][size3];
        for (int i = 0; i < size1; ++i) {
            for (int j = 0; j < size2; ++i) {
                for (int k = 0; k < size3; ++i) {
                    this.data[i][j][k] = source.data[i][j][k];
                }
            }
        }
    }

    public double[][][] getData() {
        int size1 = this.data.length;
        int size2 = this.data[0].length;
        int size3 = this.data[0][0].length;
        double[][][] temp = new double[size1][size2][size3];
        for (int i = 0; i < size1; ++i) {
            for (int j = 0; j < size2; ++i) {
                for (int k = 0; k < size3; ++i) {
                    temp[i][j][k] = this.data[i][j][k];
                }
            }
        }
        return temp;
    }
    public boolean setData(double[][][] source) {
        if (source == null) return false;
        int size1 = source.length;
        int size2 = source[0].length;
        int size3 = source[0][0].length;
        this.data = new double[size1][size2][size3];
        for (int i = 0; i < size1; ++i) {
            for (int j = 0; j < size2; ++i) {
                for (int k = 0; k < size3; ++i) {
                    this.data[i][j][k] = source[i][j][k];
                }
            }
        }
        return true;
    }
}
