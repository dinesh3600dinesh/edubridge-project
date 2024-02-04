import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Customer } from '../../../class/customer';
import { ResturantmanagemntService } from '../../../service/resturantmanagemnt.service';

@Component({
  selector: 'app-my-order',
  templateUrl: './my-order.component.html',
  styleUrls: ['./my-order.component.css']
})
export class MyOrderComponent implements OnInit {
  p: number = 1;
  count: number = 10;
  isEditable: boolean;
  order: any
  customer: Customer;
  constructor(private resturantmanagemntservice: ResturantmanagemntService, private router: Router, private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activateRoute.paramMap.subscribe(() => this.customer = JSON.parse(sessionStorage.getItem("customer")))
    this.activateRoute.paramMap.subscribe(() => this.getOrderbyCustomerid());
    this.checkSessionAndNavigate();
  }
  getOrderbyCustomerid() {
    const customerId = this.customer.customerId;

    console.log(customerId);
    if (customerId > 0) {
      this.isEditable = true;
      this.resturantmanagemntservice.getorderbycustomerid(customerId).subscribe(data => {
        this.order = data;
        console.log(this.order)
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
  checkSessionAndNavigate() {
    if (!this.customer) {
      this.router.navigateByUrl("/customer/login");
    }

  }
}