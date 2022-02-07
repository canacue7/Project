import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cuenta } from 'src/app/Cuenta/Models/Cuenta';
import { CuentaService } from 'src/app/Cuenta/Services/cuenta.service';

@Component({
  selector: 'app-edit-cuenta',
  templateUrl: './edit-cuenta.component.html',
  styleUrls: ['./edit-cuenta.component.css']
})
export class EditCuentaComponent implements OnInit {
  cuenta: Cuenta;

  constructor(private cuentaService: CuentaService, private route: ActivatedRoute, private router:Router) { }

  ngOnInit(): void {
    const cuentaId = this.route.snapshot.paramMap.get('id'); 
    this.cuentaService.getCuentabyId(+cuentaId).subscribe(data=>{
      this.cuenta=data.dato},err=>{
        alert(err.console.error.messa);
      })
  }

  Actualizar(cuenta:Cuenta){
    this.cuentaService.updateCuenta(cuenta).subscribe(data=>{this.cuenta=data.dato;
    alert(data.messa)});
    this.router.navigate(['/listar']);

  }

}
