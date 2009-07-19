package db;

import model.Dominio;
import model.Endereco;
import model.Pessoa;
import model.UsuarioTo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Hugo Fabrício
 */
public class HibernateUtil {

    private static final long serialVersionUID = 1L;
    private static HibernateUtil me;
    private SessionFactory sessionFactory;

    private HibernateUtil() {
        sessionFactory = new AnnotationConfiguration()
                .setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect")
                .setProperty("hibernate.connection.driver.class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.url","jdbc:postgresql://localhost:5432/sams_desenv")
                .setProperty("hibernate.connection.username", "postgres")
                .setProperty("hibernate.connection.password", "12345")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.format_sql", "true")
                .setProperty("hibernate.c3p0.acquire_increment", "1")
                .setProperty("hibernate.c3p0.idle_test_period", "100")
                .setProperty("hibernate.c3p0.max_size", "10")
                .setProperty("hibernate.c3p0.max_statements", "0")
                .setProperty("hibernate.c3p0.min_size", "5")
                .setProperty("hibernate.c3p0.time_out", "100")
                .addAnnotatedClass(UsuarioTo.class)
                .addAnnotatedClass(Pessoa.class)
                .addAnnotatedClass(Endereco.class)
                .addAnnotatedClass(Dominio.class)

                .buildSessionFactory();

    }
    public Session getSession(){
        Session toReturn = sessionFactory.openSession();
        toReturn.beginTransaction();
        return toReturn;
    }
    public static  HibernateUtil getInstace() {
        if (me == null) {
            me = new HibernateUtil();
        }
        return me;
    }
}
