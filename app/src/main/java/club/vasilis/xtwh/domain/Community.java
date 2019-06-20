package club.vasilis.xtwh.domain;

import java.util.List;

/**
 * @author Vasilis
 * @date 2019/3/29 * 12:59
 */
public class Community {
    private int id;   // 动态ID
    private User user;// 发布动态的用户
    private long date; // 发布动态的时间
    private String content;// 动态的内容
    private boolean isPhrase; // 是否点赞
    private List<Phrase> phraseList; // 点赞的列表
    private List<Comment> commentList; // 评论的列表

    public Community() {
    }

    public Community(int id, User user, long date, String content, boolean isPhrase, List<Phrase> phraseList, List<Comment> commentList) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.content = content;
        this.isPhrase = isPhrase;
        this.phraseList = phraseList;
        this.commentList = commentList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
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

    public List<Phrase> getPhraseList() {
        return phraseList;
    }

    public void setPhraseList(List<Phrase> phraseList) {
        this.phraseList = phraseList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "Community{" +
                "id=" + id +
                ", user=" + user +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                ", isPhrase=" + isPhrase +
                ", phraseList=" + phraseList +
                ", commentList=" + commentList +
                '}';
    }
}
