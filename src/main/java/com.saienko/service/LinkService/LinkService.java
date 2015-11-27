package com.saienko.service.LinkService;

import com.saienko.model.Link;
import com.saienko.model.User;

import java.util.List;

/**
 * Created by gleb on 27.11.2015.
 */
public interface LinkService {

    Link findLinkById(int id);

    Link findLinkByLink(String link);

    List<Link> findAllUserLinks(User user);

    void deleteLinkByID(String link);

    void updateLink(Link link);

    boolean isLinkUnique(Integer id, String link);

    void saveLink(Link link);

}
