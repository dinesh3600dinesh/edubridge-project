import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { MatToolbarModule } from '@angular/material/toolbar';
import { Customer } from '../../../class/customer';
import { ResturantmanagemntService } from '../../../service/resturantmanagemnt.service';
@Component({
  selector: 'app-viewmenu',
  templateUrl: './viewmenu.component.html',
  styleUrls: ['./viewmenu.component.css']
})
export class ViewmenuComponent {
  product:any;
  customer:Customer;
  hasSearchName: boolean;
    searchName: string;
    constructor(private resturantManageService:ResturantmanagemntService,public router:Router, private activeRoute:ActivatedRoute) { }
    ngOnInit(): void 
    {
      this.activeRoute.paramMap.subscribe(()=>this.getAllProduct());
      this.activeRoute.paramMap.subscribe(()=>this.customer=JSON.parse(sessionStorage.getItem("customer")));
      this.checkSessionAndNavigate();
    }
    getAllProduct()
    {
      this.hasSearchName = this.activeRoute.snapshot.paramMap.has("pname");
         if(this.hasSearchName)
         {
          this.searchName  = this.activeRoute.snapshot.paramMap.get("pname");
          console.log(this.searchName)
        this.resturantManageService.getProductByPname(this.searchName).subscribe(data=>{
          console.log(data);
          this.product= data;
        })
      }
      else{
      this.resturantManageService.getAllProduct().subscribe(data=>{
        console.log(data);
        this.product=data;
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

   orderMenu(id:number)
  {
    
    // this.router.navigateByUrl("order/create/"+id);
     this.router.navigateByUrl("customer/placeorder/"+id);
  
  }
  checkSessionAndNavigate() {
    if (!this.customer) {
      this.router.navigateByUrl("/customer/login");
    }

  }
}