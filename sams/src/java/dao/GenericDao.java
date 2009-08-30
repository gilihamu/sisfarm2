package dao;

import db.HibernateUtil;
import java.io.Serializable;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Hugo Fabrício
 */
public abstract class GenericDao {

    private static final long serialVersionUID = 1L;

    public GenericDao() {
    }

    @SuppressWarnings("static-access")
    protected Session getSession() {
        return HibernateUtil.getInstace().getSession();

    }

    protected void saveorUpdatePojo(Serializable pojo) {
        Session ses = getSession();
        ses.beginTransaction();
        ses.merge(pojo);
        //ses.saveOrUpdate(pojo);
        ses.getTransaction().commit();

    }

    protected void saveorUpdatePojoNoMerge(Serializable pojo) {
        Session ses = getSession();
        ses.beginTransaction();
        ses.saveOrUpdate(pojo);
        ses.flush();
        ses.getTransaction().commit();

    }

    protected void savePojo(Serializable pojo) {
        Session ses = getSession();
        ses.beginTransaction();
        ses.save(pojo);
        ses.getTransaction().commit();

    }
    //<T> neste metodo esta usando generic
    protected <T extends Serializable> T getPojo(Class<T> classToSearch, Serializable key) {
        Session ses = getSession();
        ses.beginTransaction();
        Serializable toReturn = (Serializable) ses.get(classToSearch, key);
        ses.getTransaction().commit();

        return (T) toReturn;
    }

    protected void removePojo(Serializable pojoToRemote) {
        Session ses = getSession();
        ses.beginTransaction();
        ses.delete(pojoToRemote);
        ses.getTransaction().commit();

    }

    protected Serializable getPurePojo(String query, Object... params) {
        Session ses = getSession();
       ses.beginTransaction();
        Query qr = ses.createQuery(query);

       /* for (int i = 1; i <= params.length; i++) {
            qr.setParameter(i, params[i - 1]);
        }*/
         int count =0;
        for(Object value : params){
            qr.setParameter(count++, value);
        }
        Object toReturn = qr.uniqueResult();
        ses.getTransaction().commit();

        return (Serializable) toReturn;
    }

    protected <T extends Serializable> List<T> getPureList(Class<T> classToCast, String query, Object... values) {
        Session ses = getSession();
        ses.beginTransaction();
        Query qr = ses.createQuery(query);
       /* for (int i = 1; i <= params.length; i++) {
            qr.setParameter(i, params[i -1]);
        }*/
        int count =0;
        for(Object value : values){
            qr.setParameter(count++, value);
        }
        List<T> toReturn = qr.list();
        ses.getTransaction().commit();

        return toReturn;
    }
}
