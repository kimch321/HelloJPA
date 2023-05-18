package hbm;


import model.Department;
import notmap.Empinfo;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HelloHBM04 {
    public static void main(String[] args) {
        // SesstionFactory 초기화

        Configuration cfg = new Configuration().configure();
        SessionFactory sf = cfg.addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();
        Session sess = sf.openSession();

        try {
            // 조회 - 전체 사원
            /*Query query = sess.createQuery("from Employee");
            List<Employee> emps = query.getResultList();

            for(Employee emp: emps)
                System.out.println(emp);*/

            // 조회 - 부분조회1: 이름, 연봉
            /*Query query =sess.createQuery("select fname, sal from Employee ");
            List<Object[]> items = query.getResultList();

            for(Object[] item : items)
            {
                System.out.println(item[0] + "/" + item[1]);
            }*/

            // 조회 - 부분조회2: 이름, 연봉
            /*Query query = sess.createQuery("select new model.Empinfo(fname, sal) from Employee ");
            List<Empinfo> emps = query.getResultList();

            for(Empinfo emp: emps)
                System.out.println(emp);*/

            // 조건검색: where IT직책이 IT_PROG인 사원 조회
            /*Query query = sess.createQuery("from Employee where jobid = ?1" );
            query.setParameter(1, "IT_PROG");
            List<Employee> emps = query.getResultList();

            for(Employee emp: emps)
                System.out.println(emp);*/

            // 조건검색: 연봉이 20000이상인 사원 조회
            /*Query query = sess.createQuery("from Employee where sal >= ?1");
            query.setParameter(1,20000);
            List<Employee> emps = query.getResultList();

            for(Employee emp: emps)
                System.out.println(emp);*/

            // 정렬: orderby, 부서번호 기준
            /*Query query = sess.createQuery("from Employee order by deptid desc");
            List<Employee> emps =query.getResultList();

            for(Employee emp: emps)
                System.out.println(emp);*/

            // 직책 수 조회
            /*String hql = "select count(distinct jobid) from Employee";
            Query query = sess.createQuery(hql);

            List cntjob = query.list();

            System.out.println(cntjob);*/

            // 그룹핑: 직책별 최대, 최소, 평균 연봉, 직책수 조회
            /*String hql = "select jobid, max(sal), min(sal), avg(sal), count(jobid) from Employee group by jobid";
            Query query = sess.createQuery(hql);

            List<Object[]> emps = query.getResultList();

            for(Object[] emp: emps)
                System.out.println(emp[0] + "/" + emp[1] + "/" + emp[2] + "/" + emp[3] + "/" + emp[4]);*/

            // 서브쿼리1: 평균연봉보다 작게 받는 사원들 조회
            /*String hql = "select fname, sal from Employee where sal < (select avg(sal) from Employee)";
            Query query = sess.createQuery(hql);

            List<Object[]> emps = query.getResultList();

            for(Object[] emp: emps)
                System.out.println(emp[0]+"/"+emp[1]);*/

            // join: 부서번호가 60인 사원들의 이름, 직책, 부서명 조회
            String hql = "select e.fname, e.jobid, d.dname from Employee e inner join Department d on e.deptid=d.deptid where e.deptid = 60";
            Query query = sess.createQuery(hql);

            List<Object[]> items = query.getResultList();

            for(Object[] item: items)
                System.out.println(item[0] + "/" + item[1] + "/" + item[2]);


        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            sess.close();
            sf.close();
        }
    }
}
