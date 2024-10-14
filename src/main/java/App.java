import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

File workingDirectory;

void main() {
    String path = "C:/Users/HUGO/Downloads";
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
