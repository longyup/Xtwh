package club.vasilis.xtwh.domain;

public class FolkCustom {

    /**
     * img :
     * onlinetime : 2018-07-30
     * name : 宜兴市被认定为国家级农村职业教育和成人教育示范县
     * details : 按照《教育部关于开展国家级农村职业教育和成人教育示范县创建工作的通知》（教职成〔2013〕1号）和关于开展第三批国家级农村职业教育和成人教育示范县创建工作的部署要求，2015年4月，第三批国家级示范县创建工作正式启动，各地积极组织开展创建工作，取得良好成效。经组织专家对各地推荐的国家级示范县创建申报材料进行评审，共有50个县（市、区）入围第三批国家级农村职业教育和成人教育示范县创建名单（见附件），现将名单予以公布。
     * typeid : cus001
     * id : 1
     */

    private String img;
    private String onlinetime;
    private String name;
    private String details;
    private String typeid;
    private String id;

    @Override
    public String toString() {
        return "FolkCustom{" +
                "img='" + img + '\'' +
                ", onlinetime='" + onlinetime + '\'' +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", typeid='" + typeid + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public FolkCustom() {
    }

    public FolkCustom(String img, String onlinetime, String name, String details, String typeid, String id) {
        this.img = img;
        this.onlinetime = onlinetime;
        this.name = name;
        this.details = details;
        this.typeid = typeid;
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
