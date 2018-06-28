package kh618.statisticscs;

import android.net.Uri;

public class SubjectDetails {
    private String subject;
    private String type;
    private String created_at;
    private String file ;
    private String num;
    public SubjectDetails(String subject, String type, String created_at, String file) {
        this.subject = subject;
        this.type = type;
        this.created_at = created_at;
        this.file = file;
    }
    public SubjectDetails(String subject, String type, String created_at, String file ,String num) {
        this.subject = subject;
        this.type = type;
        this.created_at = created_at;
        this.file = file;
        this.num =num;
    }

    public SubjectDetails() {
        this.subject = null;
        this.type = null;
        this.created_at = null;
        this.file = null;
    }
    public SubjectDetails(String subject, String type, String created_at) {
        this.subject = subject;
        this.type = type;
        this.created_at = created_at;
    }


    public String getSubName() {
        return subject;
    }

    public String getSubType() {
        return type;
    }

    public String getSubData() {
        return created_at;
    }
    public String getfile() {
        return file;
    }

    public String getNum() {
        return num;
    }

    public void setfile(String fileLocation) {
        this.file = fileLocation;
    }

    public void setSubName(String subName) {
        this.subject = subName;
    }

    public void setSubType(String subType) {
        this.type = subType;
    }

    public void setSubData(String subData) {
        this.created_at = subData;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
