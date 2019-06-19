package com.jeesd.generate;

import com.baomidou.mybatisplus.generator.AutoGenerator;

import java.util.Arrays;

public class MyGenerator extends BaseGenerator {

    public void generator(String ...tableName) {

        // 代码生成器
        AutoGenerator mpg = getAutoGenerator(tableName);
        mpg.execute();
        if (tableName == null) {
            System.err.println(" Generator Success !");
        } else {
            System.err.println(" TableName【 " + tableName+ " 】" + "Generator Success !");
            Arrays.asList(tableName).forEach(x -> System.out.println(x));

        }
    }

    public static void main(String[] args){
        String[] tableNames = new String[] { "sys_user","sys_role","sys_user_role","sys_resource","sys_role_resource","sys_dept","sys_org" };
        new MyGenerator().generator(tableNames);
    }
}
