package util;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import po.ClassroomPO;
import po.RentLogPO;
import po.UserPO;
import vo.Classroom;
import vo.RentLog;
import vo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenh on 2016/7/27.
 */
public class LoadUtil {

    public static ArrayList<Classroom> LoadClassroom(){
        Configuration cfg=new Configuration();
        SessionFactory sf=cfg.configure().buildSessionFactory();
        Session session=sf.openSession();

        String hql = "from ClassroomPO ";
//        int result = session.createQuery(hql).executeUpdate();
        Query query = session.createQuery(hql);
        List<ClassroomPO> list = query.list();

        session.close();
        sf.close();

        ArrayList<Classroom> classrooms=new ArrayList<>();
        for (ClassroomPO classroomPO:list){
            Classroom classroom=new Classroom();
            classroom.name=classroomPO.getName();
            classrooms.add(classroom);
        }
        return classrooms;
    }


    public static ArrayList<RentLog> loadRentLog(int type){
        Configuration cfg=new Configuration();
        SessionFactory sf=cfg.configure().buildSessionFactory();
        Session session=sf.openSession();
        String hql = "from ClassroomPO ";
        switch (type){
            case RentLog.WAITING_TO_HANDLE:
                hql+="where state = 1";
                break;
            case RentLog.PASS_BUT_NOT_TAKE_EFFECT:
                hql+="where state = 2";
                break;
            case RentLog.PASS_AND_IN_USE:
                hql+="where state= 3";
                break;
        }
        Query query = session.createQuery(hql);
        List<RentLogPO> list = query.list();

        session.close();
        sf.close();

        ArrayList<RentLog> rentLogs=new ArrayList<>();
        for (RentLogPO rentLogPO:list){
            RentLog rentLog=PO2VO.po2vo(rentLogPO);
            rentLogs.add(rentLog);
        }
        return rentLogs;
    }


    public static UserPO loadUser(String id){
        Configuration cfg=new Configuration();
        SessionFactory sf=cfg.configure().buildSessionFactory();
        Session session=sf.openSession();
        session.beginTransaction();
        UserPO user=session.get(UserPO.class,id);
        session.getTransaction().commit();
        session.close();
        sf.close();
        if (user==null)
            return null;
        return user;
    }

    public static ArrayList<String> loadStudentIDs(String uuid){
        Configuration cfg=new Configuration();
        SessionFactory sf=cfg.configure().buildSessionFactory();
        Session session=sf.openSession();
        String hql = "select r.id, from RentLogUser as r where r.uuid="+uuid;
        Query query = session.createQuery(hql);
        List<String> list = query.list();
        session.close();
        sf.close();
        return (ArrayList<String>) list;
    }
}
