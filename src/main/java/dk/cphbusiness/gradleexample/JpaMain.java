/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.gradleexample;

/**
 *
 * @author jens
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class JpaMain {

    private static EntityManager manager = null;

    public static void main(String[] args) {
        EntityManager em = getManager();
        
        Person p = new Person("a","b");
        System.out.println(p.getId());
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        System.out.println(p.getId());
        
        Person p2 = em.find(Person.class, p.getId());
        System.out.println(p2.getFornavn());
    }

    public synchronized static EntityManager getManager() {
        if (manager == null) {
            final Map<String, String> properties = new HashMap<>();
            properties.put("javax.persistence.jdbc.url", getConnectionString());
            properties.put("javax.persistence.jdbc.password", System.getenv("DATABASE_PASSWORD"));

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU", properties);
            manager = emf.createEntityManager();
        }
        return manager;
    }

    private static String getConnectionString() {
        final String databaseUrl = System.getenv("DATABASE_URL");
        final String databaseName = System.getenv("DATABASE_NAME");
        return String.format("jdbc:mysql://%s/%s?zeroDateTimeBehavior=convertToNull", databaseUrl, databaseName);
    }

}
