import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Client } from 'src/app/Client/Models/Client';
import { ClientService } from 'src/app/Client/Services/client.service';
import Swal from 'sweetalert2';
import {​​​​​​Location}​​​​​​ from '@angular/common'



@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent implements OnInit {
  profileForm = new FormGroup({
    tipoId: new FormControl(''),
    identificacion: new FormControl(''),
    name: new FormControl(''),
    apellido: new FormControl(''),
    email: new FormControl(''),
    fecha_nac: new FormControl(''),
    estado: new FormControl(''),
  }); 
  constructor(private service: ClientService,private location: Location, private router:Router) { }

  ngOnInit(): void {
  }

  Return(){
    this.location.back();
  }

  Guardar(){
    const client= this.profileForm.value
    this.service.addClient(client).subscribe(data=>{
      Swal.fire({
        icon: 'success',
        title: data.messa,
        showConfirmButton: false,
        timer: 1500
      })
    this.router.navigate(["listar"]);
  }, error =>{
    Swal.fire('Error!', error.error.messa, 'error');
  })
  }
}
