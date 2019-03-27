package other.skill;

import org.junit.Test;

public class skill {

    // 跳出多层循环
    @Test
    public void breakMultiLoop() {

        int[][] arr = {{1, 2, 3}, {4, 5, 6, 7}, {8, 9}};
        boolean found = false;

        for (int i = 0; i < arr.length && !found; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 5) {
                    found = true;
                    System.out.println(arr[i][j]);
                    break;
                }
            }
        }

    }


}
