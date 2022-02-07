import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalService } from 'src/app/Shared/Services/global.service';
import { User } from 'src/app/user/Models/User';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


  //user: User = new User();
  title = 'Bank';

  constructor(private router:Router, public globalService: GlobalService) { }

  ngOnInit(): void {
    //this.globalService= new GlobalService();
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
    }
    Logout(){
      this.globalService = new GlobalService();
    }
}
