package com.ddcsoftware;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextEditor extends JFrame implements ActionListener {
    private final int WINDOW_H = Settings.WINDOW_H;
    private final int WINDOW_W = Settings.WINDOW_W;

    FontBar fontBar;
    String fontFamily = Settings.DEFAULT_FONT_FAMILY;
    int fontSize = Settings.DEFAULT_FONT_SIZE;
    int fontStyle = Settings.DEFAULT_FONT_STYLE;

    JTextArea textArea;
    private JScrollPane scrollPane;



    public TextEditor(){

        initWindow();
        initTextArea();
        initScrollPane();
        fontBar = new FontBar(this);


        this.add(fontBar);
        //this.add(familySizeSpinner);
        this.add(scrollPane);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (fontBar == null)
            return;
        if (e.getSource()==fontBar.fontColorButton){
            JColorChooser colorChooser = new JColorChooser();
            Color color = JColorChooser.showDialog(null, "Choose a color", Color.black);
            textArea.setForeground(color);
        }
        if (e.getSource()==fontBar.fontFamilyPicker){
            textArea.setFont(new Font(
                    (String) fontBar.fontFamilyPicker.getSelectedItem(),
                    fontStyle, fontSize));
        }
    }

    private void initWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DDC Text Editor");
        setSize(WINDOW_W, WINDOW_H);
        setLayout(new FlowLayout());
        //Set in the center of screen
        setLocationRelativeTo(null);
    }

    private void initTextArea(){
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font(fontFamily, fontStyle, fontSize));
    }

    private void initScrollPane(){
        scrollPane = new JScrollPane(textArea);
        int scrollSizeW = WINDOW_W - (WINDOW_W / 18);
        int scrollSizeH = WINDOW_H - (WINDOW_H / 10);
        scrollPane.setPreferredSize(new Dimension(scrollSizeW, scrollSizeH));
        scrollPane.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

    }


}
