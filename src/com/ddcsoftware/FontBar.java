package com.ddcsoftware;

import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class FontBar extends JPanel{

    private TextEditor textEditor;
    private JSpinner fontSizeSpinner;
    private JSpinner familySizeSpinner;
    //private JSpinner fontSizeSpinner;

    JComboBox fontFamilyPicker;

    private JLabel fontSizeLabel;

    JButton fontColorButton;

    public FontBar(TextEditor textEditor) {

        this.textEditor = textEditor;
        initFontSizeSpinner();
        initFontSizeLabel();
        //initFamilySizeSpinner();
        initFontColorButton();
        initFontFamilyPicker();

        this.add(fontSizeLabel);
        this.add(fontSizeSpinner);
        this.add(fontColorButton);
        this.add(fontFamilyPicker);
    }

    private void initFontSizeSpinner() {
        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(
                new Dimension(45, 30));
        fontSizeSpinner.setValue(textEditor.fontSize);
        fontSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textEditor.fontSize = (int) fontSizeSpinner.getValue();
                textEditor.textArea.setFont(new Font(
                        textEditor.textArea.getFont().getFamily(),
                        textEditor.textArea.getFont().getStyle(),
                        textEditor.fontSize));
            }
        });
    }

    private void initFamilySizeSpinner() {
        familySizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(
                new Dimension(50, 50));
        fontSizeSpinner.setValue(0);
        fontSizeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textEditor.textArea.setFont(new Font(
                        (String) familySizeSpinner.getValue(),
                        textEditor.textArea.getFont().getStyle(),
                        (int) textEditor.textArea.getFont().getSize()));
            }
        });
    }

    private void initFontSizeLabel() {
        fontSizeLabel = new JLabel("Font: ");
    }

    private void initFontColorButton() {
        fontColorButton = new JButton("Color");
        fontColorButton.addActionListener(textEditor);
    }

    private void initFontFamilyPicker() {
        String[] availableFamilies = GraphicsEnvironment.
                getLocalGraphicsEnvironment().
                getAvailableFontFamilyNames();
        fontFamilyPicker = new JComboBox(availableFamilies);
        //You can add this since you have the ActionListener implenented in this class
        //If create in another class you should pass this class as reference
        fontFamilyPicker.addActionListener(textEditor);
        fontFamilyPicker.setSelectedItem(Settings.DEFAULT_FONT_FAMILY);
    }
}
