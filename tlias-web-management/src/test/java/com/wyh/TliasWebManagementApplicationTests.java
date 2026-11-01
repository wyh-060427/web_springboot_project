package com.wyh;

import com.wyh.mapper.DeptMapper;
import com.wyh.pojo.Dept;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TliasWebManagementApplicationTests {
    @Autowired
    private DeptMapper deptMapper;

    @Test
    void contextLoads() {
//        List<Dept> all = deptMapper.findAll();
//        System.out.println(all);
    }

}
