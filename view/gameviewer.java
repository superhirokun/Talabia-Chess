package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameviewer {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            JFrame frame = new JFrame("Tabalia chess thing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(7,7));

            for (int i = 0; i < 7; i++) {
                for (int index = 0; index < 7; index++) {
                    JButton button = new JButton();
                    button.addActionListener(new ButtonClickListener());
                    ImageIcon icon = new ImageIcon("view/pawns.jpg");
                    button.setIcon(icon);

                    frame.add(button);
                }
            }

            frame.pack();
            frame.setVisible(true);
        });
        

    }


    public static class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JButton sourceButton = (JButton) e.getSource();
            sourceButton.setSelected(!sourceButton.isSelected());
            if (sourceButton.isSelected()) {
                sourceButton.setBackground(Color.BLUE);
            } else {
                sourceButton.setBackground(null);
            }
        }
    }
    
}