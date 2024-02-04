

import { Customer } from "./customer";
import { Product } from "./product";

export class Order {
    totalPrice: number;
    quantity: number;
    customer: Customer;
    orderDate: Date;
    orderTime: string;
    product:Product;
    status: string;
     orderId: number; 

    constructor( orderId: number ,customer: Customer,orderDate: Date,orderTime: Date,product: Product,quantity: number,status: string){}
   
}
