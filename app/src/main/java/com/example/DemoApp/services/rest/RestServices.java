package com.example.DemoApp.services.rest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.DemoApp.models.GitHubProjectsListModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RestServices {

    private RestServiceInterface restServiceInterface;
    private Context appContext;
    private final String endPoint = "https://api.github.com/search/repositories?q=stars:%3E=10000+language:kotlin&sort=stars&order=desc";
    private Gson gson =  new GsonBuilder().create();

    public RestServices(RestServiceInterface restServiceInterface, Context appContext) {
        this.restServiceInterface = restServiceInterface;
        this.appContext = appContext;
    }

    public void getKotlinRepositories() {
        RequestQueue queue = Volley.newRequestQueue(this.appContext);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, this.endPoint,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            GitHubProjectsListModel responseModel = gson.fromJson(response, GitHubProjectsListModel.class);
                            restServiceInterface.fetchGitHubProjectLicenseModel(responseModel);
                        } catch (Exception e) {
                            restServiceInterface.fetchGitHubProjectLicenseModelError(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                restServiceInterface.fetchGitHubProjectLicenseModelError(error.getMessage());
            }
        });

        queue.add(stringRequest);

    }

}
