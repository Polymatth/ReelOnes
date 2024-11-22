package interface_adapter.user_repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import entity.User;

public class FileSaveUserRepository implements UserRepository {
    private final String filePath;

    public FileSaveUserRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(User user) throws IOException {
        File file = new File(filePath);

        // Check if the file exists and is empty
        boolean isFileEmpty = !file.exists() || file.length() == 0;

        try (FileWriter writer = new FileWriter(filePath, true)) {
            if (isFileEmpty) {
                String header = "Name,Password,FavMovie,FavDirector" + System.lineSeparator();
                writer.write(header);
            }

            StringBuilder csvLine = new StringBuilder();


            csvLine.append(user.getName()).append(",");
            csvLine.append(user.getPassword()).append(",");
            csvLine.append(user.getFavMovie()).append(",");
            csvLine.append(user.getFavDirector()).append(System.lineSeparator());


            writer.write(csvLine.toString());
        } catch (IOException e) {
            throw new IOException("Failed to save user to file.", e);
        }
    }
}