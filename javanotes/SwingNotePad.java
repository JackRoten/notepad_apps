import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class SwingNotePad {
    private String filePath = "";

    public SwingNotePad() {
        // Create Frame
        JFrame frame = new JFrame("Swing Notepad");

        // Create a JTextArea and make it editable
        JTextArea textArea = new JTextArea();
        textArea.setEditable(true);

        // Increase the font of textArea
        textArea.setFont(new Font("Arial", Font.PLAIN, 20));

        // Create a JScrollPane to add scrolling
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Create buttons for "New", "Open", "Save"
        JButton newButton = new JButton("New");
        JButton openButton = new JButton("Open");
        JButton saveButton = new JButton("Save");

        // Add action listener for button actions
        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a new window
                SwingNotePad newNotePad = new SwingNotePad();
            }
        });

        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filePath = FilePicker.open();
                String fileText = FileReader.read(filePath);
                if (fileText != "") {
                    textArea.setText(fileText);
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (filePath.isEmpty()) {
                    // for preventing from unselected path problems
                    String strFolderPath = "";
                    while (strFolderPath.isEmpty()) {
                        strFolderPath = FolderPicker.open();
                    }
                    String strFileName = "";
                    while (strFileName == null || strFileName.isEmpty()) {
                        strFileName = JOptionPane.showInputDialog(null, "Enter new file name:", "File Name?", JOptionPane.QUESTION_MESSAGE);
                    }
                    // joining the folder Path and file name
                    Path folderPath = Paths.get(strFolderPath);
                    Path fileName = Paths.get(strFileName);
                    filePath = folderPath.resolve(fileName).toString();
                    saveButton.doClick();
                } else {
                    //converting the string to a list to save it
                    FileWriter.write(filePath, Arrays.asList(textArea.getText().split("\n")));
                    JOptionPane.showMessageDialog(frame, "File saved at: " + filePath);
                }
            }
        });

        // Create a JPanel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Add buttons to the panel
        buttonPanel.add(newButton);
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        // Set layout to BorderLayout
        frame.getContentPane().setLayout(new BorderLayout());

        // Add button panel to the top (BorderLayout.NORTH)
        frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        // Add the JScrollPane (containing the JTextArea) to the center
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Set frame size
        frame.setSize(800, 600);
        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // set frame visable
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingNotePad notePad = new SwingNotePad();
    }

}