package org.ljh.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Student — 学生实体，演示多对多（{@code @ManyToMany}）的反向端
 * <p>{@code mappedBy = "students"} 表示由 {@link Teacher#getStudents()} 维护中间表，
 * 本端是只读（inverse）端。与 {@link Teacher} 共享中间表 {@code teacher_student}。</p>
 *
 * @author ljh
 * @since 2026/8/10
 */
@Data
@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 学生姓名 */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /** 反向端：一个学生可被多个教师教授（不维护中间表） */
    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Teacher> teachers = new ArrayList<>();
}
