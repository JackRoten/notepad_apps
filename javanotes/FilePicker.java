import javax.swing.*;
import java.io.File;

public class FilePicker {
    public static String open() {
        JFileChooser fileChooser = new JFileChooser();

        // show the open dialog
        int result = fileChooser.showOpenDialog(null);

        // check if a file was selected
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            return selectedFile.getAbsolutePath();
        } else {
            return "";
        }
    }
}