

public class LinearRegression {

    private float b0;
    private float b1;
    private float correlationCoefficient;
    private float determinationCoefficient;
    private float[] x;
    private float[] y;

    public LinearRegression(float[] x, float[] y) {
        this.x = x;
        this.y = y;
        calculateParameters();
        calculateCoefficients();
    }

    private void calculateParameters() {
        int n = x.length;
        float sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;

        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXY += x[i] * y[i];
            sumX2 += x[i] * x[i];
        }

        b1 = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        b0 = (sumY - b1 * sumX) / n;
    }

    private void calculateCoefficients() {
        int n = x.length;
        float sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0, sumY2 = 0;

        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXY += x[i] * y[i];
            sumX2 += x[i] * x[i];
            sumY2 += y[i] * y[i];
        }

        correlationCoefficient = (float) ((n * sumXY - sumX * sumY)
                / Math.sqrt((n * sumX2 - sumX * sumX) * (n * sumY2 - sumY * sumY)));

        determinationCoefficient = correlationCoefficient * correlationCoefficient;
    }

    public float predict(float x) {
        return b0 + b1 * x;
    }

    public void printEquation() {
        String equation = String.format("y = %.4f + %.4fx", b0, b1);
        System.out.println(equation);
    }

    public float getCorrelationCoefficient() {
        return correlationCoefficient;
    }

    public float getDeterminationCoefficient() {
        return determinationCoefficient;
    }
}
