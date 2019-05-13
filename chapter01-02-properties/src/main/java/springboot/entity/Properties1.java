package springboot.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
@ConfigurationProperties(prefix = "com.louis")
public class Properties1 {
    private String name;
    private Map user;
    private List<String> type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map getUser() {
        return user;
    }

    public void setUser(Map user) {
        this.user = user;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PropertiesEntity{" +
                "name='" + name + '\'' +
                ", user=" + user +
                ", type=" + type +
                '}';
    }
}


