import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

public class OutOrderToExcel {

    public static String outputFile = "D:\\IntelliJ IDEA 2019.2.4\\myFirstGithubProject\\src\\order.xlsx";
    public void createOrder(Order[] orders, int count ) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Order");
            for(int i=0;i<count;i++){
                XSSFRow row=sheet.createRow((short)i);
                for(int j=0;j<6;j++){
                    XSSFCell cell=row.createCell((short)j);
                    if(j==0)
                        cell.setCellValue(orders[i].getOrderId());
                    if(j==1)
                        cell.setCellValue(orders[i].getUserName());
                    if(j==2)
                        cell.setCellValue(orders[i].getUserPhone());
                    if(j==3)
                        cell.setCellValue(orders[i].getUserAddress());
                    if(j==4)
                        cell.setCellValue(orders[i].getProductPrice());
                    if(j==5)
                        cell.setCellValue(orders[i].getProductName());
                }
            }

            FileOutputStream fOut = new FileOutputStream(outputFile);
            workbook.write(fOut);
            fOut.flush();

            fOut.close();
            System.out.println("支付成功！将尽快为您安排发货。");
        } catch (Exception e) {
            System.out.println("xlCreate() : " + e);
        }
    }
}