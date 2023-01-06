package spring.api.util;

import l.demo.Demo;
import l.demo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.util.FastByteArrayOutputStream;

import java.io.*;
import java.util.stream.IntStream;

/**
 * FastByteArrayOutputStream
 * <pre>
 * Spring 实现的加强版的 ByteArrayOutputStream，采用一个 LinkedList<byte[]> 作为缓冲区，替代字节数组 byte buf[]；
 * 这样每次扩容的时候，分配一个数组空间并直接放到 List 中；而 ByteArrayOutputStream 需要重新分配空间并将数据复制到新数组中
 * </pre>
 *
 * @author ljh
 * @since 2021/9/8 0:56
 */
public class FastByteArrayOutputStreamDemo extends Demo {

    @Test
    public void testFastByteArrayOutputStream() {
        stopWatch.start("ByteArrayOutputStream");
        IntStream.rangeClosed(1, 500).forEach(i -> this.clone(personList.get(0)));
        stopWatch.stop();

        stopWatch.start("FastByteArrayOutputStream");
        IntStream.rangeClosed(1, 500).forEach(i -> this.fastClone(personList.get(0)));
        stopWatch.stop();

        p(stopWatch.prettyPrint());
        // ---------------------------------------------
        // ns         %     Task name
        // ---------------------------------------------
        // 058931200  074%  ByteArrayOutputStream
        // 020224200  026%  FastByteArrayOutputStream
    }

    private void clone(Person person) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(person);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fastClone(Person person) {
        FastByteArrayOutputStream fbaos = new FastByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(fbaos)) {
            oos.writeObject(person);

            ObjectInputStream ois = new ObjectInputStream(fbaos.getInputStream());
            ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
