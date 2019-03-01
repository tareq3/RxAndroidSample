/*
 * Created by Tareq Islam on 3/1/19 4:10 PM
 *
 *  Last modified 3/1/19 12:54 PM
 */

package com.mti.rxandroidsample.repository;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mti.rxandroidsample.model.GitHubRepo;
import android.support.annotation.NonNull;
import java.util.List;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubClient {

    private static final String GITHUB_BASE_URL = "https://api.github.com/";

     private static Retrofit retrofit=null;



    public static Retrofit getClient() {
        if (retrofit == null) {
            final Gson gson =
                    new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

            retrofit =  new Retrofit.Builder().baseUrl(GITHUB_BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }




}


