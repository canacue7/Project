import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OperacionesService } from 'src/app/Operaciones/Services/operaciones.service';

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

  constructor(private operacionesService: OperacionesService,private route: ActivatedRoute,  private router:Router) { }

  ngOnInit(): void {
  }

  transferir(){
    const accountId = this.route.snapshot.paramMap.get('id');
    const accountDest=this.profileForm.getRawValue().id_cuenta_destino;
    const transf = this.profileForm.value;
    this.operacionesService.trSaldo(+accountId,+accountDest,transf).subscribe(data=>{
      alert(data.messa)
    }, err=>{
      alert(err.error.messa)
    });
    this.router.navigate(["/listarCuenta",accountId]);
  }
}
