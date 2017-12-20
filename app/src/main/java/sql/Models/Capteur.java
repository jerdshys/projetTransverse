package sql.Models;

/**
 * Created by jerome on 20/12/2017.
 */

public class Capteur {

    private String name;
    private String id;

    public Capteur(String id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }


}
