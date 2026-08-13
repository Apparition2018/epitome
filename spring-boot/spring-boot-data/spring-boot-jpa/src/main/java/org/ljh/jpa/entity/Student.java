package org.ljh.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Student — 学生实体（多对多关联的反向端）。
 * <p>{@code mappedBy = "students"} 表示关联由 {@link Teacher#getStudents()} 一侧维护，
 * 本端仅做反向读取，不持有外键维护逻辑。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 姓名 */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /** 反向端：该学生的所有教师 */
    @ManyToMany(mappedBy = "students")
    @ToString.Exclude
    private List<Teacher> teachers = new ArrayList<>();
}
