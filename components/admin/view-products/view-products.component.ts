import { Component } from '@angular/core';
import { ResturantmanagemntService } from '../../../service/resturantmanagemnt.service';
import { Admin } from '../../../class/admin';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-view-products',
  templateUrl: './view-products.component.html',
  styleUrl: './view-products.component.css'
})
export class ViewProductsComponent {
  product:any;
  hasSearchName: boolean;
    searchName: string;
  admin: Admin;
  p: number = 1;
count: number = 4;
    constructor(private resturantManageService:ResturantmanagemntService,public router:Router, private activeRoute:ActivatedRoute) { }
    ngOnInit(): void 
    {
      this.activeRoute.paramMap.subscribe(()=>this.getAllProduct());
      this.activeRoute.paramMap.subscribe(()=>this.admin=JSON.parse(sessionStorage.getItem("admin")))
      this.checkSessionAndNavigate();
      // getAllProduct();

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
  
    /* getAllCustomer()
  {
    this.hasSearchName = this.activeRoute.snapshot.paramMap.has("username");
       if(this.hasSearchName)
       {this.searchName  = this.activeRoute.snapshot.paramMap.get("username");
        console.log(this.searchName)
        this.resturantManageService.getCustomerByUsername(this.searchName).subscribe(data=>{
        console.log(data);
        this.customer= data;
        })
      }
      else{
      this.resturantManageService.getAllCustomer().subscribe(data=>{
        console.log(data);
        this.customer=data;
      });
    }
    }*/
    deleteProduct(productid:number):void{
      console.log(productid);
      if(confirm("Do you want to delete ?")){
        this.resturantManageService.deleteProduct(productid).subscribe(data=>{
          console.log(data);
          this.getAllProduct();
        })
      };
    }
  
  
    updateProduct(id:number)
  {
    this.router.navigateByUrl("/admin/updateProduct/"+id);
  
  }
  addProduct():void
{
  this.router.navigateByUrl("/admin/products/addproduct");
}
logout() {
  if (sessionStorage['getproduct']("admin")) {
    sessionStorage.clear()
    localStorage.clear()
    alert("Logout Successfully")
    this.router.navigateByUrl("/admin/login")
  }
  else {
    alert("No user loged in")
  }
}
checkSessionAndNavigate() {
  if (!this.admin) {
    this.router.navigateByUrl("/admin/login");
  }
}
  
  }



