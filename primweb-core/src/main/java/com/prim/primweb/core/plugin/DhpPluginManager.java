package com.prim.primweb.core.plugin;

import android.util.Log;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prim.primweb.core.bridge.OnReturnValue;
import com.prim.primweb.core.webview.IAgentWebView;
import com.prim.primweb.core.webview.X5AgentWebView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DhpPluginManager {

    private Map<String, DhpPlugin> pluginMap = new HashMap<>();
    private Gson gson = new Gson();

    private IAgentWebView webView ;

    public static DhpPluginManager dhpPluginManager ;

    private DhpPluginManager() {

    }

    public static void init(IAgentWebView iAgentWebView) {
        if (dhpPluginManager == null) {
            synchronized (DhpPluginManager.class) {
                if (dhpPluginManager == null) {
                    dhpPluginManager = new DhpPluginManager();

                    if (iAgentWebView instanceof WebView) {
                        dhpPluginManager.webView = iAgentWebView;
                    }

                }
            }
        }

    }

    public static void destory() {
        dhpPluginManager.webView = null ;
    }

    /**
     * 获取单例
     * @return
     */
    public static DhpPluginManager shareInstance() {
        return dhpPluginManager ;
    }


    public void loadJsonArray(String jsonArray) {
        try {
            List<DhpPlugin> pluginArrayList = gson.fromJson(jsonArray ,  new TypeToken<List<DhpPlugin>>() {}.getType());
            for (DhpPlugin dhpPlugin : pluginArrayList) {
                try {
                    dhpPlugin.setClazz(Class.forName(dhpPlugin.getClassName()));
                    dhpPlugin.setPluginObject(dhpPlugin.getClazz().newInstance());
                    pluginMap.put(dhpPlugin.getPluginName(), dhpPlugin);
                } catch (Exception ex) {
                    Log.e("DmPluginManager", dhpPlugin.getClassName() + "class not load", ex);
                }
            }
        } catch (Exception e) {
            Log.e("DmPluginManager", "change json to array failed", e);
        }
    }

    public void loadJsonFile(File file) {
        BufferedReader bReader = null;
        String jsonArray = "" ;
        try {
            FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
            bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
            StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
            String s = "";
            while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
                sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
                System.out.println(s);
            }
            bReader.close();
            jsonArray = sb.toString();
        } catch (Exception e) {
            Log.e("DmPluginManager", "File read failed", e);
        } finally {
            if (bReader != null) {
                try {
                    bReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        loadJsonArray(jsonArray);
    }

    public DhpPlugin getDhpPluginByName(String pluginName) {
        return this.pluginMap.get(pluginName);
    }

    public void callSyncHander(String method, Object... args) {
        webView.callHandler(method, args);
    }

    public void callAsyncHander(String method, OnReturnValue onReturnValue, Object... args) {
        webView.callHandler(method, args, onReturnValue);
    }

    public void hasJavascriptMethod(String method, OnReturnValue<Boolean> onReturnValue) {
        webView.hasJavascriptMethod(method, onReturnValue);
    }

}
