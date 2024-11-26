package me.shinsunyoung.springbootdeveloper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {


    @Autowired
    MemberRepository memberRepository;

    @Sql("/insert-members.sql")
    @Test
    void getAllMembers() {
        List<Member> members = memberRepository.findAll();

        assertThat(members.size()).isEqualTo(3);
    }


    @DisplayName("id가 2인 멤버 찾기")
    @Sql("/insert-members.sql")
    @Test
    void getMemberById() {
        Member member = memberRepository.findById(2L).get();

        assertThat(member.getName()).isEqualTo("B");
    }

    @DisplayName("MemberRepository에 추가한 findByName() 테스트")
    @Sql("/insert-members.sql")
    @Test
    void getMemberByName() {
        Member member = memberRepository.findByName("C").get();

        assertThat(member.getId()).isEqualTo(3);
    }

    @DisplayName("JPA save() 메서드")
    @Test
    void saveMember() {
        Member member = new Member(1L, "A");

        memberRepository.save(member);
        assertThat(memberRepository.findById(1L).get().getName()).isEqualTo("A");
    }

    @DisplayName("JPA saveAll() 메서드")
    @Test
    void saveMembers() {
        List<Member> members = List.of(new Member(1L, "A"), new Member(2L, "B"), new Member(3L, "C"));

        memberRepository.saveAll(members);

        assertThat(memberRepository.findAll().size()).isEqualTo(3);
    }

    @DisplayName("JPA deleteById() 메서드 사용해 2번째 멤버 삭제하기")
    @Sql("/insert-members.sql")
    @Test
    void deleteMemberById() {
        memberRepository.deleteById(2L);

        assertThat(memberRepository.findById(2L).isEmpty()).isTrue();
    }

    @DisplayName("JPA deleteAll()")
    @Sql("/insert-members.sql")
    @Test
    void deleteAll() {
        memberRepository.deleteAll();

        assertThat(memberRepository.findAll().isEmpty()).isTrue();
    }

    /*
    id 2인 멤버의 이름 "BC"로 변경
     */
    @DisplayName("Member 클래스에 추가한 changeName() 메서드")
    @Sql("/insert-members.sql")
    @Test
    void update() {
        Member member = memberRepository.findById(2L).get();

        member.changeName("BC");

        assertThat(memberRepository.findById(2L).get().getName()).isEqualTo("BC");
    }

    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }
}