package controller;

import model.domain.Credentials;

import java.io.IOException;

public class ApplicationController implements Controller {

    Credentials credentials;

    @Override
    public void start() throws IOException {
        LoginController loginController = new LoginController();
        loginController.start();
        credentials = loginController.getCredentials();

        if(credentials.role() == null) {
            throw new RuntimeException("Invalid credentials");
        }

        switch(credentials.role()) {

            case TECNICO -> new TecnicoController().start();
            case MAGAZINO -> new MagazzinoController().start();
            case SEGRETERIA -> new SegreteriaController().start();
            default -> throw new RuntimeException("Invalid credentials");
        }

    }
}
