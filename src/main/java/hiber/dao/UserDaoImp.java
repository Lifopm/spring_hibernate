package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@EnableTransactionManagement
@Repository
public class UserDaoImp<unchecked> implements UserDao {

   Transaction transaction;

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      transaction.begin();;
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   @Transactional
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }



   //from User as u left join Car as c with c.model = m AND c.series = s
   @Override
   @Transactional
   public User getUserByCar(String model, int series) {
      Query query = sessionFactory.getCurrentSession()
              .createQuery("from User inner join Car where Car.user = User.car");

      List user = query.getResultList();
      return null;//(User) query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   @Transactional
   public List<Car> listCars() {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }

}
