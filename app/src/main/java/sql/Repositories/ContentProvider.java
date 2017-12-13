package sql.Repositories;

import java.util.ArrayList;

import sql.Models.Server;
import sql.RepoInterface;

/**
 * Created by jerome on 20/11/2017.
 */

public class ContentProvider implements RepoInterface {
    @Override
    public ArrayList<Server> getAll() {
        return null;
    }

    @Override
    public Server get(long id) {
        return null;
    }

    @Override
    public void put(Long id, String name, String description) {

    }

    @Override
    public void post(String name, String description) {
    }

    @Override
    public void delete(Long id) {

    }
}
