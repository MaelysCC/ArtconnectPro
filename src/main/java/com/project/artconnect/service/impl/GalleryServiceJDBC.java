package com.project.artconnect.service.impl;

import com.project.artconnect.dao.ExhibitionDao;
import com.project.artconnect.dao.GalleryDao;
import com.project.artconnect.model.Exhibition;
import com.project.artconnect.model.Gallery;
import com.project.artconnect.service.GalleryService;

import java.util.List;
import java.util.Optional;

public class GalleryServiceJDBC implements GalleryService {

    private final GalleryDao galleryDao;
    private final ExhibitionDao exhibitionDao;

    public GalleryServiceJDBC(GalleryDao galleryDao, ExhibitionDao exhibitionDao) {
        this.galleryDao = galleryDao;
        this.exhibitionDao = exhibitionDao;
    }

    @Override
    public List<Gallery> getAllGalleries() {
        return galleryDao.findAll();
    }

    @Override
    public Optional<Gallery> getGalleryByName(String name) {
        return galleryDao.findAll()
                .stream()
                .filter(g -> g.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public List<Exhibition> getExhibitionsByGallery(Gallery gallery) {
        if (gallery == null)
            return List.of();

        return exhibitionDao.findAll()
                .stream()
                .filter(e -> e.getGallery() != null
                        && e.getGallery().getName().equalsIgnoreCase(gallery.getName()))
                .toList();
    }
}