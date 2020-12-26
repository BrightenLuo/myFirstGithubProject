import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file=new File("D:\\IntelliJ IDEA 2019.2.4\\myFirstGithubProject\\src\\user.xlsx");
        ReadExcel readExcel=new ReadExcel();
        User[] users=readExcel.readExcel(file);
        System.out.println("请输入用户名：");
        Scanner sc=new Scanner(System.in);
        String username=sc.nextLine();
        System.out.println("请输入密码:");
        String password=sc.nextLine();
        for(User user:users){
            if(user.getUsername().equals(username)&&user.getPassword().equals(password)){
                System.out.println("登陆成功");
                return;
            }
        }
        System.out.println("用户名或密码错误");
    }
}
