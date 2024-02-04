import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from '../../../class/customer';
import { Order } from '../../../class/order';
import { Product } from '../../../class/product';
import { ResturantmanagemntService } from '../../../service/resturantmanagemnt.service';


@Component({
  selector: 'app-add-order',
  templateUrl:'./add-order.component.html',
  styleUrls: ['./add-order.component.css']
})
export class AddOrderComponent implements OnInit {
  customerId: number;
  productid: number;
  customer: Customer;
  order: Order;
  product: Product = new Product(0, "", 0, "");
  quantity: any = 0;
  isEditable: boolean;
  //products:Product;

  constructor(private activeRoute: ActivatedRoute, private http: HttpClient, public router: Router, private resturantmanagemntservice: ResturantmanagemntService) { }

  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(() => this.customer = JSON.parse(sessionStorage.getItem("customer")));
    //this.activeRoute.paramMap.subscribe(() => this.product = JSON.parse(sessionStorage.getItem("product")));
    
      this.getProductById();
  }


  getProductById() {
    console.log("hi")
    const productid = parseFloat(this.activeRoute.snapshot.paramMap.get("id"));

    console.log(productid);
    if (productid > 0) {
      this.isEditable = true;
      this.resturantmanagemntservice.getproductbyid(productid).subscribe(data => {
        this.product = data;
        console.log(this.product)
      });
    }

  }
  logout() {
    if (sessionStorage.getItem("customer")) {
      sessionStorage.clear()
      localStorage.clear()
      alert("Logout Successfully")
      this.router.navigateByUrl("/customer/login")
    }
    else {
      alert("No user loged in")
    }
  }
  placeOrder() {
    
    //console.log(productid)
    const productid = this.product.productid;
    const customerId = this.customer.customerId;
    
    
   

    const quantity = { quantity: this.quantity };

    this.resturantmanagemntservice.orderMenu( productid,customerId, quantity).subscribe(
    
      (response) => {
       // console.log(productid)
        console.log('Order placed successfully', response);
        alert("Order Successfully Placed")

        localStorage.setItem("generatedOrderId", response.orderId);

        this.router.navigateByUrl("customer/paymentoptions")
      },
      (error) => {

        console.error('Error placing order', error);
      }
    );
  }
  
}

   
  





 
  

