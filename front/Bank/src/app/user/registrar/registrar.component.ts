import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { GlobalService } from 'src/app/Shared/Services/global.service';
import { UserService } from '../Services/user.service';
import {​​​​​​Location}​​​​​​ from '@angular/common'



@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrls: ['./registrar.component.css']
})
export class RegistrarComponent implements OnInit {

  profileForm = new FormGroup({
    userName: new FormControl(''),
    password: new FormControl(''),
    firstName: new FormControl(''),
    lastName: new FormControl('')
  });
  constructor(private userService: UserService,
    private router:Router, public globalService: GlobalService,
    private location: Location) { }

  ngOnInit(): void {
  }

  Return(){
    console.log(this.globalService.user.jwt);
    if(this.globalService.user.jwt != "" ){
      this.location.back();
    }else{
      this.router.navigate(['/']);
      window.location.reload();
    }
  }

  Registrar(){
    const user = this.profileForm.value;
    console.log(user);
    this.userService.save(user).subscribe(data=>{
      alert(data.messa);
      this.Return();
    }
    )
  }
}