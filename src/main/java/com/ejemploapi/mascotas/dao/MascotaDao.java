package com.ejemploapi.mascotas.dao;

import com.ejemploapi.mascotas.models.Mascota;
import com.ejemploapi.mascotas.models.Usuario;

import java.util.List;

public interface MascotaDao {

    List<Mascota> gertMascotas();

    void eliminar(int id);

    void registrar(Mascota mascota);


    //Modificar
    Mascota modifcarMascota(Mascota mascota);

    //Buscar
    Mascota buscarMascota(int id);
}
