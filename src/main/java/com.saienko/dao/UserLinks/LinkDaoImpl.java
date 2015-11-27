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
        int userId = user.getUserId();
        Query query = getSession().createSQLQuery("SELECT * FROM link WHERE USER_ID = :userId ");
        query.setInteger("userId", userId);
        return (List<Link>) query.list();
    }

    public Link findLinkByLink(String link) {
        Criteria criteria = createEntityCriteria();
        criteria.add((Restrictions.eq("link", link)));
        return (Link) criteria.uniqueResult();
    }

    public void deleteLink(String link) {
        Query query = getSession().createSQLQuery("DELETE FROM Link WHERE LINK = :link");
        query.setString("link", link);
        query.executeUpdate();

    }

    public void saveLink(Link link) {
        persist(link);
    }
}
