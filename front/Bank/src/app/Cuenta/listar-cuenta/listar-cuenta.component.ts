import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cuenta } from 'src/app/Cuenta/Models/Cuenta';
import { Operaciones } from 'src/app/Operaciones/Models/Operaciones';
import { CuentaService } from 'src/app/Cuenta/Services/cuenta.service';
import Swal from 'sweetalert2';
import {​​​​​​Location}​​​​​​ from '@angular/common'
import { GlobalService } from 'src/app/Shared/Services/global.service';


@Component({
  selector: 'app-listar-cuenta',
  templateUrl: './listar-cuenta.component.html',
  styleUrls: ['./listar-cuenta.component.css']
})
export class ListarCuentaComponent implements OnInit {
  public ops:Operaciones[];

  cuentas:Cuenta[];
  constructor(private cuentaService: CuentaService,private route: ActivatedRoute,
    private location: Location,  private router:Router,
    public globalService:GlobalService) { }
  public userId= this.route.snapshot.paramMap.get('id');

  ngOnInit(): void {
    const userId = this.route.snapshot.paramMap.get('id'); 
    console.log(userId)
    this.cuentaService.getallCuentas(+userId).subscribe(data=>{
      console.log(data.done);
      this.cuentas=data.dato})
  }

  Return(){
      this.location.back();
  }

  Operaciones(accountId:number){
    
    //localStorage.setItem("id",usuario.id_usuario.toString());
    this.router.navigate(['/listarOperaciones', accountId]);
    // this.cuentaService.getallCuentas().subscribe(data=>{this.usuarios.filter(p=>p!==usuario);
    // alert("Usuario eliminado");})
  }

  Retirar(accountId:number){
    
   this.router.navigate(['/retiro', accountId]);

  }

  Consignar(accountId:number){
    this.router.navigate(['/consignacion', accountId]);
  }

  Transfer(accountId:number){
    this.router.navigate(['/transferencia', accountId]);
  }

  Ncuenta(){
    const userId = this.route.snapshot.paramMap.get('id'); 
    this.router.navigate(['/addCuenta', userId]);
  }

  Editar(cuentaId:number){
    this.router.navigate(['/editCuenta',cuentaId])
  }

  inactive(cuenta:Cuenta, cuentaId:number){
    console.log(cuenta.estado)
    if(cuenta.estado.toLowerCase()=="activo"){
      console.log("se hizo el incative");
    this.cuentaService.inactiveCuenta(cuenta,cuentaId).subscribe(data=>{
      Swal.fire('Success', data.messa, 'info');
    })}else{
      console.log("se hizo el active");
      this.cuentaService.activeCuenta(cuenta,cuentaId).subscribe(data=>{
        Swal.fire('Success', data.messa, 'info');
      })
    }
  }

  Delete(cuenta:Cuenta){
    Swal.fire({
      title: 'Do you really want to cancel this account?',
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonText: 'Proceed',
      denyButtonText: `Abort`,
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        this.cuentaService.deleteCuenta(cuenta.id).subscribe(data=>{        
          Swal.fire('Account cancelled', data.messa, 'success')
        },error=>{
          Swal.fire('Error!', error.error.messa, 'error')

        });
      } else if (result.isDenied) {
        Swal.fire('Account not cancelled', '', 'info');
      }
    })
  }
}
