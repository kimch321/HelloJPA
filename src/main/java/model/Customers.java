package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customers {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "userid")
    private String userid;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "age")
    private Integer age;
    @Basic
    @Column(name = "grade")
    private String grade;
    @Basic
    @Column(name = "job")
    private String job;
    @Basic
    @Column(name = "milege")
    private Integer milege;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return Objects.equals(userid, customers.userid) && Objects.equals(name, customers.name) && Objects.equals(age, customers.age) && Objects.equals(grade, customers.grade) && Objects.equals(job, customers.job) && Objects.equals(milege, customers.milege);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, name, age, grade, job, milege);
    }
}
