
public class CubicRegression {

    private float b0, b1, b2, b3;
    private float correlationCoefficient;
    private float determinationCoefficient;
    private float[] x;
    private float[] y;

    public CubicRegression(float[] x, float[] y) {
        this.x = x;
        this.y = y;
        calculateParameters();
        calculateCoefficients();
    }

    private void calculateParameters() {
        int n = x.length;
        float sumX = 0, sumX2 = 0, sumX3 = 0, sumX4 = 0, sumX5 = 0, sumX6 = 0;
        float sumY = 0, sumXY = 0, sumX2Y = 0, sumX3Y = 0;

        for (int i = 0; i < n; i++) {
            float xi = x[i];
            float yi = y[i];
            float xi2 = xi * xi;
            float xi3 = xi2 * xi;

            sumX += xi;
            sumX2 += xi2;
            sumX3 += xi3;
            sumX4 += xi2 * xi2;
            sumX5 += xi2 * xi3;
            sumX6 += xi3 * xi3;
            sumY += yi;
            sumXY += xi * yi;
            sumX2Y += xi2 * yi;
            sumX3Y += xi3 * yi;
        }

        float[][] matrix = {
            {n, sumX, sumX2, sumX3},
            {sumX, sumX2, sumX3, sumX4},
            {sumX2, sumX3, sumX4, sumX5},
            {sumX3, sumX4, sumX5, sumX6}
        };
        float[] vector = {sumY, sumXY, sumX2Y, sumX3Y};

        // Resolver sistema usando eliminación gaussiana
        solveSystem(matrix, vector);
    }

    private void solveSystem(float[][] A, float[] b) {
        int n = 4;
        // Eliminación hacia adelante
        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                float factor = A[i][k] / A[k][k];
                b[i] -= factor * b[k];
                for (int j = k; j < n; j++) {
                    A[i][j] -= factor * A[k][j];
                }
            }
        }

        // Sustitución hacia atrás
        float[] x = new float[n];
        for (int i = n - 1; i >= 0; i--) {
            float sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }

        b0 = x[0];
        b1 = x[1];
        b2 = x[2];
        b3 = x[3];
    }

    private void calculateCoefficients() {
        float sumResidualSquared = 0;
        float sumTotalSquared = 0;
        float yMean = 0;

        for (float yi : y) {
            yMean += yi;
        }
        yMean /= y.length;

        for (int i = 0; i < y.length; i++) {
            float predicted = predict(x[i]);
            sumResidualSquared += Math.pow(y[i] - predicted, 2);
            sumTotalSquared += Math.pow(y[i] - yMean, 2);
        }

        determinationCoefficient = 1 - (sumResidualSquared / sumTotalSquared);
        correlationCoefficient = (float) Math.sqrt(determinationCoefficient);
    }

    public float predict(float x) {
        return b0 + (b1 * x) + (b2 * x * x) + (b3 * x * x * x);
    }

    public void printEquation() {
        System.out.printf("y = %.4f + %.4fx + %.4fx² + %.4fx³%n", b0, b1, b2, b3);
    }

    public float getCorrelationCoefficient() {
        return correlationCoefficient;
    }

    public float getDeterminationCoefficient() {
        return determinationCoefficient;
    }
}
