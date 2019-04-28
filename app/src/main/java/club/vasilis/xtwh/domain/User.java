package club.vasilis.xtwh.domain;

/**
 * @author Vasilis
 * @date 2019/3/27 * 14:35
 */
public class User {

    private int account;//账号
    private String headImg;//头像
    private String nickName;//昵称
    private String name;//姓名
    private String password;//密码
    private String sex;//性别
    private String phoneNumber;//手机号
    private String e_mail;//电子邮件
    private String birthday;//生日
    private String signature;//个性签名
    private String profile;//个人简历
    private String localPalace;//常住地

    public User(int account, String nickName, String password) {
        this.account = account;
        this.nickName = nickName;
        this.password = password;
    }

    public User(String nickName, String name, String password, String sex, String phoneNumber, String e_mail, String birthday, String signature, String profile, String localPalace) {
        this.nickName = nickName;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.e_mail = e_mail;
        this.birthday = birthday;
        this.signature = signature;
        this.profile = profile;
        this.localPalace = localPalace;
    }

    public User(int account, String nickName, String name, String password, String sex, String phoneNumber, String e_mail, String birthday, String signature, String profile, String localPalace) {
        this.account = account;
        this.nickName = nickName;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.e_mail = e_mail;
        this.birthday = birthday;
        this.signature = signature;
        this.profile = profile;
        this.localPalace = localPalace;
    }

    @Override
    public String toString() {
        return "User{" +
                "headImg='" + headImg + '\'' +
                ", nickName='" + nickName + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", e_mail='" + e_mail + '\'' +
                ", birthday='" + birthday + '\'' +
                ", signature='" + signature + '\'' +
                ", profile='" + profile + '\'' +
                ", localPalace='" + localPalace + '\'' +
                '}';
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getLocalPalace() {
        return localPalace;
    }

    public void setLocalPalace(String localPalace) {
        this.localPalace = localPalace;
    }

    public User() {
    }
}
