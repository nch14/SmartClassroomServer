package util;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import po.*;
import vo.*;

import java.util.ArrayList;
import java.util.Calendar;
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
        String hql = "from RentLogPO as c ";
        switch (type){
            case RentLog.WAITING_TO_HANDLE:
                hql+="where c.state = 1";
                break;
            case RentLog.PASS_BUT_NOT_TAKE_EFFECT:
                hql+="where c.state = 2";
                break;
            case RentLog.PASS_AND_IN_USE:
                hql+="where c.state= 3";
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


    public static ArrayList<BlogMessage> loadBlogMessages(long sinceId,long maxId,int items){
        Configuration cfg=new Configuration();
        SessionFactory sf=cfg.configure().buildSessionFactory();
        Session session=sf.openSession();
        String hql = "from BlogMessagePO as r ";
        if (sinceId!=-1&&maxId!=-1){
            hql+="where r.id>"+sinceId+"and r.id<"+maxId;
        }else if (sinceId!=-1){
            hql+="where r.id>"+sinceId;
        }else if (maxId!=-1){
            hql+="where r.id<"+maxId;
        }
        Query query = session.createQuery(hql);
        List<BlogMessagePO> list = query.list();
        session.close();
        sf.close();

        ArrayList<BlogMessage> blogMessages=new ArrayList<>();
        if (list!=null){
            int max=Math.min(list.size(),items);
            for (int i=0;i<max;i++){
                blogMessages.add(PO2VO.po2vo(list.get(i)));
            }
        }
        return blogMessages;
    }

    public static BlogMessagePO loadBlogMessages(long id){
        Configuration cfg=new Configuration();
        SessionFactory sf=cfg.configure().buildSessionFactory();
        Session session=sf.openSession();
        String hql = "from BlogMessagePO as r where r.id = "+id;
        Query query = session.createQuery(hql);
        List<BlogMessagePO> list = query.list();
        session.close();
        sf.close();
        if (list!=null){
            return list.get(0);
        }
        return null;
    }

    public static AttitudePO loadBlogMessages(long sheetId,String userId,boolean attitude){
        Configuration cfg=new Configuration();
        SessionFactory sf=cfg.configure().buildSessionFactory();
        Session session=sf.openSession();
        String hql = "from AttitudePO as r where r.sheetId = "+sheetId+ " and r.userId =" +userId+" and r.attitude = "+attitude;
        Query query = session.createQuery(hql);
        List<AttitudePO> list = query.list();
        session.close();
        sf.close();
        if (list!=null){
            return list.get(0);
        }
        return null;
    }

    public static ArrayList<AttitudePO> loadBlogMessages(long sheetId,String userId){
        Configuration cfg=new Configuration();
        SessionFactory sf=cfg.configure().buildSessionFactory();
        Session session=sf.openSession();
        String hql = "from AttitudePO as r where r.sheetId = "+sheetId+ " and r.userId =" +userId;
        Query query = session.createQuery(hql);
        List<AttitudePO> list = query.list();
        session.close();
        sf.close();
        return (ArrayList<AttitudePO>) list;
    }


    public static ArrayList<AttitudePO> loadAttitudePOs(long sheetId){
        Configuration cfg=new Configuration();
        SessionFactory sf=cfg.configure().buildSessionFactory();
        Session session=sf.openSession();
        String hql = "from AttitudePO as r where r.sheetId = "+sheetId;
        Query query = session.createQuery(hql);
        List<AttitudePO> list = query.list();
        session.close();
        sf.close();
        return (ArrayList<AttitudePO>) list;
    }

    public static int countAttitudePOs(long sheetId,boolean attitude){
        Configuration cfg=new Configuration();
        SessionFactory sf=cfg.configure().buildSessionFactory();
        Session session=sf.openSession();
        String hql = "select count (*) from AttitudePO as r where r.sheetId = "+sheetId + " and r.attitude = "+attitude;
        Query query = session.createQuery(hql);
        int count = (int) query.uniqueResult();
        session.close();
        sf.close();
        return count;
    }


    public static ArrayList<BlogComments> loadBlogComments(long sheetId){
        Configuration cfg=new Configuration();
        SessionFactory sf=cfg.configure().buildSessionFactory();
        Session session=sf.openSession();
        String hql = "from BlogCommentPO as r where r.rawMessageId = "+sheetId;
        Query query = session.createQuery(hql);
        List<BlogCommentPO> list = query.list();
        session.close();
        sf.close();

        ArrayList<BlogComments> blogComments=new ArrayList<>();
        if (list!=null){
            for (BlogCommentPO blogCommentPO:list){
                blogComments.add(PO2VO.po2vo(blogCommentPO));
            }
        }
        return blogComments;
    }

    public static ArrayList<TimeTableCourse> loadTimeTableCourses(int week){
        Configuration cfg=new Configuration();
        SessionFactory sf=cfg.configure().buildSessionFactory();
        Session session=sf.openSession();
        String hql = "from TimeTableCourse as t where t.week ="+week;
        Query query = session.createQuery(hql);
        List<TimeTableCourse> list = query.list();
        session.close();
        sf.close();

        return (ArrayList<TimeTableCourse>) list;
    }

    public static ArrayList<TimeTableCourse> loadTimeTableCourses(int week,String userKey){
        Configuration cfg=new Configuration();
        SessionFactory sf=cfg.configure().buildSessionFactory();
        Session session=sf.openSession();
        String hql = "from TimeTableCourse as t where t.week ="+week+"and t.userKey = "+userKey;
        Query query = session.createQuery(hql);
        List<TimeTableCourse> list = query.list();
        session.close();
        sf.close();

        return (ArrayList<TimeTableCourse>) list;
    }


}
