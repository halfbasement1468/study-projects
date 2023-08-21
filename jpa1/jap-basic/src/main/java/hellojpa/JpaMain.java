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



        try{

            Child child1 = new Child();
            Child child2 = new Child();

            child1.setName("자식1");
            child2.setName("자식2");


            Parent parent = new Parent();
            parent.setName("아빠");


            parent.addChild(child1);
            parent.addChild(child2);


            em.persist(parent);

            em.flush();
            em.clear();


            Parent parent1 = em.find(Parent.class, parent.getId());


            System.out.println("parent = " + parent1.getChildList().remove(0) );

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
    private static void pM(Member member){
        String username = member.getUsername();

        System.out.println("username = " + username);

    }
    private static void memberAndTeam(Member member){
        String username = member.getUsername();

        System.out.println("username = " + username);
        Team team = member.getTeam();

        System.out.println("team = " + team);

    }
}
