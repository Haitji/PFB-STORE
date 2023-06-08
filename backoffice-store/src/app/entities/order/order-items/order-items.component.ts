import { Component } from '@angular/core';
import { OrderService } from '../service/order.service';
import { GlobalClassService } from 'src/app/global-service/global-class.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from '../model/order.model';
import { CarritoItem } from '../../carritoCompra/model/carritoItem.model';

@Component({
  selector: 'app-order-items',
  templateUrl: './order-items.component.html',
  styleUrls: ['./order-items.component.scss']
})
export class OrderItemsComponent {

  userName: string='';
  orderList: CarritoItem[]=[];
  orderId:string='';
  constructor(private orderService: OrderService, private globalService: GlobalClassService,private router: ActivatedRoute,private router2: Router) { }

  ngOnInit(): void {
    this.globalService.getUserName().subscribe(value => {
      this.userName = value;
    });
    const entryParam = this.router.snapshot.paramMap.get("orderId");
    this.orderId=entryParam!;
    this.getAllItemByOrder();
  }

  getAllItemByOrder(){
    this.orderService.getItemsByOrder(this.userName,this.orderId).subscribe({
      next: (list) => { this.orderList = list ;this.comprobar(list) },
      error: (error) => { this.handleError(error); }
    })
  }
  handleError(error: any): void {
    console.log(error)
  }
  removeItem(itemId:number){
    this.orderService.removeItemFromOrder(this.orderId,itemId).subscribe({
      next: (list) => {alert("Articulo devuelto con exito");this.getAllItemByOrder()},
      error: (error) => { this.handleError(error);alert("Error al devolver el articulo") }
    })
  }
  cancelOrder(){
    this.orderService.cancelOrder(parseInt(this.orderId)).subscribe({
      next: (list:any) => { this.router2.navigate(['orderList']);},
      error: (error) => { this.handleError(error); alert("Error al cancelar el pedido")}
    })
  }
  comprobar(list: CarritoItem[]){
    if(list.length==0){
      this.cancelOrder();
    }
  }


}
