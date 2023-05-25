package com.apirest.lookapp.models.services;

import com.apirest.lookapp.models.dao.IPuestoTrabajoDao;
import com.apirest.lookapp.models.entity.PuestosTrabajo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PuestoTrabajoService implements IPuestoTrabajoService {

    @Autowired
    private IPuestoTrabajoDao puestoTrabajoDao;
    

    public List<PuestosTrabajo> findAll() {
        return puestoTrabajoDao.findAll();
    }

    @Override
    public PuestosTrabajo findById(Long id) {
        return puestoTrabajoDao.findById(id).orElse(null);
    }

    @Override
    public PuestosTrabajo save(PuestosTrabajo puestoTrabajo) {
        return puestoTrabajoDao.save(puestoTrabajo);
    }

    @Override
    public void deleteById(Long id) {
        puestoTrabajoDao.deleteById(id);
    }
    
}
