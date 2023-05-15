package jpa;

import model.Employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJPA01 {
    public static void main(String[] args) {
        // Persistence: 객체를 데이터베이스에 저장하고 관리하는 프로세스.

        // jpa 객체 초기화: emf -> em -> etx
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
