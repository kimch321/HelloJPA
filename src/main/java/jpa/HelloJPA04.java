package jpa;

import model.Employee;
import model.SungJuk;
import org.hibernate.Criteria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.*;
import java.util.List;

public class HelloJPA04 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            // CriteriaBuilder 사용준비
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // 사원 데이터 조회
            CriteriaQuery<Employee> query = cb.createQuery(Employee.class); // 이 클래스에 대한 쿼리를 작성한다.
            Root<Employee> e = query.from(Employee.class); // from으로 조회대상 지정

            CriteriaQuery<Employee> cq = query.select(e); // select객체 생성.
            List<Employee> emps = em.createQuery(cq).getResultList();

//            for(Employee emp: emps)
//                System.out.println(emp);

            // 사원 데이터 조회 - 이름, 부서번호, 입사일: multiselect
            // 컬럼 지정: 객체.get(변수명)
            /*CriteriaQuery<Object[]> mcq = cb.createQuery(Object[].class);
            Root<Employee> me = mcq.from(Employee.class);

            mcq.multiselect(e.get("fname"), e.get("hdate"), e.get("deptid"));
            List<Object[]> items = em.createQuery(mcq).getResultList();

            for(Object[] item: items)
                System.out.println(item[0] + "/" + item[1] + "/" + item[2]);*/

            // 정렬조회: 부서번호 기준, orderby

            // 조건검색: 직책이 IT_PROG인 사원 조회, where
            Predicate jobid = cb.equal(e.get("jobid"),"IT_PROG");
            cq = query.select(e).where(jobid);

            emps = em.createQuery(cq).getResultList();

//            for(Employee emp: emps)
//                System.out.println(emp);

            // 조건검색: 연봉이 20000 이상인 사원 조회
            /*Predicate salGE = cb.ge(e.get("sal"), 20000);
            cq = query.select(e).where(salGE);

            emps = em.createQuery(cq).getResultList();

            for(Employee emp: emps)
                System.out.println(emp);*/

            // 직책 수 조회 1
            Expression cntJob = cb.count(e.get("jobid"));
            cq = query.select(cntJob);
            List<Employee> cnt = em.createQuery(cq).getResultList();

            System.out.println(cnt);

            // 직책 수 조회 2 : distinct
            cq = query.select(e.get("jobid")).distinct(true);
            cnt = em.createQuery(cq).getResultList();

            System.out.println(cnt);

            // 직책 수 조회 3 : countDistinct
            cntJob = cb.countDistinct(e.get("jobid"));
            cq = query.select(cntJob);
            cnt = em.createQuery(cq).getResultList();

            System.out.println(cnt);


        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
