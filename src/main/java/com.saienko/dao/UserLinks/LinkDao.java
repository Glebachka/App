package com.saienko.dao.UserLinks;

import com.saienko.model.Link;
import com.saienko.model.User;

import java.util.List;

/**
 * Created by gleb on 26.11.2015.
 */
public interface LinkDao {
    Link findLinkById(int id);

    List<Link> findAllUserLinks(User user);

    Link findLinkByLink(String link);

    void deleteLinkById(int id);

    void saveLink(Link link);
}
