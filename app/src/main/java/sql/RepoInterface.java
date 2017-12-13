package sql;

import java.util.ArrayList;

import sql.Models.Server;

/**
 * Created by jerome on 20/11/2017.
 */

public interface RepoInterface {

    public ArrayList<Server> getAll();
    public Server get(long id);
    public void put(Long id,String name , String description);
    public void post(String name, String description);
    public void delete(Long id);

}
