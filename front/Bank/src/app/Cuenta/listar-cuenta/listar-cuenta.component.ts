import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cuenta } from 'src/app/Cuenta/Models/Cuenta';
import { Operaciones } from 'src/app/Operaciones/Models/Operaciones';
import { CuentaService } from 'src/app/Cuenta/Services/cuenta.service';

@Component({
  selector: 'app-listar-cuenta',
  templateUrl: './listar-cuenta.component.html',
  styleUrls: ['./listar-cuenta.component.css']
})
export class ListarCuentaComponent implements OnInit {
  public ops:Operaciones[];

  cuentas:Cuenta[];
  constructor(private cuentaService: CuentaService,private route: ActivatedRoute,  private router:Router) { }

  ngOnInit(): void {
    const userId = this.route.snapshot.paramMap.get('id'); 
    console.log(userId)
    this.cuentaService.getallCuentas(+userId).subscribe(data=>{
      console.log(data.done);
      this.cuentas=data.dato})
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
      console.log("se hizo el inative");
    this.cuentaService.inactiveCuenta(cuenta,cuentaId).subscribe(data=>{
      alert(data.messa)
    })}else{
      console.log("se hizo el active");
      this.cuentaService.activeCuenta(cuenta,cuentaId).subscribe(data=>{
        alert(data.messa)
      })
    }
  }

  Delete(cuenta:Cuenta){
    this.cuentaService.deleteCuenta(cuenta.id).subscribe(data=>{
      alert(data.messa);
    },err=>{
      alert(err.error.messa)
    })
  }
}
