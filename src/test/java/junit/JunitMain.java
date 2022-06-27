package junit;

import org.junit.jupiter.api.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class JunitMain {
//    public static void main(String[] args) throws Exception {
//        // Находит классы с тестами
//        Method[] declaredMethods = JunitTest.class.getDeclaredMethods();
//        for (Method method: declaredMethods) {
//            method.setAccessible(true);
//            // Смотрит есть ли над методом @Test
//            Test testAnnotation = method.getAnnotation(Test.class);
//            Disabled disabled = method.getAnnotation(Disabled.class);
//            BeforeEach beforeEachAnnotation = method.getAnnotation(BeforeEach.class);
//            AfterEach afterEachAnnotation = method.getAnnotation(AfterEach.class);
//            BeforeAll beforeAllAnnotation = method.getAnnotation(BeforeAll.class);
//            AfterAll afterAllAnnotation = method.getAnnotation(AfterAll.class);
//
//            if (beforeAllAnnotation != null) {
//                method.invoke(JunitTest.class.getDeclaredConstructor().newInstance());
//            }
//
//            if (beforeEachAnnotation != null) {
//                method.invoke(JunitTest.class.getDeclaredConstructor().newInstance());
//            }
//
//            if (afterEachAnnotation != null) {
//                method.invoke(JunitTest.class.getDeclaredConstructor().newInstance());
//            }
//
//            if (afterAllAnnotation != null) {
//                method.invoke(JunitTest.class.getDeclaredConstructor().newInstance());
//            }
//
//
//            if (testAnnotation != null && disabled == null) {
//                // Запускает
//                try {
//                    method.invoke(JunitTest.class.getDeclaredConstructor().newInstance());
//                    System.out.println("Test Done");
//                } catch (InvocationTargetException e) {
//                    System.out.println("Тест упал: " + e.getCause().getMessage());
//                    throw e;
//                }
//            }
//        }
//    }

    public static void main(String[] args) throws Exception {
        // Находит классы с тестами
        Method[] declaredMethods = JunitTest.class.getDeclaredMethods();
        Method beforeAllMethod = null;
        Method beforeEachMethod = null;
        Method afterAllMethod = null;
        Method afterEachMethod = null;
        Method testAnnotations = null;

        for (Method method: declaredMethods) {
            method.setAccessible(true);
            BeforeAll beforeAll = method.getAnnotation(BeforeAll.class);
            BeforeEach beforeEach = method.getAnnotation(BeforeEach.class);
            AfterEach afterEach = method.getAnnotation(AfterEach.class);
            AfterAll afterAll = method.getAnnotation(AfterAll.class);
            Test test = method.getAnnotation(Test.class);
            if (beforeAll != null) {
                beforeAllMethod = method;
            }
            if (beforeEach != null) {
                beforeEachMethod = method;
            }
            if (afterAll != null) {
                afterAllMethod = method;
            }
            if (afterEach != null) {
                afterEachMethod = method;
            }
            if (test != null) {
                testAnnotations = method;
            }
        }

        if (beforeAllMethod != null && testAnnotations != null) {
            try {
                beforeAllMethod.invoke(JunitTest.class.getDeclaredConstructor().newInstance());
            } catch (InvocationTargetException e) {
                // Если тест упал, сообщает нам
                System.out.println("BeforeAll упал: ");
                throw e;
            }
        }

        for (Method method: declaredMethods) {
            method.setAccessible(true);
            // Смотрит есть ли над методом @Test
            Test testAnnotation = method.getAnnotation(Test.class);
            Disabled disabled = method.getAnnotation(Disabled.class);

            if (testAnnotation == null) {
                continue;
            }

            if (beforeEachMethod != null) {
                try {
                    beforeEachMethod.invoke(JunitTest.class.getDeclaredConstructor().newInstance());
                } catch (InvocationTargetException e) {
                    // Если тест упал, сообщает нам
                    System.out.println("BeforeEach упал: ");
                    throw e;
                }
            }

            if (disabled == null) {
                // Запускает
                try {
                    method.invoke(JunitTest.class.getDeclaredConstructor().newInstance());
                } catch (InvocationTargetException e) {
                    // Если тест упал, сообщает нам
                    System.out.println("Тест упал: ");
                    throw e;
                }
            }

            if (afterEachMethod != null) {
                try {
                    afterEachMethod.invoke(JunitTest.class.getDeclaredConstructor().newInstance());
                } catch (InvocationTargetException e) {
                    // Если тест упал, сообщает нам
                    System.out.println("AfterEach упал: ");
                    throw e;
                }
            }

        }

        if (afterAllMethod != null && testAnnotations != null) {
            try {
                afterAllMethod.invoke(JunitTest.class.getDeclaredConstructor().newInstance());
            } catch (InvocationTargetException e) {
                // Если тест упал, сообщает нам
                System.out.println("AfterAll упал: ");
                throw e;
            }
        }
    }
    // Если не упал, то все ок


//    public static void main(String[] args) {
//        // Находит классы с тестами для AnnotationsExampleTest
//        // todo вынести в массив классов для проверки
//        //todo вернуться и разобрать
//        Arrays.stream(JunitTest.class.getDeclaredMethods())
//                .filter(method -> method.getAnnotation(BeforeAll.class) != null)
//                .forEach(methodBeforeAll -> {
//                    methodBeforeAll.setAccessible(true);
//                    runMethodByClass(methodBeforeAll, JunitTest.class);
//                });
//
//        List<Method> declaredBeforeEachMethods = Arrays.stream(JunitTest.class.getDeclaredMethods())
//                .filter(method -> method.getAnnotation(BeforeEach.class) != null)
//                .collect(Collectors.toList());
//        List<Method> declaredAfterEachMethods = Arrays.stream(JunitTest.class.getDeclaredMethods())
//                .filter(method -> method.getAnnotation(AfterEach.class) != null)
//                .collect(Collectors.toList());
//
//        Arrays.stream(JunitTest.class.getDeclaredMethods())
//                .filter(method -> (method.getAnnotation(Test.class) != null && method.getAnnotation(Disabled.class) == null))
//                .forEach(methodTest -> {
//                    methodTest.setAccessible(true);
//                    runMethodsByClass(declaredBeforeEachMethods, JunitTest.class);
//                    runMethodByClass(methodTest, JunitTest.class);
//                    runMethodsByClass(declaredAfterEachMethods, JunitTest.class);
//                });
//
//        Arrays.stream(JunitTest.class.getDeclaredMethods())
//                .filter(method -> method.getAnnotation(AfterAll.class) != null)
//                .forEach(methodAfterAll -> {
//                    methodAfterAll.setAccessible(true);
//                    runMethodByClass(methodAfterAll, JunitTest.class);
//                });
//    }

    private static void runMethodByClass(Method method, Class<?> clazz) {
        try {
            method.invoke(clazz.getDeclaredConstructor().newInstance());
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) { // Тест упал
            System.out.println("Тест упал: " + e.getCause().getMessage());
        }
    }

    private static void runMethodsByClass(List<Method> methodsToRun, Class<?> clazz) {
        for (Method methodToRun: methodsToRun) {
            methodToRun.setAccessible(true);
            runMethodByClass(methodToRun, clazz);
        }
    }

}
