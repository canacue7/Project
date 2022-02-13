import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalService } from 'src/app/Shared/Services/global.service';
import { User } from 'src/app/user/Models/User';
//import { EventEmitter } from 'stream';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Output() update:EventEmitter<User> = new EventEmitter();

  isLogged = false;
  updateU = false;
  regis= false;
   
  //user: User = new User();
  title = 'Bank';

  constructor(private router:Router, public globalService: GlobalService) { }

  ngOnInit(): void {
    //this.globalService= new GlobalService();
  }

  authenticated(user: User){
    console.log("entró a autenticated");
    this.globalService.user = user;
    this.isLogged=true;
  }

  register(user: User){
    this.globalService.user = user;
    this.regis=true;
  }
  Listar(){
    //  this.router.navigate(["listar"]);
    }
  
    Nuevo(){
     // this.router.navigate(["add"]);
    }

    Login(){
      //this.globalService.user = user;
      //  this.router.navigate(["listar"]);
    }
    Registrar(){
      //  this.router.navigate(["listar"]);
      // this.globalService.user.userName="";
      // this.globalService.user.jwt="";

      this.regis=true;
    }
    Logout(){
      this.globalService = new GlobalService();
      this.isLogged=false;
      this.regis=false
    }

    // public newPassword(userDto: User):Observable<Respuesta<UserDto<User>>> {
    //   return this.http.put<Respuesta<UserDto<User>>>(`${this.apiServerUrl}/users`, userDto);
    // }
    NewPass(){

    }
}
