package club.vasilis.xtwh.domain;

public class Organization {

    private int id;
    private int imgId;
    private String title;
    private String region;
    private String hour;
    private String number;

    public Organization() {
    }

    public Organization(int id, int imgId, String title, String region, String hour, String number) {
        this.id = id;
        this.imgId = imgId;
        this.title = title;
        this.region = region;
        this.hour = hour;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", imgId=" + imgId +
                ", title='" + title + '\'' +
                ", region='" + region + '\'' +
                ", hour='" + hour + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
