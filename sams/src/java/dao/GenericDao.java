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

    protected Session getSession() {
        return HibernateUtil.getInstace().getSession();

    }

    protected void saveorUpdatePojo(Serializable pojo) {
        Session ses = getSession();
        ses.saveOrUpdate(pojo);
        ses.getTransaction().commit();
        ses.close();
    }
    protected void savePojo(Serializable pojo) {
        Session ses = getSession();
        ses.save(pojo);
        ses.getTransaction().commit();
        ses.close();
    }
    //<T> neste metodo esta usando generic
    protected <T extends Serializable> T getPojo(Class<T> classToSearch, Serializable key) {
        Session ses = getSession();
        Serializable toReturn = (Serializable) ses.get(classToSearch, key);
        ses.getTransaction().commit();
        ses.close();
        return (T) toReturn;
    }

    protected void removePojo(Serializable pojoToRemote) {
        Session ses = getSession();
        ses.delete(pojoToRemote);
        ses.getTransaction().commit();
        ses.close();
    }

    protected Serializable getPurePojo(String query, Object... params) {
        Session ses = getSession();
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
        ses.close();
        return (Serializable) toReturn;
    }

    protected <T extends Serializable> List<T> getPureList(Class<T> classToCast, String query, Object... values) {
        Session ses = getSession();
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
        ses.close();
        return toReturn;
    }
}
