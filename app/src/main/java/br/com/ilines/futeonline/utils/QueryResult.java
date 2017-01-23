package br.com.ilines.futeonline.utils;

import com.google.gson.Gson;

import br.com.ilines.futeonline.objects.Notify;

public class QueryResult {

    private Integer status;
    private Integer result;
    private Object object;

    public QueryResult() {
        this.status = null;
        this.result = null;
        this.object = null;
    }

    public QueryResult(Integer status, Integer result, Object object) {
        this.status = status;
        this.result = result;
        this.object = object;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public static QueryResult load(String result) {
        QueryResult queryResult;
        try {
            queryResult = (QueryResult) new Gson().fromJson(result, QueryResult.class);
        } catch (Exception e) {
            return null;
        }
        return queryResult;
    }

}