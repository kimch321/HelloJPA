package dsl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import model.Employee;
import model.QDepartment;
import model.QEmployee;
import org.apache.commons.lang.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.beans.Expression;
import java.util.List;

public class HelloQueryDSL02 {
    public static <Interger> void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            // 쿼리객체 준비(테이블과 유사)
            QEmployee qemp = QEmployee.employee;
            QDepartment qdept = QDepartment.department;
            JPAQueryFactory query = new JPAQueryFactory(em);

            // 조회 - 전체 사원
            /*List<Employee> emps = query.selectFrom(qemp).fetch();

            for(Employee emp: emps)
                System.out.println(emp);*/

            // 조회 - 일부 사원, 페이징 (offset, limit)
            /*List<Employee> emps = query.selectFrom(qemp).offset(30).limit(15).fetch();

            for(Employee emp: emps)
                System.out.println(emp);*/

            // 사원 데이터 일부 조회 - Query: 이름, 부서번호, 입사일
            /*List<Tuple> items  = query.select(qemp.fname, qemp.deptid, qemp.hdate).from(qemp).fetch();

            for(Tuple item: items)
                System.out.println(item);*/

            // 정렬: orderby, 부서번호 기준
            /*query = new JPAQueryFactory(em);
            List<Employee> emps = query.selectFrom(qemp).orderBy(qemp.deptid.desc()).fetch();

            for(Employee emp: emps)
                System.out.println(emp);*/

            // 조건검색: where, 직책인 IT_PROG인 사원 조회
            /*query = new JPAQueryFactory(em);
            List<Employee> emps = query.selectFrom(qemp).where(qemp.jobid.eq("IT_PROG")).fetch();

            for(Employee emp: emps)
                System.out.println(emp);*/

            // 조건검색: 연봉이 20000이상인 사원 조회
            /*query = new JPAQueryFactory(em);
            List<Employee> emps = query.selectFrom(qemp).where(qemp.sal.goe(24000)).fetch();

            for(Employee emp: emps)
                System.out.println(emp);*/

            // 직책 수 조회 1
            /*query = new JPAQueryFactory(em);
            List<Long> cnt = query.select(qemp.jobid.count()).from(qemp).fetch();

            System.out.println(cnt);*/

            // 직책 수 조회 2
            /*query = new JPAQueryFactory(em);
            Long cnt = query.select(qemp.jobid).from(qemp).fetchCount();

            System.out.println(cnt);*/

            // 직책 수 조회 3
            /*List<Long> cnt = query.select(qemp.jobid.countDistinct()).from(qemp).fetch();

            System.out.println(cnt);*/

            // 직책 수 조회 4
            /*query = new JPAQueryFactory(em);
            Long cnt = query.select(qemp.jobid).distinct().from(qemp).fetchCount();

            System.out.println(cnt);*/

            // 그룹핑: 직책별 최대, 최소, 평균 연봉, 직책수 조회
            /*query = new JPAQueryFactory(em);
            List<Tuple> grouping = query.select(qemp.jobid, qemp.sal.max(), qemp.sal.min(), qemp.sal.min(), qemp.sal.avg(), qemp.jobid.count())
                    .from(qemp).groupBy(qemp.jobid).fetch();

            for(Tuple group: grouping)
                System.out.println(group);*/

            // 그룹핑: 직책별 최대, 최소, 평균 연봉, 직책수 정렬 조회
            /*StringPath jbcnt = Expressions.stringPath("jbcnt");

            List<Tuple> grouping = query.select(qemp.jobid, qemp.sal.max(), qemp.sal.min(),
                            qemp.sal.min(), qemp.sal.avg(), qemp.jobid.count().as("jbcnt"))
                    .from(qemp).groupBy(qemp.jobid).orderBy(jbcnt.desc()).fetch();

            for(Tuple group: grouping)
                System.out.println(group);*/

            // 서브쿼리1: 평균연봉보다 작게 받는 사원들 조회
            /*query = new JPAQueryFactory(em);

            // 서브쿼리 - 평균연봉은?
            JPQLQuery<Double> subqry = JPAExpressions.select(qemp.sal.avg()).from(qemp);

            // 주쿼리 - 사원조회
            List<Employee> emps = query.selectFrom(qemp).where(qemp.sal.lt(subqry)).fetch();

            for(Employee emp: emps)
                System.out.println(emp);*/

            // 서브쿼리2: IT 부서에 근무중인 사원들의 이름, 직책, 급여 조회
            /*query = new JPAQueryFactory(em);

            // 서브쿼리 - IT 부서의 부서번호는?
            JPQLQuery<Long> subqry = JPAExpressions.select(qdept.deptid).from(qdept).where(qdept.dname.eq("IT"));

            // 주쿼리 - 부서번호에 해당하는 사원들의 이름, 직책, 급여 조회
            List<Tuple> emps = query.select(qemp.fname, qemp.jobid, qemp.sal).from(qemp).where(qemp.deptid.eq(subqry)).fetch();

            for(Tuple emp: emps)
                System.out.println(emp);*/

            // join: 부서번호가 60번인 사원들의 이름, 직책, 부서명 조회
            /*query = new JPAQueryFactory(em);
            List<Tuple> items = query.select(qemp.fname,qemp.jobid,qdept.dname)
                    .from(qemp).join(qemp.department,qdept)
                    .where(qemp.deptid.eq(60L))
                    .fetch();

            for(Tuple item: items)
                System.out.println(item);*/

            // 연봉이 10000이상인 사워 조회
            // 직책이 IT_PROG이고 연봉이 6000 이상인 사원 조회
            String fname = "Ste";
            String jobid = null;
            Integer sal = null;

            query = new JPAQueryFactory(em);
            List<Employee> emps = query.selectFrom(qemp)
                    .where(
                            (fname != null) ? qemp.fname.contains(fname) : null,
                            StringUtils.isNotEmpty(jobid) ? qemp.jobid.eq(jobid): null,
                            (sal != null) ? qemp.sal.gt(sal) : null
                    ).fetch();

            for(Employee emp: emps)
                System.out.println(emp);

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
