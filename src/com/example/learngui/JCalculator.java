package com.example.learngui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;

/**
 * Created by Julian on 15/6/22.
 */
public class JCalculator extends JFrame implements ActionListener {
    private final String[] str = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            ".", "0", "=", "+"
    };

    JButton[] buttons = new JButton[str.length];
    JButton reset = new JButton("CE");
    JTextField display = new JTextField("0");

    public JCalculator() {
        super("Calculator");
        Font font = new Font("Consolas", Font.BOLD, 18);

        JPanel pnlHead = new JPanel(new BorderLayout());
        pnlHead.add(display, BorderLayout.CENTER);
        pnlHead.add(reset, BorderLayout.EAST);
        display.setFont(font);
        reset.setFont(font);

        JPanel pnlBody = new JPanel(new GridLayout(4,4));
        for (int i = 0; i < str.length; i++) {
            buttons[i] = new JButton(str[i]);
            buttons[i].setFont(font);
            pnlBody.add(buttons[i]);
        }

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(BorderLayout.NORTH, pnlHead);
        getContentPane().add(BorderLayout.CENTER, pnlBody);

        for (int i = 0; i < str.length; i++) {
            buttons[i].addActionListener(this);
        }
        reset.addActionListener(this);
        display.addActionListener(this);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 280);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object target = e.getSource();
        String cmd = e.getActionCommand();
        if (target == reset)
            handleReset();
        else if ("0123456789.".indexOf(cmd) > 0)
            handleNumber(cmd);
        else
            handleOperator(cmd);
    }

    boolean isFirstDigit = true;
    double number = 0.0;
    String operator = "=";

    public void handleNumber(String key) {
        if (isFirstDigit)
            display.setText(key);
        else if (!key.equals("."))
            display.setText(display.getText() + key);
        else if (display.getText().indexOf(".") < 0)
            display.setText(display.getText() + ".");
        else
            ;
        isFirstDigit = false;
    }

    public void handleReset() {
        display.setText("0");
        isFirstDigit = true;
        operator = "=";
    }

    public void handleOperator(String cmd) {
        double dDisplay = Double.valueOf(display.getText());
        switch (operator) {
            case "+": number += dDisplay; break;
            case "-": number -= dDisplay; break;
            case "*": number *= dDisplay; break;
            case "/": number /= dDisplay; break;
            case "=": number = dDisplay; break;
        }
        display.setText(String.valueOf(number));
        operator = cmd;
        isFirstDigit = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new JCalculator();
        });
    }
}
