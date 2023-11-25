package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public User getUserByCar(String model, int series) {
      TypedQuery<Car> queryCar = sessionFactory.getCurrentSession().createQuery("from Car" +
              " where model=:model and series=:series");
      queryCar.setParameter("model", model);
      queryCar.setParameter("series", series);
      User user = null;
      try {
         Car car = queryCar.getSingleResult();
         TypedQuery<User> queryUser = sessionFactory.getCurrentSession().createQuery("from User where id=:id");
         queryUser.setParameter("id", car.getUser().getId());
         user = queryUser.getSingleResult();
      } catch (NoResultException e) {
         System.out.println(e.getMessage());
      }
      return user;
   }

   @Override
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
