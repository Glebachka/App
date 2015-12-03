package com.saienko.service.linkServiceT;

import com.saienko.dao.userLinksDaoT.LinkDao;
import com.saienko.model.Link;
import com.saienko.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by gleb on 27.11.2015.
 */
@Service("linkService")
@Transactional
public class LinkServiceImpl implements LinkService {

    @Autowired
    LinkDao linkDao;


    public Link findLinkById(int linkId) {
        return linkDao.findLinkById(linkId);
    }

    public Link findLinkByLink(String link) {
        return linkDao.findLinkByLink(link);
    }

    public List<Link> findAllUserLinks(User user) {
        return linkDao.findAllUserLinks(user);
    }

    public void deleteLinkByID(int link) {
        linkDao.deleteLinkById(link);
    }

    public void updateLink(Link link) {
        Link entity = linkDao.findLinkById(link.getLinkId());
        if (entity != null) {
            entity.setLink(link.getLink());
            entity.setLinkDescription(link.getLinkDescription());
        }
    }

    public boolean isLinkUnique(Integer id, String link) {

        Link linkEntity = linkDao.findLinkByLink(link);
        return (linkEntity == null || ((id != null) && (linkEntity.getLinkId() == id)));
    }

    public void saveLink(Link link) {
        linkDao.saveLink(link);
    }
}
