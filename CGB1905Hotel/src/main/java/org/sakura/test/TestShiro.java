package org.sakura.test;

import org.junit.Test;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

public class TestShiro {

    @Test
    public void test() {

        String a = "123";


        String aaa = DigestUtils.md5DigestAsHex(a.getBytes());

        System.out.println(aaa);
    }
}
