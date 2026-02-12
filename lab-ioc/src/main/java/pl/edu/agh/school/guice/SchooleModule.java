package pl.edu.agh.school.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import pl.edu.agh.logger.FileMessageSerializer;
import pl.edu.agh.logger.IMessageSerializer;
import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.persistence.PersistanceManager;
import pl.edu.agh.school.persistence.SerializablePersistenceManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SchooleModule extends AbstractModule {

    @Override
    public void configure() {
        bind(PersistanceManager.class).to(SerializablePersistenceManager.class);
        bindConstant().annotatedWith(Names.named("teacherPath")).to("teachersSet.dat");
        bindConstant().annotatedWith(Names.named("classPath")).to("classesSet.dat");
    }
    @Provides
    @Singleton
    public Logger providesLogger() {
        Logger logger = new Logger(new HashSet<>(List.of(new FileMessageSerializer("persistence.log"))));
        return logger;
    }
}
