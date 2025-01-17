package org.example.exobatchapi.service;

import org.example.exobatchapi.entity.Album;
import org.example.exobatchapi.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public Album getAlbumById(int id) {
        return albumRepository.findById(id).orElse(null);
    }

    public Album updateAlbum(int id, Album album) {
        if (albumRepository.existsById(id)) {
            album.setId(id);
            return albumRepository.save(album);
        }
        return null;
    }

    public boolean deleteAlbum(int id) {
        if (albumRepository.existsById(id)) {
            albumRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
