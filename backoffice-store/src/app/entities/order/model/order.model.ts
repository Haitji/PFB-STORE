import { CarritoItem } from "../../carritoCompra/model/carritoItem.model";

export class Order{
    id:number;

    orderDate:string;

    shippingAddress:string;

    sent:boolean;

	constructor($id: number, $carritos: CarritoItem[], $orderDate: string, $shippingAddress: string, $sent: boolean) {
		this.id = $id;
		this.orderDate = $orderDate;
		this.shippingAddress = $shippingAddress;
		this.sent = $sent;
	}

}