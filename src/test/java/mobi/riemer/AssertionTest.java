package mobi.riemer;


import org.junit.jupiter.api.*;

import java.util.stream.Stream;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class AssertionTest {

    // JUnit 4 @BeforeClass
    @BeforeAll
    static void tearUp() {
        System.out.println("tearUp!");
    }

    // JUnit 4 @AfterClass
    @AfterAll
    static void tearDown() {
        System.out.println("tearDown!");
    }

    // JUnit 4 @Before
    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach!");
    }

    // JUnit 4 @After
    @AfterEach
    void afterEach() {
        System.out.println("afterEach!");
    }

    @Test
    void simpleTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    void simpleTest2() {
        assertNotEquals(5, 2 + 2, () -> "Result should be 5");
    }

    @Test
    void testAssertTimeout() {
        assertTimeout(ofSeconds(10),
                () -> {
                    Thread.sleep(9000);
                });
    }

    @Test
    void testAssertTimeoutPreemptively() {
        assertTimeoutPreemptively(ofSeconds(10),
                () -> {
                    Thread.sleep(9000);
                });
    }

    @Test
    void shouldAssertAllAssertions() {
        assertAll(
                () -> assertEquals(2, 1 + 1),
                () -> assertEquals(4, 2 + 2)
        );
    }

    @Test
    @DisplayName("Should validate that the sum of 2 + 2 equals 4")
    void shouldCheckSumOfCalculation() {
        assertEquals(4, 2 + 2);
    }

    // JUnit 4 @Ignore
    @Disabled
    void shouldCheckSum() {
        assertEquals(4, 2 + 2);
    }

    @Nested
    @DisplayName("Inner Class")
    class InnerClass {

        @BeforeEach
        void beforeEach() {
            System.out.println("beforeEach innerClass!");
        }

        @Test
        @DisplayName("Inner Test")
        void innerClassTest() {
            assertEquals(4, 2 + 2);
        }
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestsFromStream() {
        return Stream.of(1, 2, 3)
                .map(i -> dynamicTest("test " + i,
                        () -> assertEquals(i * 2, i + i)));
    }
}
