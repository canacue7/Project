import { Component, OnInit } from '@angular/core';
// import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from 'src/app/Client/Models/Client';
import { ClientService } from 'src/app/Client/Services/client.service';
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
  constructor(private clientService: ClientService, private route: ActivatedRoute, private router:Router) { }

  ngOnInit(): void {
    const userId = this.route.snapshot.paramMap.get('id'); 
    this.clientService.getClientById(+userId).subscribe(data=>{
      this.client=data.dato},err=>{
        alert(err.console.error.messa);
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

  Actualizar(client:Client){
    this.clientService.updateClient(client).subscribe(data=>{this.client=data.dato;
    alert(data.messa)});
    this.router.navigate(['/listar']);

  }

}
