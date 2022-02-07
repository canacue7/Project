package tech.getarrays.banco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.banco.Model.Respuesta;
import tech.getarrays.banco.entity.CuentaEntity;
import tech.getarrays.banco.entity.OperacionesEntity;
import tech.getarrays.banco.entity.UsuarioEntity;
import tech.getarrays.banco.service.CuentaService;
import tech.getarrays.banco.service.OperacionesService;
import tech.getarrays.banco.Model.Respuesta;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/operaciones")
public class OperacionesController implements Serializable {

    private final OperacionesService operacionesService;
    private final CuentaService cuentaService;

    public OperacionesController(OperacionesService operacionesService, CuentaService cuentaService) {
        this.operacionesService = operacionesService;
        this.cuentaService = cuentaService;
    }

    /*@GetMapping("{idCuenta}")
    public ResponseEntity<List<OperacionesEntity>> getAllOps (@PathVariable("idCuenta")Long idCuenta) {
        List<OperacionesEntity> ops = operacionesService.findOpsByCuentaId(idCuenta);
        return new ResponseEntity<>(ops, HttpStatus.OK);
    }*/

    @GetMapping("{idCuenta}")
    public ResponseEntity<Respuesta> getAllOps (@PathVariable("idCuenta")Long idCuenta) {
        Respuesta<List> output = new Respuesta<>();
        HttpStatus status=null;
        String msg=null;
        List ops=null;
        try{
            ops = operacionesService.findOpsByCuentaId(idCuenta);
            msg="0-Successful operation";
            output.setDato(ops);
            output.setMessa(msg);
            output.setDone(true);
            status=HttpStatus.OK;

        }catch (Exception e){
            msg="1- Error ";
            output.setDato(ops);
            output.setMessa(msg);
            output.setDone(false);
            status=HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Respuesta>(output, status);
    }

    /*@PostMapping("/add/{idCuenta}")
    public ResponseEntity<OperacionesEntity> addOperaciones(@RequestBody OperacionesEntity operaciones, @PathVariable("idCuenta")Long idCuenta){
        operaciones.setId_cuenta(idCuenta);
        OperacionesEntity newOperacion = operacionesService.addOperaciones(operaciones) ;
        return new ResponseEntity<>(newOperacion, HttpStatus.CREATED);
    }*/
    // haciendo prueba para solo postear

// consignacion
    @PostMapping("/{idCuenta}/add")
    public  ResponseEntity<Respuesta> addSaldo(@RequestBody OperacionesEntity operaciones, @PathVariable("idCuenta") Long idCuenta){
        Respuesta<OperacionesEntity> output = new Respuesta<>();
        HttpStatus status=null;
        String msg=null;
        OperacionesEntity datos=null;

        try {
            CuentaEntity cuenta = cuentaService.findCuentabyId(idCuenta);
            operaciones.setSaldo_in(cuenta.getSaldo());
            cuenta.setSaldo(cuenta.getSaldo() + operaciones.getMonto());
            operaciones.setSaldo_fin(cuenta.getSaldo());
            operaciones.setTipo_transfer("consiginacion");
            operaciones.setMov_financiero("credito");
            operaciones.setId_cuenta(idCuenta);
            operaciones.setFecha_transfer(new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
            operaciones.setId_cuenta_destino(null);
            cuentaService.addCuenta(cuenta);
            System.out.println(cuenta);
            datos=operacionesService.addOperaciones(operaciones);
            msg="0-Successful operation";
            output.setDato(datos);
            output.setMessa(msg);
            output.setDone(true);
            status=HttpStatus.OK;
        }catch(Exception e){
            msg="1-Failed operacion";
            output.setMessa(msg);
            output.setDone(false);
            status=HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(output, status);
    }

    @PostMapping("/{idCuenta}/withdraw")
    public  ResponseEntity<Respuesta> retirSaldo(@RequestBody OperacionesEntity operaciones, @PathVariable("idCuenta") Long idCuenta){
        Respuesta<OperacionesEntity> output = new Respuesta<>();
        HttpStatus status=null;
        String msg=null;
        OperacionesEntity datos=null;

        try {
            CuentaEntity cuenta = cuentaService.findCuentabyId(idCuenta);
            if (((cuenta.getTipo_cuenta().toLowerCase(Locale.ROOT).equals("ahorros")) && ((cuenta.getSaldo() - operaciones.getMonto()) >= 0)) ||
                    ((cuenta.getTipo_cuenta().toLowerCase(Locale.ROOT).equals("corriente")) && ((cuenta.getSaldo() - operaciones.getMonto()) >= -2000000))) {
                operaciones.setSaldo_in(cuenta.getSaldo());
                cuenta.setSaldo(cuenta.getSaldo() - operaciones.getMonto());
                operaciones.setSaldo_fin(cuenta.getSaldo());
                operaciones.setTipo_transfer("Retiro");
                operaciones.setMov_financiero("debito");
                operaciones.setId_cuenta(idCuenta);
                operaciones.setFecha_transfer(new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
                operaciones.setId_cuenta_destino(null);
                cuentaService.addCuenta(cuenta);
                datos = operacionesService.addOperaciones(operaciones);
                msg="0-Successful operation";
                output.setDato(datos);
                output.setMessa(msg);
                output.setDone(true);
                status=HttpStatus.OK;

            } else {
                if (cuenta.getTipo_cuenta().toLowerCase(Locale.ROOT).equals("ahorros")) {
                    msg="2- Not enough funds";
                    output.setMessa(msg);
                    output.setDone(false);
                    status=HttpStatus.OK;
                } else if (cuenta.getTipo_cuenta().toLowerCase(Locale.ROOT).equals("corriente")) {
                    msg="2- Not enough funds (not possible to overdraw more than 2000000)";
                    output.setMessa(msg);
                    output.setDone(false);
                    status=HttpStatus.OK;
                }
            }
        }catch(Exception e){
                msg="1- Error";
                output.setMessa(msg);
                output.setDone(false);
                status=HttpStatus.INTERNAL_SERVER_ERROR;

        }

        return new ResponseEntity<Respuesta>(output, status);

    }

    @PostMapping("/{CuentaFuente}/transfer/{CuentaDestino}")
    public  ResponseEntity<Respuesta> addSaldo(@RequestBody OperacionesEntity operacionesFuente,
                                                 @PathVariable("CuentaFuente") Long idCuentaFuente,
                                                 @PathVariable("CuentaDestino") Long idCuentaDestino,
                                                 OperacionesEntity operacionesDestino){

        Respuesta<OperacionesEntity> output = new Respuesta<>();
        HttpStatus status=null;
        String msg=null;
        OperacionesEntity datos=null;
        OperacionesEntity datosDesty=null;
        //if (!(cuentaFuente.getTipo_cuenta().toLowerCase(Locale.ROOT)=="ahorros" && ((cuentaFuente.getSaldo()-operacionesFuente.getMonto())<0)))
        try {
            CuentaEntity cuentaFuente = cuentaService.findCuentabyId(idCuentaFuente);
            CuentaEntity cuentaDestino= cuentaService.findCuentabyId(idCuentaDestino);
            if (((cuentaFuente.getTipo_cuenta().toLowerCase(Locale.ROOT).equals("ahorros")) && ((cuentaFuente.getSaldo() - operacionesFuente.getMonto()) >= 0)) ||
                    ((cuentaFuente.getTipo_cuenta().toLowerCase(Locale.ROOT).equals("corriente")) && ((cuentaFuente.getSaldo() - operacionesFuente.getMonto()) >= -2000000))) {
                operacionesFuente.setSaldo_in(cuentaFuente.getSaldo());
                cuentaFuente.setSaldo(cuentaFuente.getSaldo() - operacionesFuente.getMonto());
                operacionesFuente.setSaldo_fin(cuentaFuente.getSaldo());
                operacionesFuente.setTipo_transfer("transferencia");
                operacionesFuente.setMov_financiero("debito");
                operacionesFuente.setFecha_transfer(new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
                operacionesFuente.setId_cuenta(idCuentaFuente);
                operacionesFuente.setId_cuenta_destino(idCuentaDestino);
                cuentaService.addCuenta(cuentaFuente);
                datos=operacionesService.addOperaciones(operacionesFuente);
                //cuenta destino
                operacionesDestino.setSaldo_in(cuentaDestino.getSaldo());
                cuentaDestino.setSaldo(cuentaDestino.getSaldo() + operacionesFuente.getMonto());
                operacionesDestino.setTipo_transfer("transferencia");
                operacionesDestino.setMov_financiero("credito");
                operacionesFuente.setFecha_transfer(new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()));
                operacionesDestino.setId_cuenta(idCuentaDestino);
                operacionesDestino.setFecha_transfer(operacionesFuente.getFecha_transfer());
                operacionesDestino.setMonto(operacionesFuente.getMonto());
                operacionesDestino.setSaldo_fin(cuentaDestino.getSaldo());
                cuentaService.addCuenta(cuentaDestino);
                operacionesService.addOperaciones(operacionesDestino);
                msg="0- transfering successfully performed";
                output.setDato(datos);
                output.setDatoDesti(datosDesty);
                output.setMessa(msg);
                output.setDone(true);
                status=HttpStatus.OK;
            } else {
                if (cuentaFuente.getTipo_cuenta().toLowerCase(Locale.ROOT).equals("ahorros")) {
                    msg="2-Not enough funds";
                    output.setMessa(msg);
                    output.setDone(false);
                    status=HttpStatus.OK;
                } else if (cuentaFuente.getTipo_cuenta().toLowerCase(Locale.ROOT).equals("corriente")) {
                    msg="2-Not enough funds (not possible to overdraw more than 2000000)";
                    output.setMessa(msg);
                    output.setDone(false);
                    status=HttpStatus.OK;
                }
            }
        }catch (Exception e){
            msg="1- Error";
            output.setMessa(msg);
            output.setDone(false);
            status=HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Respuesta>(output, status);
    }

    /*@PutMapping("/removeSaldo")
    public  ResponseEntity<Cuenta> retireSaldo(@RequestBody Cuenta cuenta){
        Cuenta monto = cuentaService.findCuentabyId(cuenta.getId());
        monto.setSaldo(monto.getSaldo()-cuenta.getSaldo());
        cuentaService.addCuenta(monto);
        return  new ResponseEntity<>(monto, HttpStatus.OK);
    }*/

}
