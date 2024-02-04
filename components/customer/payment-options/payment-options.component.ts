import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../../../class/product';
import { Customer } from '../../../class/customer';
import { ResturantmanagemntService } from '../../../service/resturantmanagemnt.service';

@Component({
  selector: 'app-payment-options',
  templateUrl: './payment-options.component.html',
  styleUrls: ['./payment-options.component.css']
})
export class PaymentOptionsComponent implements OnInit {
  orderId: number;
  productid: number;
  price: number
  order: any;
  quantity: number;
  totalprice: number;
  product:Product;
  customer: Customer;

  constructor(
    private activeRoute: ActivatedRoute,
    private router: Router,
    private resturantmanagemntservice: ResturantmanagemntService
  ) { }
  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(() => this.getOrderDetails())
    this.activeRoute.paramMap.subscribe(() => this.customer = JSON.parse(sessionStorage.getItem("customer")))
    this.checkSessionAndNavigate();
  }
  getOrderDetails() {
    this.orderId = parseInt(localStorage.getItem("generatedOrderId"));
    if (this.orderId) {
      this.resturantmanagemntservice.getorderbyid(this.orderId).subscribe(
        (data) => {
          this.order = data;
          this.quantity = this.order.quantity
          this.price = this.order.product.price
          this.totalprice = this.order.quantity * this.order.product.price
        },
        (error) => {
          console.error('Error fetching order details', error);
        }
      );
    }
  }

  deleteOrder() {
    if (this.orderId) {
      const confirmation = window.confirm("Are you sure you want to delete this order?");
      if (confirmation) {
        this.resturantmanagemntservice.deleteOrder(this.orderId).subscribe(
          () => {
            alert("Order deleted successfully");
            this.router.navigateByUrl('/customer/myorders');
          },
          (error) => {
            console.error("Error deleting order", error);
            alert("Error deleting order");
          }
        );
      }
    } else {
      alert("Order Not Available");
    }
  }

  redirectToPayment(paymentMethod: string) {
    if (paymentMethod === 'cod') {
      this.router.navigateByUrl('/customer/myorders');
    } else if (paymentMethod === 'online') {
      this.router.navigateByUrl('/customer/addpayments');
    }
  }
  checkSessionAndNavigate() {
    if (!this.customer) {
      this.router.navigateByUrl("/customer/login");
    }

  }
}