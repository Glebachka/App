package com.saienko.service.LinkService;

import com.saienko.model.Link;

import java.util.List;

/**
 * Created by gleb on 27.11.2015.
 */
public interface LinkService {

    Link findLinkById(int id);

    Link findLinkByLink(String link);

    List<Link> findAllLinks();
}
