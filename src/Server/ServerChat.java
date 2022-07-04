package Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerChat implements ActionListener
{
    JFrame jf;
    JTextField jt;
    JTextArea ja;
    Image icon;
    InetAddress ia;
//    String ipaddress;
    String ip_address;
    ServerSocket ss;
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;

    //-----------------Thread Created---------------------
    Thread t = new Thread(){
        public void run()
        {
            while(true)
            {
                readMessage();
            }
        }
    };
            //-----------------Thread ended-----------------------
    public ServerChat()
    {

        jf = new JFrame("Server");
        jf.setSize(500 , 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jt = new JTextField();
        jt.addActionListener(this);
        jf.add(jt , BorderLayout.SOUTH);
        ja = new JTextArea();
        Font f = new Font("Arial" , 1 , 16);
        ja.setFont(f);
        ja.setEditable(false);
        //jf.add(ja);

        icon = Toolkit.getDefaultToolkit().getImage("D:\\Java Projects dev\\Chat Application\\chat.jpg");
        jf.setIconImage(icon);

        JScrollPane js = new JScrollPane(ja);
        js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jf.add(js);

        jf.setVisible(true);
    }

    public String getIpAddress() {

        try {
            ia = InetAddress.getLocalHost();
            ip_address = ia.getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return ip_address;
    }

    public void waitingForClient()
    {
        ip_address = getIpAddress();
        try {
            ss = new ServerSocket(1111);
            ja.setText("To connect to Server provide the IP Address: "+ip_address);
            s = ss.accept();
            ja.setText("Client Connected\n");
            ja.append("--------------------------\n");
            //ja.setEditable(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void setIoStreams()
    {
        try {
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        t.start();
    }

    public void readMessage()
    {
        try {
            String message = dis.readUTF();
            showMessage("Client: "+message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message)
    {
        try {
            dos.writeUTF(message);
            dos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showMessage(String message)
    {
        ja.append(message+"\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == jt)
        {
            sendMessage(jt.getText());
            ja.append(jt.getText()+"\n");
            jt.setText("");
        }
    }
}









//import java.awt.BorderLayout;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.net.InetAddress;
//import java.net.ServerSocket;
//import java.net.Socket;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
//import javax.swing.JFrame;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//
///**
// *
// * @author Deepak
// */
//public class ServerChat implements ActionListener
//{
//    private JFrame serverframe;
//    private JTextArea ta;
//    private JScrollPane scrollpane;
//    private JTextField tf;
//
//    private ServerSocket serversocket;
//
//    private InetAddress inet_address;
//
//    private Socket socket;
//    private DataInputStream dis;
//    private DataOutputStream dos;
//
//    //---------------------------Thread created--------------------------------------
//    Thread thread=new Thread(){
//        public void run()
//        {
//            while(true)
//            {
//                readMessage();
//            }
//        }
//    };
//    //-------------------------------------------------------------------------------
//
//    ServerChat()
//    {
//        serverframe=new JFrame("Server");
//        serverframe.setSize(500,500);
//        serverframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        ta=new JTextArea();
//        ta.setEditable(false);
//        Font font=new Font("Arial", 1, 16);
//        ta.setFont(font);
//        scrollpane=new JScrollPane(ta);
//        serverframe.add(scrollpane);
//
//        tf=new JTextField();
//        tf.addActionListener(this);
//        tf.setEditable(false);
//        serverframe.add(tf, BorderLayout.SOUTH);
//
//        serverframe.setVisible(true);
//}
//
//    public void waitingForClient()
//    {
//        try
//        {
//            String ipaddress=getIpAddress();
//
//            serversocket=new ServerSocket(1111);
//            ta.setText("To connect with server, please provide IP Address : "+ipaddress);
//            socket=serversocket.accept();
//            ta.setText("Client Connected\n");
//            ta.append("----------------------------------------------------\n");
//
//            tf.setEditable(true);
//        }
//        catch(Exception e)
//        {
//            System.out.println(e);
//        }
//    }
//    public String getIpAddress()
//    {
//        String ip_address="";
//        try
//        {
//            inet_address=InetAddress.getLocalHost();
//            ip_address=inet_address.getHostAddress();
//        }
//        catch(Exception e)
//        {
//            System.out.println(e);
//        }
//        return ip_address;
//    }
//
//    void setIoStreams()
//    {
//        try
//        {
//            dis=new DataInputStream(socket.getInputStream());
//            dos=new DataOutputStream(socket.getOutputStream());
//        }
//        catch(Exception e)
//        {
//            System.out.println(e);
//        }
//        thread.start();
//    }
//
//    public void sendMessage(String message)
//    {
//        try
//        {
//            dos.writeUTF(message);
//            dos.flush();
//        }
//        catch(Exception e)
//        {
//            System.out.println(e);
//        }
//    }
//
//    public void readMessage()
//    {
//        try
//        {
//            String message=dis.readUTF();
//            showMessage("Client : "+message);
//        }
//        catch(Exception e)
//        {
//            System.out.println(e);
//        }
//    }
//
//    public void showMessage(String message)
//    {
//        ta.append(message+"\n");
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        sendMessage(tf.getText());
//        ta.append(tf.getText()+"\n");
//        tf.setText("");
//    }
//}