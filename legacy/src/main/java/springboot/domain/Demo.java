package springboot.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Demo
 *
 * @author ljh
 * @since 2022/2/18 18:03
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "demo")
public class Demo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
}
