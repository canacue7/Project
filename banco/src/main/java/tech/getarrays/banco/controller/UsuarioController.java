package tech.getarrays.banco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.banco.Model.Respuesta;
import tech.getarrays.banco.entity.CuentaEntity;
import tech.getarrays.banco.entity.UsuarioEntity;
import tech.getarrays.banco.service.CuentaService;
import tech.getarrays.banco.service.UsuarioService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final CuentaService cuentaService;

    public UsuarioController(UsuarioService usuarioService, CuentaService cuentaService) {
        this.usuarioService = usuarioService;
        this.cuentaService = cuentaService;
    }
// get all users
    @GetMapping("")
    public ResponseEntity<List<UsuarioEntity>> getAllUsuarios () {
        List<UsuarioEntity> usuarios = usuarioService.findAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
// get user by id
    @GetMapping("/{id}")
    public ResponseEntity<Respuesta> getUsuariobyId (@PathVariable("id") Long id) {
        Respuesta<UsuarioEntity> output = new Respuesta<>();
        HttpStatus status = null;
        String msg = null;
        UsuarioEntity datos =null;
        try{
            datos = usuarioService.findUsuarioById(id);
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
        }
        return new ResponseEntity<>(output, status);
    }

// add a new user
    @PostMapping("")
    public ResponseEntity<Respuesta> addUsuario(@RequestBody UsuarioEntity usuario){
        UsuarioEntity newUsuario = usuarioService.addUsuario(usuario);
        newUsuario.setFecha_crea(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
        Respuesta<UsuarioEntity> output = new Respuesta<>();
        HttpStatus status = null;
        String msg = null;
        UsuarioEntity datos =null;
        try{
            datos = usuarioService.addUsuario(usuario);
            msg ="0- user posted";
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
//update user
    @PutMapping("/update")
    public ResponseEntity<Respuesta> updateUsuario(@RequestBody UsuarioEntity usuario){
        UsuarioEntity updateUsuario = usuarioService.updateUsuario(usuario);
        Respuesta<UsuarioEntity> output = new Respuesta<>();
        HttpStatus status = null;
        String msg = null;
        UsuarioEntity datos =null;
        try{
            datos = usuarioService.updateUsuario(usuario);
            msg ="0- user updated";
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
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") Long id){
        List<CuentaEntity> cuentas = cuentaService.findCuentabyIdUsuario(id);
        Integer Count=0;
        for(int i=0; i<cuentas.size(); i++){

            if(cuentas.get(i).getEstado().toLowerCase(Locale.ROOT).equals("activo")){
                Count++;
            }
        }
        if(Count>0){
            return new ResponseEntity<>("No se pudo porque tiene cuentas activas", HttpStatus.BAD_REQUEST);
        }else{
            usuarioService.deleteUsuario(id);

            return new ResponseEntity<>(cuentas,HttpStatus.OK);
        }
    }

    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") Long id){
        List<Cuenta> cuentas = cuentaService.findCuentabyIdUsuario(id);
        if (cuentas.isEmpty()){
            usuarioService.deleteUsuario(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(cuentas.isEmpty(), HttpStatus.BAD_REQUEST);
        }
    }*/


}

