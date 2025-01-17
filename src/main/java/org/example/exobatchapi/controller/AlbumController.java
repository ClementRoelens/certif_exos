package org.example.exobatchapi.controller;

import org.example.exobatchapi.entity.Album;
import org.example.exobatchapi.service.AlbumService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("api/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    private final JobLauncher jobLauncher;
    private final Job importJob;

    public AlbumController(JobLauncher jobLauncher, Job importJob) {
        this.jobLauncher = jobLauncher;
        this.importJob = importJob;
    }

    @GetMapping
    public ResponseEntity<List<Album>> getAll() {
        List<Album> albums = albumService.getAllAlbums();

        if (albums != null) {
            return new ResponseEntity<>(albums, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getById(@PathVariable int id) {
        Album album = albumService.getAlbumById(id);

        if (album != null) {
            return new ResponseEntity<>(album, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Album> create(@RequestBody Album album) {
        Album createdAlbum = albumService.createAlbum(album);

        if (createdAlbum != null) {
            return new ResponseEntity<>(createdAlbum, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/csv")
    public ResponseEntity<String> createCSV(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Le fichier est vide",HttpStatus.BAD_REQUEST);
        }

        Path uploadPath = Paths.get("C:\\Users\\Administrateur\\Documents\\Exos\\exo-batch-api\\src\\main\\resources");
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            Path filePath = uploadPath.resolve(originalFilename);
            try {
                file.transferTo(filePath.toFile());

                JobParameters jobParameters = new JobParametersBuilder()
                        .addLong("time",System.currentTimeMillis())
                        .addString("fileName",originalFilename)
                        .toJobParameters();

                jobLauncher.run(importJob,jobParameters);

                return new ResponseEntity<>("Albums correctement ajoutés",HttpStatus.CREATED);
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return new ResponseEntity<>("Le fichier n'a pas pu être copié sur le serveur\n" + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (JobExecutionException e) {
                System.err.println(e.getMessage());
                return new ResponseEntity<>("Le fichier n'a pas pu être traité\n" + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (Exception e){
                System.err.println(e.getMessage());
                return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("Le fichier n'a pas pu être copié sur le serveur",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> update(@PathVariable int id, @RequestBody Album album) {
        Album updatedAlbum =  albumService.updateAlbum(id, album);

        if (updatedAlbum != null) {
            return new ResponseEntity<>(updatedAlbum, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (albumService.deleteAlbum(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
