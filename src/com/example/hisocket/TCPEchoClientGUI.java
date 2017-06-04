package com.example.hisocket;

import java.net.*;           // for Socket
import java.io.*;            // for IOException and Input/OutputStreams
import java.awt.event.*;     // for ActionListener
import javax.swing.*;        // for JFrame

public class TCPEchoClientGUI extends JFrame implements ActionListener {

    private JTextField echoSend;  // Field to entry echo string
    private JTextArea echoReply;  // Area to receive reply
    private Socket socket;        // Client socket
    private DataInputStream in;   // Socket input stream
    private OutputStream out;     // Socket output stream

    public static void main(String[] args) {
        JFrame frame = new TCPEchoClientGUI("127.0.0.1", 8003);
        frame.setVisible(true);
    }

    public TCPEchoClientGUI(String server, int servPort) {

        super("TCP Echo Client");  // Set the window title
        setSize(300, 300);                // Set the window size

        // Set echo send text field
        echoSend = new JTextField();
        getContentPane().add(echoSend, "South");
        echoSend.addActionListener(this);

        // Set echo replay text area
        echoReply = new JTextArea(8, 40);
        JScrollPane scrollPane = new JScrollPane(echoReply);
        getContentPane().add(scrollPane, "Center");

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    socket.close();
                } catch (Exception exception) {}
                System.exit(0);
            }
        });

        try {
            // Create socket and fetch I/O streams
            socket = new Socket(server, servPort);

            in = new DataInputStream(socket.getInputStream());
            out = socket.getOutputStream();
        } catch (IOException exception) {
            echoReply.append(exception.toString() + "\n");
        }
    }

    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == echoSend) {
            byte[] byteBuffer = echoSend.getText().getBytes();
            try {
                out.write(byteBuffer);
                in.readFully(byteBuffer);
                echoReply.append(new String(byteBuffer) + "\n");
                echoSend.setText("");
            } catch (IOException e) {
                echoReply.append("ERROR\n");
            }
        }
    }
}
