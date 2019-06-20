package club.vasilis.xtwh.domain;

/**
 * @author Vasilis
 * @date 2019/3/29 * 21:11
 */
public class Phrase {
    private int id;
    private String UUID;//点赞者的id
    private int communityId;// 点赞的动态id

    public Phrase() {
    }

    public Phrase(int id, String UUID, int communityId) {
        this.id = id;
        this.UUID = UUID;
        this.communityId = communityId;
    }

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

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    @Override
    public String toString() {
        return "Phrase{" +
                "id=" + id +
                ", UUID='" + UUID + '\'' +
                ", communityId=" + communityId +
                '}';
    }
}
