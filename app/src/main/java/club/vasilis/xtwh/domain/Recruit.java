package club.vasilis.xtwh.domain;

public class Recruit {

    private int id;
    private int ImgId;
    private String titleTv;
    private String distanceTv;
    private String regionTv;
    private String numberTv;

    public Recruit() {
    }

    public Recruit(int id, int imgId, String titleTv, String distanceTv, String regionTv, String numberTv) {
        this.id = id;
        ImgId = imgId;
        this.titleTv = titleTv;
        this.distanceTv = distanceTv;
        this.regionTv = regionTv;
        this.numberTv = numberTv;
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

    public String getTitleTv() {
        return titleTv;
    }

    public void setTitleTv(String titleTv) {
        this.titleTv = titleTv;
    }

    public String getDistanceTv() {
        return distanceTv;
    }

    public void setDistanceTv(String distanceTv) {
        this.distanceTv = distanceTv;
    }

    public String getRegionTv() {
        return regionTv;
    }

    public void setRegionTv(String regionTv) {
        this.regionTv = regionTv;
    }

    public String getNumberTv() {
        return numberTv;
    }

    public void setNumberTv(String numberTv) {
        this.numberTv = numberTv;
    }

    @Override
    public String toString() {
        return "Recruit{" +
                "id=" + id +
                ", ImgId=" + ImgId +
                ", titleTv='" + titleTv + '\'' +
                ", distanceTv='" + distanceTv + '\'' +
                ", regionTv='" + regionTv + '\'' +
                ", numberTv='" + numberTv + '\'' +
                '}';
    }
}
