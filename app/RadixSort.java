import java.util.Arrays;

public class RadixSort {

    public void radixSort(int inputArray[], int length) {
        int max = getMax(inputArray, length);

        for (int exp = 1; max / exp > 0; exp *= 10)
            countSort(inputArray, length, exp);
    }

    public int getMax(int inputArray[], int length) {
        int max = inputArray[0];
        for (int i = 1; i < length; i++) {
            if (inputArray[i] > max)
                max = inputArray[i];
        }
        return max;
    }

    public void countSort(int inputArray[], int length, int exp) {
        int output[] = new int[length];
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        // Paso 1: Contar las ocurrencias de cada dígito y almacenarlas en count[]
        for (i = 0; i < length; i++)
            count[(inputArray[i] / exp) % 10]++;

        // Paso 2: Cambiar count[i] para que ahora contenga la posición real de los dígitos en output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Paso 3: Construir el array de output
        for (i = length - 1; i >= 0; i--) {
            output[count[(inputArray[i] / exp) % 10] - 1] = inputArray[i];
            count[(inputArray[i] / exp) % 10]--;
        }

        // Paso 4: Copiar el array de output a inputArray para que almacene los números ordenados según el dígito actual
        for (i = 0; i < length; i++)
            inputArray[i] = output[i];
    }
    
}
