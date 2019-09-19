/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moddb.dw_apiuser.dw_apiuser.model;

import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 *
 * @author danielsan
 */
public class UsuarioDAO extends AbstractDAO<Usuario> {

    public UsuarioDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Usuario findById(Usuario usuario) {
        return get(usuario.getIdUsuario());
    }

    public long createUsuario(Usuario usuario) {
        return persist(usuario).getIdUsuario();
    }

    public long deleteUsuario(Usuario usuario) {
        currentSession().delete(usuario);
        return usuario.getIdUsuario();
    }

    public List<Usuario> findAll() {
        return list(query("SELECT u FROM Usuario u"));
    }

}
