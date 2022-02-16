import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cuenta } from 'src/app/Cuenta/Models/Cuenta';
import { CuentaService } from 'src/app/Cuenta/Services/cuenta.service';
import Swal from 'sweetalert2';

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
    

    Swal.fire({
      title: 'Do you want to save the changes?',
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonText: 'Save',
      denyButtonText: `Don't save`,
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        Swal.fire('Saved!', '', 'success')
        this.cuentaService.updateCuenta(cuenta).subscribe(data=>{this.cuenta=data.dato;
          alert(data.messa)
        }, error =>{
          Swal.fire('Error!', error.messa, 'error');
        });
          this.router.navigate(['/listar/'+cuenta.id_usuario]);
      } else if (result.isDenied) {
        Swal.fire('Changes are not saved', '', 'info');
          this.router.navigate(['/listar/'+cuenta.id_usuario]);
      }
    })

  }

}
