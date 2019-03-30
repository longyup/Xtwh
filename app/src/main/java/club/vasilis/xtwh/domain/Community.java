package club.vasilis.xtwh.domain;

import java.util.List;

/**
 * @author Vasilis
 * @date 2019/3/29 * 12:59
 */
public class Community {
    private int id;   // 动态ID
    private String UUID; // 发布动态的用户的ID
    private String date; // 发布动态的时间
    private String content;// 动态的内容
    private boolean isPhrase; // 是否点赞
    private int phraseNum; // 点赞的数目
    private int commentNum; // 评论的数目
    private List<Comment> commentList; // 评论的列表

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPhrase() {
        return isPhrase;
    }

    public void setPhrase(boolean phrase) {
        isPhrase = phrase;
    }

    public int getPhraseNum() {
        return phraseNum;
    }

    public void setPhraseNum(int phraseNum) {
        this.phraseNum = phraseNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
