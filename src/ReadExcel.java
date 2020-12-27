import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadExcel {
    /*
    readExcel是什么方法？成员方法
     */
    public User[] readExcel(InputStream in) {
        User[] users = null;
        try {
            XSSFWorkbook xw = new XSSFWorkbook(in);
            XSSFSheet xs = xw.getSheetAt(0);//获取第0个表
            users = new User[xs.getLastRowNum()];
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                User user = new User();
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        user.setUsername(this.getValue(cell));
                    } else if (k == 1) {
                        user.setPassword(this.getValue(cell));
                    } else if (k == 2) {
                        user.setAddress(this.getValue(cell));
                    } else if (k == 3) {
                        user.setPhone(this.getValue(cell));
                    }
                }
                users[j-1]=user;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private String getValue(XSSFCell cell) {
        String value;
        CellType type = cell.getCellTypeEnum();

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