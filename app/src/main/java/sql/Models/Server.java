package sql.Models;

/**
 * Created by jerome on 31/10/2017.
 */
public class Server {
    private String title;
    private String description;
    private Long id;

    public Server(Long id,String name, String description) {
        this.id = id;
        this.title = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return this.id+" "+this.title+" "+this.description;
    }

}
