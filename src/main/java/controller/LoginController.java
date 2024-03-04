package controller;

import model.domain.Credentials;
import view.LoginView;
import exception.DAOException;
import model.dao.LoginProcedureDAO;

import java.io.IOException;


public class LoginController implements Controller {
    Credentials credentials = null;

    @Override
    public void start() {
        try {
            credentials = LoginView.authenticate();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        try {
            credentials = new LoginProcedureDAO().execute(credentials.username(), credentials.password());
        } catch(DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public Credentials getCredentials() {
        return credentials;
    }


}
