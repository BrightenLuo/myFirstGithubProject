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
            String[] firstRowData={"用户id","用户名","手机号","地址","商品id","商品名","商品描述","商品单价","购买数量","总价","创建时间"};
            XSSFRow firstRow=sheet.createRow((short)0);
            for(int i=0;i<11;i++){
                XSSFCell cell=firstRow.createCell((short)i);
                cell.setCellValue(firstRowData[i]);
            }
            for(int i=0;i<count;i++){
                XSSFRow row=sheet.createRow((short)i+1);
                for(int j=0;j<11;j++){
                    XSSFCell cell=row.createCell((short)j);
                    if(j==0)
                        cell.setCellValue(orders[i].getOrderId());
                    if(j==1)
                        cell.setCellValue(orders[i].getUser().getUsername());
                    if(j==2)
                        cell.setCellValue(orders[i].getUser().getPhone());
                    if(j==3)
                        cell.setCellValue(orders[i].getUser().getAddress());
                    if(j==4)
                        cell.setCellValue(orders[i].getProduct().getId());
                    if(j==5)
                        cell.setCellValue(orders[i].getProduct().getName());
                    if(j==6)
                        cell.setCellValue(orders[i].getProduct().getDescr());
                    if(j==7)
                        cell.setCellValue(orders[i].getProduct().getPrice());
                    if(j==8)
                        cell.setCellValue(orders[i].getShopCount());
                    if(j==9)
                        cell.setCellValue(orders[i].getFinalPrice());
                    if(j==10)
                        cell.setCellValue(orders[i].getCreateDate());
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