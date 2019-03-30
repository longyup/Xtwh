package club.vasilis.xtwh.domain;

/**
 * @author Vasilis
 * @date 2019/3/29 * 21:18
 */
public class Comment {
    private int id; // comment表对应的主键Id
    private String commentA; // 评论的一方
    private String commentB; // 被评论的一方
    private String commentContent; // 评论的内容
    private String commentDate;// 评论的日期
    private int communityId;// 评论在哪个动态下

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }
}
