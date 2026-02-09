import java.util.Objects;

/** Burbuja mejorada (con bandera para cortar temprano). */
public class Burbujam {


    public SortResult<int[]> burbujam(int[] vec) {
        Objects.requireNonNull(vec, "vec no puede ser null");

        long inicio = System.nanoTime();

        for (int p = vec.length - 1; p > 0; p--) {
            boolean bandera = false;

            for (int d = 0; d < p; d++) {
                if (vec[d] > vec[d + 1]) {
                    bandera = true;
                    int tmp = vec[d];
                    vec[d] = vec[d + 1];
                    vec[d + 1] = tmp;
                }
            }

            if (!bandera) break;
        }

        double duracionMs = (System.nanoTime() - inicio) / 1_000_000.0;
        return new SortResult<>(vec, duracionMs);
    }

    public SortResult<int[]> sort(int[] vec) {
        return burbujam(vec);
    }

    /** Resultado de un ordenamiento: datos + duración (ms). */
    public static final class SortResult<T> {
        private final T data;
        private final double durationMs;

        public SortResult(T data, double durationMs) {
            this.data = data;
            this.durationMs = durationMs;
        }

        public T getData() {
            return data;
        }

        public double getDurationMs() {
            return durationMs;
        }
    }
}