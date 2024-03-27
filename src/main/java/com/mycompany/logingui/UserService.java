/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.logingui;

/**
 *
 * @author gabri
 */
import java.util.HashMap;
import java.util.Map;

class UserService {
    private Map<String, String[]> usuarios;

    public UserService() {
        usuarios = new HashMap<>();
        usuarios.put("usuario1", new String[]{"contraseña1", "matricula1"});
        usuarios.put("usuario2", new String[]{"contraseña2", "matricula2"});
        usuarios.put("admin", new String[]{"admin123", "adminmatricula"});
    }

    public boolean validarCredenciales(String id, String contraseña) {
        if (usuarios.containsKey(id)) {
            String[] datosUsuario = usuarios.get(id);
            return contraseña.equals(datosUsuario[0]);
        }
        return false;
    }

    public boolean registrarUsuario(String id, String matricula, String contraseña) {
        if (!usuarios.containsKey(id)) {
            usuarios.put(id, new String[]{contraseña, matricula});
            return true;
        }
        return false;
    }
}
