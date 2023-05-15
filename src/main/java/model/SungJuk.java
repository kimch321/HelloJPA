package model;

import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SUNGJUK")
@Data

public class SungJuk {
    //GenerationType.IDENTITY 전략은 기본 키를 데이터베이스에 위임하는 전략입니다. 즉, JPA는 데이터베이스에 엔티티를 저장할 때 기본 키 값을 생성합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int sjno;
    private String name;
    private int kor;
    private int eng;
    private int mat;
    private int tot;
    private double avg;
    private String grd;
    private Date regdate;

    // persist 호출 전에 regdate 컬럼에 현재 날짜/시간 대입
    @PrePersist
    protected void onCreate() {
        regdate = new Date();
    }
}
