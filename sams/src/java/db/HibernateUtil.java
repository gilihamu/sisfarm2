package db;

import model.AtendimentoSolicitacao;
import model.Doacao;
import model.Email;
import model.Endereco;
import model.Entidade;
import model.Estado;
import model.Municipio;
import model.PreUsuarioTo;
import model.Produtos;
import model.Reserva;
import model.Solicitacao;
import model.Telefone;
import model.UsuarioTo;
import org.hibernate.HibernateException;
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
    //private SessionFactory sessionFactory;
    private static SessionFactory sessionFactory;
    public static final ThreadLocal session = new ThreadLocal();

    private HibernateUtil() {
        sessionFactory = new AnnotationConfiguration()
                .setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.connection.driver.class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url" , "jdbc:mysql://mysql09.kinghost.net:3306/atrixian")
                .setProperty("hibernate.connection.username", "atrixian")
                .setProperty("hibernate.connection.password", "sams123")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.format_sql", "true")
                .setProperty("hibernate.c3p0.acquire_increment", "1")
                .setProperty("hibernate.c3p0.idle_test_period", "100")
                .setProperty("hibernate.c3p0.max_size", "10")
                .setProperty("hibernate.c3p0.max_statements", "0")
                .setProperty("hibernate.c3p0.min_size", "5")
                .setProperty("hibernate.c3p0.time_out", "100")
                .addAnnotatedClass(Entidade.class)
                .addAnnotatedClass(UsuarioTo.class)
                .addAnnotatedClass(Solicitacao.class)
                .addAnnotatedClass(Doacao.class)
                .addAnnotatedClass(Produtos.class)
                .addAnnotatedClass(Reserva.class)
                .addAnnotatedClass(Email.class)
                .addAnnotatedClass(Telefone.class)
                .addAnnotatedClass(Endereco.class)
                .addAnnotatedClass(Estado.class)
                .addAnnotatedClass(Municipio.class)
                .addAnnotatedClass(AtendimentoSolicitacao.class)
                .addAnnotatedClass(PreUsuarioTo.class)
                .buildSessionFactory();

    }
//    public Session getSession(){
//        Session toReturn = sessionFactory.openSession();
//        toReturn.beginTransaction();
//        return toReturn;
//    }

    public static Session getSession() throws HibernateException {
        Session s = (Session) session.get();
        if (s == null) {
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s;
    }

    public static  HibernateUtil getInstace() {
        if (me == null) {
            me = new HibernateUtil();
        }
        return me;
    }
}
