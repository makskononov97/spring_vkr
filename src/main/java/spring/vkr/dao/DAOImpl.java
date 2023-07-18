package spring.vkr.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.vkr.entity.InitialData;

import java.util.List;

@Repository
public class DAOImpl implements DAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public List<InitialData> showOldInitialData() {

        Session session = sessionFactory.getCurrentSession();

        Query<InitialData> query = session.createQuery("from InitialData"
                            , InitialData.class);
        List<InitialData> allInitialData;
        allInitialData = query.getResultList();

        return allInitialData;
    }

    @Override
    @Transactional
    public void createNewData(InitialData initialData) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(initialData);
    }

    @Override
    @Transactional
    public InitialData getOldData(int id) {

        Session session = sessionFactory.getCurrentSession();

        InitialData initialData = session.find(InitialData.class, id);
        return initialData;
    }

    @Override
    @Transactional
    public void deleteOldData(int id) {

        Session session = sessionFactory.getCurrentSession();

        Query<InitialData> query = session.createQuery("delete from InitialData " +
                "where id =:dataId");
        query.setParameter("dataId", id);
        query.executeUpdate();
    }

}
