import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { MessageService } from 'primeng/api';
import { AutoCompleteCompleteEvent } from 'primeng/autocomplete';
import { DialogService, DynamicDialogRef } from 'primeng/dynamicdialog';
import { SpinnerDefaultServiceService } from 'src/app/common/spinnerDefault/spinner-default-service.service';
import { SalesQuoteDTO, AxiosSalesQuoteResourceClient, CustomerDTO, AxiosCustomerResourceClient, SalesQuoteProductDTO, SalesQuoteX3LogDTO, AxiosSiteResourceClient, SiteDTO } from 'src/app/shared/java-objects';
import { UtilsService } from 'src/app/shared/utils.service';
import { ModalEditSalesQuoteProductComponent } from '../modal-edit-sales-quote-product/modal-edit-sales-quote-product.component';
import { ModalViewIntegrationDataComponent } from '../modal-view-integration-data/modal-view-integration-data.component';
import { TableRowSelectEvent } from 'primeng/table';

@Component({
  selector: 'app-edit-sales-quote',
  templateUrl: './edit-sales-quote.component.html',
  styleUrls: ['./edit-sales-quote.component.scss']
})
export class EditSalesQuoteComponent implements OnInit {

  tabRef: string = 'salesQuote';
  cdnTabRef: string = 'cdnSalesQuote';
  activeRegister: SalesQuoteDTO = {}
  cdnParam: any

  customerFiltred   : CustomerDTO[]=[]
  siteFiltred       : SiteDTO[]=[]

  salesQuoteProductPopup: DynamicDialogRef | undefined;
  salesQuoteDataIntegrationtPopup: DynamicDialogRef | undefined;
  selectedProduct: SalesQuoteProductDTO | any;

  constructor(
    private activatedRoute              : ActivatedRoute,

    private salesQuoteApi               : AxiosSalesQuoteResourceClient,
    private customerApi                 : AxiosCustomerResourceClient,
    private siteApi                     : AxiosSiteResourceClient,

    public dialogService                : DialogService,
    private messageService              : MessageService,
    private translate                   : TranslateService,
    private spinner                     : SpinnerDefaultServiceService,
    private utilService                 : UtilsService,

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


  selectCustomer ($event: AutoCompleteCompleteEvent){
    let query = $event.query;
    const filtered: any[] = [];

    this.customerApi.paginationFull({
      razaoSocial: {
        value: query,
        matchMode: 'contains'
      }
    }).then((response)=>{
      this.customerFiltred = response.data.data!.content!
    }).finally(()=>{

    })
  }

  selectSite ($event: AutoCompleteCompleteEvent){
    let query = $event.query;
    const filtered: any[] = [];

    this.siteApi.paginationFull({
      cdnX3Site: {
        value: query,
        matchMode: 'contains'
      }
    }).then((response)=>{
      this.siteFiltred = response.data.data!.content!
    }).finally(()=>{

    })
  }

  viewDataIntegration(salesQuoteIntgration: SalesQuoteX3LogDTO,line: any) {
    this.salesQuoteDataIntegrationtPopup = this.dialogService.open(ModalViewIntegrationDataComponent, {
        header: this.translate.instant("SalesQuoteProdcut.singular"),
        width: '70%',
        height: '500px',
        contentStyle: { overflow: 'auto' },
        baseZIndex: 10000,
        maximizable: true,
        resizable:true,
        keepInViewport: true,
        draggable: true,
        data:{
          activeRegister: salesQuoteIntgration,
        }
    });
  }

  addProductLine(salesQuoteProduct?: SalesQuoteProductDTO, line?: any) {
      if (!salesQuoteProduct){
        salesQuoteProduct = new SalesQuoteProductDTO({
          lineNumber : this.activeRegister.salesQuoteProducts ? this.activeRegister?.salesQuoteProducts!.length+1  : 1
        }); // Crie uma nova instância de SalesQuoteProductDTO  
      }

      this.salesQuoteProductPopup = this.dialogService.open(ModalEditSalesQuoteProductComponent, {
        header: this.translate.instant("SalesQuoteProdcut.singular"),
        width: '70%',
        height: '500px',
        contentStyle: { overflow: 'auto' },
        baseZIndex: 10000,
        maximizable: true,
        resizable:true,
        keepInViewport: true,
        draggable: true,
        data:{
          activeRegister: salesQuoteProduct,
        }
    });

    this.salesQuoteProductPopup.onClose.subscribe((productModal: any) => {
      if (productModal.action=='save') {
          if (line!<0){
            if(!this.activeRegister.salesQuoteProducts){
              this.activeRegister.salesQuoteProducts = this.activeRegister.salesQuoteProducts || [];
            }
            this.activeRegister.salesQuoteProducts?.push(productModal.item)
          }
      }else if(productModal.action=='delete'){
        const index = this.activeRegister.salesQuoteProducts?.findIndex(item => item.lineNumber === productModal.item.lineNumber);

        // Se o índice for encontrado, remova o item
        if (index !== -1) {
            this.activeRegister.salesQuoteProducts?.splice(index ||0, 1);
          }
        }
      });
    }

  editSalesQuoteProduct(salesQuoteProduct: TableRowSelectEvent) {
    console.log(salesQuoteProduct.data)
    this.addProductLine(salesQuoteProduct.data, salesQuoteProduct.data.lineNumber)
  }


  loadData(){
    this.spinner.show()
    this.salesQuoteApi.obtain({
      cdnSalesQuote:this.cdnParam
    })
    .then(resp=>{
      this.activeRegister = resp.data.data!
      //this.activeRegister.creationDate = '2023-05-31T00:00:00Z'; //new Date(this.activeRegister.creationDate!);
      console.log(this.activeRegister)
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
    this.salesQuoteApi.save(this.activeRegister)
    .then(resp=>{
      this.utilService.showToast(resp.data.messagens!,'success')
      this.activeRegister = resp.data.data!
      this.router.navigate(['/salesQuoteEdit'], { queryParams: { cdnSalesQuote: this.activeRegister.cdnSalesQuote } });
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
    this.router.navigate(['/salesQuoteList'], {

    });
  }
}
