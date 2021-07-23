package hello.itemservice.domain.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Collections;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage() {
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode() {
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeWithDefaultMessage() {
        String defaultMessage = "기본메시지";
        String result = ms.getMessage("no_code", null, defaultMessage, null);
        assertThat(result).isEqualTo(defaultMessage);
    }

    @Test
    void helloNameMessage() {
        String result = ms.getMessage("hello.name", new String[]{"ydh"}, null);
        assertThat(result).isEqualTo("안녕 ydh");
    }

    @Test
    void helloMessageEn() {
        String result = ms.getMessage("hello", null, Locale.US);
        assertThat(result).isEqualTo("hello");
    }
}
