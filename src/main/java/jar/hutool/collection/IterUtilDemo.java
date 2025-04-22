package jar.hutool.collection;

import cn.hutool.core.collection.IterUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * <a href="https://doc.hutool.cn/pages/IterUtil/">IterUtil</a>
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/collection/IterUtil.html">IterUtil api</a>
 * <pre>
 * static <T> Iterator<T>   empty()                                 返回一个空 Iterator
 * static Class<?>          getElementType(Iterable/Iterator)       获得 Iterable 对象的元素类型
 * static <T> T             getFirst(Iterable/Iterator)             获取集合的第一个元素
 *
 * static boolean           hasNull(Iterable/Iterator)              是否包含 null 元素
 * static boolean           isEmpty(Iterable/Iterator)              是否为空 Iterable
 * static boolean           isNotEmpty(Iterable/Iterator)           是否不为空 Iterable
 * </pre>
 *
 * @author ljh
 * @since 2020/10/29 2:22
 */
public class IterUtilDemo extends Demo {

    @Test
    public void testIterUtil() {
        // countMap(Iterator)                                   返回一个 Map，记录集合各元素出现的次数
        p(IterUtil.countMap(list.iterator()));                  // {1=1, 2=1, 3=1, 4=1, 5=1, 6=1, 7=1, 8=1, 9=1}

        // join(Iterator, conj, prefix, suffix)                 StrUtil.split() 的反方法
        p(IterUtil.join(list.iterator(), "-", "[", "]"));       // [1]-[2]-[3]-[4]-[5]-[6]-[7]-[8]-[9]

        // filter(T/Iterator, Filter)                           经过 Filter 判断返回
        p(IterUtil.filter(list, i -> i % 2 == 1));              // [1, 3, 5, 7, 9]

        // trans(Iterator<F>, Function<? super F, ? extends T>) 经过 Function 处理返回
        Iterator<Integer> it = IterUtil.trans(list.iterator(), i -> i * 2);
    }

    /**
     * 转换
     */
    @Test
    public void convert() {
        // asIterable(Iterator<E> iter)                         Iterator → Iterable
        Iterable<Integer> iterable = IterUtil.asIterable(list.iterator());

        // asIterator(Enumeration<E> e)                         Enumeration → Iterator
        Iterator<Integer> iterator = IterUtil.asIterator(new Vector<>(list).elements());


        // toList(Iterable/Iterator)                            Iterable/Iterator → List
        List<Map.Entry<Integer, String>> entryList = IterUtil.toList(map.entrySet());


        // toMap(Iterable<Map.Entry<K,V>> entryIter)            Entry → HashMap
        IterUtil.toMap(map.entrySet());

        // toMap(Iterable<K>, Iterable<V>[, isOrder])           Iterable/Iterator + Iterable/Iterator → Map
        p(IterUtil.toMap(subList, descList, true));             // {2=9, 3=8, 4=7, 5=6, 6=5}

        // toMap(Iterable<T>, Function<T,K>, Function<T,V>)     Iterable → Map
        p(IterUtil.toMap(subList, String::valueOf, i -> i));    // {2=[2], 3=[3], 4=[4], 5=[5], 6=[6]}

        // toMap(Iterable<V> , Function<V,K>)                   Iterable → Map
        p(IterUtil.toMap(subList, String::valueOf));            // {2=[2], 3=[3], 4=[4], 5=[5], 6=[6]}

        // toListMap(Iterable<T>, Function<T,K>, Function<T,V>) Iterable → Map<K,List<V>>
        p(IterUtil.toListMap(subList, String::valueOf, i -> i));// {2=[2], 3=[3], 4=[4], 5=[5], 6=[6]}

        // toListMap(Iterable<V> , Function<V,K>)               Iterable → Map<K,List<V>>
        p(IterUtil.toListMap(subList, String::valueOf));        // {2=[2], 3=[3], 4=[4], 5=[5], 6=[6]}


        // fieldValueList(Iterable<V>/Iterator<V>, fieldName)   Iterable<Obj>/Iterator<Obj> → Iterable<field>
        p(IterUtil.fieldValueList(personList, "id"));           // [1, 2, 3]

        // fieldValueMap(Iterable<V>/Iterator<V>, fieldName)    Iterable<Obj>/Iterator<Obj> → Map<field, Obj>
        p(IterUtil.fieldValueMap(personList.iterator(), "id")); // {1=Person{id=1, name='张三'}, 2=Person{id=2, name='李四'}, 3=Person{id=3, name='王五'}}

        // fieldValueAsMap(Iterable<V>/Iterator<V>, fieldNameForKey, fieldNameForValue)
        // Iterable<Obj>/Iterator<Obj> → Map<fieldNameForKey, fieldNameForValue>
        p(IterUtil.fieldValueAsMap(personList.iterator(), "id", "name"));   // {1=张三, 2=李四, 3=王五}
    }
}
