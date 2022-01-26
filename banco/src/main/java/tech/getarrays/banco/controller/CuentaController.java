package tech.getarrays.banco.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.banco.Model.Respuesta;
import tech.getarrays.banco.entity.CuentaEntity;
import tech.getarrays.banco.service.CuentaService;

import java.util.List;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;
    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }


    @GetMapping("")
    /*public ResponseEntity<List<CuentaEntity>> getAllCuentas (@PathParam("idUsuario")Long idUsuario) {
        List<CuentaEntity> cuentas = cuentaService.findCuentabyIdUsuario(idUsuario);
        return new ResponseEntity<>(cuentas, HttpStatus.OK);*/
    public ResponseEntity<List<CuentaEntity>> getAllCuentas () {
        List<CuentaEntity> cuentas = cuentaService.findAllCuentas();
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }
// get account by id account
    @GetMapping("/{id}")
    public ResponseEntity<Respuesta> getCuentabyId (@PathVariable("id") Long id) {
        Respuesta<CuentaEntity> output = new Respuesta<>();
        HttpStatus status = null;
        String msg = null;
        CuentaEntity datos =null;
        try {
            datos = cuentaService.findCuentabyId(id);
            msg="0- all accounts found";
            output.setDato(datos);
            output.setMessa(msg);
            output.setDone(true);
            status = HttpStatus.OK;
        }catch (Exception e){
            msg=" Account not found ";
            output.setMessa(msg);
            output.setDone(false);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(output, status);
    }
//add account
    @PostMapping("/{idUsuario}")
    public ResponseEntity<CuentaEntity> addCuenta(@RequestBody CuentaEntity cuenta, @PathVariable("idUsuario")Long idUsuario){
        cuenta.setId_usuario(idUsuario);
        CuentaEntity newCuenta = cuentaService.addCuenta(cuenta) ;
        return new ResponseEntity<>(newCuenta, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCuenta(@PathVariable("id") Long id){
        HttpStatus status = null;
        String msg = "quepasa";
        try {
            cuentaService.deleteCuenta(id);
            msg = "0-Successfully deleted";

            status = HttpStatus.OK;
        }catch(Exception e){

            msg = "1-Something failed, contact support";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(msg, status);
    }
    /*@PutMapping("/{id}/active")
    public ResponseEntity<CuentaEntity> updateCuenta(@RequestBody UsuarioEntity usuario){
        UsuarioEntity updateUsuario = usuarioService.updateUsuario(usuario);
        return new ResponseEntity<>(updateUsuario, HttpStatus.OK);
    }*/

    /*@PutMapping("/addSaldo")
    public  ResponseEntity<Cuenta> addSaldo(@RequestBody Cuenta cuenta){
        Cuenta monto = cuentaService.findCuentabyId(cuenta.getId());
        monto.setSaldo(monto.getSaldo()+ cuenta.getSaldo());
        cuentaService.addCuenta(monto);
        return new ResponseEntity<>(monto, HttpStatus.OK);
    }

    @PutMapping("/removeSaldo")
    public  ResponseEntity<Cuenta> retireSaldo(@RequestBody Cuenta cuenta){
        Cuenta monto = cuentaService.findCuentabyId(cuenta.getId());
        monto.setSaldo(monto.getSaldo()-cuenta.getSaldo());
        cuentaService.addCuenta(monto);
        return  new ResponseEntity<>(monto, HttpStatus.OK);
    }*/


    //


}
