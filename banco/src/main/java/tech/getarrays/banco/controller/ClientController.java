package tech.getarrays.banco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.banco.Model.Respuesta;
import tech.getarrays.banco.entity.CuentaEntity;
import tech.getarrays.banco.entity.ClientEntity;
import tech.getarrays.banco.service.CuentaService;
import tech.getarrays.banco.service.ClientService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    private final CuentaService cuentaService;

    public ClientController(ClientService clientService, CuentaService cuentaService) {
        this.clientService = clientService;
        this.cuentaService = cuentaService;
    }
// get all users
    @GetMapping("")
    /*public ResponseEntity<List<ClientEntity>> getAllUsuarios () {
        List<ClientEntity> usuarios = clientService.findAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }*/
    public ResponseEntity<Respuesta> getAllClients () {
        Respuesta<List> output = new Respuesta<>();
        HttpStatus status=null;
        String msg=null;
        List clients=null;
        try {
            clients = clientService.findAllClients();
            msg="0-Successful operation";
            output.setDato(clients);
            output.setMessa(msg);
            output.setDone(true);
            status=HttpStatus.OK;

        }catch(Exception e){
            msg="1 error";
            output.setMessa(msg);
            output.setDone(false);
            status=HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Respuesta>(output, status);
    }
// get user by id
    @GetMapping("/{id}")
    public ResponseEntity<Respuesta> getClientbyId (@PathVariable("id") Long id) {
        Respuesta<ClientEntity> output = new Respuesta<>();
        HttpStatus status = null;
        String msg = null;
        ClientEntity datos =null;
        try{
            datos = clientService.findClientById(id);
            System.out.println(id);
            msg="0- all accounts found";
            output.setDato(datos);
            output.setMessa(msg);
            output.setDone(true);
            status = HttpStatus.OK;
        }catch(Exception e){
            msg=" Client not found ";
            output.setMessa(msg);
            output.setDone(false);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            System.out.print(e);
        }
        return new ResponseEntity<>(output, status);
    }

// add a new customer
    @PostMapping("")
    public ResponseEntity<Respuesta> addUsuario(@RequestBody ClientEntity client){
        ClientEntity newClient = clientService.addClient(client);
        newClient.setFecha_crea(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
        Respuesta<ClientEntity> output = new Respuesta<>();
        HttpStatus status = null;
        String msg = null;
        ClientEntity datos =null;
        try{
            datos = clientService.addClient(client);
            datos.setFecha_crea(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
            msg ="0- client posted";
            output.setDato(datos);
            output.setMessa(msg);
            output.setDone(true);
            status = HttpStatus.CREATED;
        }catch(Exception e){
            msg=" 1-Error, contact support ";
            output.setMessa(msg);
            output.setDone(false);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }
//update client
    @PutMapping("/update")
    public ResponseEntity<Respuesta> updateClient(@RequestBody ClientEntity client){
        //ClientEntity updateUsuario = clientService.updateUsuario(usuario);
        Respuesta<ClientEntity> output = new Respuesta<>();
        HttpStatus status = null;
        String msg = null;
        ClientEntity datos =null;
        try{
            datos = clientService.updateClient(client);
            msg ="0- client updated";
            output.setDato(datos);
            output.setMessa(msg);
            output.setDone(true);
            status = HttpStatus.OK;
        }catch(Exception e){
            msg=" 1-Error, contact support ";
            output.setMessa(msg);
            output.setDone(false);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

// delete user
@DeleteMapping("/delete/{id}")
public ResponseEntity<Respuesta> deleteClient(@PathVariable("id") Long id){
    Respuesta<List> output = new Respuesta<>();
    HttpStatus status = HttpStatus.OK;
    String msg = null;
    List<CuentaEntity> cuentas = null;
    try {
        cuentas = cuentaService.findCuentabyIdClient(id);
        Integer Count = 0;
        System.out.println(cuentas);
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getEstado().toLowerCase(Locale.ROOT).equals("activo")) {
                Count++;
            }
        }
        System.out.println(Count);
        if (Count > 0) {
            msg=" 1- Not possible to delete, customer has accounts ";
            output.setMessa(msg);
            output.setDone(false);
            status= HttpStatus.OK;
        } else {
            clientService.deleteClient(id);
            msg=" 0- Deleted User ";
            output.setMessa(msg);
            output.setDone(true);
            status= HttpStatus.OK;

        }
    }catch (Exception e){
        msg=" 0- Error, contact support ";
        output.setMessa(msg);
        output.setDone(false);
        status= HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<Respuesta>(output, status);
}

    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") Long id){
        List<Cuenta> cuentas = cuentaService.findCuentabyIdUsuario(id);
        if (cuentas.isEmpty()){
            clientService.deleteUsuario(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(cuentas.isEmpty(), HttpStatus.BAD_REQUEST);
        }
    }*/


}

