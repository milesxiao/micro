package me.tdcarefor.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 类: InitQuartz<br/>
 * 描述: Quartz 表结构初始化 <br>
 * 作者: tzw <br>
 * 时间: 2016 16/9/19 下午9:35 <br>
 */
public class InitQuartz {
    private final static Logger logger = LoggerFactory.getLogger(InitQuartz.class);
    private static String QRTZ_JOB_DETAILS="CREATE TABLE QRTZ_JOB_DETAILS\n" +
            "  (\n" +
            "    SCHED_NAME VARCHAR(120) NOT NULL,\n" +
            "    JOB_NAME  VARCHAR(200) NOT NULL,\n" +
            "    JOB_GROUP VARCHAR(200) NOT NULL,\n" +
            "    DESCRIPTION VARCHAR(250) NULL,\n" +
            "    JOB_CLASS_NAME   VARCHAR(250) NOT NULL,\n" +
            "    IS_DURABLE VARCHAR(1) NOT NULL,\n" +
            "    IS_NONCONCURRENT VARCHAR(1) NOT NULL,\n" +
            "    IS_UPDATE_DATA VARCHAR(1) NOT NULL,\n" +
            "    REQUESTS_RECOVERY VARCHAR(1) NOT NULL,\n" +
            "    JOB_DATA BLOB NULL,\n" +
            "    PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)\n" +
            ")";
    private static String QRTZ_TRIGGERS="CREATE TABLE QRTZ_TRIGGERS\n" +
            "  (\n" +
            "    SCHED_NAME VARCHAR(120) NOT NULL,\n" +
            "    TRIGGER_NAME VARCHAR(200) NOT NULL,\n" +
            "    TRIGGER_GROUP VARCHAR(200) NOT NULL,\n" +
            "    JOB_NAME  VARCHAR(200) NOT NULL,\n" +
            "    JOB_GROUP VARCHAR(200) NOT NULL,\n" +
            "    DESCRIPTION VARCHAR(250) NULL,\n" +
            "    NEXT_FIRE_TIME BIGINT(13) NULL,\n" +
            "    PREV_FIRE_TIME BIGINT(13) NULL,\n" +
            "    PRIORITY INTEGER NULL,\n" +
            "    TRIGGER_STATE VARCHAR(16) NOT NULL,\n" +
            "    TRIGGER_TYPE VARCHAR(8) NOT NULL,\n" +
            "    START_TIME BIGINT(13) NOT NULL,\n" +
            "    END_TIME BIGINT(13) NULL,\n" +
            "    CALENDAR_NAME VARCHAR(200) NULL,\n" +
            "    MISFIRE_INSTR SMALLINT(2) NULL,\n" +
            "    JOB_DATA BLOB NULL,\n" +
            "    PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),\n" +
            "    FOREIGN KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)\n" +
            "        REFERENCES QRTZ_JOB_DETAILS(SCHED_NAME,JOB_NAME,JOB_GROUP)\n" +
            ")";
    private static String QRTZ_SIMPLE_TRIGGERS="CREATE TABLE QRTZ_SIMPLE_TRIGGERS\n" +
            "  (\n" +
            "    SCHED_NAME VARCHAR(120) NOT NULL,\n" +
            "    TRIGGER_NAME VARCHAR(200) NOT NULL,\n" +
            "    TRIGGER_GROUP VARCHAR(200) NOT NULL,\n" +
            "    REPEAT_COUNT BIGINT(7) NOT NULL,\n" +
            "    REPEAT_INTERVAL BIGINT(12) NOT NULL,\n" +
            "    TIMES_TRIGGERED BIGINT(10) NOT NULL,\n" +
            "    PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),\n" +
            "    FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)\n" +
            "        REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)\n" +
            ")";
    private static String QRTZ_CRON_TRIGGERS="CREATE TABLE QRTZ_CRON_TRIGGERS\n" +
            "  (\n" +
            "    SCHED_NAME VARCHAR(120) NOT NULL,\n" +
            "    TRIGGER_NAME VARCHAR(200) NOT NULL,\n" +
            "    TRIGGER_GROUP VARCHAR(200) NOT NULL,\n" +
            "    CRON_EXPRESSION VARCHAR(200) NOT NULL,\n" +
            "    TIME_ZONE_ID VARCHAR(80),\n" +
            "    PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),\n" +
            "    FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)\n" +
            "        REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)\n" +
            ")";
    private static String QRTZ_SIMPROP_TRIGGERS="CREATE TABLE QRTZ_SIMPROP_TRIGGERS\n" +
            "  (          \n" +
            "    SCHED_NAME VARCHAR(120) NOT NULL,\n" +
            "    TRIGGER_NAME VARCHAR(200) NOT NULL,\n" +
            "    TRIGGER_GROUP VARCHAR(200) NOT NULL,\n" +
            "    STR_PROP_1 VARCHAR(512) NULL,\n" +
            "    STR_PROP_2 VARCHAR(512) NULL,\n" +
            "    STR_PROP_3 VARCHAR(512) NULL,\n" +
            "    INT_PROP_1 INT NULL,\n" +
            "    INT_PROP_2 INT NULL,\n" +
            "    LONG_PROP_1 BIGINT NULL,\n" +
            "    LONG_PROP_2 BIGINT NULL,\n" +
            "    DEC_PROP_1 NUMERIC(13,4) NULL,\n" +
            "    DEC_PROP_2 NUMERIC(13,4) NULL,\n" +
            "    BOOL_PROP_1 VARCHAR(1) NULL,\n" +
            "    BOOL_PROP_2 VARCHAR(1) NULL,\n" +
            "    PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),\n" +
            "    FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP) \n" +
            "    REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)\n" +
            ")";
    private static String QRTZ_BLOB_TRIGGERS="CREATE TABLE QRTZ_BLOB_TRIGGERS\n" +
            "  (\n" +
            "    SCHED_NAME VARCHAR(120) NOT NULL,\n" +
            "    TRIGGER_NAME VARCHAR(200) NOT NULL,\n" +
            "    TRIGGER_GROUP VARCHAR(200) NOT NULL,\n" +
            "    BLOB_DATA BLOB NULL,\n" +
            "    PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),\n" +
            "    FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)\n" +
            "        REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)\n" +
            ")";
    private static String QRTZ_CALENDARS="CREATE TABLE QRTZ_CALENDARS\n" +
            "  (\n" +
            "    SCHED_NAME VARCHAR(120) NOT NULL,\n" +
            "    CALENDAR_NAME  VARCHAR(200) NOT NULL,\n" +
            "    CALENDAR BLOB NOT NULL,\n" +
            "    PRIMARY KEY (SCHED_NAME,CALENDAR_NAME)\n" +
            ")";
    private static String QRTZ_PAUSED_TRIGGER_GRPS="CREATE TABLE QRTZ_PAUSED_TRIGGER_GRPS\n" +
            "  (\n" +
            "    SCHED_NAME VARCHAR(120) NOT NULL,\n" +
            "    TRIGGER_GROUP  VARCHAR(200) NOT NULL, \n" +
            "    PRIMARY KEY (SCHED_NAME,TRIGGER_GROUP)\n" +
            ")";
    private static String QRTZ_FIRED_TRIGGERS="CREATE TABLE QRTZ_FIRED_TRIGGERS\n" +
            "  (\n" +
            "    SCHED_NAME VARCHAR(120) NOT NULL,\n" +
            "    ENTRY_ID VARCHAR(95) NOT NULL,\n" +
            "    TRIGGER_NAME VARCHAR(200) NOT NULL,\n" +
            "    TRIGGER_GROUP VARCHAR(200) NOT NULL,\n" +
            "    INSTANCE_NAME VARCHAR(200) NOT NULL,\n" +
            "    FIRED_TIME BIGINT(13) NOT NULL,\n" +
            "    SCHED_TIME BIGINT(13) NOT NULL,\n" +
            "    PRIORITY INTEGER NOT NULL,\n" +
            "    STATE VARCHAR(16) NOT NULL,\n" +
            "    JOB_NAME VARCHAR(200) NULL,\n" +
            "    JOB_GROUP VARCHAR(200) NULL,\n" +
            "    IS_NONCONCURRENT VARCHAR(1) NULL,\n" +
            "    REQUESTS_RECOVERY VARCHAR(1) NULL,\n" +
            "    PRIMARY KEY (SCHED_NAME,ENTRY_ID)\n" +
            ")";
    private static String QRTZ_SCHEDULER_STATE="CREATE TABLE QRTZ_SCHEDULER_STATE\n" +
            "  (\n" +
            "    SCHED_NAME VARCHAR(120) NOT NULL,\n" +
            "    INSTANCE_NAME VARCHAR(200) NOT NULL,\n" +
            "    LAST_CHECKIN_TIME BIGINT(13) NOT NULL,\n" +
            "    CHECKIN_INTERVAL BIGINT(13) NOT NULL,\n" +
            "    PRIMARY KEY (SCHED_NAME,INSTANCE_NAME)\n" +
            ")";
    private static String QRTZ_LOCKS="CREATE TABLE QRTZ_LOCKS\n" +
            "  (\n" +
            "    SCHED_NAME VARCHAR(120) NOT NULL,\n" +
            "    LOCK_NAME  VARCHAR(40) NOT NULL, \n" +
            "    PRIMARY KEY (SCHED_NAME,LOCK_NAME)\n" +
            ")";

