package club.vasilis.xtwh.domain;

/**
 * @author Vasilis
 * @date 2019/3/27 * 14:35
 */
public class User {
    private String uuid;
    private String headimg;
    private String name;
    private String password;

    public User() {
    }

    public User(String uuid, String name, String password) {
        this.uuid = uuid;
        this.name = name;
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
