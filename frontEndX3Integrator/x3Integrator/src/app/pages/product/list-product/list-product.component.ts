import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { TableLazyLoadEvent, TableRowSelectEvent } from 'primeng/table';
import { SpinnerDefaultServiceService } from 'src/app/common/spinnerDefault/spinner-default-service.service';
import { EnvironmentServiceService } from 'src/app/shared/environment.service.service';
import { AxiosProductResourceClient, ProductDTO } from 'src/app/shared/java-objects';
import { UtilsService } from 'src/app/shared/utils.service';

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.scss']
})
export class ListProductComponent implements OnInit {

  productList: ProductDTO[] | any
  selectProduct: ProductDTO | any
  paginationTotal: number = 0
  paginationRows:   number = 10
  teste: number=0;

  constructor (
    private productApi: AxiosProductResourceClient,
    private utilService: UtilsService,
    private spinner: SpinnerDefaultServiceService,
    private translate: TranslateService,
    private router: Router,
    public environment: EnvironmentServiceService
    ){
  } 

  onRowSelect(event:TableRowSelectEvent){
    this.router.navigate(['/productEdit'], {
      queryParams: { cdnProduct: event.data.cdnProduct },
    });
  }

  onLazyLoad(event: TableLazyLoadEvent){
    this.spinner.show();
    const paginaAtual = event.first!/event.rows!
    this.productApi.paginationFull(event.filters!, { 
      page: paginaAtual,
      pageSize: event.rows!,
      sortBy: event.sortField! as string,
      direction: event.sortOrder!==1 ? 'asc' : 'desc',
    })
    .then(resp=>{
      this.productList=resp.data.data?.content!
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
    this.router.navigate(['/productEdit'], {
      
    });
  }

}
