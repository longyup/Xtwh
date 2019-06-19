package club.vasilis.xtwh.domain;

/**
 * @author Vasilis
 * @date 2019/3/29 * 21:18
 */
public class Comment {
    private int id; // comment表对应的主键Id
    private int communityId;// 评论在哪个动态下
    private String commentA; // 评论的一方
    private String commentB; // 被评论的一方
    private String content; // 评论的内容
    private long date;// 评论的日期

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public String getCommentA() {
        return commentA;
    }

    public void setCommentA(String commentA) {
        this.commentA = commentA;
    }

    public String getCommentB() {
        return commentB;
    }

    public void setCommentB(String commentB) {
        this.commentB = commentB;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", communityId=" + communityId +
                ", commentA='" + commentA + '\'' +
                ", commentB='" + commentB + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
