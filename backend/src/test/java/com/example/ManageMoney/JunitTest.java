package com.example.ManageMoney;

import com.example.ManageMoney.common.JunitCommon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@MockBeanを使用する場合は必須のアノテーション
@SpringBootTest
public class JunitTest {

    //JunitCommonのモックをSpringのテストコンテキスト内でのみ機能するように設定
    @MockBean
    JunitCommon junitCommon;
    @Test
    @DisplayName("文字列の反転 モックオブジェクトを使ったテスト練習 by Mockit")
    public void testReverse() {
        //モックのふるまいを設定：reverseメソッドが"hello"を受け取ったら、"olleh"を返す
        when(junitCommon.reverse("hello")).thenReturn("olleh");

        // テスト対象のメソッドを実行し、モックを使用→結果の検証
        assertEquals("olleh", junitCommon.reverse("hello"));
    }
}