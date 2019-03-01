/*
 * Created by Tareq Islam on 3/1/19 4:10 PM
 *
 *  Last modified 3/1/19 12:54 PM
 */

package com.mti.rxandroidsample.repository;


import com.mti.rxandroidsample.model.GitHubRepo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("users/{user}/starred")
    io.reactivex.Observable<List<GitHubRepo>> getStarredRepositories(@Path("user") String userName);

}
