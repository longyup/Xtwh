package club.vasilis.xtwh.domain;

/**
 * 文化遗址
 */
public class   CultureSites {
    private String id;
    private String title;
    private String content;
    private String img;
    private String onlinetime;
    private String locationid;

    public CultureSites() {
    }

    public CultureSites(String id, String title, String content, String img, String onlinetime, String locationid) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.img = img;
        this.onlinetime = onlinetime;
        this.locationid = locationid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getOnlinetime() {
        return onlinetime;
    }

    public void setOnlinetime(String onlinetime) {
        this.onlinetime = onlinetime;
    }

    public String getLocationid() {
        return locationid;
    }

    public void setLocationid(String locationid) {
        this.locationid = locationid;
    }

    @Override
    public String toString() {
        return "CultureSites{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                ", onlinetime='" + onlinetime + '\'' +
                ", locationid='" + locationid + '\'' +
                '}';
    }
}
