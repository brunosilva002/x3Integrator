import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { MessageService } from 'primeng/api';
import { DialogService } from 'primeng/dynamicdialog';
import { SpinnerDefaultServiceService } from 'src/app/common/spinnerDefault/spinner-default-service.service';
import { AxiosProductResourceClient, ProductDTO } from 'src/app/shared/java-objects';
import { UtilsService } from 'src/app/shared/utils.service';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.scss']
})
export class EditProductComponent implements OnInit {

  tabRef: string = 'product';
  cdnTabRef: string = 'cdnProduct';
  activeRegister: ProductDTO = {}
  cdnParam: any


  constructor(
    private activatedRoute      : ActivatedRoute,
    private productApi          : AxiosProductResourceClient,
    public dialogService        : DialogService,
    private messageService      : MessageService,
    private translate           : TranslateService,
    private spinner             : SpinnerDefaultServiceService,
    private utilService         : UtilsService,
    private router              : Router,
  ){}

  ngOnInit(): void {
    this.activatedRoute.queryParamMap.subscribe((params) => {
      this.cdnParam = params.get(this.cdnTabRef);
      if (this.cdnParam) {
          this.loadData();
      }
    });
  }
  loadData(){
    this.spinner.show()
    this.productApi.obtain({
      cdnProduct:this.cdnParam
    })
    .then(resp=>{
      this.activeRegister = resp.data.data!
    })
    .catch(err=>{
      this.utilService.showToast(err.response.data.messagens, "error")
    })
    .finally(()=>{
      this.spinner.hide()
    })
  }

  onSave(): void{
    this.spinner.show()
    this.productApi.save(this.activeRegister)
    .then(resp=>{
      this.utilService.showToast(resp.data.messagens!,'success')
      this.activeRegister = resp.data.data!
      this.router.navigate(['/productEdit'], { queryParams: { cdnProduct: this.activeRegister.cdnProduct } });
      this.loadData()
    })
    .catch( err=>{
      this.utilService.showToast(err.response.data.messagens, "error")
    })
    .finally(()=>{
      this.spinner.hide()
    })
  }

  onDelete() {
    throw new Error('Method not implemented.');
  }

  onBack() {
    this.router.navigate(['/productList'], {
      
    });
  }
}
