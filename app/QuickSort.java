import java.util.Arrays;

public class QuickSort {

    public void swap(int inputArray[], int i, int j) {
        int temp = inputArray[i];
        inputArray[i] = inputArray[j];
        inputArray[j] = temp;
    }

    public int partition(int inputArray[], int low, int high) {
        int pivot = inputArray[high];
        int i = (low - 1);

        // Recorre el arreglo desde low hasta high-1
        // Se mueven los elementos menores al pivote hacia la izquierda
        for (int j = low; j <= high - 1; j++) {
            if (inputArray[j] < pivot) {
                i++;
                swap(inputArray, i, j);
            }
        }

        // Coloca el pivote en su posición correcta
        swap(inputArray, i + 1, high);
        return i + 1;
    }

    public void quickSort(int inputArray[], int low, int high) {
        if (low < high) {
            int pi = partition(inputArray, low, high);

            // Ordena recursivamente los elementos antes y después de la partición
            quickSort(inputArray, low, pi - 1);
            quickSort(inputArray, pi + 1, high);
        }
    }
}