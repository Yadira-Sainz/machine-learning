
public class DataSet {

    private float x[];
    private float y[];
    private float trainingX[];
    private float trainingY[];
    private float testingX[];
    private float testingY[];

    public DataSet() {
        x = new float[]{108, 115, 106, 97, 95, 91, 97, 83, 83, 78, 54, 67, 56, 53, 61, 115, 81, 78, 30, 45, 99, 32, 25, 28, 90, 89};
        y = new float[]{95, 96, 95, 97, 93, 94, 95, 93, 92, 86, 73, 80, 65, 69, 77, 96, 87, 89, 60, 63, 95, 61, 55, 56, 94, 93};
        splitDataSet();
    }

    private void splitDataSet() {
        int totalSize = x.length;
        int trainingSize = (int) (totalSize * 0.7);
        int testingSize = totalSize - trainingSize;

        trainingX = new float[trainingSize];
        trainingY = new float[trainingSize];
        testingX = new float[testingSize];
        testingY = new float[testingSize];

        // Realizar una copia aleatoria para el conjunto de entrenamiento
        boolean[] used = new boolean[totalSize];
        for (int i = 0; i < trainingSize; i++) {
            int index;
            do {
                index = (int) (Math.random() * totalSize);
            } while (used[index]);

            used[index] = true;
            trainingX[i] = x[index];
            trainingY[i] = y[index];
        }

        // El resto va al conjunto de prueba
        int testIndex = 0;
        for (int i = 0; i < totalSize; i++) {
            if (!used[i]) {
                testingX[testIndex] = x[i];
                testingY[testIndex] = y[i];
                testIndex++;
            }
        }
    }

    // Getters existentes
    public float[] getX() {
        return this.x;
    }

    public float[] getY() {
        return this.y;
    }

    // Nuevos getters
    public float[] getTrainingX() {
        return this.trainingX;
    }

    public float[] getTrainingY() {
        return this.trainingY;
    }

    public float[] getTestingX() {
        return this.testingX;
    }

    public float[] getTestingY() {
        return this.testingY;
    }
}
