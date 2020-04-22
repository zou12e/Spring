package com.sc.producer;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MysqlGenerator {

    static final String URL = "jdbc:mysql://127.0.0.1:3306/mybatis?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false";
    static final String DRIVERNAME = "com.mysql.cj.jdbc.Driver";
    static final String USERNAME = "root";
    static final String PASSWORD = "zrz2623396";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        String[] tables = {"user"}; //表名
        String projectName = "/springcloud-eureka-producer";
        String parent = "com.sc.producer";
        String root = System.getProperty("user.dir") + projectName;

        // 1. 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(root + "/src/main/java");
        gc.setAuthor("Jeff");
        gc.setOpen(true);
        gc.setSwagger2(true);
        gc.setFileOverride(false);// 是否覆盖文件
        gc.setActiveRecord(true);// 开启 activeRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setServiceName("%sService");

        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);

        // 2. 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        dsc.setDriverName(DRIVERNAME);
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);

        // 3. 包配置
        PackageConfig pc = new PackageConfig();
        // pc.setModuleName("");
        pc.setParent(parent);
        pc.setEntity("entity");
        pc.setMapper("mappers");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);


        // 4. 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setCapitalMode(true);
        strategy.setEntityLombokModel(true); //lombok
        strategy.setRestControllerStyle(true);
        strategy.setInclude(tables);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setLogicDeleteFieldName("deleted"); //逻辑删除
        strategy.setVersionFieldName("version"); //乐观锁
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategy.setTableFillList(tableFills);

        mpg.setStrategy(strategy);

        // 5.mappers
        InjectionConfig cgf = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                this.setMap(map);
            }
        }.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig("/templates/mapper.xml.vm") {
            // 自定义输出文件目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                return root  + "/src/main/resources/mappers/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        }));
        mpg.setTemplate(new TemplateConfig().setXml(null));
        mpg.setCfg(cgf);

        // 执行生成
        mpg.execute();


    }
}
