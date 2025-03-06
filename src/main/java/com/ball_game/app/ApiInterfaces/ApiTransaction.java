package com.ball_game.app.ApiInterfaces;

import java.io.IOException;

import java.lang.reflect.Type;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.*;

import com.ball_game.app.ApiInterfaces.errors.*;


public class ApiTransaction<T> {

    private String restCommand;
    private String host;
    private Type typeToCreate; 
    
    private Gson gson = new Gson();
    private CloseableHttpClient client = HttpClientBuilder.create().build();
    private String content_type = "application/json";

    public ApiTransaction(){}

    public ApiTransaction(String restCommand, String uri, Type typeToCreate){
        this.restCommand = restCommand;
        this.host = uri;
        this.typeToCreate = typeToCreate;
    }

    public T execute() throws InvalidRestVerbError{
        T res;
        switch (this.restCommand) {
            case "get":
                res = get();
                break;
        
            case "post":
                res = post();
                break;

            default:
                throw new InvalidRestVerbError(String.format("%s is not a valid rest verb", this.restCommand));
        }
        return res;
    }

    private T get(){
        String return_string = "";
        HttpGet request = new HttpGet(this.host);
        request.addHeader("Content-Type", this.content_type);
        try {
            CloseableHttpResponse response = this.client.execute(request);
            int status = response.getStatusLine().getStatusCode();
            if (status != 200) {
                System.err.println(status);
                throw new ApiResponseError("did not recieve status code 200");
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                return_string = result;
            }

        } catch (IOException e) {
            System.err.println(String.format("\nerror executing command %s", this.host));
        } catch (ApiResponseError e){
            System.err.println(e.getMessage());
            System.err.println(String.format("\nerror %s is not a valid GET url", this.host));
        }
        return gson.fromJson(return_string, this.typeToCreate);
    }

    private T post(){
        return gson.fromJson("", this.typeToCreate);
    }

}
