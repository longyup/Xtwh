package club.vasilis.xtwh.domain;

/**
 * @author Vasilis
 * @date 2019/3/27 * 14:35
 */
public class User {
    private String UUID;
    private String account;//账号
    private String headImg;//头像
    private String nickName;//昵称
    private String name;//姓名
    private String password;//密码
    private String sex;//性别
    private String phone;//手机号
    private String e_mail;//电子邮件
    private String birthday;//生日
    private String signature;//个性签名
    private String localPlace;//常住地
    private String profile;//个人简历
    private String idCard;



    public User(String nickName, String name, String password, String sex, String phone, String e_mail, String birthday, String signature, String profile, String localPlace) {
        this.nickName = nickName;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.e_mail = e_mail;
        this.birthday = birthday;
        this.signature = signature;
        this.profile = profile;
        this.localPlace = localPlace;
    }

    public User(String account, String nickName, String name, String password, String sex, String phone, String e_mail, String birthday, String signature, String profile, String localPlace) {
        this.account = account;
        this.nickName = nickName;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.e_mail = e_mail;
        this.birthday = birthday;
        this.signature = signature;
        this.profile = profile;
        this.localPlace = localPlace;
    }


    public User(String account, String nickName, String password) {
        this.account = account;
        this.nickName = nickName;
        this.password = password;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getLocalPlace() {
        return localPlace;
    }

    public void setLocalPlace(String localPlace) {
        this.localPlace = localPlace;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "UUID='" + UUID + '\'' +
                ", account='" + account + '\'' +
                ", headImg='" + headImg + '\'' +
                ", nickName='" + nickName + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", e_mail='" + e_mail + '\'' +
                ", birthday='" + birthday + '\'' +
                ", signature='" + signature + '\'' +
                ", localPlace='" + localPlace + '\'' +
                ", profile='" + profile + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
