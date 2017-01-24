package br.com.futeonline.objects;

import java.util.ArrayList;
import java.util.List;


public class Result {

    private String result;
    private List notify;

    public Result() {
        this.result = "0";
        this.notify = new ArrayList();
    }

    public Result(String result, List notify) {
        this.result = result;
        this.notify = notify;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public List getNotify() {
        return notify;
    }

    public void setNotify(List notify) {
        this.notify = notify;
    }

}
