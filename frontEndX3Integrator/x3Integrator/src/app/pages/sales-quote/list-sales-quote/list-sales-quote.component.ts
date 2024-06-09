import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { TableRowSelectEvent, TableLazyLoadEvent } from 'primeng/table';
import { SpinnerDefaultServiceService } from 'src/app/common/spinnerDefault/spinner-default-service.service';
import { EnvironmentServiceService } from 'src/app/shared/environment.service.service';
import { SalesQuoteDTO, AxiosSalesQuoteResourceClient } from 'src/app/shared/java-objects';
import { UtilsService } from 'src/app/shared/utils.service';

@Component({
  selector: 'app-list-sales-quote',
  templateUrl: './list-sales-quote.component.html',
  styleUrls: ['./list-sales-quote.component.scss']
})
export class ListSalesQuoteComponent implements OnInit {

  salesQuoteList: SalesQuoteDTO[] | any
  selectSalesQuote: SalesQuoteDTO | any
  paginationTotal: number = 0
  paginationRows:   number = 10
  teste: number=0;

  constructor (
    private salesQuoteApi: AxiosSalesQuoteResourceClient,
    private utilService: UtilsService,
    private spinner: SpinnerDefaultServiceService,
    private translate: TranslateService,
    private router: Router,
    public environment: EnvironmentServiceService
    ){
  } 

  onRowSelect(event:TableRowSelectEvent){
    this.router.navigate(['/salesQuoteEdit'], {
      queryParams: { cdnSalesQuote: event.data.cdnSalesQuote },
    });
  }

  onLazyLoad(event: TableLazyLoadEvent){
    this.spinner.show();
    const paginaAtual = event.first!/event.rows!
    this.salesQuoteApi.paginationFull(event.filters!, { 
      page: paginaAtual,
      pageSize: event.rows!,
      sortBy: event.sortField! as string,
      direction: event.sortOrder!==1 ? 'asc' : 'desc',
    })
    .then(resp=>{
      this.salesQuoteList=resp.data.data?.content!
      this.paginationTotal = resp.data.data?.totalElements!
    })
    .catch(err=>{
      console.log(err)
      this.utilService.showToast(err.response.data.messagens, "error")
    })
    .finally(()=>{
      this.spinner.hide()
    })

  }

  ngOnInit(): void {
  }

  onNewRegister(){
    this.router.navigate(['/salesQuoteEdit'], {
      
    });
  }

}
