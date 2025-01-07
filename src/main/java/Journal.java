import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class Journal {
    private final String txtPath;
    private final String binaryPath;
    private final File file;
    private static int count = 0;

    public Journal(String path, String binaryPath) {
        this.txtPath = path;
        this.binaryPath = binaryPath;
        this.file = new File(path);
    }

    public String getBinaryPath() {
        return binaryPath;
    }

    private void createFileIfNotExists() throws IOException {
        if (!file.exists()) {
            Files.createFile(Path.of(txtPath));
        }
    }

    public void addActivity(String activity) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            String activityLine = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " - " + activity;
            writer.write(activityLine);
            writer.newLine();
            count++;
        } catch (FileNotFoundException e) {
            createFileIfNotExists();
        }

        if (count % 5 == 0){
            System.out.println("SAUVEGARDE AUTOMATIQUE");
            saveBinary();
        }
    }

    public String getActivities() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(txtPath))){
            return reader.lines()
                    .collect(Collectors.joining("\n"));
        }
    }

    public void saveBinary() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(binaryPath)))
        {
            for (String line : reader.lines().toArray(String[]::new)){
                outputStream.write(line.getBytes(StandardCharsets.UTF_8));
                outputStream.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    public String readBinary() throws IOException {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(binaryPath))){
            byte[] buffer = new byte[1024];
            int bytesRead;
            StringBuilder result = new StringBuilder();

            while ((bytesRead = inputStream.read(buffer)) > 0){
                result.append(new String(buffer, 0, bytesRead));
            }

            return result.toString();
        }
    }
}
