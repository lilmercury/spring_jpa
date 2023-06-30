package unicordoba.crud_dw.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import unicordoba.crud_dw.Entity.servidor;
import unicordoba.crud_dw.Entity.aplicacion;
import unicordoba.crud_dw.Repositories.ServidorRepository;
import unicordoba.crud_dw.Repositories.AplicacionRepository;
import unicordoba.crud_dw.Repositories.ServidorRepository;
import unicordoba.crud_dw.Repositories.ServSpecs;

import java.io.Console;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/servidor")
public class ServiceController {

    @Autowired
    private ServidorRepository repository;

    //@GetMapping(value = "/")
    //public String holaMundo(){
    //    return "HOLA MUNDO!!!";
    //}

    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<servidor> Todos() {
        return repository.findAll();
    }

    @RequestMapping (value = "/{serId}", method = RequestMethod.GET)
    public Optional <servidor> EncontrarPorId(@PathVariable Integer serId){
        return repository.findById(serId);
    }


    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public servidor crearRegistro (@RequestBody servidor e){ //Inserta el request en la clase u objeto bitacora, el JSON
         return repository.save(e); //con .save los inserta
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.DELETE)
    public boolean eliminarRegistro(){ //Integer por el parametro del repositorio
        repository.deleteAll();
        return true;
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public boolean eliminarRegistroById(@PathVariable Integer id){ //Integer por el parametro del repositorio
        servidor deletedServidor = repository.findById(id).get();
        repository.delete(deletedServidor);
        return true;
    }

    //@RequestMapping(value = "/actualizar/{id}", method = RequestMethod.PUT)
    //public boolean actualizarRegistro(@PathVariable Integer id){ //Integer por el parametro del repositorio
    //    servidor updateServidor = repository.findById(id).get();
    //    repository.save(updateServidor);
    //    return true;
    //}

    //Se manda el JSON con el ID
    @RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
    public servidor editar(@RequestBody servidor ev) {
    return repository.save(ev);
    }

    @RequestMapping(value = "/actualizar/{id}", method = RequestMethod.PUT)
    public boolean editarById(@RequestBody servidor ev, @PathVariable Integer id) {
        servidor updatedServidor = repository.findById(id).get();
        updatedServidor.setNombre(ev.getNombre());
        updatedServidor.setMemoria(ev.getMemoria());
        updatedServidor.setProcesador(ev.getProcesador());
        updatedServidor.setUbicacion(ev.getUbicacion());
        updatedServidor.setSistemaOperativo(ev.getSistemaOperativo());
        updatedServidor.setDisco(ev.getDisco());
        repository.save(updatedServidor);
    return true;
    }

        //procedimientos personzalizados JPA specs
    //Despues nos vamos al controlador y definimos el ENDPOINT
    @RequestMapping(value = "/obtenerServersVario", method = RequestMethod.GET)
    public Iterable<servidor> findByTwoApp() {
        List<servidor> list = repository.findAll(ServSpecs.getCountserverByApp());
        return list;
        //Se implementa una lista de tipo Bitacora, se busca con findAll todos los que se encuentren
        //con ese nombre al que se le paso de parametro a BitacoraSpecs(Repositorio).getEvenetosByServidor(metodo)(parametro)
    }
  
}
