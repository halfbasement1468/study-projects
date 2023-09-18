package com.example.jpa2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;


    public Long save(Member member) {
        em.persist(member);
        return member.getId(); // 사이드이펙트때문에 보통 안넘기는데 ID정도는 넘겨준다
    }

    public Member find(Long id){
        return em.find(Member.class,id);
    }
}
