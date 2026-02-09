public class MergeSort {
    
    public void merge(int inputArray[], int left, int mid, int right) {

        // Encontrar los tamaños de los subarreglos que se mezclarán
        int size1 = mid - left + 1;
        int size2 = right - mid;

        // Crear arreglos temporales que almacenarán los elementos de los subarreglos
        int leftArray[] = new int[size1];
        int rightArray[] = new int[size2];

        // Copiar datos a arreglos temporales
        for (int i = 0; i < size1; ++i)
            leftArray[i] = inputArray[left + i];

        for (int j = 0; j < size2; ++j)
            rightArray[j] = inputArray[mid + 1 + j];

        // Índices iniciales de los subarreglos y del arreglo principal
        int i = 0, j = 0, k = left;

        // Mezclar los arreglos temporales en inputArray[left..right]
        while (i < size1 && j < size2) {
            if (leftArray[i] <= rightArray[j]) {
                inputArray[k] = leftArray[i];
                i++;
            }
            else {
                inputArray[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copiar elementos restantes de leftArray[], si los hay
        while (i < size1) {
            inputArray[k] = leftArray[i];
            i++;
            k++;
        }

        // Copiar elementos restantes de rightArray[], si los hay
        while (j < size2) {
            inputArray[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public void mergeSort(int inputArray[], int left, int right) {
        if (left < right) {

            // Encontrar el punto medio del arreglo
            int mid = left + (right - left) / 2;

            // Ordenar recursivamente las mitades
            mergeSort(inputArray, left, mid);
            mergeSort(inputArray, mid + 1, right);

            // Mezclar las mitades ordenadas
            merge(inputArray, left, mid, right);
        }
    }
}
