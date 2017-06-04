package com.example.learngui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Julian on 15/6/23.
 */
public class BlockMoveGame extends JFrame {
    final int RC = 4;
    final int N = RC * RC;

    int []num = new int[N];
    JButton []btn = new JButton[N];
    JButton btnStart = new JButton("start");

    public BlockMoveGame() {
        setTitle("简单的排块游戏");
        setSize(300,350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        p1.setLayout(new GridLayout(RC, RC));
        p2.add(btnStart);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(p1, BorderLayout.CENTER);
        getContentPane().add(p2, BorderLayout.SOUTH);

        Font font = new Font("Times New Rome", 0, 24);
        for (int i = 0; i < N; i++) {
            num[i] = i;
            btn[i] = new JButton("" + (i+1));
            btn[i].setFont(font);
            p1.add(btn[i]);
            btn[i].setVisible(true);
        }
        btn[N-1].setVisible(false);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStart_Click();
            }
        });

        for (int i = 0; i < N; i++) {
            btn[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int j = 0; j < N; j++) {
                        if ((JButton)e.getSource() == btn[j])
                            btn_Click(j);
                    }
                }
            });
        }
    }

    int findBlank(){
        for(int i=0; i<N; i++ ){
            if( num[i] == N-1 ) return i;
        }
        return -1;
    }

    public void btnStart_Click() {
        for (int i = 0; i < 500; i++) {
            int m = (int)(Math.random() * N);
            int n = (int)(Math.random() * N);
            int t = num[m]; num[m] = num[n]; num[n] = t;
        }

        for (int i = 0; i < N; i++) {
            btn[i].setText("" + (num[i] + 1));
            btn[i].setVisible(true);
        }
        int blank = findBlank();
        btn[blank].setVisible(false);
    }

    void btn_Click(int index){
        int blank = findBlank();           //找到空白按钮(隐藏的)
        if( isNeighbor(blank, index)){       //如果相邻
            btn[index].setVisible( false );  //一个隐藏,一个显示
            btn[blank].setVisible( true );   //并交换其上的数字
            int t = num[blank];
            num[blank] = num[index];
            num[index] = t;
            btn[blank].setText( ""+ (num[blank] + 1));
            btn[index].setText( ""+ (num[index] + 1));
            btn[blank].requestFocus();  //焦点移到原按钮上，以让用户看清
        }
        checkResult();  //调用过程,检查是否完成
    }

    //判断是否相邻
    boolean isNeighbor(int a, int b){
        boolean r = false;
        if( a == b - RC || a == b + RC )
            r = true;  //上下相邻
        if( (a == b - 1 || a == b + 1) && a / RC == b / RC )
            r = true; //左右相邻,注意要在同一排
        return r;
    }
    //检查结果是否完全到位
    void checkResult(){
        for(int i=0; i<N; i++ ){
            if( num[i] != i ) return;
        }
        JOptionPane.showMessageDialog(this,
                "你赢了!请点击 [开始] 再来一次.");
    }

    public static void main(String... args) {
        SwingUtilities.invokeLater(()->{
            new BlockMoveGame().setVisible(true);
        });
    }
}
