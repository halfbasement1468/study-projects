package org.example;

import org.example.jpql.Member;
import org.example.jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //하나만 생성해서 애플리케이션 전체 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");//xml파일의 이름


        //엔티티 매니저는 쓰레드간 공유 절대 X 사용후 CLOSE
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();



        //JPA에서 모든 데이터 변경은 트랜잭션 안에서 실행
        tx.begin();


//
        try{

            Team teamA= new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member memberA = new Member();
            memberA.setUsername("회원1");
            memberA.changeTeam(teamA);
            em.persist(memberA);



            Member memberB = new Member();
            memberB.setUsername("회원2");
            memberB.changeTeam(teamA);
            em.persist(memberB);

            Member memberC = new Member();
            memberC.setUsername("회원3");
            memberC.changeTeam(teamB);
            em.persist(memberC);

            Member memberD = new Member();
            memberD.setUsername("회원4");
            em.persist(memberD);


            em.flush();
            em.clear();



                String jpql = "update Member m set m.age = 20";
            int i = em.createQuery(jpql).executeUpdate();

            System.out.println("i = " + i);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }


        //code
        em.close();
        emf.close();

    }
}