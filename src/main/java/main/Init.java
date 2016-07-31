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
import po.ClassroomPO;
import po.UserPO;
import vo.User;

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
        userPO.setId("141250001");
        userPO.setIdentify(User.STUDENT);
        userPO.setPassword("666666");
        add(userPO);
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
}
