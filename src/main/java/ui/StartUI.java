package ui;

import model.ClassroomManage;
import vo.Classroom;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by chenh on 2016/7/27.
 */
public class StartUI {



    public static void startUI(){
        JFrame frame=new JFrame();
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0,0,1000,625);

        MainPanel mainPanel=new MainPanel();
        frame.add(mainPanel);

        ArrayList<Classroom> classrooms=ClassroomManage.getClassroomManage().getmClassrooms();
        for (int i=0;i<classrooms.size();i++){
            mainPanel.addClassroomPanel(new ClassroomPanel(classrooms.get(i).name));
        }
        mainPanel.sort();


        frame.setVisible(true);
    }
}
