package ru.selenium.training;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;

import java.io.*;
import java.util.Date;

public class Excel extends TestBase {


    /**
     * Создаём объект XSSFWorkbook;
     * Создаём лист, используя на объекте, созданном в предыдущем шаге, createSheet();
     * Создаём на листе строку, используя createRow();
     * Создаём в строке ячейку — createCell();
     * Задаём значение ячейки через setCellValue();
     * Записываем workbook в File через FileOutputStream;
     * Закрываем workbook, вызывая close().
     */
    @Test
    public void writeIntoExcel() throws IOException {

        //Excel WorkBook
        XSSFWorkbook book = new XSSFWorkbook();

        //Excel sheet
        XSSFSheet sheet = book.createSheet();


        // Нумерация начинается с нуля
        XSSFRow row = sheet.createRow(0);

        // Мы запишем имя и дату в два столбца
        // имя будет String, а дата рождения --- Date,
        // формата dd.mm.yyyy
        XSSFCell name = row.createCell(0);
        name.setCellValue("Test1");

        XSSFCell birthday = row.createCell(1);


        /*
         * чтобы записать дату, нам понадобится сделать:
         *Создать DateFormat - класс для преобразования даты в формат;
         * Создать CellStyle;
         * Записать DateFormat в CellStyle;
         * Записать CellStyle в ячейку;
         * Теперь в эту ячейку можно записать объект Date через всё тот же setCellValue;
         * Чтобы дата поместилась в ячейку, нам нужно добавить столбцу свойство автоматически менять размер: sheet.autoSizeColumn(1).
         */
        DataFormat format = book.createDataFormat();
        XSSFCellStyle dateStyle = book.createCellStyle();
        dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
        birthday.setCellStyle(dateStyle);

        birthday.setCellValue(new Date());

        // Меняем размер столбца
        sheet.autoSizeColumn(1);

        // Записываем всё в файл
        File myExcelFile = new File("screenshots/myExcel.xlsx");

        book.write(new FileOutputStream(myExcelFile));
        book.close();
    }

    /**
     * Теперь мы считаем из только что созданного файла то, что мы туда записали.
     *
     *  Для начала создадим XSSFWorkbook, передав в конструктор FileInputStream;
     *  Получаем лист, передавая в getSheet() его номер или название;
     *  Получаем строку, используя getRow();
     *  Получаем ячейку, используя getCell();
     *  Узнаём тип ячейки, используя на ней getCellType();
     *  В зависимости от типа ячейки, читаем её значения, используя getStringCellValue(), getNumericCellValue() или getDateCellValue();
     *  Закрываем workbook используя close();
     *
     */
    @Test
    public void readFromExcel() throws IOException {

        File myExcelFile = new File("screenshots/myExcel.xlsx");

        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(myExcelFile));

        XSSFSheet myExcelSheet = myExcelBook.getSheet("Sheet0");
        XSSFRow row = myExcelSheet.getRow(0);

        if(row.getCell(0).getCellType() == CellType.STRING){
            String name = row.getCell(0).getStringCellValue();
            System.out.println("name : " + name);
        }

        if(row.getCell(1).getCellType() == CellType.NUMERIC){
            Date birthdate = row.getCell(1).getDateCellValue();
            System.out.println("birthdate :" + birthdate);
        }

        myExcelBook.close();
    }


}
