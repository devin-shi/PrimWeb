package com.prim.primweb.core.plugin;

import com.prim.primweb.core.bridge.CompletionHandler;

public class DhpPlugin {
    String pluginName ;
    String className ;
    String descr ;
    Class clazz ;
    Object pluginObject ;

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Object getPluginObject() {
        return pluginObject;
    }

    public void setPluginObject(Object pluginObject) {
        this.pluginObject = pluginObject;
    }


}
