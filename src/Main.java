import java.io.InputStream;
import java.util.Scanner;

public class Main {
    /*
    ctrl+alt+l可规范化代码
     */
    public static void main(String[] args) {
        //File file=new File("D:\\IntelliJ IDEA 2019.2.4\\myFirstGithubProject\\src\\user.xlsx");//实际项目不是这样设置路径
        InputStream u=Main.class.getResourceAsStream("/user.xlsx");//读取本项目中的文件指定文件路径
        InputStream p=Main.class.getResourceAsStream("/product.xlsx");
        ReadUser readExcel=new ReadUser();
        ReadProduct readProduct=new ReadProduct();
        Product[] products=readProduct.readExcel(p);
        User[] users=readExcel.readExcel(u);
        boolean bool=true;
        while(bool) {
            System.out.println("请输入用户名：");
            Scanner sc = new Scanner(System.in);
            String username = sc.nextLine();
            System.out.println("请输入密码:");
            String password = sc.nextLine();
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    System.out.println("登陆成功");
                    bool = false;
                    break;
                }
            }
            if(bool) System.out.println("用户名或密码错误");
        }
        for(Product pro:products){
            System.out.println(pro.toString());
        }
    }
    public static void testUser(){

    }
}
