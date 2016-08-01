package main;

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
import po.BlogMessagePO;
import po.ClassroomPO;
import po.UserPO;
import util.SaveUtil;
import vo.User;

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
        userPO.setIdentify(User.STUDENT);
        userPO.setPassword("666666");
        add(userPO);

        userPO=new UserPO();
        userPO.setUsername("大星星");
        userPO.setNickName("东北狼探路女儿国");
        userPO.setMotto("我的剑就是你的剑！");
        userPO.setId("141250094");
        userPO.setIdentify(User.STUDENT);
        userPO.setPassword("666666");
        add(userPO);

        userPO=new UserPO();
        userPO.setUsername("年松");
        userPO.setNickName("C++也大神");
        userPO.setMotto("我将为你指路。");
        userPO.setId("141250095");
        userPO.setIdentify(User.STUDENT);
        userPO.setPassword("666666");
        add(userPO);

        userPO=new UserPO();
        userPO.setUsername("匿名用户");
        userPO.setNickName("匿名用户");
        userPO.setMotto("留人不留名……");
        userPO.setId("000000000");
        userPO.setIdentify(User.STUDENT);
        userPO.setPassword("666666");
        add(userPO);


        BlogMessagePO blogMessage=new BlogMessagePO();
        blogMessage.setText("洛枳喜欢盛淮南。谁都不知道！");
        blogMessage.setSendTime("Mon Aug 01 20:53:41 CST 2016");
        blogMessage.setTag("最好的我们");
        blogMessage.setAuthorId("000000000");
        blogMessage.setId(1);
        SaveUtil.save(blogMessage);

        blogMessage=new BlogMessagePO();
        blogMessage.setText("明天上午有人学一波C++吗？");
        blogMessage.setSendTime("Mon Aug 02 17:51:01 CST 2016");
        blogMessage.setTag("我爱学习");
        blogMessage.setAuthorId("141250095");
        blogMessage.setId(2);
        SaveUtil.save(blogMessage);

        blogMessage=new BlogMessagePO();
        blogMessage.setText("如何吃火龙果？挺急的，在线等");
        blogMessage.setSendTime("Mon Aug 03 07:23:14 CST 2016");
        blogMessage.setTag("救人一命胜吃七粒葡萄");
        blogMessage.setAuthorId("141250094");
        blogMessage.setId(3);
        SaveUtil.save(blogMessage);

        blogMessage=new BlogMessagePO();
        blogMessage.setText("北京时间7月31日晚，2016赛季中超联赛第20轮赛事继续进行，石家庄永昌主" +
                "场1-1战平河北华夏。阿洛伊西奥在第50分钟头球破门，连续两轮为河北华夏入球；第78分钟，" +
                "河北华夏替补出场的罗森文禁区内手球犯规，马修斯点球破门扳平比分。");
        blogMessage.setSendTime("Mon Aug 03 22:58:56 CST 2016");
        blogMessage.setTag("中超快讯");
        blogMessage.setAuthorId("141250001");
        blogMessage.setId(4);
        SaveUtil.save(blogMessage);

    }



    private static void add(UserPO user){
          Configuration cfg=new Configuration();
          SessionFactory sf=cfg.configure().buildSessionFactory();
          Session session=sf.openSession();
          session.beginTransaction();
          session.save(user);
          session.getTransaction().commit();
          session.close();
          sf.close();
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

    @Test
    public void getT(){
        for (int i=0;i<10;i++){
            System.out.println(new Date());
        }
    }
}
