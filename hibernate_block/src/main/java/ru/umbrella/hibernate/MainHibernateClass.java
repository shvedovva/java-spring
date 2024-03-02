package ru.umbrella.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainHibernateClass {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Catalog.class)
                .buildSessionFactory();

        //CRUD
        Session session = null;

        Catalog catalog1 = new Catalog("Detective #1");
        Catalog catalog2 = new Catalog("Detective #2");
        Catalog catalog3 = new Catalog("Detective #3");
        Catalog catalog4 = new Catalog("Detective #4");

        try {
            //CREATE
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(catalog1);
            session.save(catalog2);
            session.save(catalog3);
            session.save(catalog4);
            session.getTransaction().commit();

            //READ
            session = factory.getCurrentSession();
            session.beginTransaction();
            Catalog catalog5 = session.get(Catalog.class, 3L);
            session.getTransaction().commit();
            System.out.println(catalog5);

            //UPDATE
            Catalog catalog7 = new Catalog("Detective #77");

            session = factory.getCurrentSession();
            session.beginTransaction();
            Catalog catalog6 = session.get(Catalog.class, 2L);
            //session.detach(catalog6); // убрать из наблюдения hibernate
            //session.remove(catalog6); // удаление из БД
            session.refresh(catalog6);
            session.persist(catalog7);
            catalog6.setTitle("Detective #22");
            session.getTransaction().commit();
            System.out.println(catalog6);

            //DELETE
            session = factory.getCurrentSession();
            session.beginTransaction();
            Catalog catalog8 = session.get(Catalog.class, 3L);
            session.delete(catalog8);
            session.getTransaction().commit();


            //READ ALL
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<Catalog> listCatalog = session
                    .createQuery("from Catalog").getResultList();
            System.out.println(listCatalog);
            session.getTransaction().commit();

            //READ + condition
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<Catalog> lists = session
                    .createQuery("from Catalog c where c.title = :t")
                    .setParameter("t", "Detective #4")
                    .getResultList();
            session.getTransaction().commit();
            System.out.println(lists);

            //UPDATE/DELETE + condition
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("update Catalog c set title='Fantasy #1' where c.id = 1")
                    .executeUpdate();
            session.createQuery("delete Catalog c where c.id = 2")
                    .executeUpdate();
            session.getTransaction().commit();


        } finally {
            session.close();
            factory.close();
        }
    }
}
