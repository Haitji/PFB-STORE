import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GlobalClassService {


  public static usuarioNick: string;
  private static usuarioNickSubject: Subject<string> = new Subject<string>();

  public static usuarioNick$ = GlobalClassService.usuarioNickSubject.asObservable();

  public static setUsuarioNick(nuevoUsuarioNick: string) {
    GlobalClassService.usuarioNick = nuevoUsuarioNick;
    GlobalClassService.usuarioNickSubject.next(nuevoUsuarioNick);
  }
  constructor() { }
}
