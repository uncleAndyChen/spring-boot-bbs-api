package bbs.api.common.model.request;

import bbs.api.common.lib.JsonHelper;
import bbs.api.common.model.PagingView;
import com.fasterxml.jackson.databind.JsonNode;

public class BaseRequest {
    private String method;               //调用的业务方法名
    private String jsonStringParameter;  //传递完整的应用级参数信息，json 格式
    private JsonNode jsonNodeParameter;  //对 apiParas 处理之后得到，方便程序加工处理。
    private PagingView pagingView;       //分页信息，无须分页的调用，不用传递该参数。该参数是放在 jsonStringParameter 里传入的，json 字符串
    private String extendValue;          //扩展参数，各业务方法根据需要传递扩展参数

    /**
     * 不能删除 setJsonStringParameter
     */
    public void setJsonStringParameter(String jsonStringParameter) {
        if (jsonStringParameter != null && jsonStringParameter.length() > 0) {
            //this.jsonStringParameter = CommonFunction.urlAndBase64Decode(jsonStringParameter);
            this.jsonStringParameter = jsonStringParameter;
            this.setJsonNodeParameter(JsonHelper.jsonStringToJsonNode(this.jsonStringParameter));
        } else {
            this.jsonStringParameter = jsonStringParameter;
        }

        if (this.jsonNodeParameter.get("pagingView") != null) {
            this.pagingView = JsonHelper.jsonNodeToPojo(this.jsonNodeParameter.get("pagingView"), PagingView.class);
        }
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getJsonStringParameter() {
        return jsonStringParameter;
    }

    public JsonNode getJsonNodeParameter() {
        return jsonNodeParameter;
    }

    public void setJsonNodeParameter(JsonNode jsonNodeParameter) {
        this.jsonNodeParameter = jsonNodeParameter;
    }

    public PagingView getPagingView() {
        return pagingView;
    }

    public void setPagingView(PagingView pagingView) {
        this.pagingView = pagingView;
    }

    public String getExtendValue() {
        return extendValue;
    }

    public void setExtendValue(String extendValue) {
        this.extendValue = extendValue;
    }
}