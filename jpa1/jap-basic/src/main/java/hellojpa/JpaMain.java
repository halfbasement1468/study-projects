package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
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

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("city1","street","1000"));

            member.getFavriteFoods().add("치킨");
            member.getFavriteFoods().add("피자");
            member.getFavriteFoods().add("족발");

            member.getAddressHistory().add(new Address("old1","street","1000"));


            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("------------");
            Member member1 = em.find(Member.class, member.getId());


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
