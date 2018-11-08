package com.prim.primweb.core.plugin;

public class DhpPluginResponse<T> {
    String methodUuid ;
    String status ;    // ok error cancel
    int code ;         // 对应错误码
    String msg ;   // 对应错误状态
    T data ;

    public static DhpPluginResponse generateFailData(int code , String msg) {
        DhpPluginResponse dmJsPluginJsResponse = new DhpPluginResponse();
        dmJsPluginJsResponse.status = "fail";
        dmJsPluginJsResponse.code = code ;
        dmJsPluginJsResponse.msg = msg ;
        return dmJsPluginJsResponse ;
    }

    public static DhpPluginResponse generateCancelData() {
        DhpPluginResponse dmJsPluginJsResponse = new DhpPluginResponse();
        dmJsPluginJsResponse.status = "cancel";
        dmJsPluginJsResponse.code = -20001 ;
        dmJsPluginJsResponse.msg = "用户取消当前操作" ;
        return dmJsPluginJsResponse ;
    }

    public static <T> DhpPluginResponse<T> generateSuccessData(int code , String msg, T data) {
        DhpPluginResponse dmJsPluginJsResponse = new DhpPluginResponse();
        dmJsPluginJsResponse.status = "ok";
        dmJsPluginJsResponse.code = code ;
        dmJsPluginJsResponse.msg = msg ;
        dmJsPluginJsResponse.data = data ;
        return dmJsPluginJsResponse ;
    }

    public String getMethodUuid() {
        return methodUuid;
    }

    public void setMethodUuid(String methodUuid) {
        this.methodUuid = methodUuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
