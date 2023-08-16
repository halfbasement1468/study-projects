package jpabook;

import jpabook.jpashop.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("유정식");

            em.persist(book);
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