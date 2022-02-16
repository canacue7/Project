import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CuentaService } from 'src/app/Cuenta/Services/cuenta.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-cuenta',
  templateUrl: './add-cuenta.component.html',
  styleUrls: ['./add-cuenta.component.css']
})
export class AddCuentaComponent implements OnInit {
  profileForm = new FormGroup({
    estado: new FormControl(''),
    tipo_cuenta: new FormControl(''),
    saldo: new FormControl('')
  });
  constructor(private service: CuentaService, private route: ActivatedRoute, private router:Router) { }

  ngOnInit(): void {
    console.log("la prueba da: "+ this.route.snapshot.paramMap.get('id'));
  }

  Guardar(){
    const userId = this.route.snapshot.paramMap.get('id'); 
    console.log(this.route.snapshot.paramMap.get('id'));
    const cuenta= this.profileForm.value
    this.service.addCuenta(cuenta,+userId).subscribe(data=>{
      Swal.fire({
        icon: 'success',
        title: data.messa,
        showConfirmButton: false,
        timer: 1500
      });
    this.router.navigate(['/listarCuentas', userId]);
  }, error =>{
    Swal.fire('Error!', error.error.messa, 'error');
  })
  }

}
