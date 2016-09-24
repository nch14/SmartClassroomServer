package po;

import course.Course;
import course.CourseTimeAndClassroom;
import course.CourseTool;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 把课程翻译成课程表存进数据库
 * Created by chenh on 2016/9/10.
 */
public class TimeTableCourse {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * 学号
     */
    private String userKey;

    /**
     *学期
     */
    private String term;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 教师
     */
    private String courseTeacher;

    /**
     * 备注
     */
    private String note;

    /**
     * 课程类型
     */
    private String courseType;

    /**
     * 校区
     */
    private String campus;

    /**
     * 从那一节开始
     */
    private int startSection;

    /**
     * 持续几节课
     */
    private   int lastSection;

    /**
     * 周几
     */
    private   String courseDate;
    /**
     * 教室
     */
    private String courseClassroom;
    /**
     * 周数
     */
    private int week;


    public TimeTableCourse(){

    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public int getStartSection() {
        return startSection;
    }

    public void setStartSection(int startSection) {
        this.startSection = startSection;
    }

    public int getLastSection() {
        return lastSection;
    }

    public void setLastSection(int lastSection) {
        this.lastSection = lastSection;
    }

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public String getCourseClassroom() {
        return courseClassroom;
    }

    public void setCourseClassroom(String courseClassroom) {
        this.courseClassroom = courseClassroom;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public static ArrayList<TimeTableCourse> getTimeTableCourse(String userKey,Course course){
        ArrayList<TimeTableCourse> timeTableCourses=new ArrayList<>();

        for (CourseTimeAndClassroom courseTimeAndClassroom:course.courseTimeAndClassrooms){
            for (int i=0;i<courseTimeAndClassroom.weeks.length;i++){
                TimeTableCourse timeTableCourse=new TimeTableCourse();
                timeTableCourse.campus=course.campus;
                timeTableCourse.userKey=userKey;
                timeTableCourse.courseTeacher=course.courseTeacher;
                timeTableCourse.courseName=course.courseName;
                timeTableCourse.note=course.note;
                timeTableCourse.courseType=course.courseType;
                timeTableCourse.week=courseTimeAndClassroom.weeks[i];
                timeTableCourse.startSection= CourseTool.parseStartSection(courseTimeAndClassroom.courseTime);
                timeTableCourse.lastSection=CourseTool.parseLastSection(courseTimeAndClassroom.courseTime);
                timeTableCourse.courseClassroom=courseTimeAndClassroom.courseClassroom;
                timeTableCourse.courseDate=courseTimeAndClassroom.courseDate;
                timeTableCourse.term=computeTerm(userKey)+"";
                timeTableCourses.add(timeTableCourse);
            }
        }
        return timeTableCourses;
    }

    private static int computeTerm(String id){
        String year="20"+id.substring(0,2);
        int yearInt= Integer.parseInt(year);
        int nowYear= Calendar.getInstance().get(Calendar.YEAR);
        int term = nowYear-yearInt;
        int nowMonth= Calendar.getInstance().get(Calendar.MONTH)+1;
        if (nowMonth<8){
            term=term*2;
        }else {
            term=term*2+1;
        }
        return term;
    }
}
