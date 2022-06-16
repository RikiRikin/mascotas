package com.ejemploapi.mascotas.controllers;


import com.ejemploapi.mascotas.dao.MascotaDao;
import com.ejemploapi.mascotas.dao.UsuarioDao;
import com.ejemploapi.mascotas.models.Mascota;
import com.ejemploapi.mascotas.models.Usuario;
import com.ejemploapi.mascotas.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private MascotaDao mascotaDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("");
        usuario.setApellido("");
        usuario.setEmail("");
        usuario.setTelefono("");

        return usuario;
    }
    private boolean validarToken(String token){
        String usuarioid = jwtUtil.getKey(token);
        return usuarioid != null;
    }
                //USUARIOS
    //Mostrar
    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader (value = "Authorization") String token){


        if (!validarToken(token)){
            return null;
        }

        return usuarioDao.gertUsuarios();
    }
    //Eliminar
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader (value = "Authorization") String token,@PathVariable Long id){
        if (!validarToken(token)){
            return ;
        }
        usuarioDao.eliminar(id);
    }
    //Regsitrar
    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuarios(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }






                    //Mascotas
    //Mostrar
    @RequestMapping(value = "api/mascotas", method = RequestMethod.GET)
    public List<Mascota> getMascotas(@RequestHeader (value = "Authorization") String token){
        if (!validarToken(token)){
            return null;
        }
        return mascotaDao.gertMascotas();
    }
    //Eliminar
    @RequestMapping(value = "api/mascotas/{id}", method = RequestMethod.DELETE)
    public void eliminarMascotas(@RequestHeader (value = "Authorization") String token,@PathVariable int id){
        mascotaDao.eliminar(id);
    }
    //Regsitrar
    @RequestMapping(value = "api/mascotas", method = RequestMethod.POST)
    public void registrarUsuarios(@RequestHeader (value = "Authorization") String token,@RequestBody Mascota mascota){
        mascotaDao.registrar(mascota);
    }
    //Buscar
    @RequestMapping(value = "api/mascotas/{id}", method = RequestMethod.GET)
    public Mascota buscarMascota(@RequestHeader (value = "Authorization") String token,@PathVariable int id){
        return mascotaDao.buscarMascota(id);
    }
    //Modificar
    @RequestMapping(value = "api/mascotas/modificar", method = RequestMethod.PUT)
    public void modificarUsuarios(@RequestHeader (value = "Authorization") String token,@RequestBody Mascota mascota){
        mascotaDao.modifcarMascota(mascota);
    }
}
