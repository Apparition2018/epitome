package org.ljh.jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Teacher — 教师实体（多对多关联的维护端）。
 * <p>演示 {@code @ManyToMany} + {@code @JoinTable}：通过中间表
 * {@code teacher_student(teacher_id, student_id)} 关联 {@link Student}。
 * 维护端负责维护关联；反向端 {@link Student} 用 {@code mappedBy} 声明。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {

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

    /** 多对多：一个教师带多个学生（级联持久化，保存教师时一并保存学生） */
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "teacher_student",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @ToString.Exclude
    private List<Student> students = new ArrayList<>();
}
