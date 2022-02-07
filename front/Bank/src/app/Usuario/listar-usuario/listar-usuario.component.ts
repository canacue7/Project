import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cuenta } from 'src/app/Cuenta/Models/Cuenta';
import { User } from 'src/app/user/Models/User';
import { Usuario } from 'src/app/Usuario/Models/Usuario';
import { UsuarioService } from 'src/app/Usuario/Services/usuario.service';


@Component({
  selector: 'app-listar-usuario',
  templateUrl: './listar-usuario.component.html',
  styleUrls: ['./listar-usuario.component.css']
})
export class ListarUsuarioComponent implements OnInit {
  user: User = new User();

  public usuarios?:Usuario[];
  public cuentas?:Cuenta[];

  constructor(private usuarioService: UsuarioService, private router:Router) { }

  ngOnInit(): void {
    this.usuarioService.getUsuarios().subscribe(resp=>{
      console.log(resp.messa);
      if(resp.done){
        this.usuarios=resp.dato;
        console.log(resp.dato);
      }/*else{
        this.usuarios=resp.messa
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

  Delete(usuario:Usuario){
    // this.usuarioService.deleteUsuario(usuario.id).subscribe(data=>{this.usuarios.filter(p=>p!==usuario);
    // alert("Usuario eliminado");}, error=>alert("User has active accounts"))
    this.usuarioService.deleteUsuario(usuario.id).subscribe(data=>{
      alert(data.messa);
    },err=>{
      console.log(err.error.messa);
      alert(err.error.messa);
  })
  }

  Cuentas(userId:number){
    
    //localStorage.setItem("id",usuario.id_usuario.toString());
    this.router.navigate(['/listarCuentas', userId]); 
    // this.cuentaService.getallCuentas().subscribe(data=>{this.usuarios.filter(p=>p!==usuario);
    // alert("Usuario eliminado");})
  }

}
