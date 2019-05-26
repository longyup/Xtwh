package club.vasilis.xtwh.domain;

/**
 * @author Vasilis
 * @date 2019/3/29 * 21:11
 */
public class
Phrase {
    private int phraseId;// phrase表的主键ID
    private String phraseDate;// 点赞的时间
    private String uuid;// 点赞者的名字
    private int communityId;// 点赞的动态id

    public Phrase() {
    }

    public Phrase(int phraseId, String phraseDate, String uuid, int communityId) {
        this.phraseId = phraseId;
        this.phraseDate = phraseDate;
        this.uuid = uuid;
        this.communityId = communityId;
    }

    public int getPhraseId() {
        return phraseId;
    }

    public void setPhraseId(int phraseId) {
        this.phraseId = phraseId;
    }

    public String getPhraseDate() {
        return phraseDate;
    }

    public void setPhraseDate(String phraseDate) {
        this.phraseDate = phraseDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }
}
