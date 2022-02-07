import { Component, OnInit } from '@angular/core';
// import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from 'src/app/Usuario/Models/Usuario';
import { UsuarioService } from 'src/app/Usuario/Services/usuario.service';
// import { Respuesta } from 'src/app/Shared/Respuesta';

@Component({
  selector: 'app-edit-usuario',
  templateUrl: './edit-usuario.component.html',
  styleUrls: ['./edit-usuario.component.css']
})
export class EditUsuarioComponent implements OnInit {
  usuario: Usuario;
  // profileForm = new FormGroup({
  //   tipoId: new FormControl(''),
  //   identificacion: new FormControl(''),
  //   name: new FormControl(''),
  //   apellido: new FormControl(''),
  //   email: new FormControl(''),
  //   fecha_nac: new FormControl(''),
  //   estado: new FormControl(''),
  // }); 
  constructor(private usuarioService: UsuarioService, private route: ActivatedRoute, private router:Router) { }

  ngOnInit(): void {
    const userId = this.route.snapshot.paramMap.get('id'); 
    this.usuarioService.getUsuarioById(+userId).subscribe(data=>{
      this.usuario=data.dato},err=>{
        alert(err.console.error.messa);
      })

  }

  // Editar(){
  //   // let id=localStorage.getItem("id");
  //   // this.service.getUsuarioById(+id).subscribe(data=>{this.usuarios=data})
  //   const userId = this.route.snapshot.paramMap.get('id'); 
  //   // console.log(userId)
  //   this.usuarioService.getUsuarioById(+userId).subscribe(data=>{
  //     this.usuario=data.dato},err=>{
  //       alert(err.console.error.messa);
  //     })
  // }

  Actualizar(usuario:Usuario){
    this.usuarioService.updateUsuario(usuario).subscribe(data=>{this.usuario=data.dato;
    alert(data.messa)});
    this.router.navigate(['/listar']);

  }

}
