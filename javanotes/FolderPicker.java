import javax.swing.*;
import java.io.File;

public class FolderPicker {
    public static String open() {
        JFileChooser folderChooser = new JFileChooser();

        // set selection mode to directories only
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // show the open dialog and store the user's response
        int result = folderChooser.showOpenDialog(null);

        // check if a folder was selected
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = folderChooser.getSelectedFile();
            
            return selectedFolder.getAbsolutePath();
        } else {
            return "";
        } 
    }
}