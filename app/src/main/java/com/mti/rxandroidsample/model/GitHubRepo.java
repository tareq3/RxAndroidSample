/*
 * Created by Tareq Islam on 3/1/19 4:10 PM
 *
 *  Last modified 3/1/19 12:54 PM
 */

package com.mti.rxandroidsample.model;


public class GitHubRepo {
    public final int id;
    public final String name;
    public final String htmlUrl;
    public final String description;
    public final String language;
    public final int stargazersCount;

    public GitHubRepo(int id, String name, String htmlUrl, String description, String language, int stargazersCount) {
        this.id = id;
        this.name = name;
        this.htmlUrl = htmlUrl;
        this.description = description;
        this.language = language;
        this.stargazersCount = stargazersCount;
    }
}
