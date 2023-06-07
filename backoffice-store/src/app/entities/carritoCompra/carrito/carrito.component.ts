import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalClassService } from 'src/app/global-service/global-class.service';
import { CarritoService } from '../service/carrito.service';
import { CarritoItem } from '../model/carritoItem.model';
import { Item } from '../../item/model/item.model';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.scss']
})
export class CarritoComponent {

  Carrito: CarritoItem[] = [];
  userName: string = "";
  precioTotal: string = "0";

  constructor(private carritoService: CarritoService, private globalService: GlobalClassService, private router: Router) { }

  ngOnInit(): void {
    this.globalService.getUserName().subscribe(value => {
      this.userName = value;
    });
    this.getCarrito();
    this.calcularPrecio();
  }

  private getCarrito() {
    this.carritoService.getCarritoDelUsuario(this.userName).subscribe({
      next: (list) => { this.Carrito = list;this.calcularPrecioInicio(list) },
      error: (error) => { this.handleError(error); }
    })
  }

  handleError(error: any): void {
    console.log(error)
  }
  calcularPrecioInicio(lista:CarritoItem[]) {
    let precio = 0;
    lista.forEach((item: CarritoItem) => {
      precio = precio + (item.price * item.units);
    })
    this.precioTotal=precio.toFixed(2);
  }

  calcularPrecio() {
    let precio = 0;
    this.Carrito.forEach((item: CarritoItem) => {
      precio = precio + (item.price * item.units);
    })
    this.precioTotal=precio.toFixed(2);
  }
  quitarDeCarrito(id:number){
    this.carritoService.removeItemOnShoppingCart(this.userName,id).subscribe({
      next: (list:any) => { this.quitar(id);this.calcularPrecioInicio(this.Carrito) },
      error: (error) => { this.handleError(error); }
    })
  }
  quitar(id: number){
    const index = this.Carrito.findIndex(item => item.id === id);
    if(index != -1){
      this.Carrito.splice(index,1);
    }
  }

  addCarrito(id: number,unidades: number){
    this.carritoService.addItemOnShoppingCart(this.userName,id,unidades).subscribe({
      next: (responseBody: any) => {alert("Item guardado con exito al carrito")},
      error: (error) => { this.handleError(error) }
    })
  }
}
