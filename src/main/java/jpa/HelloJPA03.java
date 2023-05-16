package jpa;

import javax.persistence.*;
import java.util.List;

public class HelloJPA03 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            // 사원 데이터 조회
            // createQuery(질의문, 리턴될 객체 종류)
            String jpql = "select e from Employee as e";
            //List<Employees> emps = em.createQuery(jpql, Employees.class).getResultList();
//            for(Employees emp : emps)
//                System.out.println(emp);

            // 사원 데이터 조회 - Query: 이름, 부서번호, 입사일
            // createQuery(질의문)
            jpql = "select fname, deptid, hdate from Employee e";
            //List<Object[]> items = em.createQuery(jpql).getResultList();
//            for(Object[] item: items)
//                System.out.println(item[0] + "/" + item[1] + "/" + item[2]);

            // 사원 직책 조회 - jobid가 IT_PROG인 사원
            // 파라메터 바인딩 - :파라미터명, ?순번
            jpql = "select e from Employee e where jobid = :jobid";  // 이름기반
            // jpql = "select e from Employees e where jobid = ?1";  // 위치기반

            //TypedQuery<Employees> query = em.createQuery(jpql, Employees.class);
            //query.setParameter("jobid","IT_PROG");
            // query.setParameter(1,"IT_PROG");

           // emps = query.getResultList();
           //     for(Employees emp : emps)
           //        System.out.println(emp);

           // 사원 평균 연봉 조회 - TypedQuery
           jpql = "select avg(sal) from Employee e";
//           Double avgsal = em.createQuery(jpql, Double.class).getSingleResult();
//            System.out.println(avgsal);

           // 사원 직책수 조회
           // select COUNT(distinct JOB_ID) cnt from employees;
           jpql = "select COUNT(DISTINCT jobid) FROM Employee e";
//           Long cntJobid = em.createQuery(jpql, Long.class).getSingleResult();
//           System.out.println(cntJobid);

           // 페이징 - 직책으로 정렬 후 3페이지 조회 (페이지 당 출력건수 15)
           // setFirstResult(startpos): 페이징 시작 위치
           // setMaxResult(getdatecnt): 조회할 데이터 수
           jpql = "select e from Employee e order by jobid";
//           List<Employees> pemps = em.createQuery(jpql, Employees.class)
//                   .setFirstResult(30).setMaxResults(15).getResultList();
//
//           for(Employees emp : pemps)
//                System.out.println(emp);

           // 직책별 평균 연봉, 사원수 조회
           jpql = "select e.jobid, avg(sal), count(jobid) from Employee e group by jobid";
//           List<Object[]> avgEmps = em.createQuery(jpql, Object[].class).getResultList();
//
//           for(Object[] avgEmp: avgEmps)
//               System.out.println(avgEmp[0] + "/" + avgEmp[1] + "/" + avgEmp[2]);

            // 사원이름, 직책, 부서명 조회 : join
            jpql = "select e.fname, e.jobid, e.deptid, d.dname from Employee e inner join e.department d";
            List<Object[]> items = em.createQuery(jpql).getResultList();

            for (Object[] item : items)
                System.out.println(item[0] + "/" + item[1] + "/" + item[2]);

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
