package com.example.app; // {{ groupId}}.app// import the rest service you created!
 import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
 import com.example.rest.HelloRestService;

public class HelloApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    public HelloApplication() {
        // Register our hello service
        singletons.add(new HelloRestService());
    }    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}