    public static void InitQuartzDb(String quartzTablePrefix,DataSource dataSource){
        Connection connection ;

        try {
            connection = dataSource.getConnection();
          try {
              //从Quartz 表中读数据,如果表不存在,就创建。
              String qrtzTableSql="select * FROM "+quartzTablePrefix+"FIRED_TRIGGERS";
              connection.prepareStatement(qrtzTableSql).execute();
              logger.debug("Quartz表结构已经存在");
          }catch (SQLException e){
             try {
              logger.debug("开始初化Quartz表结构");
              connection.prepareStatement(QRTZ_JOB_DETAILS.replace("QRTZ_",quartzTablePrefix)).execute();
              connection.prepareStatement(QRTZ_TRIGGERS.replace("QRTZ_",quartzTablePrefix)).execute();
              connection.prepareStatement(QRTZ_SIMPLE_TRIGGERS.replace("QRTZ_",quartzTablePrefix)).execute();
              connection.prepareStatement(QRTZ_CRON_TRIGGERS.replace("QRTZ_",quartzTablePrefix)).execute();
              connection.prepareStatement(QRTZ_SIMPROP_TRIGGERS.replace("QRTZ_",quartzTablePrefix)).execute();
              connection.prepareStatement(QRTZ_BLOB_TRIGGERS.replace("QRTZ_",quartzTablePrefix)).execute();
              connection.prepareStatement(QRTZ_CALENDARS.replace("QRTZ_",quartzTablePrefix)).execute();
              connection.prepareStatement(QRTZ_PAUSED_TRIGGER_GRPS.replace("QRTZ_",quartzTablePrefix)).execute();
              connection.prepareStatement(QRTZ_FIRED_TRIGGERS.replace("QRTZ_",quartzTablePrefix)).execute();
              connection.prepareStatement(QRTZ_SCHEDULER_STATE.replace("QRTZ_",quartzTablePrefix)).execute();
              connection.prepareStatement(QRTZ_LOCKS.replace("QRTZ_",quartzTablePrefix)).execute();
             }catch (SQLException e1){
                 logger.error("初始化Quartz表结构失败",e1);
             }

          } finally {
             // connection.close();
              logger.debug("完成初化Quartz表结构");
          }
       }catch (SQLException e){
           logger.error("数据库连接出错",e);
        }


    }
}
