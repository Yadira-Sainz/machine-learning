import java.util.*;

public class Main {
    public static void main(String[] args) {
        DataSet dataSet = new DataSet();
        DiscreteMaths discreteMaths = new DiscreteMaths();
        Random rand = new Random();

        float bestB0 = 0;
        float bestB1 = 0;
        float bestR2 = 0;

        for (int i = 0; i < 3; i++) {
            // Obtener los datos originales
            float[] x = dataSet.getX();
            float[] y = dataSet.getY();

            int totalSize = x.length;
            int trainSize = (int) (totalSize * 0.7);

            List<Integer> indices = new ArrayList<>();
            for (int j = 0; j < totalSize; j++) indices.add(j);
            Collections.shuffle(indices, rand);

            float[] xTrain = new float[trainSize];
            float[] yTrain = new float[trainSize];
            float[] xTest = new float[totalSize - trainSize];
            float[] yTest = new float[totalSize - trainSize];

            // Asignación del conjunto de entrenamiento (70%)
            for (int j = 0; j < trainSize; j++) {
                xTrain[j] = x[indices.get(j)];
                yTrain[j] = y[indices.get(j)];
            }

            // Asignación del conjunto de prueba (30%)
            for (int j = trainSize; j < totalSize; j++) {
                xTest[j - trainSize] = x[indices.get(j)];
                yTest[j - trainSize] = y[indices.get(j)];
            }

            float b1 = discreteMaths.b1(xTrain, yTrain);
            float b0 = discreteMaths.b0(xTrain, yTrain);

            // Calcular R² y correlación
            float ssTotal = 0;
            float ssResidual = 0;
            float meanY = discreteMaths.sumY(yTest) / yTest.length;
            float sumXY = discreteMaths.sumXY(xTrain, yTrain);
            float sumX = discreteMaths.sumX(xTrain);
            float sumY = discreteMaths.sumY(yTrain);
            float sumX2 = discreteMaths.XPow2(xTrain);
            float sumY2 = discreteMaths.XPow2(yTrain);

            for (int j = 0; j < yTest.length; j++) {
                float predictedY = b0 + (b1 * xTest[j]);
                ssResidual += Math.pow(yTest[j] - predictedY, 2);
                ssTotal += Math.pow(yTest[j] - meanY, 2);
            }

            float r2 = 1 - (ssResidual / ssTotal);
            float correlation = (float) ((sumXY - (sumX * sumY / trainSize)) / Math.sqrt((sumX2 - Math.pow(sumX, 2) / trainSize) * (sumY2 - Math.pow(sumY, 2) / trainSize)));

            System.out.println("Iteración " + (i+1) + " -> B0: " + b0 + ", B1: " + b1 + ", R²: " + r2 + ", Correlación: " + correlation);

            if (r2 > bestR2) {
                bestR2 = r2;
                bestB0 = b0;
                bestB1 = b1;
            }
        }

        System.out.println("\nMejor modelo: y = " + bestB0 + " + " + bestB1 + "x, con R² = " + bestR2);

        // Predicciones con valores conocidos y desconocidos
        float[] testBatchSizes = {50, 75, 100, 125, 150};
        System.out.println("\nPredicciones:");
        for (float batchSize : testBatchSizes) {
            float predictedEfficiency = bestB0 + (bestB1 * batchSize);
            System.out.println("Batch Size: " + batchSize + " -> Predicción de Machine Efficiency: " + predictedEfficiency);
        }
    }
}
