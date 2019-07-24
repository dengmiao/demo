package com.corgi.test.office;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @title: WriteDoc
 * @description:
 * @author: dengmiao
 * @create: 2019-06-16 14:08
 **/
public class WriteDoc {

    public void testWrite() throws Exception {
        List<Users> list = new ArrayList<Users>();
        list.add(new Users("a","男",10,new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-08")));
        list.add(new Users("b","女",20,new SimpleDateFormat("yyyy-MM-dd").parse("2017-07-07")));

        String templatePath = "D:\\template.doc";
        InputStream is = new FileInputStream(templatePath);
        OutputStream os = null;
        HWPFDocument doc = new HWPFDocument(is);
        Range range = doc.getRange();
        for(int i=0;i<list.size();i++){
            Users user = list.get(i);
            //把range范围内的${reportDate}替换为当前的日期
            range.replaceText("${name}", user.getName());
            range.replaceText("${sex}", user.getSex());
            range.replaceText("${age}", String.valueOf(user.getAge()));
            range.replaceText("${date}", user.getBirthday().toString());
            os = new FileOutputStream(new File("D:\\"+user.getName()+".doc"));
            //把doc输出到输出流中
            doc.write(os);
        }
        os.close();
        is.close();
    }
}
