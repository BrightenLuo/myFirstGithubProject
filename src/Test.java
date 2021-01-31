import java.io.InputStream;
import java.util.Date;
import java.util.Scanner;

public class Test {
    /*
    ctrl+alt+l可规范化代码
     */
    static Scanner sc = new Scanner(System.in);//用于全局从键盘读入

    static Product[] carts = new Product[10];//购物车
    static int count = 0;//购物车商品数量
    static String username;//当前用户
    static User[] users=null;//获取系统存储的用户信息
    static Order[] orders=new Order[10];//订单表
    static int[] shopCounts=new int[10];//获取购物车中某一个商品的购买数量

    //注:类的静态方法只能使用类的静态成员变量，而不能使用非静态成员变量

    //主函数，程序执行入口
    public static void main(String[] args) {
        testUser();//验证用户名密码
        readProduction();//浏览商品
    }


    //验证用户名和密码
    public static void testUser() {
        //File file=new File("D:\\IntelliJ IDEA 2019.2.4\\myFirstGithubProject\\src\\user.xlsx");//实际项目不是这样设置路径
        InputStream u = Test.class.getResourceAsStream("/user.xlsx");//在编译输出的class目录中寻找user.xlsx,即classpath
        ReadUser readExcel = new ReadUser();
        users = readExcel.readExcel(u);
        boolean bool = true;
        while (bool) {
            System.out.println("请输入用户名：");
            username = sc.nextLine();
            System.out.println("请输入密码:");
            String password = sc.nextLine();
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    System.out.println("欢迎回来，"+user.getUsername());
                    bool = false;
                    break;
                }
            }
            if (bool) System.out.println("用户名或密码错误,请重新输入：");
        }
    }

    //读取和显示商品
    public static void readProduction() {
        InputStream p = Test.class.getResourceAsStream("/product.xlsx");//在编译输出的class目录中寻找product.xlsx,即classpath
        ReadProduct readProduct = new ReadProduct();
        Product[] products = readProduct.readExcel(p);
        while(true) {
            System.out.println("可供购买的商品如下:");
            for (Product pro : products) {
                System.out.print("product{");
                System.out.print(pro.toString());
                System.out.println("}");
            }
            System.out.println("请选择商品，输入商品id，即可把该商品加入购物车,输入exit结束选购");
            while (!sc.hasNext("exit")) {
                String flag = sc.nextLine();
                for (int i = 0; i < products.length; i++) {
                    if (flag.equals(products[i].getId())){
                        carts[count++] = products[i];
                    }

                }
            }
            sc.nextLine();//缓冲掉退出循环的exit输入
            System.out.println("是否立即查看您的购物车(yes or no)");
            if (sc.nextLine().equals("yes")) {
                createOrder();//查看购物车并下订单
                continue;
            } else if (sc.nextLine().equals("no")) {
                System.out.println("Would you like continue to browse products or exit system?(Type browse or exit)");
                if(sc.nextLine().equals("exit")){
                    System.out.println("谢谢您的使用！");
                    System.exit(0);
                }
            }
        }
    }

    //下订单
    public static void createOrder(){
        System.out.println("您的购物车商品如下:");
        for (int i=0;i<count;i++) {
            System.out.print("product{");
            System.out.print(carts[i].toString());
            System.out.println('}');
        }
        System.out.println("是否想要下订单:(是输入yes，否输入no)");
        if(sc.nextLine().equals("yes")){
            System.out.println("请输入想要购买的对应商品数量:");
            for(int i=0;i<count;i++)
                shopCounts[i]=sc.nextInt();
            sc.nextLine();//缓冲for循环后的键盘输入
            for(int i=0;i<count;i++){
                orders[i]=new Order();
                orders[i].setOrderId("000"+(i+1));
                for(User usr:users){
                    if(usr.getUsername().equals(username)){
                        orders[i].setUser(usr);
                    }
                }
                orders[i].setProduct(carts[i]);
                orders[i].setShopCount(shopCounts[i]);
                orders[i].setFinalPrice(carts[i].getPrice()*shopCounts[i]);
                orders[i].setCreateDate(new Date());
            }
            System.out.println("下单成功！您的订单如下");
            for(int i=0;i<count;i++){
                System.out.println(orders[i].toString());
            }
            System.out.println("是否确认支付？(yes or not)");
            if(sc.nextLine().equals("yes")){
                OutOrderToExcel outOrderToExcel=new OutOrderToExcel();
                outOrderToExcel.createOrder(orders,count);
            }
            else{
                System.out.println("请在15分钟内支付订单，否则订单自动作废！");
                sc.nextLine();
            }
            System.out.println("是否还想继续浏览商品或者继续购买商品(yes or no)");
            if(sc.nextLine().equals("no")){
                System.out.println("谢谢您的使用");
                System.exit(0);
            }
        }
    }
}
