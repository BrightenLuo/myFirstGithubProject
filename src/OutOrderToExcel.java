import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

public class OutOrderToExcel {

    public static String outputFile = "D:\\IntelliJ IDEA 2019.2.4\\myFirstGithubProject\\src\\order.xlsx";
    public void createOrder( ) {
        String[][] data={{"科目","分数"},{"语文","99"},{"数学","100"},{"英语","120"}};
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("学生成绩");
            for(int i=0;i<data.length;i++){
                XSSFRow row=sheet.createRow((short)i);
                for(int j=0;j<data[i].length;j++){
                    XSSFCell cell=row.createCell((short)j);
                    cell.setCellValue(data[i][j]);
                }
            }

            FileOutputStream fOut = new FileOutputStream(outputFile);
            workbook.write(fOut);
            fOut.flush();

            fOut.close();
            System.out.println("下单成功！");
        } catch (Exception e) {
            System.out.println("xlCreate() : " + e);
        }
    }
}