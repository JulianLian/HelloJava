package com.example.hisocket;

import com.sun.deploy.util.ArrayUtil;
import com.sun.tools.javac.util.ArrayUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by Julian on 15/7/17.
 */
public class TcpClientGUI extends JFrame implements ActionListener{

    JTextField address = new JTextField("0.0.0.0");
    JButton connect = new JButton("connect");
    JTextField command = new JTextField("type in command here");
    JButton send = new JButton("send");
    JTextArea receive = new JTextArea(8,80);

    CmdTcpClient client;

    public TcpClientGUI() {
        super("Tcp Client");
        Font font = new Font("Consolas", Font.BOLD, 18);

        JPanel pnlAddress = new JPanel(new BorderLayout());

        pnlAddress.add(address, BorderLayout.CENTER);
        pnlAddress.add(connect, BorderLayout.EAST);
        address.setFont(font);
        connect.setFont(font);

        JPanel pnlSend = new JPanel(new BorderLayout());

        pnlSend.add(command, BorderLayout.CENTER);
        pnlSend.add(send, BorderLayout.EAST);
        command.setFont(font);
        send.setFont(font);

        JPanel pnlInteract = new JPanel(new GridLayout(2,1));

        pnlInteract.add(pnlSend);
        pnlInteract.add(new JScrollPane(receive));
        receive.setFont(font);
        receive.setAutoscrolls(true);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(BorderLayout.NORTH, pnlAddress);
        getContentPane().add(BorderLayout.CENTER, pnlInteract);

        address.addActionListener(this);
        connect.addActionListener(this);
        command.addActionListener(this);
        send.addActionListener(this);
//        receive.addActionListener(this);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 480);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object cmd = e.getSource();
        if (cmd == connect || cmd == address) {
            client = handleConnect();
        } else if (cmd == send || cmd == command) {
            try {
                handleSend();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    private CmdTcpClient handleConnect() {
        String serverAddress = address.getText();
        try {
            return new CmdTcpClient(serverAddress, 8003);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void handleSend() throws IOException {
        byte[] delimit = {0x0d,0x0a};

        byte[] msg = new byte[65536];

        byte[] cmd = command.getText().concat(new String(delimit)).getBytes();

        System.out.println("server: " + client.clientSocket.getRemoteSocketAddress());

        System.out.println("cmd len: " + cmd.length);
        System.out.println("Cmd: " + new String(cmd));

        client.outputStream.write(cmd);

        client.outputStream.flush();

//        int totalSize = 0;
//        int rcvSize = 0;
//        while (totalSize < 65536) {
//            rcvSize = client.inputStream.read(msg, totalSize, 65536 - totalSize);
//            if (rcvSize == -1)
//                break;
//            else {
//                totalSize += rcvSize;
//                if ((totalSize >= 2) &&
//                        (msg[totalSize - 2] == 0x0d && msg[totalSize - 1] == 0x0a)) {
//                    break;
//                }
//            }
//        }

        client.inputStream.read(msg);

        System.out.println("Received msg: " + msg.toString());
        receive.append(new String(msg));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new TcpClientGUI();
        });
    }
}

class CmdTcpClient{

    protected DataInputStream inputStream;
    protected OutputStream outputStream;
    protected Socket clientSocket;

    public CmdTcpClient(String address, int port) throws IOException {
        clientSocket = new Socket(address, port);
        System.out.println("address: " + clientSocket.getLocalAddress()
                + "\tport: " + clientSocket.getLocalPort() + "\n");
        inputStream = new DataInputStream(clientSocket.getInputStream());
        outputStream = clientSocket.getOutputStream();
    }
}