import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OperacionesService } from 'src/app/Operaciones/Services/operaciones.service';
import Swal from 'sweetalert2';
import {​​​​​​Location}​​​​​​ from '@angular/common'


@Component({
  selector: 'app-consignacion',
  templateUrl: './consignacion.component.html',
  styleUrls: ['./consignacion.component.css']
})
export class ConsignacionComponent implements OnInit {
  profileForm= new FormGroup({
    monto: new FormControl('')
  });

  constructor(private operacionesService: OperacionesService,
    private location: Location, private route: ActivatedRoute,  private router:Router,) { }

  ngOnInit(): void {
  }

  Return(){
    this.location.back();
  }
  consignar(){
    const accountId = this.route.snapshot.paramMap.get('id');
    const depos = this.profileForm.value;
    console.log(depos);
    this.operacionesService.addSaldo(+accountId,depos).subscribe(data=>{
      Swal.fire('Success!', data.messa, 'success');

    },err=>{
      Swal.fire('Error!', err.error.messa, 'error');

    });
    this.router.navigate(['/listarCuentas/'+accountId]);
  }

}
