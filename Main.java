import java.io.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        EquilibriumData testering = new EquilibriumData();
        System.out.println("Testering");

//        double[][] testData = new double[2][7];
//        testData[0] = new double[]{0, 10, 30, 50, 70, 90, 100};
//        testData[1] = new double[]{30, 130, 150, 150, 170, 220, 320};
//        CubicSplineInterpolator spline = new CubicSplineInterpolator(testData);
//        for (int i = 0; i < 4; ++i) {
//            System.out.println(spline.coefficientMatrix[i][0] + "    ");
//        }

        CubicSplineInterpolator spline = new CubicSplineInterpolator(testering.data[0]);
        double interpolatedValue = spline.interpolateY(0.128);
        System.out.println(interpolatedValue);
        String[] testing = new String[1200];
        double x = 0;
        for (int i = 0; i < 1000; ++i) {
            testing[i] = Double.toString(spline.interpolateY(x));
            x+= 0.001;
        }

        File file = new File("EquilibriumCurve.csv");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter writer = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(writer);
        for (int i = 0; i < 1000; ++i) {
            bw.append(testing[i]).append(",");

        }
        bw.append("\n");
        x = 0;
        for (int i = 0; i < 1000; ++i) {
            bw.append(Double.toString(x)).append(",");
            x+= 0.001;
        }
        bw.close();


    }
}













/* TODO

Compute the concentration profile across all trays of the distillation column for each case

Given:

Fully specified feed (flow rate, composition)
Number of trays
Feed tray position
Reflux rate
Composition required in the bottoms / distillate stream
** Going to assume a complete condenser?? So the product is going to be
the same composition as the top reflux ? **

















 */

