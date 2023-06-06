import { Component } from '@angular/core';
import { GlobalClassService } from 'src/app/global-service/global-class.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent {
  username: string='';

  constructor(private globalService: GlobalClassService) {
  }

  ngOnInit() {
    this.globalService.getUserName().subscribe(value =>{
      this.username=value;
    });
  }
  logout(){
    this.globalService.setUserName('');
  }

}
