
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DataSet dataSet = new DataSet();
        LinearRegression bestLinearModel = null;
        QuadraticRegression bestQuadraticModel = null;
        CubicRegression bestCubicModel = null;
        float bestLinearR2 = -1;
        float bestQuadraticR2 = -1;
        float bestCubicR2 = -1;

        // ==================== MODELO LINEAL ====================
        System.out.println("================================================================");
        System.out.println("                         MODELO LINEAL ");
        System.out.println("================================================================");
        for (int i = 0; i < 3; i++) {
            System.out.println("\nIteración " + (i + 1));
            dataSet = new DataSet();
            LinearRegression linearModel = new LinearRegression(dataSet.getTrainingX(), dataSet.getTrainingY());

            System.out.print("Ecuación: ");
            linearModel.printEquation();
            float currentLinearR2 = linearModel.getDeterminationCoefficient();
            System.out.printf("Coeficiente de determinación (R²) = %.4f%n", currentLinearR2);
            System.out.printf("Coeficiente de correlación (r)   = %.4f%n", linearModel.getCorrelationCoefficient());

            if (currentLinearR2 > bestLinearR2) {
                bestLinearR2 = currentLinearR2;
                bestLinearModel = linearModel;
                System.out.println(">> Nuevo mejor modelo encontrado (R² = " + String.format("%.4f", bestLinearR2) + ") <<");
            } else {
                System.out.println(">> Mejor R² hasta ahora: " + String.format("%.4f", bestLinearR2) + " <<");
            }
        }

        System.out.println("\n==================== Mejor Modelo Lineal ====================");
        System.out.print("Ecuación: ");
        bestLinearModel.printEquation();
        System.out.printf("Coeficiente de correlación (r)   = %.4f%n", bestLinearModel.getCorrelationCoefficient());
        System.out.printf("Coeficiente de determinación (R²) = %.4f%n", bestLinearModel.getDeterminationCoefficient());

        System.out.println("\nPredicciones:");
        float[] valuesToPredict = {50, 75, 100, 125, 150};
        for (float value : valuesToPredict) {
            System.out.printf("Batch Size = %6.2f  -->  Eficiencia = %6.2f%%%n", value, bestLinearModel.predict(value));
        }

        // ==================== MODELO CUADRÁTICO ====================
        System.out.println("\n================================================================");
        System.out.println("                         MODELO CUADRÁTICO ");
        System.out.println("================================================================");
        for (int i = 0; i < 3; i++) {
            System.out.println("\nIteración " + (i + 1));
            dataSet = new DataSet();
            QuadraticRegression quadraticModel = new QuadraticRegression(dataSet.getTrainingX(), dataSet.getTrainingY());

            System.out.print("Ecuación: ");
            quadraticModel.printEquation();
            float currentQuadraticR2 = quadraticModel.getDeterminationCoefficient();
            System.out.printf("Coeficiente de determinación (R²) = %.4f%n", currentQuadraticR2);
            System.out.printf("Coeficiente de correlación (r)   = %.4f%n", quadraticModel.getCorrelationCoefficient());

            if (currentQuadraticR2 > bestQuadraticR2) {
                bestQuadraticR2 = currentQuadraticR2;
                bestQuadraticModel = quadraticModel;
                System.out.println(">> Nuevo mejor modelo encontrado (R² = " + String.format("%.4f", bestQuadraticR2) + ") <<");
            } else {
                System.out.println(">> Mejor R² hasta ahora: " + String.format("%.4f", bestQuadraticR2) + " <<");
            }
        }

        System.out.println("\n==================== Mejor Modelo Cuadrático ====================");
        System.out.print("Ecuación: ");
        bestQuadraticModel.printEquation();
        System.out.printf("Coeficiente de correlación (r)   = %.4f%n", bestQuadraticModel.getCorrelationCoefficient());
        System.out.printf("Coeficiente de determinación (R²) = %.4f%n", bestQuadraticModel.getDeterminationCoefficient());

        System.out.println("\nPredicciones:");
        for (float value : valuesToPredict) {
            System.out.printf("Batch Size = %6.2f  -->  Eficiencia = %6.2f%%%n", value, bestQuadraticModel.predict(value));
        }

        // ==================== MODELO CÚBICO ====================
        System.out.println("\n================================================================");
        System.out.println("                         MODELO CÚBICO ");
        System.out.println("================================================================");

        for (int i = 0; i < 3; i++) {
            System.out.println("\nIteración " + (i + 1));
            dataSet = new DataSet();
            CubicRegression cubicModel = new CubicRegression(dataSet.getTrainingX(), dataSet.getTrainingY());

            System.out.print("Ecuación: ");
            cubicModel.printEquation();
            float currentCubicR2 = cubicModel.getDeterminationCoefficient();
            System.out.printf("Coeficiente de determinación (R²) = %.4f%n", currentCubicR2);
            System.out.printf("Coeficiente de correlación (r)   = %.4f%n", cubicModel.getCorrelationCoefficient());

            if (currentCubicR2 > bestCubicR2) {
                bestCubicR2 = currentCubicR2;
                bestCubicModel = cubicModel;
                System.out.println(">> Nuevo mejor modelo encontrado (R² = " + String.format("%.4f", bestCubicR2) + ") <<");
            } else {
                System.out.println(">> Mejor R² hasta ahora: " + String.format("%.4f", bestCubicR2) + " <<");
            }
        }

        System.out.println("\n==================== Mejor Modelo Cúbico ====================");
        System.out.print("Ecuación: ");
        bestCubicModel.printEquation();
        System.out.printf("Coeficiente de correlación (r)   = %.4f%n", bestCubicModel.getCorrelationCoefficient());
        System.out.printf("Coeficiente de determinación (R²) = %.4f%n", bestCubicModel.getDeterminationCoefficient());

        System.out.println("\nPredicciones:");
        for (float value : valuesToPredict) {
            System.out.printf("Batch Size = %6.2f  -->  Eficiencia = %6.2f%%%n", value, bestCubicModel.predict(value));
        }
    }
}
