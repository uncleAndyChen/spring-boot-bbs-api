package bbs.api.common.lib.view;

public class MailInfoView {
    private String to;
    private String subject;
    private String body;
    private String filePath;
    private String rscPath;//静态资源路径和文件名
    private String rscId;  //静态资源id
    private boolean addServerInfoFlag = true; //添加服务器信息标志

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getRscPath() {
        return rscPath;
    }

    public void setRscPath(String rscPath) {
        this.rscPath = rscPath;
    }

    public String getRscId() {
        return rscId;
    }

    public void setRscId(String rscId) {
        this.rscId = rscId;
    }

    public boolean isAddServerInfoFlag() {
        return addServerInfoFlag;
    }

    public void setAddServerInfoFlag(boolean addServerInfoFlag) {
        this.addServerInfoFlag = addServerInfoFlag;
    }
}
