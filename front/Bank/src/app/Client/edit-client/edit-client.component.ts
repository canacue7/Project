import { Component, OnInit } from '@angular/core';
// import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from 'src/app/Client/Models/Client';
import { ClientService } from 'src/app/Client/Services/client.service';
import Swal from 'sweetalert2';
import {​​​​​​Location}​​​​​​ from '@angular/common'

// import { Respuesta } from 'src/app/Shared/Respuesta';

@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styleUrls: ['./edit-client.component.css']
})
export class EditClientComponent implements OnInit {
  client: Client = new Client();
  // profileForm = new FormGroup({
  //   tipoId: new FormControl(''),
  //   identificacion: new FormControl(''),
  //   name: new FormControl(''),
  //   apellido: new FormControl(''),
  //   email: new FormControl(''),
  //   fecha_nac: new FormControl(''),
  //   estado: new FormControl(''),
  // }); 
  constructor(private clientService: ClientService, private location: Location, private route: ActivatedRoute, private router:Router) { }

  ngOnInit(): void {
    const userId = this.route.snapshot.paramMap.get('id'); 
    this.clientService.getClientById(+userId).subscribe(data=>{
      this.client=data.dato}, error =>{
        Swal.fire('Error!', error.messa, 'error');
      })

  }

  // Editar(){
  //   // let id=localStorage.getItem("id");
  //   // this.service.getClientById(+id).subscribe(data=>{this.clients=data})
  //   const userId = this.route.snapshot.paramMap.get('id'); 
  //   // console.log(userId)
  //   this.clientService.getClientById(+userId).subscribe(data=>{
  //     this.client=data.dato},err=>{
  //       alert(err.console.error.messa);
  //     })
  // }

  Return(){
    this.location.back();
}
  Actualizar(client:Client){

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
        this.clientService.updateClient(client).subscribe(data=>{this.client=data.dato;
        }, error =>{
          Swal.fire('Error!', error.error.messa, 'error');
        });
          this.router.navigate(['/listar']);
      } else if (result.isDenied) {
        Swal.fire('Changes are not saved', '', 'info');
        this.router.navigate(['/listar']);
      }
    })

  }

}
