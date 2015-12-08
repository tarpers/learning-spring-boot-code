package learningspringboot;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Document()
public class Team {

    @Id
    private BigInteger id;

    private String name;

    @DBRef
    private List<Teammate> members;

    private Team() {
        members = new ArrayList<>();
    }

    public Team(String name) {
        this();
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Teammate> getMembers() {
        return members;
    }

    public void setMembers(List<Teammate> members) {
        this.members = members;
    }
}
