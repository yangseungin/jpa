package com.giantdwarf.jpa;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    //엔티티 매니저 생성
    @PersistenceContext
    EntityManager em ;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //트랜잭션 획득
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("승인");
        member.setAge(28);

        String id2 = "id2";
        Member member2 = new Member();
        member2.setId(id2);
        member2.setUsername("재혁이");
        member2.setAge(24);


        String id3 = "id3";
        Member member3 = new Member();
        member3.setId(id3);
        member3.setUsername("잼민이");
        member3.setAge(14);

        //등록
        em.persist(member);
        em.persist(member2);
        em.persist(member3);

        //수정
        member.setAge(20);

        //한 건 조회
        com.giantdwarf.jpa.Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());


        com.giantdwarf.jpa.Member findMember2 = em.find(Member.class, id2);
        System.out.println("findMember=" + findMember2.getUsername() + ", age=" + findMember2.getAge());

        com.giantdwarf.jpa.Member findMember3 = em.find(Member.class, id3);
        System.out.println("findMember=" + findMember3.getUsername() + ", age=" + findMember3.getAge());

        //목록 조회
        List<Member> members = em.createQuery("select m from Member m",Member.class).getResultList();
        System.out.println("members.size=" + members.size());

        //삭제
        em.remove(member);
    }
}
