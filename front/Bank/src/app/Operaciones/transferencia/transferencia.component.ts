import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OperacionesService } from 'src/app/Operaciones/Services/operaciones.service';
import Swal from 'sweetalert2';
import {​​​​​​Location}​​​​​​ from '@angular/common'


@Component({
  selector: 'app-transferencia',
  templateUrl: './transferencia.component.html',
  styleUrls: ['./transferencia.component.css']
})
export class TransferenciaComponent implements OnInit {
  profileForm = new FormGroup({
    monto: new FormControl(''),
    id_cuenta_destino: new FormControl('')
  })

  constructor(private operacionesService: OperacionesService,
    private location: Location,private route: ActivatedRoute,  private router:Router) { }

  ngOnInit(): void {
  }

  Return(){
    this.location.back();
  }
  transferir(){
    const accountId = this.route.snapshot.paramMap.get('id');
    const accountDest=this.profileForm.getRawValue().id_cuenta_destino;
    const transf = this.profileForm.value;
    this.operacionesService.trSaldo(+accountId,+accountDest,transf).subscribe(data=>{
      Swal.fire('Success!', data.messa, 'success');
    }, err=>{
      Swal.fire('Error!', err.error.messa, 'error');
    });
    this.router.navigate(["/listarCuenta",accountId]);
  }
}
