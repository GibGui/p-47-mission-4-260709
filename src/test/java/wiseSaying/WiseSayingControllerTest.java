package wiseSaying;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingControllerTest {

    @Test
    @DisplayName("'== 명언 앱 ==' 출력")
    void t1() {

        // 이게 테스트용 스캐너였구나.
        // 종료를 넣은 스캐너를 써서 app에 넘겨주어 종료를 출력해야하기 때문에
        // 이 sc라는 스캐너를 앱에 넘겨준것이다.
        //그럼 저 앱을 받은 스캐너는 명언 앱 출력하고, 종료도 출력해야하네
        //근데 왜 이 테스트 케이스에서는 종료를 판단하지않나?
        //아 종료는 내가 주는 거기때문에, 출력이랑은 관께없음
        String out = AppTestRunner.run("종료") ;


        assertThat(out).contains("== 명언 앱 ==");


    }
    @Test
    @DisplayName("등록")
    void t2() {

        // 이게 테스트용 스캐너였구나.
        // 종료를 넣은 스캐너를 써서 app에 넘겨주어 종료를 출력해야하기 때문에
        // 이 sc라는 스캐너를 앱에 넘겨준것이다.
        //그럼 저 앱을 받은 스캐너는 명언 앱 출력하고, 종료도 출력해야하네
        //근데 왜 이 테스트 케이스에서는 종료를 판단하지않나?
        //아 종료는 내가 주는 거기때문에, 출력이랑은 관께없음
        String out = AppTestRunner.run("""
                등록
                명언
                작가
                """);


        assertThat(out).contains("명령) : ");
        assertThat(out).contains("명언 : ");
        assertThat(out).contains("작가 : ");


    }
    @Test
    @DisplayName("생성된 명언번호 노출")
    void t3(){


        String out = AppTestRunner.run("""
                등록
                명언
                작가
                """);


        assertThat(out).contains("1번 명언이 등록되었습니다.");





    }
    @Test
    @DisplayName("생성할때마다 명언번호 증가")
    void t4(){


        String out = AppTestRunner.run("""
                등록
                명언
                작가
                등록
                명언
                작가
                """);


        assertThat(out).contains("2번 명언이 등록되었습니다.");





    }
    @Test
    @DisplayName("생성할때마다 명언번호 증가")
    void t5(){


        String out = AppTestRunner.run("""
                등록
                명언
                작가
                등록
                명언
                작가
                등록
                명언
                작가
                """);


        assertThat(out).contains("3번 명언이 등록되었습니다.");





    }
    @Test
    @DisplayName("목록 조회")
    void t6(){


        String out = AppTestRunner.run("""
                등록
                명언
                작가
                목록
                """);


        assertThat(out).contains("번호 / 작가 / 명언");
        assertThat(out).contains("-------------------");

    }
    @Test
    @DisplayName("목록 조회")
    void t7(){


        String out = AppTestRunner.run("""
                등록
                명언
                작가
                목록
                """);


        assertThat(out).contains("번호 / 작가 / 명언");
        assertThat(out).contains("-------------------");
        assertThat(out).contains("1 / 작가 / 명언");


    }
    @Test
    @DisplayName("목록")
    void t8() {
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                """);

        assertThat(out)
                .contains("번호 / 작가 / 명언")
                .contains("-------------------")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.");

    }
    @Test
    @DisplayName("삭제?id=1")
    void t9() {
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                삭제?id=1
                목록
                """);

        System.out.println(out);

        assertThat(out)
                .contains("1번 명언이 삭제되었습니다.")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.");

    }
    @Test
    @DisplayName("삭제?id=1 두번 요청에 대한 예외 처리")
    void t10() {
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                삭제?id=1
                삭제?id=1
                """);

        assertThat(out)
                .contains("1번 명언은 존재하지 않습니다.");

    }
    @Test
    @DisplayName("수정id=3, 없는 명언에 대한 수정 요청")
    void t11() {
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                수정?id=3
                """);

        assertThat(out)
                .contains("3번 명언은 존재하지 않습니다.");

    }
    @Test
    @DisplayName("수정id=1")
    void t12() {
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                수정?id=1
                너 자신을 알라
                소크라테스
                목록
                """);

        assertThat(out)
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.")
                .contains("1 / 소크라테스 / 너 자신을 알라")
                .contains("명언(기존) : 현재를 사랑하라.")
                .contains("작가(기존) : 작자미상");

    }
    @Test
    @DisplayName("목록?keywordType=content&keyword=과거")
    void t13() {
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록?keywordType=content&keyword=과거
                """);

        assertThat(out)
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.");
    }

    @Test
    @DisplayName("목록?keywordType=author&keyword=안녕")
    void t14() {
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록?keywordType=author&keyword=안녕
                """);

        assertThat(out)
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.")
                .doesNotContain("2 / 작자미상 / 과거에 집착하지 마라.");
    }
    @Test
    @DisplayName("목록: 한 페이지에 최신 명언 5개 출력")
    void t15() throws IOException {

        String input = IntStream.rangeClosed(1, 10)
                .mapToObj(num -> """
                        등록
                        명언 %d
                        작가 %d
                        """.formatted(num, num))
                .collect(Collectors.joining("\n"));

        input += "목록\n";

        String out = AppTestRunner.run(input);

        System.out.println(out);
        assertThat(out)
                .contains("10 / 작가 10 / 명언 10")
                .contains("9 / 작가 9 / 명언 9")
                .contains("8 / 작가 8 / 명언 8")
                .contains("7 / 작가 7 / 명언 7")
                .contains("6 / 작가 6 / 명언 6")
                .doesNotContain("5 / 작가 5 / 명언 5")
                .doesNotContain("4 / 작가 4 / 명언 4")
                .doesNotContain("3 / 작가 3 / 명언 3")
                .doesNotContain("2 / 작가 2 / 명언 2")
                .doesNotContain("1 / 작가 1 / 명언 1");


    }
    @Test
    @DisplayName("목록: 사용자가 페이지당 개수 선택 가능하도록. 목록?pageSize=3")
    void t16() throws IOException {

        String input = IntStream.rangeClosed(1, 10)
                .mapToObj(num -> """
                        등록
                        명언 %d
                        작가 %d
                        """.formatted(num, num))
                .collect(Collectors.joining("\n"));

        input += "목록?pageSize=3\n";

        String out = AppTestRunner.run(input);

        System.out.println(out);
        assertThat(out)
                .contains("10 / 작가 10 / 명언 10")
                .contains("9 / 작가 9 / 명언 9")
                .contains("8 / 작가 8 / 명언 8")
                .doesNotContain("7 / 작가 7 / 명언 7")
                .doesNotContain("6 / 작가 6 / 명언 6")
                .doesNotContain("5 / 작가 5 / 명언 5")
                .doesNotContain("4 / 작가 4 / 명언 4")
                .doesNotContain("3 / 작가 3 / 명언 3")
                .doesNotContain("2 / 작가 2 / 명언 2")
                .doesNotContain("1 / 작가 1 / 명언 1");


    }
    @Test
    @DisplayName("목록?page=2")
    void t17() {

        String input = IntStream.rangeClosed(1, 10)
                .mapToObj(num -> """
                        등록
                        명언 %d
                        작가 %d
                        """.formatted(num, num))
                .collect(Collectors.joining("\n"));

        input += "목록?page=2\n";

        String out = AppTestRunner.run(input);

        assertThat(out)
                .doesNotContain("10 / 작가 10 / 명언 10")
                .doesNotContain("9 / 작가 9 / 명언 9")
                .doesNotContain("8 / 작가 8 / 명언 8")
                .doesNotContain("7 / 작가 7 / 명언 7")
                .doesNotContain("6 / 작가 6 / 명언 6")
                .contains("5 / 작가 5 / 명언 5")
                .contains("4 / 작가 4 / 명언 4")
                .contains("3 / 작가 3 / 명언 3")
                .contains("2 / 작가 2 / 명언 2")
                .contains("1 / 작가 1 / 명언 1");
    }





}