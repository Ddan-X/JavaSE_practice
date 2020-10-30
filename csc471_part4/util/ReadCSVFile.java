package csc471_part4.util;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import csc471_part4.People;
import csc471_part4.Pet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ReadCSVFile {

    public static List readCSV(String finalPath, HeaderColumnNameMappingStrategy mappingStrategy){
        InputStreamReader is = null;//注意此处的编码格式，我之前遇到了用utf-8读取出来全是null的问题
        try {
            is = new InputStreamReader(new FileInputStream(finalPath), "GBK");
            CsvToBean build = new CsvToBeanBuilder(is)
                    .withMappingStrategy(mappingStrategy)
                    .withSeparator(',')  //逗号分隔符文件当然使用,分隔，当然也有例外
                    //.withSkipLines(1)	//意思是跳过前面几行后再读取
                    .withIgnoreQuotations(true)
                    .build();
            List list = build.parse();
            return list;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // HeaderColumnNameMappingStrategy mappingStrategy = new HeaderColumnNameMappingStrategy<>();
       // mappingStrategy.setType(Bill.class);
      return null;
    }
}
