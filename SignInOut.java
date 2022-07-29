import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

public class SignInOut extends JFrame implements ActionListener {
    private JButton signInButton;
    private JButton signOutButton;
    private JButton confirmButton;
    private JButton goBackButton;
    JFrame confirm = new JFrame("Confirm");
    ArrayList<String> names = new ArrayList<String>();
    JList<String> list;
    public SignInOut (){
        super("Sign In/Sign Out");
        goBackButton = new JButton("Go Back");
        confirmButton = new JButton("Confirm");
        goBackButton.addActionListener(this);
        confirmButton.addActionListener(this);
        names.add("<Select Your Name>");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        try{
            Scanner in = new Scanner(new File("names.csv"));
            while(in.hasNextLine()){
                String s = in.nextLine();
                names.add(s);
                //System.out.println(s);
            }
        }
        catch(Exception e){}
        String[] arr = new String[names.size()];
        //arr = names.toArray(arr);
        list = new JList<String>(names.toArray(arr));
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        //list.setVisibleRowCount(-1);
        JScrollPane namesList = new JScrollPane(list);
        namesList.setMaximumSize(new Dimension(400,400));
        namesList.setAlignmentX(Component.CENTER_ALIGNMENT);
        signInButton = new JButton("Sign In");
        signOutButton = new JButton("Sign Out");
        signInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(namesList);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(signInButton);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(signOutButton);
        mainPanel.add(Box.createVerticalStrut(25));
        signInButton.addActionListener(this);
        signOutButton.addActionListener(this);
        add(mainPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == signInButton){
            String s = list.getSelectedValue();
            confirmWindow("Confirm Sign-In For: " + s);
        }else if(e.getSource() == signOutButton){
            String s = list.getSelectedValue();
            confirmWindow("Confirm Sign-Out For: " + s);
        }else if(e.getSource() == confirmButton){
            confirm.dispose();
        }else if(e.getSource() == goBackButton){
            confirm.dispose();
        }
    }
    public void confirmWindow(String s){
            JPanel topPanel = new JPanel();
            topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
            
            JLabel label = new JLabel("Confirm Sign-in For: " + s);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            topPanel.add(label);
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(goBackButton);
            buttonPanel.add(confirmButton);
            confirm.add(topPanel, BorderLayout.NORTH);
            confirm.add(buttonPanel, BorderLayout.CENTER);
            setFocusable(false);
            confirm.setSize(500,100);
            confirm.setVisible(true);
    }
    public void logSignOut(String name){

    }
    public void logSignIn(String name){

    }
    public static void main(String[] args) {
        new SignInOut();
    }
}