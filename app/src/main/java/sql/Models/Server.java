package sql.Models;

/**
 * Created by jerome on 31/10/2017.
 */
public class Server {
    private String title;
    private String description;
    private int id;

    public Server(String name, String description) {
        this.title = name;
        this.description = description;
    }

    public int getId() {
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
        return this.title+" "+this.description;
    }

}
