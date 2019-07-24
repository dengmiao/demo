package com.corgi.test;

import com.corgi.test.office.WriteDoc;
import org.junit.Test;

/**
 * @title: WriteDocTest
 * @description:
 * @author: dengmiao
 * @create: 2019-06-16 14:14
 **/
public class WriteDocTest {

    @Test
    public void testWrite() throws Exception {
        WriteDoc wd = new WriteDoc();
        wd.testWrite();
    }
}
