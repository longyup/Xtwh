package club.vasilis.xtwh.domain;


/**
 * @author Vasilis
 * @date 2019/5/28 * 16:23
 */
public class Product {
    private String id;
    private String name;
    private String img;
    private String brief;
    private String onlinetime;
    private String typeId;

    public Product() {
    }

    public Product(String id, String name, String img, String brief, String onlinetime, String typeId) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.brief = brief;
        this.onlinetime = onlinetime;
        this.typeId = typeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getOnlinetime() {
        return onlinetime;
    }

    public void setOnlinetime(String onlinetime) {
        this.onlinetime = onlinetime;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", brief='" + brief + '\'' +
                ", onlinetime='" + onlinetime + '\'' +
                ", typeId='" + typeId + '\'' +
                '}';
    }
}
