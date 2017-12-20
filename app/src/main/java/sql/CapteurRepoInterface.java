package sql;

import java.util.ArrayList;

import sql.Models.Capteur;
import sql.Models.Server;

/**
 * Created by jerome on 20/11/2017.
 */

public interface CapteurRepoInterface {

    public void getAll(final VolleyCallback callback);
    public Server get(String id);
    public void put(String id, String name);
    public void post(String name);
    public void delete(String id);

}
