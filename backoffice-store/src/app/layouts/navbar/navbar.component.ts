import { Component } from '@angular/core';
import { GlobalClassService } from 'src/app/global-service/global-class.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {

  username: string='';

  constructor() {
  }
  ngOnInit() {
    this.username = GlobalClassService.usuarioNick;
    GlobalClassService.usuarioNick$.subscribe((nuevoUsuarioNick: string) => {
      this.username = nuevoUsuarioNick;
    });
  }

}
