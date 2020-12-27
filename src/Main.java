import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //File file=new File("D:\\IntelliJ IDEA 2019.2.4\\myFirstGithubProject\\src\\user.xlsx");//实际项目不是这样设置路径
        InputStream in=Main.class.getResourceAsStream("/user.xlsx");//读取本项目中的文件指定文件路径
        ReadExcel readExcel=new ReadExcel();
        User[] users=readExcel.readExcel(in);
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
