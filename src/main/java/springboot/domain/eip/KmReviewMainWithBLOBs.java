package springboot.domain.eip;

import java.io.Serializable;

public class KmReviewMainWithBLOBs extends KmReviewMain implements Serializable {
    private String extendDataXml;

    private String docContent;

    private static final long serialVersionUID = 1L;

    public String getExtendDataXml() {
        return extendDataXml;
    }

    public void setExtendDataXml(String extendDataXml) {
        this.extendDataXml = extendDataXml == null ? null : extendDataXml.trim();
    }

    public String getDocContent() {
        return docContent;
    }

    public void setDocContent(String docContent) {
        this.docContent = docContent == null ? null : docContent.trim();
    }
}