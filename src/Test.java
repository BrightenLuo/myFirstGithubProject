import java.io.InputStream;
import java.util.Scanner;

public class Test {
    /*
    ctrl+alt+l可规范化代码
     */

    static Product[] carts = new Product[3];//购物车
    static int count = 0;//购物车数量

    //类的静态方法只能使用类的静态成员变量，而不能使用非静态成员变量

    public static void main(String[] args) {
        testUser();
        readProduction();
    }

    //验证用户名和密码
    public static void testUser() {
        //File file=new File("D:\\IntelliJ IDEA 2019.2.4\\myFirstGithubProject\\src\\user.xlsx");//实际项目不是这样设置路径
        InputStream u = Test.class.getResourceAsStream("/user.xlsx");//读取本项目中的文件指定文件路径
        ReadUser readExcel = new ReadUser();
        User[] users = readExcel.readExcel(u);
        boolean bool = true;
        while (bool) {
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
            if (bool) System.out.println("用户名或密码错误");
        }
    }

    //读取和显示商品
    public static void readProduction() {
        InputStream p = Test.class.getResourceAsStream("/product.xlsx");
        ReadProduct readProduct = new ReadProduct();
        Product[] products = readProduct.readExcel(p);
        System.out.println("可供购买的商品如下:");
        for (Product pro : products) {
            System.out.println(pro.toString());
        }
        System.out.println("请输入商品id，把该商品加入购物车：");
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNext("exit")) {
            String flag=sc.nextLine();
            for (int i = 0; i < products.length; i++) {
                if (flag.equals(products[i].getId()))
                    carts[count++] = products[i];
            }
        }
        sc.close();
        System.out.println("您的购物车商品如下:");
        for (int i=0;i<count;i++) {
            System.out.println(carts[i].toString());
        }
    }
}
