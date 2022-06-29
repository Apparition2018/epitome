import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * DrawTest
 *
 * @author ljh
 * created on 2022/6/17 14:59
 */
public class DrawTest {

    public static void main(String[] args) throws Exception {
        Date startDate = new Date();
        /* 抽奖活动 */
        // Draw draw = new Draw(1L, "test", startDate, DateUtils.addDays(startDate, 7), 30, 100, 0, "");
        /* 奖品列表 */
        DrawPrize prize = new DrawPrize(1L, 1L, "2,6", "可乐", 1L, 100, 0, 30.0);
        DrawPrize prize2 = new DrawPrize(2L, 1L, "4", "手机", 2L, 2, 0, 2.0);
        DrawPrize prize3 = new DrawPrize(3L, 1L, "8", "电脑", 3L, 1, 0, 0.5);
        List<DrawPrize> prizeList = new ArrayList<>();
        if (prize.getTotalQty() - prize.getWinQty() > 0) prizeList.add(prize);
        if (prize2.getTotalQty() - prize2.getWinQty() > 0) prizeList.add(prize2);
        if (prize3.getTotalQty() - prize3.getWinQty() > 0) prizeList.add(prize3);
        double totalPrizeProbability = prizeList.stream().mapToDouble(DrawPrize::getProbability).sum();
        DrawPrize noPrize = new DrawPrize(0L, 1L, "1,3,5,7", "谢谢惠顾", 0L, null, null, 100 - totalPrizeProbability);
        prizeList.add(noPrize);
        /* 抽奖 */
        Map<Long, DrawPrize> prizeMap = prizeList.stream().collect(Collectors.toMap(DrawPrize::getId, Function.identity(),
                (duplicate1, duplicate2) -> duplicate2, TreeMap::new));
        Map<Long, Integer> winMap = new HashMap<>();
        winMap.put(0L, 0);
        winMap.put(1L, 0);
        winMap.put(2L, 0);
        winMap.put(3L, 0);
        for (int i = 0; i < 200; i++) {
            Long id = draw(prizeMap);
            DrawPrize winPrize = prizeMap.get(id);
            if (id != 0L && winPrize.getTotalQty() - winPrize.getWinQty() > 0) {
                winPrize.setWinQty(winPrize.getWinQty() + 1);
                prizeMap.put(id, winPrize);
                winMap.put(id, winMap.get(id) + 1);
                System.out.println(winPrize.getPrizeName());
            } else {
                winMap.put(0L, winMap.get(0L) + 1);
                System.out.println(noPrize.getPrizeName());
            }
        }
        System.out.println(winMap);
    }

    public static Long draw(Map<Long, DrawPrize> prizeMap) {
        Set<Long> idSet = prizeMap.keySet();
        List<Long> weights = new ArrayList<>();
        for (Long id : idSet) {
            int weight = (int) (prizeMap.get(id).getProbability() * 10);
            for (int i = 0; i < weight; i++) {
                weights.add(id);
            }
        }
        int idx = new Random().nextInt(weights.size());
        return weights.get(idx);
    }

    @Data
    @AllArgsConstructor
    static class Draw {
        private Long id;
        private String drawName;
        private Date startDate;
        private Date endDate;
        private Integer times;
        private Integer score;
        private Integer target;
        private String description;
    }

    @Data
    @AllArgsConstructor
    static class DrawPrize {
        private Long id;
        private Long drawId;
        private String serial;
        private String prizeName;
        private Long picId;
        private Integer totalQty;
        private Integer winQty;
        private Double probability;
    }

    @Data
    @AllArgsConstructor
    static class DrawWinRecord {
        private Long id;
        private Long drawId;
        private Long bookUserId;
        private Date drawTime;
        private Long drawPrizeId;
        private Boolean delivered;
        private String expressNo;
        private Long addressId;
        private String remark;
    }
}
