/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.gradleexample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sofus
 */
@Path("hello")
public class RestService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello World";
    }

    @GET
    @Path("user")
    public String user() {
        try {
            return JpaMain.getManager().createQuery("SELECT p FROM Person p", Person.class).getResultList().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GET
    @Path("store")
    public String store() {
        Person p = new Person("John", "Doe");
        JpaMain.getManager().getTransaction().begin();
        JpaMain.getManager().persist(p);
        JpaMain.getManager().getTransaction().commit();

        return p.getId() + "";
    }

}
