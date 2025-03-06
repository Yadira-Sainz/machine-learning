
public class QuadraticRegression {

    private float b0;
    private float b1;
    private float b2;
    private float correlationCoefficient;
    private float determinationCoefficient;
    private float[] x;
    private float[] y;

    public QuadraticRegression(float[] x, float[] y) {
        this.x = x;
        this.y = y;
        calculateParameters();
        calculateCoefficients();
    }

    private void calculateParameters() {
        int n = x.length;
        float sumX = 0, sumX2 = 0, sumX3 = 0, sumX4 = 0, sumY = 0, sumXY = 0, sumX2Y = 0;

        for (int i = 0; i < n; i++) {
            float xi = x[i];
            float yi = y[i];
            float xi2 = xi * xi;

            sumX += xi;
            sumX2 += xi2;
            sumX3 += xi2 * xi;
            sumX4 += xi2 * xi2;
            sumY += yi;
            sumXY += xi * yi;
            sumX2Y += xi2 * yi;
        }

        // Resolver sistema de ecuaciones para obtener b0, b1 y b2
        float[][] matrix = {
            {n, sumX, sumX2},
            {sumX, sumX2, sumX3},
            {sumX2, sumX3, sumX4}
        };

        float[] vector = {sumY, sumXY, sumX2Y};

        // Resolver usando el método de Cramer
        float det = determinant3x3(matrix);
        float det1 = determinantWithColumn(matrix, vector, 0);
        float det2 = determinantWithColumn(matrix, vector, 1);
        float det3 = determinantWithColumn(matrix, vector, 2);

        b0 = det1 / det;
        b1 = det2 / det;
        b2 = det3 / det;
    }

    private float determinant3x3(float[][] m) {
        return m[0][0] * (m[1][1] * m[2][2] - m[1][2] * m[2][1])
                - m[0][1] * (m[1][0] * m[2][2] - m[1][2] * m[2][0])
                + m[0][2] * (m[1][0] * m[2][1] - m[1][1] * m[2][0]);
    }

    private float determinantWithColumn(float[][] m, float[] v, int col) {
        float[][] temp = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = j == col ? v[i] : m[i][j];
            }
        }
        return determinant3x3(temp);
    }

    private void calculateCoefficients() {
        float sumResidualSquared = 0;
        float sumTotalSquared = 0;
        float yMean = 0;

        // Calcular la media de y
        for (float yi : y) {
            yMean += yi;
        }
        yMean /= y.length;

        // Calcular R²
        for (int i = 0; i < y.length; i++) {
            float predicted = predict(x[i]);
            sumResidualSquared += Math.pow(y[i] - predicted, 2);
            sumTotalSquared += Math.pow(y[i] - yMean, 2);
        }

        determinationCoefficient = 1 - (sumResidualSquared / sumTotalSquared);
        correlationCoefficient = (float) Math.sqrt(determinationCoefficient);
    }

    public float predict(float x) {
        return b0 + (b1 * x) + (b2 * x * x);
    }

    public void printEquation() {
        System.out.printf("y = %.4f + %.4fx + %.4fx²%n", b0, b1, b2);
    }

    public float getCorrelationCoefficient() {
        return correlationCoefficient;
    }

    public float getDeterminationCoefficient() {
        return determinationCoefficient;
    }
}
