package mobi.riemer;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class TestcontainersExtension implements BeforeAllCallback, AfterAllCallback {


    private List<Field> allContainers;

    private List<Field> findAllContainers(ExtensionContext context) {
        Class<?> clazz = context.getTestClass().get();
        Field[] declaredFields = clazz.getDeclaredFields();
        return Arrays.stream(declaredFields).filter(f -> GenericContainer.class.isAssignableFrom(f.getType())).collect(toList());
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        allContainers = findAllContainers(context);
        GenericContainer container = new GenericContainer();
        for (Field f : allContainers) {
            f.setAccessible(true);
            ((GenericContainer) f.get(container)).start();
        }
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        GenericContainer container = new GenericContainer();
        // sort class names of testcontainer types in reverse order to stop VNC container first
        allContainers.sort((field1, field2) -> field2.getType().getName().compareTo(field1.getType().getName()));
        for (Field f : allContainers) {
            f.setAccessible(true);
            ((GenericContainer) f.get(container)).stop();
        }
    }
}
