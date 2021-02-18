package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class WebUserDao implements IWebUserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    @Override
    public void delUserByID(Long userID) {
        Query query = entityManager.createQuery("delete from User where id_user = :id");
        query.setParameter("id", userID);
        query.executeUpdate();
    }

    @Override
    public void updateUser(User user, Long userID) {
        user.setId_user(userID);
        entityManager.merge(user);
    }

    @Override
    public User getUserByID(Long userID) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.id_user = :id", User.class);
        query.setParameter("id", userID);
        return query.getSingleResult();
    }

    @Override
    public void delUser(User user) {
        User delUser = entityManager.find(User.class, user.getId_user());
        entityManager.remove(delUser);
    }

    @Override
    public User getUserByName(String username) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getResultList().get(0);
    }
}
