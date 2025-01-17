package org.example.exobatchapi.tasklet;

import org.example.exobatchapi.entity.Album;
import org.example.exobatchapi.service.AlbumService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExtractingAlbumsFromCSVTasklet implements Tasklet {
    @Autowired
    private AlbumService albumService;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + chunkContext.getStepContext().getJobParameters().get("fileName");
        File csvFile = new File(filePath);

        if (csvFile.exists()) {
            List<Album> albums = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String line;
                reader.readLine();

                while ((line = reader.readLine()) != null) {
                    albums.add(new Album(
                            line.split(",")[0],
                            line.split(",")[1],
                            Integer.parseInt(line.split(",")[2]))
                    );
                }
            }

            for (Album album : albums) {
                albumService.createAlbum(album);
            }

            csvFile.delete();
            return RepeatStatus.FINISHED;
        }
        throw new IOException("Le fichier n'a pas été trouvé");
    }
}
