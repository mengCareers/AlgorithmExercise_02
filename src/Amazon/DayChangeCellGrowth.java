package Amazon;

import java.util.Arrays;

/**
 * day change of cells[i]:
 * cells[i] = (cells[i - 1] == cells[i + 1] ? 0 : 1)
 * cells[0]'s left == cells[cells.length - 1]'s right == 0
 * <p>
 * cells after .. days
 */
public class DayChangeCellGrowth {
    public int[] daychange(int[] cells, int days) {

        int len = cells.length;
        int[] ncells = new int[len + 2];
        ncells[0] = 0;
        ncells[len + 1] = 0;
        for (int i = 1; i < len + 1; i++) {
            ncells[i] = cells[i - 1];
        }

        for (int i = 0; i < days; i++) {
            for (int j = 1; j < len + 1; j++) {
                ncells[j] = (ncells[j - 1] ^ ncells[j + 1]);
            }
        }

        return Arrays.copyOfRange(ncells, 1, len + 1);
    }
}
