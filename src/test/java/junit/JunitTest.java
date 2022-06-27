package junit;

import org.junit.jupiter.api.*;

public class JunitTest {

    @BeforeAll
    static void setUp() {
        System.out.println("Запуск @BeforeAll");
    }

    @BeforeEach
    void setUp2() {
        System.out.println("Запуск @BeforeEach");

    }

    @Test
    void test() {
        System.out.println("Запуск @Test1");

    }

    @Test
    void test2() {
        System.out.println("Запуск @Test2");

    }

    @Test
    void test3() {
        System.out.println("Запуск @Test3");

    }

    @Test
    void test4() {
        System.out.println("Запуск @Test4");

    }

    @AfterEach
    void exit() {
        System.out.println("Запуск @AfterEach");

    }

    @AfterAll
    static void exit2() {
        System.out.println("Запуск @AfterAll");

    }
}
