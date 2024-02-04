import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Admin } from '../../../class/admin';
import { ResturantmanagemntService } from '../../../service/resturantmanagemnt.service';

@Component({
  selector: 'app-admin-viewmenu',
  templateUrl: './admin-viewmenu.component.html',
  styleUrls: ['./admin-viewmenu.component.css']
})

export class AdminViewmenuComponent {
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
    }
    getAllProduct()
    {
      this.hasSearchName = this.activeRoute.snapshot.paramMap.has("pname");
         if(this.hasSearchName)
         {
          this.searchName  = this.activeRoute.snapshot.paramMap.get("pname");
          console.log(this.searchName)
        this.resturantManageService.getProductByPname(this.searchName).subscribe((data: any)=>{
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
  
