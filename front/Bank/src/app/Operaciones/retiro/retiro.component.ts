import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
// import { ConsoleReporter } from 'jasmine';
import { Operaciones } from 'src/app/Operaciones/Models/Operaciones';
import { OperacionesService } from 'src/app/Operaciones/Services/operaciones.service';

@Component({
  selector: 'app-retiro',
  templateUrl: './retiro.component.html',
  styleUrls: ['./retiro.component.css']
})
export class RetiroComponent implements OnInit {
  ops: Operaciones;
  profileForm = new FormGroup({
  //   tipo_transfer: new FormControl(''),
  //   fecha_transfer: new FormControl(''),
  //   saldo_in: new FormControl(''),
  //   saldo_fin: new FormControl(''),
    monto: new FormControl('')
    // mov_financiero: new FormControl(''),
    // id_cuenta: new FormControl(''),
  });
  constructor(private operacionesService: OperacionesService,private route: ActivatedRoute,  private router:Router) { }


  ngOnInit(): void {
  }

  retirar(){
    
    const accountId= this.route.snapshot.paramMap.get('id');
    const withd = this.profileForm.value;
    console.log(accountId);
    this.operacionesService.retirSaldo(+accountId,withd).subscribe(data=>alert(data.messa));
    this.router.navigate(["/listarCuentas/"+accountId]);
  }

}
