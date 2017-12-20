package sql.Repositories;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sql.CapteurRepoInterface;
import sql.Models.Server;
import sql.ServerRepoInterface;

/**
 * Created by jerome on 19/12/2017.
 */

public class HttpCapteurRepository implements CapteurRepoInterface {

    private RequestQueue queue;
    private String path;

    public HttpCapteurRepository(Context context) {
        queue = Volley.newRequestQueue(context.getApplicationContext());
        path = "https://serveurprojet.herokuapp.com/api";
    }

    @Override
    public ArrayList<Server> getAll() {
        String url = path+"/capteurs";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

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
    public void post(final String name) {
        String url = path+"/capteurs";

        url = "http://httpbin.org/post";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                       // Log.d("Error.Response", response);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", name);

                return params;
            }
        };
        queue.add(postRequest);

    }

    @Override
    public void delete(Long id) {

    }
}
