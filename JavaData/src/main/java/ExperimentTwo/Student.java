package ExperimentTwo;

public class Student {
    private static int count = 0; // 已经创建的学生对象数目
    private String num;
    private String name;
    private String sex;
    private String banji;

    public Student(String num, String name, String sex, String banji) {
        this.num = num;
        this.name = name;
        this.sex = sex;
        this.banji = banji;
        count++;
    }
    public void show() {
        System.out.println("学号：" + num + "，姓名：" + name + "，性别：" + sex + "，班级：" +banji);
    }
    public static int getCount() {
        return count;
    }
}
