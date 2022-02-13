import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Client } from 'src/app/Client/Models/Client';
import { ClientService } from 'src/app/Client/Services/client.service';

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
  constructor(private service: ClientService, private router:Router) { }

  ngOnInit(): void {
  }

  Guardar(){
    const client= this.profileForm.value
    this.service.addClient(client).subscribe(data=>{alert(data.messa);
    this.router.navigate(["listar"]);
  })
  }
}
