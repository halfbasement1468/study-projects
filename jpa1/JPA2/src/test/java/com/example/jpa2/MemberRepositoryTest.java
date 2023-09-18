package com.example.jpa2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Test
    @Transactional// 테스트에 트랜잭셔널 있으면 롤백해버림
    @Rollback(value = false)
    void save() {

        //g
        Member member = new Member();
        member.setUsername("memberA");

        //w
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //t
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
    }

    @Test
    void find() {
    }
}