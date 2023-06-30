package unicordoba.crud_dw.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import unicordoba.crud_dw.Entity.aplicacion;
import unicordoba.crud_dw.Entity.servidor;
import unicordoba.crud_dw.Repositories.AplicacionRepository;
import unicordoba.crud_dw.Repositories.ServidorRepository;
import unicordoba.crud_dw.Repositories.AppSpecs;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/aplicacion")
public class AplicacionController {
    
    @Autowired
    private AplicacionRepository repository;

    @Autowired
    private ServidorRepository _servidorRepository;

    @RequestMapping (value = "/", method = RequestMethod.GET)
    public Iterable <aplicacion> Todos(){
        return repository.findAll();

    }

    @RequestMapping (value = "/{appId}", method = RequestMethod.GET)
    public Optional <aplicacion> EncontrarPorId(@PathVariable Integer appId){
        return repository.findById(appId);
    }

    @RequestMapping (value = "crear/{serverId}", method = RequestMethod.POST)
    public aplicacion crearApp (@PathVariable Integer serverId, @RequestBody aplicacion e){
        servidor s = _servidorRepository.findById(serverId).get();
        e.setServer(s);
        return repository.save(e);
    }

    @RequestMapping (value = "crear-multiples/{serverId}", method = RequestMethod.POST)
    public Iterable<aplicacion> crearAplicaciones(@PathVariable Integer serverId, @RequestBody List<aplicacion> e){
        servidor s = _servidorRepository.findById(serverId).get();
        for (aplicacion app : e) {
            app.setServer(s);
        }
        return repository.saveAll(e);
    }
    
    @RequestMapping (value = "eliminar", method = RequestMethod.DELETE)
    public boolean EliminarApp (){
        repository.deleteAll();
        return true;
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public boolean eliminarRegistroById(@PathVariable Integer id){ //Integer por el parametro del repositorio
        aplicacion deletedAplicacion = repository.findById(id).get();
        repository.delete(deletedAplicacion);
        return true;
    }

    //@RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
    //public aplicacion editar(@RequestBody aplicacion ev) {
    //return repository.save(ev);
    //}

    @RequestMapping(value = "/actualizar/{id}", method = RequestMethod.PUT)
    public boolean editarById(@RequestBody aplicacion ev, @PathVariable Integer id) {
        aplicacion updatedApp = repository.findById(id).get();
        updatedApp.setNombre(ev.getNombre());
        updatedApp.setVersion(ev.getVersion());
        repository.save(updatedApp);
    return true;
    }

    @RequestMapping(value = "/nameApp/{name}", method = RequestMethod.GET)
    public Iterable<aplicacion> AppByName(@PathVariable String name) {
            List<aplicacion> list = repository.findAll(AppSpecs.getAppByname(name));
            return list;
        }

    @RequestMapping(value = "/SOnameApp/{name}", method = RequestMethod.GET)
    public Iterable<aplicacion> SOAppByName(@PathVariable String name) {
        return repository.findAll(AppSpecs.getAppBySOWithServer(name));
        //Se implementa una lista de tipo Bitacora, se busca con findAll todos los que se encuentren
        //con ese nombre al que se le paso de parametro a BitacoraSpecs(Repositorio).getEvenetosByServidor(metodo)(parametro)
    }

}
