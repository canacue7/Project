import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/app/Shared/Services/global.service';
import { User } from '../Models/User';
import { UserDto } from '../Models/UserDto';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-new-password',
  templateUrl: './new-password.component.html',
  styleUrls: ['./new-password.component.css']
})
export class NewPasswordComponent implements OnInit {

  userDto= new UserDto;
  constructor(private userService: UserService, public globalService: GlobalService) { }

  ngOnInit(): void {
    this.userDto.user.userName = this.globalService.user.userName;
    //this.userService.getByUserName(this.globalService.user.userName).subscribe(data=>
      // {

      // })
  }

  update():void{
    this.userService.newPassword(this.userDto).subscribe(resp=>{
      if (resp.done){
        alert(resp.messa)
      }
      },err=>{
        alert(err.error.messa);
    })
  }

}
