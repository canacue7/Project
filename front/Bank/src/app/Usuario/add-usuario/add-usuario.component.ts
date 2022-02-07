import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/Usuario/Models/Usuario';
import { UsuarioService } from 'src/app/Usuario/Services/usuario.service';

@Component({
  selector: 'app-add-usuario',
  templateUrl: './add-usuario.component.html',
  styleUrls: ['./add-usuario.component.css']
})
export class AddUsuarioComponent implements OnInit {
  profileForm = new FormGroup({
    tipoId: new FormControl(''),
    identificacion: new FormControl(''),
    name: new FormControl(''),
    apellido: new FormControl(''),
    email: new FormControl(''),
    fecha_nac: new FormControl(''),
    estado: new FormControl(''),
  }); 
  constructor(private service: UsuarioService, private router:Router) { }

  ngOnInit(): void {
  }

  Guardar(){
    const usuario= this.profileForm.value
    this.service.addUsuario(usuario).subscribe(data=>{alert(data.messa);
    this.router.navigate(["listar"]);
  })
  }
}
