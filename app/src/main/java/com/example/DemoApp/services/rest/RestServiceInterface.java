package com.example.DemoApp.services.rest;

import com.example.DemoApp.models.GitHubProjectsListModel;

import java.util.List;

public interface RestServiceInterface {

    public void fetchGitHubProjectLicenseModel(GitHubProjectsListModel gitHubProjectLicenseModelList);
    public void fetchGitHubProjectLicenseModelError(String error);

}
