package hibernate;

import hibernate.model.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AddressMain {
    private static SessionFactory sessionFactory;


    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        AddressMain addressMain = new AddressMain();
        addressMain.addAddress(9, "DC", "Gotham City", "Arkham street 2", "71900");


        System.out.println("===================================");

        List<Address> addresses = addressMain.listAddress();
        for (Address address : addresses) {
            System.out.println(address);
        }
    }

    public void addAddress(int id, String country, String city, String street, String postCode) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Address address = new Address(id, country, city, street, postCode);
        session.save(address);
        transaction.commit();
        session.close();
    }

    public List<Address> listAddress() {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List<Address> addresses = session.createQuery("FROM Address").list();

        transaction.commit();
        session.close();
        return addresses;
    }


}

