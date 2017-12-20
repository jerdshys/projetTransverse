package sql.Repositories;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import sql.Models.Server;
import sql.ServerRepoInterface;

/**
 * Created by jerome on 19/12/2017.
 */

public class HttpServerRepository implements ServerRepoInterface {

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
