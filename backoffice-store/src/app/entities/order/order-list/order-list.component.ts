import { Component } from '@angular/core';
import { Order } from '../model/order.model';
import { GlobalClassService } from 'src/app/global-service/global-class.service';
import { Router } from '@angular/router';
import { OrderService } from '../service/order.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.scss']
})
export class OrderListComponent {
  userName: string='';
  orderList: Order[]=[];
  constructor(private orderService: OrderService, private globalService: GlobalClassService, private router: Router) { }

  ngOnInit(): void {
    this.globalService.getUserName().subscribe(value => {
      this.userName = value;
    });
    this.getAllOrder();
  }

  getAllOrder(){
    this.orderService.getOrderByUser(this.userName).subscribe({
      next: (list) => { this.orderList = list },
      error: (error) => { this.handleError(error); }
    })
  }
  handleError(error: any): void {
    console.log(error)
  }
  cancelOrder(orderId:number){
    this.orderService.cancelOrder(orderId).subscribe({
      next: (list) => { alert("Pedido cancelado con exito");this.getAllOrder(); },
      error: (error) => { this.handleError(error); alert("Error al cancelar el pedido")}
    })
  }

}
