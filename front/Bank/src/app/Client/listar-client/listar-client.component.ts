import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cuenta } from 'src/app/Cuenta/Models/Cuenta';
import { Client } from 'src/app/Client/Models/Client';
import { ClientService } from 'src/app/Client/Services/client.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-listar-client',
  templateUrl: './listar-client.component.html',
  styleUrls: ['./listar-client.component.css']
})
export class ListarClientComponent implements OnInit {

  public clients?:Client[];
  public cuentas?:Cuenta[];

  constructor(private clientService: ClientService, private router:Router) { }

  ngOnInit(): void {
    this.clientService.getClients().subscribe(resp=>{
      console.log(resp.messa);
      if(resp.done){
        this.clients=resp.dato;
        console.log(resp.dato);
      }/*else{
        this.clients=resp.messa
        console.log(resp.messa);
        alert(resp.messa);
      }*/
      },err=>{
        console.log(err.error.messa);
        alert(err.error.messa);
    })
  }

  Editar(userId:number){
    this.router.navigate(['/edit', userId]); 
  }

  Delete(client:Client){
    // this.clientService.deleteClient(client.id).subscribe(data=>{this.clients.filter(p=>p!==client);
    // alert("Client eliminado");}, error=>alert("User has active accounts"))
    this.clientService.deleteClient(client.id).subscribe(data=>{
      Swal.fire('Success', data.messa, 'info');

    }, error =>{
    Swal.fire('Error!', error.messa, 'error');
  })
  }

  Cuentas(userId:number){
    
    //localStorage.setItem("id",client.id_client.toString());
    this.router.navigate(['/listarCuentas', userId]); 
    // this.cuentaService.getallCuentas().subscribe(data=>{this.clients.filter(p=>p!==client);
    // alert("Client eliminado");})
  }

}
