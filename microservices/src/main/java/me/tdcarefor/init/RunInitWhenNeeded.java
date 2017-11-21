package me.tdcarefor.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 类: RunDBInitializerWhenNeeded<br/>
 * 描述: 基础数据初始化。系统第一次部署时所需要的基础数据 <br>
 * 作者: tzw <br>
 * 时间: 2016 16/9/18 下午3:34 <br>
 */
//@Component
public class RunInitWhenNeeded implements ApplicationListener<ContextRefreshedEvent> {
    private final static Logger logger = LoggerFactory.getLogger(RunInitWhenNeeded.class);
    @Value("${quartz.tablePrefix}")
    private String quartzTablePrefix;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //Spring IOC 容器启动完成后,只执行一次。spring boot 启动后会发送两次ContextRefreshedEvent 两次的名称不一样。
        if (event.getApplicationContext().getDisplayName().indexOf("AnnotationConfigEmbeddedWebApplicationContext")!=-1) {
         //如果微服务中存在Init 接口的Bean,就调用。
            try {
                Init init = event.getApplicationContext().getBean(Init.class);
                init.createInitDataIfNeed();
                logger.info("初始化基础数据完成。");
            } catch (Exception e){}
        }else {
            //初始化定时调度器的表结构
            DataSource dataSource= event.getApplicationContext().getBean(DataSource.class);
            InitQuartz.InitQuartzDb(quartzTablePrefix,dataSource);
        }

    }
}
