package tests;

import models.member.BadRequestException;
import models.member.Joinservice;
import models.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("회원가입 기능 테스트") // 단위 테스트에 대한 설명
public class JoinServiceTest {
    /*@Test
    void joinSucess() {
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {

            }
        });
    }*/
    private Joinservice service;

    private Member getMember() {
        Member member = new Member();
        member.setUserId("user" + System.currentTimeMillis()); // ID중복방지 난수 발생 메서드 사용
        member.setUserNm("사용자");
        member.setUserPw("12345678");
        member.setConfirmUserPw(member.getUserPw());

        return member;
    }

    @BeforeEach 
    void init() { // 각 단위 테스트 메서드 실행 전 객체 생성
        service = new Joinservice();
    }

    @Test
    @DisplayName("회원가입 성공시 예외가 발생하지 않음")
    void joinSucess() {
        assertDoesNotThrow(() -> {
            service.join(getMember());
        });
    }

    @Test
    @DisplayName("필수 항목(userId, userPw, confirmUserPw, userNm) 검증, 검증 실패시 BadRequestException 발생")
    void requiredFields() {
        // userId가 null, 또는 " "(빈 값)일 때
        assertThrows(BadRequestException.class, () -> {
           Member member = getMember();
           member.setUserId(null);
           service.join(member);

           member.setUserId("    ");
           service.join(member);

        });
    }
}
