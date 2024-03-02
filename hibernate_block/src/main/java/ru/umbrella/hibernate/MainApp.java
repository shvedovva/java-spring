package ru.umbrella.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.umbrella.hibernate.entities.Author;
import ru.umbrella.hibernate.entities.Book;
import ru.umbrella.hibernate.entities.Reader;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Author.class)
                .addAnnotatedClass(Reader.class)
                .buildSessionFactory();

        Session session = null;

        try{
            Author author = new Author();
            author.setName("Rowling");

            Book book1 = new Book();
            book1.setTitle("Potter #1");
            book1.setAuthor(author);

            Book book2 = new Book();
            book2.setTitle("Potter #2");
            book2.setAuthor(author);

            Book book3 = new Book();
            book3.setTitle("Potter #3");
            book3.setAuthor(author);

            Reader reader1 = new Reader();
            reader1.setName("Bill");
            reader1.setBooks(List.of(book1,book2));

            Reader reader2 = new Reader();
            reader2.setName("John");
            reader2.setBooks(List.of(book2,book3));

            session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(author);
            session.save(book1);
            session.save(book2);
            session.save(book3);
            session.save(reader1);
            session.save(reader2);
            session.getTransaction().commit();


            session = factory.getCurrentSession();
            session.beginTransaction();
            Reader reader = session.get(Reader.class, 2L);
            Book book = session.get(Book.class, 1L);
            reader.getBooks().add(book);
            session.getTransaction().commit();


        } finally {
            session.close();
            factory.close();
        }
    }
}
