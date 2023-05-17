package model;

import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "sungjuk")
@Data

public class SungJuk {
    //GenerationType.IDENTITY 전략은 기본 키를 데이터베이스에 위임하는 전략입니다. 즉, JPA는 데이터베이스에 엔티티를 저장할 때 기본 키 값을 생성합니다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sjno;
    @Column(length = 10, nullable = false)
    private String name;
    private Integer kor;
    private Integer eng;
    private Integer mat;
    @Column(nullable = true)
    private Integer tot;
    @Column(nullable = true, precision = 5, scale = 1)
    private BigDecimal avg;
    @Column(length = 1)
    private String grd;
    private Date regdate;

    // persist 호출 전에 regdate 컬럼에 현재 날짜/시간 대입
    @PrePersist
    protected void onCreate() {
        regdate = new Date();
    }
}
