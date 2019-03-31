package club.vasilis.xtwh.domain;

public class Organization {

    private int id;
    private int imgId;
    private String addTitleTv;
    private String addRegionTv;
    private String addHourTv;
    private String addnumberTv;

    public Organization() {
    }

    public Organization(int id, int imgId, String addTitleTv, String addRegionTv, String addHourTv, String addnumberTv) {
        this.id = id;
        this.imgId = imgId;
        this.addTitleTv = addTitleTv;
        this.addRegionTv = addRegionTv;
        this.addHourTv = addHourTv;
        this.addnumberTv = addnumberTv;
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

    public String getAddTitleTv() {
        return addTitleTv;
    }

    public void setAddTitleTv(String addTitleTv) {
        this.addTitleTv = addTitleTv;
    }

    public String getAddRegionTv() {
        return addRegionTv;
    }

    public void setAddRegionTv(String addRegionTv) {
        this.addRegionTv = addRegionTv;
    }

    public String getAddHourTv() {
        return addHourTv;
    }

    public void setAddHourTv(String addHourTv) {
        this.addHourTv = addHourTv;
    }

    public String getAddnumberTv() {
        return addnumberTv;
    }

    public void setAddnumberTv(String addnumberTv) {
        this.addnumberTv = addnumberTv;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", imgId=" + imgId +
                ", addTitleTv='" + addTitleTv + '\'' +
                ", addRegionTv='" + addRegionTv + '\'' +
                ", addHourTv='" + addHourTv + '\'' +
                ", addnumberTv='" + addnumberTv + '\'' +
                '}';
    }
}
