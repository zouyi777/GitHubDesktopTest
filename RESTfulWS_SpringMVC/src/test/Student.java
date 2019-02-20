package test;

public class Student {

	/**id*/
    private int id;
    /**名称*/
    private String name;
    /**年龄*/
    private int age;
    /**性别*/
    private String sex;
    /**专业*/
    private String major;
    /**头像*/
    private String headPic;

    public Student() {

    }

    public Student(int id, String name, int age, String sex, String major,String headPic) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.major = major;
        this.headPic = headPic;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
}
