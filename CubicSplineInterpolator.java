public class CubicSplineInterpolator {
    public double[][] coefficientMatrix; // 5 x 11 matrix of coefficients of cubic equations (5th spot for x values

    public CubicSplineInterpolator(double[][] dataPoints) {
        this.coefficientMatrix = calculateCubicSpline(dataPoints);
    }
// this actually calculates the coefficients of the interpolating polynomial
    private double[][] calculateCubicSpline(double[][] dp) {
        double[][] coefficientArray = new double[5][11];
        double a, b, c, d;
        double firstDerivativeX1;
        double firstDerivativeX0;
        double secondDerivativeX1;
        double secondDerivativeX0;
        int i = 1;
        // calculate f'(x1)
        firstDerivativeX1 = 2 /
                (((dp[0][i + 1] - dp[0][i]) / (dp[1][i + 1] - dp[1][i])) + ((dp[0][i] - dp[0][i - 1]) / (dp[1][i] - dp[1][i - 1])));
        // calculate f'(x0)
        firstDerivativeX0 = 3 * (dp[1][i] - dp[1][i - 1]) / (2 * (dp[0][i] - dp[0][i - 1])) - (firstDerivativeX1 / 2);

        do {
            // calculate f"(x0)
            secondDerivativeX0 = (-2 * (firstDerivativeX1 + 2 * firstDerivativeX0) / (dp[0][i] - dp[0][i - 1]))
                    + (6 * (dp[1][i] - dp[1][i - 1])) / Math.pow(dp[0][i] - dp[0][i - 1], 2);
            // calculate f"(x1)
            secondDerivativeX1 = (2 * (2 * firstDerivativeX1 + firstDerivativeX0) / (dp[0][i] - dp[0][i - 1]))
                    - (6 * (dp[1][i] - dp[1][i - 1])) / Math.pow(dp[0][i] - dp[0][i - 1], 2);
            // calculate coefficients finally
            d = ((1.0 / 6) * (secondDerivativeX1 - secondDerivativeX0)) / (dp[0][i] - dp[0][i - 1]);

            c = ((1.0 / 2) * (dp[0][i] * secondDerivativeX0 - dp[0][i - 1] * secondDerivativeX1)) / (dp[0][i] - dp[0][i - 1]);

            b = ((dp[1][i] - dp[1][i - 1]) - c * (Math.pow(dp[0][i], 2)
                    - Math.pow(dp[0][i - 1], 2)) - d * (Math.pow(dp[0][i], 3) - Math.pow(dp[0][i - 1], 3)))
                    / (dp[0][i] - dp[0][i - 1]);
            a = dp[1][i - 1] - b * dp[0][i - 1] - c * Math.pow(dp[0][i - 1], 2) - d * Math.pow(dp[0][i - 1], 3);
            coefficientArray[0][i - 1] = a;
            coefficientArray[1][i - 1] = b;
            coefficientArray[2][i - 1] = c;
            coefficientArray[3][i - 1] = d;
            ++i;
            if (i > coefficientArray[0].length - 1) break; // break out at the second last element to avoid calculating fdx1 incorrectly
            // calculate f'(x1)
            firstDerivativeX1 = 2 /
                    (((dp[0][i + 1] - dp[0][i]) / (dp[1][i + 1] - dp[1][i])) + ((dp[0][i] - dp[0][i - 1]) / (dp[1][i] - dp[1][i - 1])));
            // calculate f'(x0)
            firstDerivativeX0 = 2 /
                    (((dp[0][i] - dp[0][i - 1]) / (dp[1][i] - dp[1][i - 1])) + ((dp[0][i - 1] - dp[0][i - 2]) / (dp[1][i - 1] - dp[1][i - 2])));

        } while (true);
        // last iteration also has a different equation for a derivative ? how to make this cleaner
        firstDerivativeX1 = (3 * (dp[1][i] - dp[1][i - 1]) / (2 * (dp[0][i] - dp[0][i - 1]))) - (firstDerivativeX1 / 2);
        firstDerivativeX0 = 2 /
                (((dp[0][i] - dp[0][i - 1]) / (dp[1][i] - dp[1][i - 1])) + ((dp[0][i - 1] - dp[0][i - 2]) / (dp[1][i - 1] - dp[1][i - 2])));
        secondDerivativeX0 = (-2 * (firstDerivativeX1 + 2 * firstDerivativeX0) / (dp[0][i] - dp[0][i - 1]))
                + (6 * (dp[1][i] - dp[1][i - 1])) / Math.pow(dp[0][i] - dp[0][i - 1], 2);
        secondDerivativeX1 = (2 * (2 * firstDerivativeX1 + firstDerivativeX0) / (dp[0][i] - dp[0][i - 1]))
                - (6 * (dp[1][i] - dp[1][i - 1])) / Math.pow(dp[0][i] - dp[0][i - 1], 2);
        d = ((1.0 / 6) * (secondDerivativeX1 - secondDerivativeX0)) / (dp[0][i] - dp[0][i - 1]);
        c = ((1.0 / 2) * (dp[0][i] * secondDerivativeX0 - dp[0][i - 1] * secondDerivativeX1)) / (dp[0][i] - dp[0][i - 1]);
        b = ((dp[1][i] - dp[1][i - 1]) - c * (Math.pow(dp[0][i], 2)
                - Math.pow(dp[0][i - 1], 2)) - d * (Math.pow(dp[0][i], 3) - Math.pow(dp[0][i - 1], 3)))
                / (dp[0][i] - dp[0][i - 1]);
        a = dp[1][i - 1] - b * dp[0][i - 1] - c * Math.pow(dp[0][i - 1], 2) - d * Math.pow(dp[0][i - 1], 3);
        coefficientArray[0][i - 1] = a;
        coefficientArray[1][i - 1] = b;
        coefficientArray[2][i - 1] = c;
        coefficientArray[3][i - 1] = d;

        for (int j = 0; j < coefficientArray[4].length; ++j) { // add the x value ranges to the matrix; we know first point is 0
            coefficientArray[4][j] = dp[0][j + 1];
        }
        return coefficientArray;
        }

// this is how the caller can request an interpolated value from the polynomial
    public double interpolateY(double x) { // when we calculate a new y its because there was an itersection with an operating line??
        assert (x >= 0 && x <= 1);
        int i = 0;
        if (x <= coefficientMatrix[4][0]){ ; }
        else {
            for (i = 1; i < coefficientMatrix[0].length - 1; ++i) {
                if ((x >= coefficientMatrix[4][i - 1]) && (x <= coefficientMatrix[4][i])) break;
            }
        }
        System.out.println("i = " + i);
        double interpolatedY = coefficientMatrix[0][i]
                + coefficientMatrix[1][i] * x
                + coefficientMatrix[2][i] * x * x
                + coefficientMatrix[3][i] * x * x * x;
        return interpolatedY;
    }

    public double interpolateX(double y) {



        return 0;
    }
}
