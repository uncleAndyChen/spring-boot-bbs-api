package bbs.api.biz.service;

import bbs.api.common.lib.ConfigProperties;
import bbs.api.common.lib.JavaMailHelper;
import bbs.api.common.lib.JsonHelper;
import bbs.api.common.lib.LogHelper;
import bbs.api.common.model.request.BaseRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 需要在 config.properties 配置 key:evn
 */
public class ErrorLogService {
    private static String env = ConfigProperties.getValue("evn");

    public static void addErrorLogApiCall(Exception e, BaseRequest baseRequest, HttpServletRequest request) {
        String parameterJson = JsonHelper.pojoToJsonString(baseRequest);
        LogHelper.add(baseRequest.getMethod(), e);

        // 本地开发不用发邮件
        if (env.equals("localDev")) {
            e.printStackTrace();
        } else {
            JavaMailHelper.emailErrInfo(baseRequest.getMethod(), "errorMessage:" + e.getMessage()
                    + "<br><br>StackTraceInfo:" + LogHelper.getStackTraceInfo(e)
                    + "<br><br>baseRequest:" + parameterJson);
        }
    }
}
