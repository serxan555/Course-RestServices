package az.services;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath(value="services")
public class ApplicationConfig extends Application {

    public Set<Class<?>> getClasses(){
        Set set = new HashSet();
        set.add(StudentServices.class);
        return set;
    }
}
