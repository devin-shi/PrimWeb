package com.prim.primweb.core.plugin;

import com.prim.primweb.core.bridge.CompletionHandler;

import java.util.HashMap;

public class DhpPluginParam {

    String classTag ;
    String methodTag ;
    HashMap data ;
    CompletionHandler completionHandler ;
    String htmlUrl;

    public String getClassTag() {
        return classTag;
    }

    public void setClassTag(String classTag) {
        this.classTag = classTag;
    }

    public String getMethodTag() {
        return methodTag;
    }

    public void setMethodTag(String methodTag) {
        this.methodTag = methodTag;
    }

    public HashMap getData() {
        return data;
    }

    public void setData(HashMap data) {
        this.data = data;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public CompletionHandler getCompletionHandler() {
        return completionHandler;
    }

    public void setCompletionHandler(CompletionHandler completionHandler) {
        this.completionHandler = completionHandler;
    }
}
