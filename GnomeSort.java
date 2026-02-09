public class GnomeSort {

    public void gnomeSort(int arr[], int length) {
        int index = 0;

        while (index < length) {
            if (index == 0)
                index++;
            if (arr[index] >= arr[index - 1])
                index++;
            else {
                int temp = 0;
                temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                index--;
            }
        }
    }
}