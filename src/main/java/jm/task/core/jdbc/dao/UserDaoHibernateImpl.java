package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final String CREATE_TABLE;
    private final String DROP_TABLE;

    {
        CREATE_TABLE = "CREATE TABLE testbase.usertable (id BIGINT NOT NULL AUTO_INCREMENT," +
                " name VARCHAR(255)," +
                " lastName VARCHAR(255)," +
                " age TINYINT," +
                " PRIMARY KEY (id))";
        DROP_TABLE = "DROP TABLE testbase.usertable";
    }

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSession()) {
            session.doWork(new Work() {
                @Override
                public void execute(Connection connection) throws SQLException {
                    connection.prepareStatement(CREATE_TABLE).executeUpdate();
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSession()) {
            session.doWork(new Work() {
                @Override
                public void execute(Connection connection) throws SQLException {
                    connection.prepareStatement(DROP_TABLE).executeUpdate();
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession()) {
            session.getTransaction().begin();
            User user = new User(name, lastName, age);
            session.persist(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession()) {
            session.getTransaction().begin();
            Query<User> query = session.createQuery("DELETE FROM User u WHERE u.id = :id", User.class);
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> resultList = null;
        try (Session session = Util.getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            query.select(query.from(User.class));
            resultList = session.createQuery(query).getResultList();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultList;
    }

    @Override
    public void cleanUsersTable() {
        dropUsersTable();
        createUsersTable();
    }
}
