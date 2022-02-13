import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Respuesta } from 'src/app/Shared/Respuesta';
import { GlobalService } from 'src/app/Shared/Services/global.service';
import { User } from '../Models/User';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-editar-user',
  templateUrl: './editar-user.component.html',
  styleUrls: ['./editar.component.css']
})
export class EditarUserComponent implements OnInit {

  user= new User;
  // @Input() user: User={
  //   userName:"",
  //   password:"",
  //   jwt:"",
  //   firstName:"",
  //   lastName:""
  // }


  constructor(private userService: UserService, public globalService: GlobalService, router: Router) { }

  ngOnInit(): void {
    this.userService.getByUserName(this.globalService.user.userName).subscribe(data=>{
      if(data.done){
        this.user=data.dato;
      }
      console.log(this.user);
    } ,err=>{
      alert(err.error.messa);
    })
  }

  update():void{
    this.userService.update(this.user).subscribe(resp=>{
      if (resp.done){
        alert(resp.messa)
      }
      },err=>{
        alert(err.error.messa);
    })
  }

}
