package com.corgi.test;

import com.corgi.test.office.ReadDoc;
import org.junit.Test;

/**
 * @title: ReadDocTest
 * @description:
 * @author: dengmiao
 * @create: 2019-06-16 14:05
 **/
public class ReadDocTest {

    @Test
    public void testReadByDoc() throws Exception {
        ReadDoc rd = new ReadDoc();
        rd.testReadByDoc("D:\\template.doc");
    }
}
