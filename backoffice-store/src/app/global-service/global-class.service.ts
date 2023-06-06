import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GlobalClassService {

  private userName: BehaviorSubject<string>;

  constructor() {
    const storedValue = localStorage.getItem('Nick');
    const initialValue = storedValue !== null ? storedValue : '';
    this.userName = new BehaviorSubject<string>(initialValue)
  }

  getUserName() {
    return this.userName.asObservable();
  }

  setUserName(value: string) {
    localStorage.setItem('Nick', value);
    this.userName.next(value);
  }
}
