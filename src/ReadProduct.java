import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

public class ReadProduct {
    /*
    readExcel是什么方法？成员方法
     */
    public Product[] readExcel(InputStream in) {
        Product[] products = null;
        try {
            XSSFWorkbook xw = new XSSFWorkbook(in);
            XSSFSheet xs = xw.getSheetAt(0);//获取第0个表
            products = new Product[xs.getLastRowNum()];
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                Product product = new Product();
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        product.setId(this.getValue(cell));
                    } else if (k == 1) {
                        product.setName(this.getValue(cell));
                    } else if (k == 2) {
                        product.setPrice(Float.parseFloat(this.getValue(cell)));
                    } else if (k == 3) {
                        product.setDescr(this.getValue(cell));
                    }
                }
                products[j-1]=product;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private String getValue(XSSFCell cell) {
        String value;
        CellType type = cell.getCellType();

        switch (type) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            case NUMERIC:
                DataFormatter formatter = new DataFormatter();
                value = formatter.formatCellValue(cell);//把数字类型转换为字符串类型
                //value = cell.getNumericCellValue() + "";//非字符串类型和一个空字符相连，最终类型是String
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case ERROR:
                value = "非法字符";
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
}