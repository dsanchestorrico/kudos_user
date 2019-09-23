/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moddb.dw_apiuser.dw_apiuser.resource;

import com.moddb.dw_apiuser.dw_apiuser.model.CommonInput;
import com.moddb.dw_apiuser.dw_apiuser.model.Publisher;
import com.moddb.dw_apiuser.dw_apiuser.model.Usuario;
import com.moddb.dw_apiuser.dw_apiuser.model.UsuarioDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author danielsan
 */
@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    private UsuarioDAO dao;

    public UsuarioResource(UsuarioDAO dao) {
        this.dao = dao;
    }

    @GET
    @UnitOfWork
    public List<Usuario> findAll() {
        return dao.findAll();
    }

    @POST
    @Path("/findById")
    @UnitOfWork
    public Usuario findPerson(CommonInput input) {
        return dao.findById(input.getUsuario());
    }

    @POST
    @Path("/create")
    @UnitOfWork
    public Long createPerson(CommonInput input) {
        return dao.createUsuario(input.getUsuario());
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Long deleteUsuario(@PathParam("id") IntParam id) throws IOException, TimeoutException {
        Publisher publisher = new Publisher();
        publisher.publishMessage(id.get().toString());
        return dao.deleteUsuario(id.get());
    }
}
