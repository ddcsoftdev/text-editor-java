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

    private String fontFamily = Settings.DEFAULT_FONT_FAMILY;
    private int fontSize = Settings.DEFAULT_FONT_SIZE;
    private int fontStyle = Settings.DEFAULT_FONT_STYLE;

    private JTextArea textArea;
    private JScrollPane scrollPane;

    private JSpinner fontSizeSpinner;
    private JSpinner familySizeSpinner;
    //private JSpinner fontSizeSpinner;

    private JComboBox fontFamilyPicker;

    private JLabel fontSizeLabel;

    private JButton fontColorButton;

    public TextEditor(){
        initWindow();
        initTextArea();
        initScrollPane();
        initFontSizeSpinner();
        initFontSizeLabel();
        //initFamilySizeSpinner();
        initFontColorButton();
        initFontFamilyPicker();

        this.add(fontSizeLabel);
        this.add(fontSizeSpinner);
        this.add(fontColorButton);
        this.add(fontFamilyPicker);
        //this.add(familySizeSpinner);
        this.add(scrollPane);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==fontColorButton){
            JColorChooser colorChooser = new JColorChooser();
            Color color = JColorChooser.showDialog(null, "Choose a color", Color.black);
            textArea.setForeground(color);
        }
        if (e.getSource()==fontFamilyPicker){
            textArea.setFont(new Font(
                    (String) fontFamilyPicker.getSelectedItem(),
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

    private void initFontSizeSpinner(){
        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(
                new Dimension(50, 50));
        fontSizeSpinner.setValue(fontSize);
        fontSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fontSize = (int) fontSizeSpinner.getValue();
                textArea.setFont(new Font(
                        textArea.getFont().getFamily(),
                        textArea.getFont().getStyle(),
                        fontSize));
            }
        });
    }

    private void initFamilySizeSpinner(){
        familySizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(
                new Dimension(50, 50));
        fontSizeSpinner.setValue(0);
        fontSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(
                        (String) familySizeSpinner.getValue(),
                        textArea.getFont().getStyle(),
                        (int) textArea.getFont().getSize()));
            }
        });
    }

    private void initFontSizeLabel(){
        fontSizeLabel = new JLabel("Font: ");
    }

    private void initFontColorButton(){
        fontColorButton = new JButton("Color");
        fontColorButton.addActionListener(this);
    }

    private void initFontFamilyPicker(){
        String[] availableFamilies = GraphicsEnvironment.
                getLocalGraphicsEnvironment().
                getAvailableFontFamilyNames();
        fontFamilyPicker = new JComboBox(availableFamilies);
        //You can add this since you have the ActionListener implenented in this class
        //If create in another class you should pass this class as reference
        fontFamilyPicker.addActionListener(this);
        fontFamilyPicker.setSelectedItem(Settings.DEFAULT_FONT_FAMILY);
    }
}
