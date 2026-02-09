public class GnomeSort {

    public void gnomeSort(int inputArray[], int length) {
        int index = 0;

        while (index < length) {
            if (index == 0)
                index++;
            if (inputArray[index] >= inputArray[index - 1])
                index++;
            else {
                int temp = 0;
                temp = inputArray[index];
                inputArray[index] = inputArray[index - 1];
                inputArray[index - 1] = temp;
                index--;
            }
        }
    }
}