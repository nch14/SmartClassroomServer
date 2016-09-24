package main;

import course.Course;
import course.CourseHelperForNJU;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Test;
import po.*;
import util.SaveUtil;
import vo.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;

/**
 * Created by chenh on 2016/7/30.
 */
public class Init {



    public static void startHibernate(){
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();

        SchemaExport se = new SchemaExport();
        se.create(EnumSet.of(TargetType.DATABASE), metadata);
    }

    public static void initClassrooms(){
        String[] classroomNames=new String[]{
                "1101","1102"
        };
        for (int i=0;i<classroomNames.length;i++){
            ClassroomPO classroomPO=new ClassroomPO();
            classroomPO.setName(classroomNames[i]);
            add(classroomPO);
        }
    }


    public static void initUser(){
        UserPO userPO=new UserPO();
        userPO.setUsername("方浩");
        userPO.setNickName("碧血剑");
        userPO.setMotto("那个……你看见过我的小熊吗？");
        userPO.setId("141250001");
        userPO.setUniversity("南京大学");
        userPO.setCourseEnabled(true);
        userPO.setIdentify(User.STUDENT);
        userPO.setPassword("666666");
        SaveUtil.save(userPO);

        userPO=new UserPO();
        userPO.setUsername("赵飞燕");
        userPO.setNickName("小学妹");
        userPO.setMotto("那个……你看见过我的小熊吗？");
        userPO.setId("151099183");
        userPO.setUniversity("南京大学");
        userPO.setCourseEnabled(false);
        userPO.setIdentify(User.STUDENT);
        userPO.setPassword("zfy233");
        SaveUtil.save(userPO);

        userPO=new UserPO();
        userPO.setUsername("孟鑫");
        userPO.setNickName("金金金");
        userPO.setMotto("我的剑就是你的剑！");
        userPO.setId("141250094");
        userPO.setUniversity("南京大学");
        userPO.setCourseEnabled(false);
        userPO.setIdentify(User.STUDENT);
        userPO.setPassword("666666");
        SaveUtil.save(userPO);

        userPO=new UserPO();
        userPO.setUsername("年松");
        userPO.setNickName("C++也大神");
        userPO.setMotto("我将为你指路。");
        userPO.setId("141250095");
        userPO.setUniversity("南京大学");
        userPO.setCourseEnabled(false);
        userPO.setIdentify(User.STUDENT);
        userPO.setPassword("666666");
        SaveUtil.save(userPO);

        userPO=new UserPO();
        userPO.setUsername("小天狼星");
        userPO.setNickName("大脚板");
        userPO.setUniversity("南京大学");
        userPO.setCourseEnabled(false);
        userPO.setMotto("汪。代购纯种哈士奇、萨摩耶、拉布拉多幼犬……");
        userPO.setId("141250096");
        userPO.setIdentify(User.STUDENT);
        userPO.setPassword("666888");
        SaveUtil.save(userPO);

        userPO=new UserPO();
        userPO.setUsername("林伟");
        userPO.setUniversity("南京大学");
        userPO.setCourseEnabled(false);
        userPO.setNickName("熊猫");
        userPO.setMotto("我不管我最帅！");
        userPO.setId("141250097");
        userPO.setIdentify(User.STUDENT);
        userPO.setPassword("666666");
        SaveUtil.save(userPO);

        userPO=new UserPO();
        userPO.setUsername("斯内普");
        userPO.setUniversity("南京大学");
        userPO.setCourseEnabled(false);
        userPO.setNickName("混血王子");
        userPO.setMotto("愚蠢的波特先生，格莱芬多因为你扣10分");
        userPO.setId("141250098");
        userPO.setIdentify(User.STUDENT);
        userPO.setPassword("666666");
        SaveUtil.save(userPO);

        userPO=new UserPO();
        userPO.setUsername("路明非");
        userPO.setNickName("Sakula");
        userPO.setUniversity("南京大学");
        userPO.setCourseEnabled(true);
        userPO.setMotto("滚出去！我只认识一个狮心会长，他的名字叫楚子航，而你他妈的不配！");
        userPO.setId("141250099");
        userPO.setIdentify(User.STUDENT);
        userPO.setPassword("666666");
        SaveUtil.save(userPO);

        userPO=new UserPO();
        userPO.setUsername("上杉绘梨衣");
        userPO.setUniversity("南京大学");
        userPO.setCourseEnabled(true);
        userPO.setNickName("小怪兽");
        userPO.setMotto("我们都是小怪兽，有一天会被正义的奥特曼杀死！");
        userPO.setId("141250100");
        userPO.setIdentify(User.STUDENT);
        userPO.setPassword("666666");
        SaveUtil.save(userPO);

        userPO=new UserPO();
        userPO.setUsername("匿名用户");
        userPO.setUniversity("南京大学");
        userPO.setCourseEnabled(true);
        userPO.setNickName("匿名用户");
        userPO.setMotto("留人不留名……");
        userPO.setId("000000000");
        userPO.setIdentify(User.STUDENT);
        userPO.setPassword("666666");
        SaveUtil.save(userPO);


        BlogMessagePO blogMessage=new BlogMessagePO();
        blogMessage.setText("洛枳喜欢盛淮南。谁都不知道！");
        blogMessage.setSendTime("Mon Aug 01 20:53:41 CST 2016");
        blogMessage.setTag("最好的我们");
        blogMessage.setAuthorId("000000000");
        blogMessage.setId(1);
        SaveUtil.save(blogMessage);

        blogMessage=new BlogMessagePO();
        blogMessage.setText("明天上午有人约一波C++吗？仙2-102");
        blogMessage.setSendTime("Mon Aug 02 17:51:01 CST 2016");
        blogMessage.setTag("我爱学习");
        blogMessage.setAuthorId("141250095");
        blogMessage.setId(2);
        SaveUtil.save(blogMessage);

        blogMessage=new BlogMessagePO();
        blogMessage.setText("今晚七点半仙二216微积分大作战有人约嘛~ヘ( ^o^)ノ＼(^_^ )");
        blogMessage.setSendTime("Mon Aug 03 07:23:14 CST 2016");
        blogMessage.setTag("约自习");
        blogMessage.setAuthorId("141250094");
        blogMessage.setId(3);
        SaveUtil.save(blogMessage);

        blogMessage=new BlogMessagePO();
        blogMessage.setText("今晚八点仙一115南京大学辩论赛总决赛不见不散~(´▽｀)ノ♪");
        blogMessage.setSendTime("Mon Aug 03 22:58:56 CST 2016");
        blogMessage.setTag("校园快讯");
        blogMessage.setAuthorId("141250001");
        blogMessage.setId(4);
        SaveUtil.save(blogMessage);

        blogMessage=new BlogMessagePO();
        blogMessage.setText("大家好……我是路明非，学号是141270016……TAT请问有人捡到我的校园卡吗");
        blogMessage.setSendTime("Mon Aug 03 23:59:56 CST 2016");
        blogMessage.setTag("寻物启事");
        blogMessage.setAuthorId("141250099");
        blogMessage.setId(5);
        SaveUtil.save(blogMessage);


        BlogCommentPO blogCommentPO=new BlogCommentPO();
        blogCommentPO.setAuthorId("141250095");
        blogCommentPO.setText("约约约！！！");
        blogCommentPO.setRawMessageId(3);
        blogCommentPO.setSendTime("Mon Aug 03 07:25:11 CST 2016");
        SaveUtil.save(blogCommentPO);

        blogCommentPO=new BlogCommentPO();
        blogCommentPO.setAuthorId("000000000");
        blogCommentPO.setText("不约、不约，叔叔我们不约~");
        blogCommentPO.setRawMessageId(3);
        blogCommentPO.setSendTime("Mon Aug 03 08:25:02 CST 2016");
        SaveUtil.save(blogCommentPO);

        blogCommentPO=new BlogCommentPO();
        blogCommentPO.setAuthorId("141250100");
        blogCommentPO.setText("求大腿！");
        blogCommentPO.setRawMessageId(3);
        blogCommentPO.setSendTime("Mon Aug 03 08:27:02 CST 2016");
        SaveUtil.save(blogCommentPO);


        blogCommentPO=new BlogCommentPO();
        blogCommentPO.setAuthorId("000000000");
        blogCommentPO.setText("国安永远争冠军！");
        blogCommentPO.setRawMessageId(4);
        blogCommentPO.setSendTime("Mon Aug 04 08:27:02 CST 2016");
        SaveUtil.save(blogCommentPO);

        blogCommentPO=new BlogCommentPO();
        blogCommentPO.setAuthorId("000000000");
        blogCommentPO.setText("楼上在说啥……！");
        blogCommentPO.setRawMessageId(4);
        blogCommentPO.setSendTime("Mon Aug 04 09:27:02 CST 2016");
        SaveUtil.save(blogCommentPO);

        blogCommentPO=new BlogCommentPO();
        blogCommentPO.setAuthorId("000000000");
        blogCommentPO.setText("听说有蒲熠星~男神！！！");
        blogCommentPO.setRawMessageId(4);
        blogCommentPO.setSendTime("Mon Aug 04 09:28:07 CST 2016");
        SaveUtil.save(blogCommentPO);

        blogCommentPO=new BlogCommentPO();
        blogCommentPO.setAuthorId("000000000");
        blogCommentPO.setText("软院PK电子？");
        blogCommentPO.setRawMessageId(4);
        blogCommentPO.setSendTime("Mon Aug 04 09:29:19 CST 2016");
        SaveUtil.save(blogCommentPO);

        blogCommentPO=new BlogCommentPO();
        blogCommentPO.setAuthorId("141250100");
        blogCommentPO.setText("7点半下课TAT,求帮忙占座……");
        blogCommentPO.setRawMessageId(4);
        blogCommentPO.setSendTime("Mon Aug 04 09:31:19 CST 2016");
        SaveUtil.save(blogCommentPO);

        blogCommentPO=new BlogCommentPO();
        blogCommentPO.setAuthorId("141250094");
        blogCommentPO.setText("郭靖喜欢黄蓉，大家都知道！");
        blogCommentPO.setRawMessageId(1);
        blogCommentPO.setSendTime("Mon Aug 02 06:22:19 CST 2016");
        SaveUtil.save(blogCommentPO);

        blogCommentPO=new BlogCommentPO();
        blogCommentPO.setAuthorId("000000000");
        blogCommentPO.setText("你好、17岁的耿耿。我是17岁的陆星河！");
        blogCommentPO.setRawMessageId(1);
        blogCommentPO.setSendTime("Mon Aug 02 07:17:17 CST 2016");
        SaveUtil.save(blogCommentPO);

        blogCommentPO=new BlogCommentPO();
        blogCommentPO.setAuthorId("000000000");
        blogCommentPO.setText("当时的他是最好的他，后来的我是最好的我。可是最好的我们之间，隔了一整个青春。" +
                "怎么奔跑也跨不过的青春，只好伸出手道别。");
        blogCommentPO.setRawMessageId(1);
        blogCommentPO.setSendTime("Mon Aug 02 09:29:19 CST 2016");
        SaveUtil.save(blogCommentPO);

        blogCommentPO=new BlogCommentPO();
        blogCommentPO.setAuthorId("000000000");
        blogCommentPO.setText("盛淮南是谁。。。我看得不仔细?");
        blogCommentPO.setRawMessageId(1);
        blogCommentPO.setSendTime("Mon Aug 02 09:32:39 CST 2016");
        SaveUtil.save(blogCommentPO);

        blogCommentPO=new BlogCommentPO();
        blogCommentPO.setAuthorId("141250001");
        blogCommentPO.setText("回楼上：我是三年二班的盛淮南！");
        blogCommentPO.setRawMessageId(1);
        blogCommentPO.setSendTime("Mon Aug 02 09:40:55 CST 2016");
        SaveUtil.save(blogCommentPO);

        blogCommentPO=new BlogCommentPO();
        blogCommentPO.setAuthorId("141250097");
        blogCommentPO.setText("一群匿名的渣渣！");
        blogCommentPO.setRawMessageId(1);
        blogCommentPO.setSendTime("Mon Aug 02 10:01:02 CST 2016");
        SaveUtil.save(blogCommentPO);

        blogCommentPO=new BlogCommentPO();
        blogCommentPO.setAuthorId("141250100");
        blogCommentPO.setText("去一栋宿管阿姨那边看看？~");
        blogCommentPO.setRawMessageId(5);
        blogCommentPO.setSendTime("Mon Aug 04 08:25:02 CST 2016");
        SaveUtil.save(blogCommentPO);

        blogCommentPO=new BlogCommentPO();
        blogCommentPO.setAuthorId("000000000");
        blogCommentPO.setText("问江南啊。百度有啥用？");
        blogCommentPO.setRawMessageId(5);
        blogCommentPO.setSendTime("Mon Aug 05 15:25:02 CST 2016");
        SaveUtil.save(blogCommentPO);


        AttitudePO attitudePO=new AttitudePO();
        attitudePO.setSheetId(1);
        attitudePO.setUserId("141250097");
        attitudePO.setAttitude(true);
        SaveUtil.save(attitudePO);

        attitudePO=new AttitudePO();
        attitudePO.setSheetId(1);
        attitudePO.setUserId("141250001");
        attitudePO.setAttitude(true);
        SaveUtil.save(attitudePO);

        attitudePO=new AttitudePO();
        attitudePO.setSheetId(1);
        attitudePO.setUserId("141250094");
        attitudePO.setAttitude(false);
        SaveUtil.save(attitudePO);

        attitudePO=new AttitudePO();
        attitudePO.setSheetId(2);
        attitudePO.setUserId("141250094");
        attitudePO.setAttitude(true);
        SaveUtil.save(attitudePO);

        attitudePO=new AttitudePO();
        attitudePO.setSheetId(1);
        attitudePO.setUserId("141250097");
        attitudePO.setAttitude(false);
        SaveUtil.save(attitudePO);

        attitudePO=new AttitudePO();
        attitudePO.setSheetId(1);
        attitudePO.setUserId("141250100");
        attitudePO.setAttitude(true);
        SaveUtil.save(attitudePO);

        attitudePO=new AttitudePO();
        attitudePO.setSheetId(2);
        attitudePO.setUserId("141250097");
        attitudePO.setAttitude(true);
        SaveUtil.save(attitudePO);

        attitudePO=new AttitudePO();
        attitudePO.setSheetId(2);
        attitudePO.setUserId("141250001");
        attitudePO.setAttitude(true);
        SaveUtil.save(attitudePO);

        attitudePO=new AttitudePO();
        attitudePO.setSheetId(2);
        attitudePO.setUserId("141250094");
        attitudePO.setAttitude(false);
        SaveUtil.save(attitudePO);

        attitudePO=new AttitudePO();
        attitudePO.setSheetId(2);
        attitudePO.setUserId("141250097");
        attitudePO.setAttitude(true);
        SaveUtil.save(attitudePO);

        attitudePO=new AttitudePO();
        attitudePO.setSheetId(2);
        attitudePO.setUserId("141250100");
        attitudePO.setAttitude(true);
        SaveUtil.save(attitudePO);

        //
        attitudePO=new AttitudePO();
        attitudePO.setSheetId(4);
        attitudePO.setUserId("141250097");
        attitudePO.setAttitude(true);
        SaveUtil.save(attitudePO);

        attitudePO=new AttitudePO();
        attitudePO.setSheetId(4);
        attitudePO.setUserId("141250001");
        attitudePO.setAttitude(false);
        SaveUtil.save(attitudePO);

        attitudePO=new AttitudePO();
        attitudePO.setSheetId(4);
        attitudePO.setUserId("141250094");
        attitudePO.setAttitude(false);
        SaveUtil.save(attitudePO);

        attitudePO=new AttitudePO();
        attitudePO.setSheetId(4);
        attitudePO.setUserId("141250097");
        attitudePO.setAttitude(false);
        SaveUtil.save(attitudePO);

        attitudePO=new AttitudePO();
        attitudePO.setSheetId(4);
        attitudePO.setUserId("141250099");
        attitudePO.setAttitude(false);
        SaveUtil.save(attitudePO);



/*        CourseHelperForNJU courseHelperForNJU=new CourseHelperForNJU();
        try {
            courseHelperForNJU.login("141250096","nch2012");
        } catch (IOException e) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            try {
                courseHelperForNJU.login("141250096","nch2012");
            } catch (IOException e1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e2) {
                    e1.printStackTrace();
                }
                try {
                    courseHelperForNJU.login("141250096","nch2012");
                } catch (IOException e3) {
                    e1.printStackTrace();
                }
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        ArrayList<Course> courseArrayList = courseHelperForNJU.getMyCourse();
        ArrayList<TimeTableCourse> timeTableCourses=new ArrayList<>();
        for (Course c:courseArrayList){
            timeTableCourses.addAll(TimeTableCourse.getTimeTableCourse("141250096",c));
        }
        for (TimeTableCourse t:timeTableCourses){
            SaveUtil.save(t);
        }*/




    }

    private static void add(ClassroomPO classroomPO) {
        Configuration cfg=new Configuration();
        SessionFactory sf=cfg.configure().buildSessionFactory();
        Session session=sf.openSession();
        session.beginTransaction();
        session.save(classroomPO);
        session.getTransaction().commit();
        session.close();
        sf.close();
    }

}
