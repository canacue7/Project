import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OperacionesService } from 'src/app/Operaciones/Services/operaciones.service';

@Component({
  selector: 'app-consignacion',
  templateUrl: './consignacion.component.html',
  styleUrls: ['./consignacion.component.css']
})
export class ConsignacionComponent implements OnInit {
  profileForm= new FormGroup({
    monto: new FormControl('')
  });

  constructor(private operacionesService: OperacionesService,private route: ActivatedRoute,  private router:Router) { }

  ngOnInit(): void {
  }

  consignar(){
    const accountId = this.route.snapshot.paramMap.get('id');
    const depos = this.profileForm.value;
    console.log(depos);
    this.operacionesService.addSaldo(+accountId,depos).subscribe(data=>{
      alert(data.messa)
    },err=>{
      alert(err.error.messa)
    });
    this.router.navigate(['/listarCuenta/'+accountId]);
  }

}
