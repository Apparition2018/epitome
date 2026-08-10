package org.ljh.hibernate.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Teacher — 教师实体，演示多对多（{@code @ManyToMany}）
 * <p>教师与 {@link Student} 是多对多关系，通过中间表 {@code teacher_student}
 * （由 {@code @JoinTable} 指定，持有 {@code teacher_id} 与 {@code student_id} 两列）关联。
 * 本端为维护端（{@code Teacher} 管理中间表），另一端用 {@code mappedBy} 反向引用。</p>
 *
 * @author ljh
 * @since 2026/8/10
 */
@Data
@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 教师姓名 */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * 多对多：一个教师教多个学生。
     * <p>维护端持有中间表 {@code teacher_student}；{@code cascade = PERSIST} 使持久化
     * 教师时级联保存其学生（不级联删除，避免误删学生）；延迟加载；
     * 集合按学生 id 排序以保证断言稳定。</p>
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "teacher_student",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @OrderBy("id ASC")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Student> students = new ArrayList<>();

    /** 双向维护：添加学生并回填教师引用 */
    public Teacher addStudent(Student student) {
        students.add(student);
        student.getTeachers().add(this);
        return this;
    }
}
