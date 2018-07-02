package mobi.riemer;

import org.junit.*;

import static org.junit.Assert.*;

public class JUnit4Test {

    @BeforeClass
    public static void tearUp() {
        System.out.println("tearUp!");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("tearDown!");
    }

    @Before
    public void before() {
        System.out.println("before!");
    }

    @After
    public void after() {
        System.out.println("after!");
    }

    @Test
    public void simpleTest() {
        assertEquals(2, 1+1);
    }
}
