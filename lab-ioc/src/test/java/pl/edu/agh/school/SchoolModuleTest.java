package pl.edu.agh.school;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;
import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.guice.SchooleModule;

import static org.junit.jupiter.api.Assertions.assertSame;

public class SchoolModuleTest {

    @Test
    public void isLoggerSingleton() {
        Injector i = Guice.createInjector(new SchooleModule());

        Logger l1 = i.getInstance(Logger.class);
        Logger l2 = i.getInstance(Logger.class);

        assertSame(l1,l2);
    }
}
