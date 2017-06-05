package com.icsax.eagleally;

/**
 * Created by icsax on 5/25/2017.
 */

public class Problem {
    String id;
    String status; //Completed, in progress, To do
    String problem;
    String name;

    public String getProblem() {

        return problem;
    }

    public void setProblem(String p){

        problem = p;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String s){

        status = s;
    }

    public String getName() {

        return name;
    }

    public void setName(String n){
        name = n;
    }

    public String getId() {

        return id;
    }

    public void setId(String i){

        id = i;
    }
}
