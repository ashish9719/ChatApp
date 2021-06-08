import java.net.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;


class Server extends JFrame{
    //java.net
    ServerSocket server;
    Socket socket;
    //java.io
    BufferedReader br;
    PrintWriter out;

     //Components
     private JLabel heading = new JLabel("Server Area");
     private JTextArea messageArea = new JTextArea();
     private JTextField messageInput = new JTextField();
     private Font font  = new Font("Roboto",Font.PLAIN,20);

    public Server(){
        try {
            server = new ServerSocket(8888);
            System.out.println("Server is ready");
            System.out.println("waiting....");
            socket = server.accept();

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream());
            createGUI();
            handleEvents();
            startReading();
            //startWriting();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void handleEvents() {

        messageInput.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // key code for enter == 10
                if(e.getKeyCode()==10){

                    String contentToSend = messageInput.getText();
                    messageArea.append("Me : "+contentToSend +"\n");
                    out.println(contentToSend);
                    out.flush();
                    messageInput.setText("");
                    messageInput.requestFocus();

                }
                
            }

        });
    }

    private void createGUI() {

        this.setTitle("Server Messenger");
        this.setSize(600,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        heading.setFont(font);
        messageArea.setFont(font);
        messageInput.setFont(font);

        heading.setIcon(new ImageIcon("logo.png"));
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        messageInput.setHorizontalAlignment(SwingConstants.CENTER);
        messageArea.setEditable(false);

        this.setLayout(new BorderLayout());

        this.add(heading,BorderLayout.NORTH);
        JScrollPane jScrollPane = new JScrollPane(messageArea);
        this.add(jScrollPane,BorderLayout.CENTER);
        this.add(messageInput,BorderLayout.SOUTH);
        
        this.setVisible(true);
    }

    public void startReading(){
        //thread to read
        Runnable r1 = ()->{
            System.out.println("Reader Started");
           try {
            while(true){
                String message = br.readLine();
                if(message.equals("exit")){
                    System.out.println("client terminated");
                    JOptionPane.showMessageDialog(this,"Server terminated the chat");
                    messageInput.setEnabled(false);
                    socket.close();
                    break;
                    }
                //System.out.println("client : "+message);
                messageArea.append("Server : "+message+"\n");  
            }
           } catch (Exception e) {
               //e.printStackTrace();
               System.out.println("Connection Closed");
           }
        };
        new Thread(r1).start();
    }
    public void startWriting(){
        //thread to take data and send to client
        System.out.println("writer started");
        Runnable r2 =()->{

            try {
                while(!socket.isClosed()){
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine();
                    out.println(content);
                    out.flush();
                    if(content.equals("exit")){
                        socket.close();
                        break;
                    }
                   
            }
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("Connection Closed");
            }

        };

        new Thread(r2).start();
    }

    public static void main(String[] args) {
    System.out.println("Server");
    new Server();
    }
}