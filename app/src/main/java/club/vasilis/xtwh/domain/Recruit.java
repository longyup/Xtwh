package club.vasilis.xtwh.domain;

public class Recruit {

    private int id;
    private int ImgId;
    private String title;
    private String distance;
    private String region;
    private String number;

    public Recruit() {
    }

    public Recruit(int id, int imgId, String title, String distance, String region, String number) {
        this.id = id;
        ImgId = imgId;
        this.title = title;
        this.distance = distance;
        this.region = region;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgId() {
        return ImgId;
    }

    public void setImgId(int imgId) {
        ImgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Recruit{" +
                "id=" + id +
                ", ImgId=" + ImgId +
                ", title='" + title + '\'' +
                ", distance='" + distance + '\'' +
                ", region='" + region + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
