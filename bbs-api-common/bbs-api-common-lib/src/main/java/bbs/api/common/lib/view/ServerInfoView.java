package bbs.api.common.lib.view;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class ServerInfoView {
    public static String hostIP;
    public static String hostName;
    public static String osName;
    public static String osArch;
    public static String osVersion;
    public static String processors;
    public static String javaVersion;
    public static String javaHome;
    public static String javaVendor;

    static {
        Runtime r = Runtime.getRuntime();
        Properties props = System.getProperties();
        InetAddress inetAddress = null;

        hostIP = "";
        osName = props.getProperty("os.name");
        osArch = props.getProperty("os.arch");
        osVersion = props.getProperty("os.version");
        processors = String.valueOf(r.availableProcessors());
        javaVersion = props.getProperty("java.version");
        javaHome = props.getProperty("java.home");
        javaVendor = props.getProperty("java.vendor");

        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            hostIP = "无法获取主机IP";
            hostName = "无法获取主机名";
        }

        if (null != inetAddress) {
            try {
                hostIP = inetAddress.getHostAddress();
            } catch (Exception e) {
                hostIP = "无法获取主机IP";
            }

            try {
                hostName = inetAddress.getHostName();
            } catch (Exception e) {
                hostName = "无法获取主机名";
            }
        }
    }
}
