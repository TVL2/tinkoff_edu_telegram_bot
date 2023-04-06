package parsers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.tinkoff.edu.java.parser.parsers.StackOverflowLinkParser;
import ru.tinkoff.edu.java.parser.responses.StackOverflowResponse;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackOverflowLinkParserTest {
    StackOverflowLinkParser parser = new StackOverflowLinkParser();

    @ParameterizedTest
    @MethodSource("Arguments")
    public void parseLinkTest(String link, StackOverflowResponse response) {
        assertEquals(parser.parseLink(link), response);
    }

    public static Stream<Arguments> Arguments() {
        return Stream.of(
                Arguments.of("https://github.com/sanyarnd/tinkoff-java-course-2022/", null),
                Arguments.of("https://github.com/sanyarnd/tinkoff-java-course-2022/tree/main/01", null),
                Arguments.of("https://github.com/sanyarnd/", null),
                Arguments.of("https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c",
                        new StackOverflowResponse(1642028)),
                Arguments.of("https://stackoverflow.com/questions/164202g/what-is-the-operator-in-c", null),
                Arguments.of("https://stackoverflow.com/1642028/what-is-the-operator-in-c", null),
                Arguments.of("https://stackoverflow.com/questions/1642028", null),
                Arguments.of("https://stackoverflow.com/search?q=unsupported%20link", null)
        );

    }
}
