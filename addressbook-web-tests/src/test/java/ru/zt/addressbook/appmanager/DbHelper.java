package ru.zt.addressbook.appmanager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.zt.addressbook.model.ContactData;
import ru.zt.addressbook.model.Contacts;
import ru.zt.addressbook.model.GroupData;
import ru.zt.addressbook.model.Groups;

import java.util.List;

public class DbHelper {
private final SessionFactory sessionFactory;

public DbHelper() {
  // A SessionFactory is set up once for an application!
  final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
          .configure() // configures settings from hibernate.cfg.xml
          .build();
  sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
}

//получение списка групп
public Groups groups() {
  Session session = sessionFactory.openSession();
  session.beginTransaction();
  List<GroupData> result = session.createQuery("from GroupData").list();
  session.getTransaction().commit();
  session.close();
  return new Groups(result);
}

//получение списка контактов
public Contacts contacts() {
  Session session = sessionFactory.openSession();
  session.beginTransaction();
  List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
  session.getTransaction().commit();
  session.close();
  return new Contacts(result);
}

//
/*
public Contacts contactInGroup(int id,int getId){
  Session session = sessionFactory.openSession();
  session.beginTransaction();
  List<ContactData> result= session.createQuery( "from address_in_groups where id=:paramId and group_id=:paramGetId")
          .setParameter("paramId", id).setParameter("paramGetId",getId).list();
   session.getTransaction().commit();
  session.close();
  return new Contacts(result);
}
*/

public Contacts contactInGroup(int id) {
  Session session = sessionFactory.openSession();
  session.beginTransaction();
  List<ContactData> result = session.createQuery("from ContactData where id=:paramId")
          .setParameter("paramId", id).list();
  session.getTransaction().commit();
  session.close();
  return new Contacts(result);
}
}
