import lombok.*;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 史上最强整理: https://mp.weixin.qq.com/s/kJpRgfI3zT77XqMeRfmmQQ
// SpringBoot 之整合 Swagger2: https://www.cnblogs.com/zhangyinhua/p/9286391.html
// SpringBoot + Spring Batch
// SSL：https://www.cnblogs.com/crazyacking/p/5648520.html
// JAVA与模式
// NIO
// JWT
// shiro
// Guava (140)
// 多种 Collections (143)

// https://blog.csdn.net/u012426327/article/list/13
// 【小家java】java5新特性（简述十大新特性） 重要一跃：https://blog.csdn.net/f641385712/article/details/81783266
// 【小家Spring】Spring Framework提供的实用纯Java工具类大合集（一）：https://blog.csdn.net/f641385712/article/details/86749481
// java长连接

// CSDN 阿_毅
// 林祥纤 SpringBoot
public class Test {

    public static void main(String[] args) {


        List<Desc> descList = new ArrayList<>();

        // 学生。。。
        List<Child> childList1 = new ArrayList<>();   // 旧
        childList1.add(new Child(3, "3", "3"));
        childList1.add(new Child(2, "2", "2"));
        childList1.add(new Child(1, "1", "1"));
        childList1.add(new Child(5, "5", "5"));

        Map<Integer, Child> map1 = childList1.stream().collect(Collectors.toMap(Child::getCId, child -> child));

        List<Child> childList2 = new ArrayList<>();   // 新
        childList2.add(new Child(3, "3", "3"));
        childList2.add(new Child(2, "22", "22"));
        childList2.add(new Child(1, "1", "1"));
        childList2.add(new Child(4, "4", "4"));

        Map<Integer, Child> map2 = childList2.stream().collect(Collectors.toMap(Child::getCId, child -> child));


        List<Integer> idList = childList1.stream().map(Child::getCId).collect(Collectors.toList());
        System.out.println(idList); // [3, 2, 1, 5]

        List<Integer> idList2 = childList2.stream().map(Child::getCId).collect(Collectors.toList());
        System.out.println(idList2); // [3, 2, 1, 4]

        List<Integer> noChangeAndUpdate = ListUtils.retainAll(idList, idList2);
        System.out.println(noChangeAndUpdate); // [3, 2, 1]，不变或修改
        List<Integer> delete = ListUtils.removeAll(idList, idList2);
        System.out.println(delete); // [5]，删除
        List<Integer> add = ListUtils.removeAll(idList2, idList);
        System.out.println(add); // [4]，新增
        System.out.println("\n");

        // 新增
        for (Integer cId : add) {
            Child child = map2.get(cId);
            descList.add(new Desc(cId, "请新增学生，姓名名为" + child.getName() + ",学号为" + child.getSno() + "。"));
        }

        // 删除
        for (Integer cId : delete) {
            Child child = map1.get(cId);
            descList.add(new Desc(cId, "请将姓名为" + child.getName() + "的学生删除。"));
        }

        // 不变和修改 [1, 2]
        for (Integer cId : noChangeAndUpdate) {
            Child child1 = map1.get(cId);
            Child child2 = map2.get(cId);
            if (!child1.equals(child2)) {
                String name = child1.getName().equalsIgnoreCase(child2.getName()) ? "" : child2.getName();
                String sno = child1.getSno().equalsIgnoreCase(child2.getSno()) ? "" : child2.getSno();

                descList.add(new Desc(cId, name.isEmpty() ? "请将姓名为" + child1.getName() : ("请将姓名为" + child1.getName() + "的学生的姓名修改成" + name) + (sno.isEmpty() ? "" : "，学号修改成" + sno + "。")));
            }
        }

        // 家长。。。
        List<Parent> parentList1 = new ArrayList<>();   // 旧
        parentList1.add(new Parent(1, "01", 1));
        parentList1.add(new Parent(1, "01", 2));
        parentList1.add(new Parent(2, "02", 3));
        parentList1.add(new Parent(3, "03", 3));
        parentList1.add(new Parent(8, "08", 5));


        Map<String, Parent> map3 = parentList1.stream().collect(
                Collectors.toMap(parent -> parent.getCId() + "-" + parent.getPId(), parent -> parent));

        List<Parent> parentList2 = new ArrayList<>();   // 新
        parentList2.add(new Parent(1, "011", 1));
        parentList2.add(new Parent(1, "011", 2));
        parentList2.add(new Parent(2, "022", 3));
        parentList2.add(new Parent(4, "044", 3));

        Map<String, Parent> map4 = parentList2.stream().collect(
                Collectors.toMap(parent -> parent.getCId() + "-" + parent.getPId(), parent -> parent));


        List<String> idList3 = parentList1.stream().map(parent -> parent.getCId() + "-" + parent.getPId()).collect(Collectors.toList());
        System.out.println(idList3); // [1-1, 2-1, 3-2, 3-3]

        List<String> idList4 = parentList2.stream().map(parent -> parent.getCId() + "-" + parent.getPId()).collect(Collectors.toList());
        System.out.println(idList4); // [1-1, 2-1, 3-2, 3-4]

        List<String> noChangeAndUpdate2 = ListUtils.retainAll(idList3, idList4);
        System.out.println(noChangeAndUpdate2); // [1-1, 2-1, 3-2]，不变或修改
        List<String> delete2 = ListUtils.removeAll(idList3, idList4);
        System.out.println(delete2); // [3-3]，删除
        List<String> add2 = ListUtils.removeAll(idList4, idList3);
        System.out.println(add2); // [3-4]，新增
        System.out.println("\n");

        // 新增
        for (String cpId : add2) {
            Parent parent = map4.get(cpId);
            Integer cId = Integer.valueOf(cpId.split("-")[0]);
            String nName = map2.get(cId).getName();
            descList.add(new Desc(cId, "请给姓名为" + nName + "的学生添加家长，家长手机号为" + parent.getNum() + "。"));
        }

        // 删除
        for (String cpId : delete2) {
            Parent parent = map3.get(cpId);
            Integer cId = Integer.valueOf(cpId.split("-")[0]);
            String nName = map1.get(cId).getName();
            descList.add(new Desc(cId, "请给姓名为" + nName + "的学生删除家长，家长手机号为" + parent.getNum() + "。"));
        }

        // 不变和修改
        for (String cpId : noChangeAndUpdate2) {
            Parent parent1 = map3.get(cpId);
            Parent parent2 = map4.get(cpId);
            Integer cId = Integer.valueOf(cpId.split("-")[0]);
            String nName = map1.get(cId).getName();
            if (!parent1.equals(parent2)) {
                String num = parent1.getNum().equalsIgnoreCase(parent2.getNum()) ? "" : parent2.getNum();
                descList.add(new Desc(cId, num.isEmpty() ? "" : 
                        "请将姓名为" + nName + "的学生的旧家长手机号" + parent1.getNum() + "修改成" + parent2.getNum() + "。"));
            }
        }


        
        for (Desc desc : descList) {
            System.out.println(desc);
        }
        System.out.println("\n");
        Collections.sort(descList);
        for (Desc desc : descList) {
            System.out.println(desc);
        }
        System.out.println("\n");

        List<String> newDescList = new ArrayList<>();
        Integer lastUserId = 0;
        StringBuilder temp = new StringBuilder();
        for (Desc desc : descList) {
            if (!desc.getCId().equals(lastUserId) && temp.length() != 0) {
                newDescList.add(temp.toString());
                temp = new StringBuilder();
            }
            temp.append(desc.getDesc());
            lastUserId = desc.getCId();
        }
        newDescList.add(temp.toString());

        for (String s : newDescList) {
            System.out.println(s);
        }
    }


    @Getter
    @Setter
    @EqualsAndHashCode
    @AllArgsConstructor
    @ToString
    static class Child {
        private Integer cId;
        private String name;
        private String sno;
    }

    @Getter
    @Setter
    @EqualsAndHashCode
    @AllArgsConstructor
    @ToString
    static class Parent {
        private Integer pId;
        private String num;
        private Integer cId;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    static class Desc implements Comparable<Desc> {
        private Integer cId;
        private String desc;

        @Override
        public int compareTo(Desc o) {
            return new CompareToBuilder().append(cId, o.cId).toComparison();
        }
    }

}