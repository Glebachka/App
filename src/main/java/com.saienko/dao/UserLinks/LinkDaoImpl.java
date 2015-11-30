package com.saienko.dao.UserLinks;

import com.saienko.dao.AbstractDao;
import com.saienko.model.Link;
import com.saienko.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gleb on 26.11.2015.
 */
@Repository("linkDao")
public class LinkDaoImpl extends AbstractDao<Integer, Link> implements LinkDao {

    public Link findLinkById(int id) {
        return getByKey(id);
    }


    public List<Link> findAllUserLinks(User user) {
        Criteria criteria = getSession().createCriteria(Link.class);
        criteria.add(Restrictions.eq("user", user));
        return (List<Link>) criteria.list();

    }

    public Link findLinkByLink(String link) {
        Criteria criteria = createEntityCriteria();
        criteria.add((Restrictions.eq("link", link)));
        return (Link) criteria.uniqueResult();
    }

    public void deleteLinkById(int id) {
        Query query = getSession().createSQLQuery("DELETE FROM LINK WHERE LINK_ID = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    public void saveLink(Link link) {
        persist(link);
    }
}
