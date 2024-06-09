import { Component, OnInit } from '@angular/core';
import { AutoCompleteCompleteEvent } from 'primeng/autocomplete';
import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/dynamicdialog';
import { SpinnerDefaultServiceService } from 'src/app/common/spinnerDefault/spinner-default-service.service';
import { AxiosProductResourceClient, ProductDTO, SalesQuoteProductDTO } from 'src/app/shared/java-objects';

@Component({
  selector: 'app-modal-edit-sales-quote-product',
  templateUrl: './modal-edit-sales-quote-product.component.html',
  styleUrls: ['./modal-edit-sales-quote-product.component.scss']
})
export class ModalEditSalesQuoteProductComponent implements OnInit {


  activeRegister : SalesQuoteProductDTO = {}

  productFiltred     : ProductDTO[]=[];

  constructor(
    private spinner         : SpinnerDefaultServiceService,
    private dialogRef       : DynamicDialogRef,
    private dialogConfig    : DynamicDialogConfig,
    private productApi      : AxiosProductResourceClient,
  ){
  }

  ngOnInit(): void {
    this.spinner.show()
  
    this.activeRegister = this.dialogConfig?.data?.activeRegister || {} as SalesQuoteProductDTO

    this.spinner.hide()
  }

  selectProduct($event: AutoCompleteCompleteEvent) {
    let query = $event.query;
    const filtered: any[] = [];
  
    this.productApi.paginationFull({
      description: {
        value: query,
        matchMode: 'contains'
      }
    }).then((response)=>{
      this.productFiltred = response.data.data!.content!
    }).finally(()=>{
 
    })
  }

  onClose() {
    this.dialogRef.close({ action: 'save', item: this.activeRegister })
  }
    
  onDelete() {
    this.dialogRef.close({ action: 'delete', item: this.activeRegister })
  }
}
