import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { TableRowSelectEvent, TableLazyLoadEvent } from 'primeng/table';
import { SpinnerDefaultServiceService } from 'src/app/common/spinnerDefault/spinner-default-service.service';
import { EnvironmentServiceService } from 'src/app/shared/environment.service.service';
import { AxiosCustomerResourceClient, CustomerDTO } from 'src/app/shared/java-objects';
import { UtilsService } from 'src/app/shared/utils.service';

@Component({
  selector: 'app-list-customer',
  templateUrl: './list-customer.component.html',
  styleUrls: ['./list-customer.component.scss']
})
export class ListCustomerComponent implements OnInit {

  customerList: CustomerDTO[] | any
  selectCustomer: CustomerDTO | any
  paginationTotal: number = 0
  paginationRows:   number = 10
  teste: number=0;

  constructor (
    private customerApi: AxiosCustomerResourceClient,
    private utilService: UtilsService,
    private spinner: SpinnerDefaultServiceService,
    private translate: TranslateService,
    private router: Router,
    public environment: EnvironmentServiceService
    ){
  } 

  onRowSelect(event:TableRowSelectEvent){
    this.router.navigate(['/customerEdit'], {
      queryParams: { cdnCustomer: event.data.cdnCustomer },
    });
  }

  onLazyLoad(event: TableLazyLoadEvent){
    this.spinner.show();
    const paginaAtual = event.first!/event.rows!
    this.customerApi.paginationFull(event.filters!, { 
      page: paginaAtual,
      pageSize: event.rows!,
      sortBy: event.sortField! as string,
      direction: event.sortOrder!==1 ? 'asc' : 'desc',
    })
    .then(resp=>{
      this.customerList=resp.data.data?.content!
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
    this.router.navigate(['/customerEdit'], {
      
    });
  }

}
