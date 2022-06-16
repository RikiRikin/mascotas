package com.ejemploapi.mascotas.dao;


import com.ejemploapi.mascotas.models.Mascota;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class MascotaDaoImp implements MascotaDao {

    @PersistenceContext
    private EntityManager entityManager;

//Mostrar
    @Override
    public List<Mascota> gertMascotas() {
        String query = "FROM Mascota";
        return entityManager.createQuery(query).getResultList();
    }
    //Eliminar
    @Override
    public void eliminar(int id) {
        Mascota mascota = entityManager.find(Mascota.class, id);
        entityManager.remove(mascota);
    }

//Registrar
    @Override
    public void registrar(Mascota mascota) {
        entityManager.merge(mascota);
    }

    //Modificar
    @Override
    public Mascota modifcarMascota(Mascota mascota) {
         entityManager.merge(mascota);
        return mascota;
    }


    //Buscar
    @Override
    public Mascota buscarMascota(int id) {
        return (Mascota) entityManager.find(Mascota.class, id);
    }

}
