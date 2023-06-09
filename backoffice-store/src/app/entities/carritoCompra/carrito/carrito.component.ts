import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalClassService } from 'src/app/global-service/global-class.service';
import { CarritoService } from '../service/carrito.service';
import { CarritoItem } from '../model/carritoItem.model';
import { Item } from '../../item/model/item.model';
import { format } from 'date-fns';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.scss']
})
export class CarritoComponent {

  Carrito: CarritoItem[] = [];
  userName: string = "";
  precioTotal: string = "0";
  botonDeshabilitado:boolean=true;
  direccion: string = "";
  itemId:number =0;

  constructor(private carritoService: CarritoService, private globalService: GlobalClassService, private router: Router) { }

  ngOnInit(): void {
    this.globalService.getUserName().subscribe(value => {
      this.userName = value;
    });
    this.getCarrito();
    this.calcularPrecio();
    
  }

  getCarrito() {
    this.carritoService.getCarritoDelUsuario(this.userName).subscribe({
      next: (list) => { this.Carrito = list;this.calcularPrecioInicio(list);this.comprobarListaLenght(list);},
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
  quitarDeCarrito(){
    if(this.itemId!=0){
      this.carritoService.removeItemOnShoppingCart(this.userName,this.itemId).subscribe({
        next: (list:any) => { this.quitar(this.itemId);this.calcularPrecioInicio(this.Carrito) },
        error: (error) => { this.handleError(error); }
      })
    }else{
      alert("Error al quitar ell producto del carrito")
    }

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

  comprobarListaLenght(lista: CarritoItem[]){
    if(lista.length > 0){
      this.botonDeshabilitado=false;
    }
  }

  addOrder(){
    const fechaHoy: Date = new Date();
    const formatoFecha = format(fechaHoy, 'dd/MM/yyyy HH:mm');
    let lista:number[]=this.Carrito.map(item => item.shoppingCartId);
    if(this.direccion==""){
      alert("La direccion no puede estar vacio")
    }else{
      this.carritoService.addOrder(this.userName,formatoFecha,this.direccion,lista).subscribe({
        next: (responseBody: any) => {alert("Pedido realizado con exito");this. getCarrito()},
        error: (error) => { this.handleError(error); alert(error.message) }
      })
    }
  }

  asignarItemId(id:number){
    this.itemId=id;
  }
}
