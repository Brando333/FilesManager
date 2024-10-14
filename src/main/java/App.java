import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

File workingDirectory;

void main() {

    String path = "";
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    int returnValue = chooser.showOpenDialog(null);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedDirectory = chooser.getSelectedFile();
        path = selectedDirectory.getAbsolutePath();

    } else {
        System.out.println("No se seleccionó ningún directorio.");
    }

    workingDirectory = new File(path);
    createDirectoriesByExtensionFiles();

}

private void createDirectoriesByExtensionFiles() {
    for (File file : Objects.requireNonNull(workingDirectory.listFiles())) {
        if (file.isFile()) {
            String fileExtension = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
            File newFolder = new File(workingDirectory, fileExtension);
            if (!newFolder.exists()) newFolder.mkdir();

            Path sourcePath = Paths.get(file.getPath());
            Path destinationPath = Paths.get(newFolder.getPath() + File.separator + file.getName());
            try {
                Files.move(sourcePath, destinationPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }


}
