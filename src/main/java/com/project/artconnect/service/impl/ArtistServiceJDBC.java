package com.project.artconnect.service.impl;

import com.project.artconnect.dao.ArtistDao;
import com.project.artconnect.model.Artist;
import com.project.artconnect.model.Discipline;
import com.project.artconnect.service.ArtistService;

import java.util.List;
import java.util.Optional;

public class ArtistServiceJDBC implements ArtistService {

    private final ArtistDao artistDao;

    public ArtistServiceJDBC(ArtistDao artistDao) {
        this.artistDao = artistDao;
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistDao.findAll();
    }

    @Override
    public Optional<Artist> getArtistByName(String name) {
        return artistDao.findAll()
                .stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public void createArtist(Artist artist) {
        artistDao.save(artist);
    }

    @Override
    public void updateArtist(Artist artist) {
        artistDao.update(artist);
    }

    @Override
    public void deleteArtist(String name) {
        artistDao.delete(name);
    }

    @Override
    public List<Discipline> getAllDisciplines() {
        throw new UnsupportedOperationException("Géré par DisciplineService JDBC");
    }

    @Override
    public List<Artist> searchArtists(String query, String disciplineName, String city) {

        return artistDao.findAll()
                .stream()
                .filter(a -> query == null || query.isBlank()
                        || a.getName().toLowerCase().contains(query.toLowerCase()))
                .filter(a -> city == null || city.isBlank()
                        || a.getCity().equalsIgnoreCase(city))
                .filter(a -> disciplineName == null || disciplineName.isBlank()
                        || a.getDisciplines().stream()
                            .anyMatch(d -> d.getName().equalsIgnoreCase(disciplineName)))
                .toList();
    }
}