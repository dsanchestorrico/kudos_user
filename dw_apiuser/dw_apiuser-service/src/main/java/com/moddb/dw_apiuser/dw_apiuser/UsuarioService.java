/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moddb.dw_apiuser.dw_apiuser;

import com.moddb.dw_apiuser.dw_apiuser.conf.UsuarioConfiguration;
import com.moddb.dw_apiuser.dw_apiuser.model.Usuario;
import com.moddb.dw_apiuser.dw_apiuser.model.UsuarioDAO;
import com.moddb.dw_apiuser.dw_apiuser.resource.UsuarioResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 *
 * @author danielsan
 */
public class UsuarioService extends Application<UsuarioConfiguration> {

    private final HibernateBundle<UsuarioConfiguration> hibernate = new HibernateBundle<UsuarioConfiguration>(Usuario.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(UsuarioConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<UsuarioConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    public static void main(String args[]) throws Exception {
        new UsuarioService().run(args);
    }

    @Override
    public void run(UsuarioConfiguration configuration,
        Environment environment) {

        final UsuarioDAO dao = new UsuarioDAO(hibernate.getSessionFactory());
        environment.jersey().register(new UsuarioResource(dao));
    }

}
