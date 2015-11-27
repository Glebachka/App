package com.saienko.service.LinkService;

import com.saienko.dao.Link.LinkDao;
import com.saienko.model.Link;
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

    public List<Link> findAllLinks() {
        return linkDao.findtAllLinks();
    }
}
