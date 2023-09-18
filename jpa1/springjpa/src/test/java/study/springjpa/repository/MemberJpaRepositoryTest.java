package study.springjpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.springjpa.entity.Member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Transactional
    @Test
    public void testMember(){
        Member member = new Member("memberA");


        Member save = memberJpaRepository.save(member);

        Member member1 = memberJpaRepository.find(save.getId());

        System.out.println("member1 = " + member1.getUsername());
        assertThat(member1.getId()).isEqualTo(save.getId());
        assertThat(member1.getUsername()).isEqualTo(save.getUsername());


    }

}