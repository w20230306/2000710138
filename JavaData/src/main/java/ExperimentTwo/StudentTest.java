package ExperimentTwo;

public class StudentTest {
    public static void main(String[] args) {
        Student s1 = new Student("1", "张", "男", "1班");
        Student s2 = new Student("2", "四", "女", "2班");
        Student s3 = new Student("3", "五", "男", "9班");

        s1.show();
        s2.show();
        s3.show();

        System.out.println("已经创建的学生对象数目为：" + Student.getCount());
    }
}
