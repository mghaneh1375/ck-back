package bogen.vr.ck.Model;

public class Content {

    private String content;
    private Long lastUpdateAt;

    public Content(String content) {
        this.content = content;
        lastUpdateAt = System.currentTimeMillis();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        lastUpdateAt = System.currentTimeMillis();
    }

    public Long getLastUpdateAt() {
        return lastUpdateAt;
    }
}
