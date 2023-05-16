package jpa;

import model.Employee;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

public class HelloJPA04 {
    public static void main(String[] args) {
        // 엔터티 매니저 생성
        // EntityManagerFactory는 JPA를 사용하여 엔터티 매니저를 생성하기 위한 팩토리 클래스
        // "default"라는 이름의 지속성 유닛(Persistence Unit)을 기반으로 엔터티 매니저를 생성하기 위해 EntityManagerFactory를 만듭니다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

        // EntityManager는 JPA에서 엔터티의 영속성 컨텍스트(Persistence Context)와 상호작용하는 인터페이스입니다.
        // 엔터티 매니저를 사용하여 데이터베이스에 대한 CRUD(Create, Read, Update, Delete) 작업을 수행할 수 있습니다.
        EntityManager em = emf.createEntityManager();

        try {
            // CriteriaBuilder 사용준비
            // CriteriaQuery를 생성하기 위한 빌더 역할을 합니다.
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // 사원 데이터 조회: select
            // 조회 대상을 Employee 엔터티로 지정합니다.
            CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
            // 조회할 테이블 또는 엔터티를 지정합니다.
            Root<Employee> e = cq.from(Employee.class);
            // select 문을 생성합니다.
            cq.select(e);
            // CriteriaQuery를 기반으로 TypedQuery를 생성합니다.
            // TypedQuery는 실제로 데이터베이스에 쿼리를 실행하고 결과를 반환하는 역할을 합니다.
            // 실행전 병합하는 단계??
            TypedQuery<Employee> query = em.createQuery(cq.select(e));
            List<Employee> emps = query.getResultList();

            /*for(Employee emp: emps)
                System.out.println(emp);*/

            // 사원 데이터 조회 - 이름, 부서번호, 입사일: multiselect
            // 컬럼 지정: 객체.get(변수명)
            CriteriaQuery<Object[]> mcq = cb.createQuery(Object[].class);
            Root<Employee> me = mcq.from(Employee.class);

            mcq.multiselect(me.get("fname"), me.get("deptid"), me.get("hdate"));

            List<Object[]> oEmps = em.createQuery(mcq).getResultList();

            /*for(Object[] oEmp: oEmps)
                System.out.println(oEmp[0] + "/" + oEmp[1] + "/" + oEmp[2]);*/

            // 정렬조회: 부서번호 기준, orderby
            // 초기화 부분
            cq = cb.createQuery(Employee.class);
            e = cq.from(Employee.class);

            // 쿼리 입력 전 단계(orderby => Order)?
            Order deptid = cb.desc(e.get("deptid"));

            // 쿼리 입력 단계
            cq.select(e).orderBy(deptid);

            // 실행부
            emps = em.createQuery(cq).getResultList();

            // 출력부
            /*for(Employee emp: emps)
                System.out.println(emp);*/

            // 조건검색: 직책이 IT_PROG인 사원 조회, where
            cq = cb.createQuery(Employee.class);
            e = cq.from(Employee.class);

            // 쿼리 입력 전 단계(where => Predicate)
            // equal: "="
            Predicate jobid = cb.equal(e.get("jobid"), "IT_PROG");

            // 쿼리 입력 단계
            cq.select(e).where(jobid);

            // 실행부 엔티티 매니저를 이용해서 실행한다.
            emps = em.createQuery(cq).getResultList();

            // 출력부
            /*for(Employee emp: emps)
                System.out.println(emp);*/

            // 조건검색: 연봉이 20000 이상인 사원 조회: where
            cq = cb.createQuery(Employee.class);
            e = cq.from(Employee.class);

            // 쿼리 입력 전 단계(where => Predicate)
            Predicate salGE = cb.ge(e.get("sal"), 17000);

            // 쿼리 입력 단계
            cq.select(e).where(salGE);

            // 실행부
            emps = em.createQuery(cq).getResultList();

            // 출력부
            /*for(Employee emp: emps)
                System.out.println(emp);*/

            // 직책 수 조회 1: Expression
            // 초기화
            cq = cb.createQuery(Employee.class);
            e = cq.from(Employee.class);

            // 쿼리 입력 전 단계(count => Expression)
            Expression cntJob = cb.count(e.get("jobid"));

            // 쿼리 입력 단계
            cq.select(cntJob);

            // 실행부
            List<Employee> cnt = em.createQuery(cq).getResultList();

            // 출력부
            /*System.out.println(cnt);*/


            // 직책 수 조회 2 : distinct
            cq = cb.createQuery(Employee.class);
            e = cq.from(Employee.class);

            // 쿼리 입력 전 단계

            // 실행부
            cq.select(e.get("jobid")).distinct(true);
            cnt = em.createQuery(cq).getResultList();

            // 출력부
            /*System.out.println(cnt.size());*/

            // 직책 수 조회 3 : countDistinct
            cq = cb.createQuery(Employee.class);
            e = cq.from(Employee.class);

            // 쿼리 입력 전 단계
            Expression distintCntJob = cb.countDistinct(e.get("jobid"));

            // 쿼리 입력 단계
            cq.select(distintCntJob);

            // 실행부
            cnt = em.createQuery(cq).getResultList();

            // 출력부
            /*System.out.println(cnt);*/


        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
