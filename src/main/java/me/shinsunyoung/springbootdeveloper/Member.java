package me.shinsunyoung.springbootdeveloper;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    /*
    setter 대신 객체의 책임과 의도를 드러내는 행위 중심 메서드를 제공하여, 외부에서 상태를 변경하도록 설계합니다.
    추가적인 조건 부여 가능.
     */
    public void changeName(String name) {
        this.name = name;

    }
}
