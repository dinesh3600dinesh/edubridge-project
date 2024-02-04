import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Admin } from '../../../class/admin';
import { Product } from '../../../class/product';
import { ResturantmanagemntService } from '../../../service/resturantmanagemnt.service';


@Component({
  selector: 'app-add-menu',
  templateUrl: './add-menu.component.html',
  styleUrls: ['./add-menu.component.css']
})
export class AddMenuComponent {
  product:Product= new Product(0,"",0,"");
  isEditable!: boolean;
  admin: Admin;
  constructor(private resturantmanagemntservice:ResturantmanagemntService,private router:Router,private activateRoute:ActivatedRoute) { }
  ngOnInit(): void 
  {
    this.activateRoute.paramMap.subscribe(()=>this.product);
    this.activateRoute.paramMap.subscribe(()=>this.getproductbyid());
    this.activateRoute.paramMap.subscribe(()=>this.admin=JSON.parse(sessionStorage['getProduct']("admin")))
    this.checkSessionAndNavigate();

  }
  onSubmit(){
    console.log(this.product);
    if(this.isEditable){
      this.resturantmanagemntservice.updateProduct(this.product).subscribe(data=>
        console.log(data))
        alert("The product Item is updated")
        this.router.navigateByUrl("/admin/product");

    }
    else{
    this.resturantmanagemntservice.SaveProduct( this.product ).subscribe(data =>
      console.log(data))
      alert("The product  is Added!!")
      this.router.navigateByUrl("/admin/product");
  }
}
getproductbyid(){
  const productid  = parseFloat(this.activateRoute.snapshot.paramMap.get("id"));
  
console.log(productid);
if(productid> 0)
{
  this.isEditable = true;
  this.resturantmanagemntservice.getproductbyid(productid).subscribe(data=>{
    this.product = data;
    console.log(this.product)
  });
}

}
checkSessionAndNavigate() {
  if (!this.admin) {
    this.router.navigateByUrl("/admin/product/addproduct");
  }
}

}
