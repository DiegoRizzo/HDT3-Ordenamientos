import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final class RunResult {
        final String name;
        final double ms;
        final String outputFile;
        final boolean sorted;

        RunResult(String name, double ms, String outputFile, boolean sorted) {
            this.name = name;
            this.ms = ms;
            this.outputFile = outputFile;
            this.sorted = sorted;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int length = readPositiveInt(sc, "Escribe la cantidad de números a generar: ");

        int[] base = new int[length];
        Random rnd = new Random();

        File inputFile = new File("input.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
            for (int i = 0; i < length; i++) {
                int value = rnd.nextInt(10_000); // 0..9999
                base[i] = value;
                writer.write(Integer.toString(value));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error escribiendo input.txt.");
            e.printStackTrace();
            return;
        }

        List<RunResult> results = new ArrayList<>();

        // 1) Burbujam
        {
            int[] arr = base.clone();
            Burbujam.SortResult<int[]> r = new Burbujam().burbujam(arr);
            System.out.println(r.getDurationMs());
            String out = "output_burbujam.txt";
            safeWriteArray(out, r.getData());
            results.add(new RunResult("Burbujam", r.getDurationMs(), out, isSorted(r.getData())));
        }

        // 2) MergeSort
        {
            int[] arr = base.clone();
            double ms = timeMs(() -> {
                if (arr.length > 1) {
                    new MergeSort().mergeSort(arr, 0, arr.length - 1);
                }
            });
            String out = "output_mergesort.txt";
            safeWriteArray(out, arr);
            results.add(new RunResult("MergeSort", ms, out, isSorted(arr)));
        }

        // 3) QuickSort
        {
            int[] arr = base.clone();
            double ms = timeMs(() -> {
                if (arr.length > 1) {
                    new QuickSort().quickSort(arr, 0, arr.length - 1);
                }
            });
            String out = "output_quicksort.txt";
            safeWriteArray(out, arr);
            results.add(new RunResult("QuickSort", ms, out, isSorted(arr)));
        }

        // 4) RadixSort
        {
            int[] arr = base.clone();
            double ms = timeMs(() -> {
                if (arr.length > 1) {
                    new RadixSort().radixSort(arr, arr.length);
                }
            });
            String out = "output_radixsort.txt";
            safeWriteArray(out, arr);
            results.add(new RunResult("RadixSort", ms, out, isSorted(arr)));
        }
        // 5) GnomeSort
        {
            int[] arr = base.clone();
            double ms = timeMs(() -> {
                if (arr.length > 1) {
                    new GnomeSort().gnomeSort(arr, arr.length);
                }
            });
            String out = "output_gnomesort.txt";
            safeWriteArray(out, arr);
            results.add(new RunResult("GnomeSort", ms, out, isSorted(arr)));
        }

        System.out.println("\n=== Resultados (ms) ===");
        for (RunResult r : results) {
            System.out.printf("%-10s : %10.3f ms | sorted=%s | %s%n",
                    r.name, r.ms, r.sorted, r.outputFile);
        }

        safeWriteSummary("timings.txt", results);
    }

    private static int readPositiveInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                int n = Integer.parseInt(line);
                if (n > 0) return n;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Por favor ingresa un entero positivo.");
        }
    }

    private static double timeMs(Runnable action) {
        long start = System.nanoTime();
        action.run();
        return (System.nanoTime() - start) / 1_000_000.0;
    }

    private static void safeWriteArray(String filename, int[] arr) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)))) {
            for (int v : arr) {
                writer.write(Integer.toString(v));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error escribiendo " + filename);
            e.printStackTrace();
        }
    }

    private static void safeWriteSummary(String filename, List<RunResult> results) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)))) {
            writer.write("name,ms,sorted,outputFile");
            writer.newLine();
            for (RunResult r : results) {
                writer.write(r.name + "," + String.format("%.3f", r.ms) + "," + r.sorted + "," + r.outputFile);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error escribiendo " + filename);
            e.printStackTrace();
        }
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }
}