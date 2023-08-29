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


            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

        /*    List<Member> resultList = em.createQuery("select m from Member m where m.username = :name", Member.class)
                    .setParameter("name", "member1")
                    .getResultList();
*/

            String query ="select m from Member m where m.team.name = :teamName";

            String query2 ="select m from Member m join m.team t where t.name = :teamName";

            List<Member> resultList = em.createQuery(query, Member.class)
                    .setParameter("teamName","teamA")
                    .getResultList();

            for (Member s : resultList) {
                System.out.println("팀 접근 before mebmer.username " + s.getUsername());
                System.out.println("팀접근 = " + s.getTeam().getName());

            }


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