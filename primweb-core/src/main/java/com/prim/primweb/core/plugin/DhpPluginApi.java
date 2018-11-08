package com.prim.primweb.core.plugin;

import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.prim.primweb.core.bridge.CompletionHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 此处为 Demo
 */
public class DhpPluginApi {

    Gson gson = new Gson();

    // JS调用JAVA的同步API
    @JavascriptInterface
    public Object callSync(Object param)  {
        DhpPluginParam dhpPluginParam = gson.fromJson(gson.toJson(param), DhpPluginParam.class);
        DhpPlugin dhpPlugin = DhpPluginManager.shareInstance().getDhpPluginByName(dhpPluginParam.getClassTag());

        // 同步调用此方法
        try {
            Method method = dhpPlugin.getClazz().getMethod(dhpPluginParam.getMethodTag(), DhpPluginParam.class ,DhpPlugin.class);
            method.setAccessible(true);
            return method.invoke(dhpPlugin.getPluginObject(), dhpPluginParam, dhpPlugin);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //访问失败
        return DhpPluginResponse.generateFailData(-10001, "接口访问失败");
    }

    //异步API
    @JavascriptInterface
    public void callAsync(Object param, CompletionHandler handler) {

        DhpPluginParam dhpPluginParam = gson.fromJson(gson.toJson(param), DhpPluginParam.class);
        DhpPlugin dhpPlugin = DhpPluginManager.shareInstance().getDhpPluginByName(dhpPluginParam.getClassTag());
        dhpPluginParam.setCompletionHandler(handler);
        // 同步调用此方法
        try {
            Method method = dhpPlugin.getClazz().getMethod(dhpPluginParam.getMethodTag(), DhpPluginParam.class ,DhpPlugin.class);
            method.setAccessible(true);
            method.invoke(dhpPlugin.getPluginObject(), dhpPluginParam, dhpPlugin);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        handler.complete(DhpPluginResponse.generateFailData(-10001, "接口访问失败"));

    }

}
