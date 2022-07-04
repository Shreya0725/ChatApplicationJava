package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientChat implements ActionListener
{
    JFrame jf;
    JTextField jt;
    JTextArea ja;
    Image icon;
    Socket s;
    String ip_address;
    DataInputStream dis;
    DataOutputStream dos;

    //-----------------Thread Created---------------------
    Thread t = new Thread() {
        public void run()
        {
            while(true)
            {
                readMessage();
            }
        }
    };
    //-----------------Thread ended-----------------------
    public ClientChat()
    {
        String str = JOptionPane.showInputDialog("Enter the IP Address: ");
        if(str != null)
        {
            if(!str.equals(""))
            {
                connectedToServer();

                jf = new JFrame("Client");
                jf.setSize(500 , 500);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jt = new JTextField();
                jt.addActionListener(this);
                jf.add(jt , BorderLayout.SOUTH);
                ja = new JTextArea();
                Font f = new Font("Arial" , 1 , 16);
                ja.setFont(f);
                ja.setEditable(false);
                jf.add(ja);

                icon = Toolkit.getDefaultToolkit().getImage("D:\\Java Projects dev\\Chat Application\\chat.jpg");
                jf.setIconImage(icon);

                JScrollPane js = new JScrollPane(ja);
                js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                jf.add(js);

                jf.setVisible(true);
            }
        }
    }

    public void connectedToServer()
    {
       try
       {
           s = new Socket(ip_address , 1111);
           //s.close();
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
        //s.accept();
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
            showMessage("Server: "+message);
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
























