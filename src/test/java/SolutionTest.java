import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Solution
 *
 * @author ljh
 * created on 2021/6/16 10:15
 */
public class SolutionTest {

    static class Easy {

        public static void main(String[] args) {
            String j = "aA";
            String s = "aAAbbbb";
            System.out.println(numJewelsInStones(j, s));
        }

        public static int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) {
                    return new int[]{map.get(target - nums[i]), i};
                }
                map.put(nums[i], i);
            }
            return new int[0];
        }

        public static int numJewelsInStones(String jewels, String stones) {
            int jewelCount = 0;
            Set<Character> jewelSet = new HashSet<>();
            for (int i = 0, len = jewels.length(); i < len; i++) {
                jewelSet.add(jewels.charAt(i));
            }
            for (int i = 0, len = stones.length(); i < len; i++) {
                if (jewelSet.contains(stones.charAt(i))) {
                    jewelCount++;
                }
            }
            return jewelCount;
        }

    }

    public static boolean stoneGame(int[] piles) {
        Map<String, Object> map = new HashMap<>();
        map.put("x", 0);
        map.put("y", 0);
        map.put("arr", piles);
        return takeStone(map);

    }

    public static boolean takeStone(Map<String, Object> map) {
        int x = (int) map.get("x");
        int y = (int) map.get("y");
        int[] arr = (int[]) map.get("arr");
        int[] arr2 = new int[arr.length - 1];
        int[] arr3 = new int[arr.length - 2];
        if (arr[0] > arr[arr.length - 1]) {
            x += arr[0];
            System.arraycopy(arr, 1, arr2, 0, arr2.length);
        } else {
            x += arr[arr.length - 1];
            System.arraycopy(arr, 0, arr2, 0, arr2.length);
        }
        if (arr2[0] > arr2[arr2.length - 1]) {
            y += arr2[0];
            System.arraycopy(arr2, 1, arr3, 0, arr3.length);
        } else {
            y += arr2[arr2.length - 1];
            System.arraycopy(arr2, 0, arr3, 0, arr3.length);
        }
        if (arr3.length == 0) {
            return x > y;
        } else {
            map.put("x", x);
            map.put("y", y);
            map.put("arr", arr3);
            return takeStone(map);
        }
    }

}
