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
public class NewClass {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        
        Person p = new Person("a","b");
        System.out.println(p.getId());
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        System.out.println(p.getId());
        
        Person p2 = em.find(Person.class, p.getId());
        System.out.println(p2.getFornavn());
    }
    
}
