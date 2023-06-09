import { Component } from '@angular/core';
import { GlobalClassService } from 'src/app/global-service/global-class.service';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {

  username: string='';
  login:boolean=false;
  showModal = false;

  constructor(private globalService: GlobalClassService,private router:Router) {
  }
  ngOnInit() {
    this.globalService.getUserName().subscribe(value =>{
      this.username=value;
      this.comprobarLogin();
    });
  }
  openModal() {
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
  }

  private comprobarLogin(){
    if(this.username===""){
      this.login=false;
    }else{
      this.login=true;
    }
  }

  irALogin(){
    this.router.navigate(['login']);
    this.closeModal();
  }

  comprobar(){
    if(!this.login){
      this.openModal();
    }
  }
  navegar(){
    if(this.login){
      this.router.navigate(['logout']);
    }else{
      this.router.navigate(['login']);
    }
  }

}
