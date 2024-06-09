/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.32.889 on 2024-06-01 16:59:16.

export class AuthenticationRequestDTO {
    email?: string;
    password?: string;

    constructor(data: AuthenticationRequestDTO) {
        this.email = data.email;
        this.password = data.password;
    }
}

export class AuthenticationRequestDTOBuilder {

    constructor(data: AuthenticationRequestDTOBuilder) {
    }
}

export class AuthenticatoinResponseDTO {
    token?: string;
    timeOut?: Date;
    timeAt?: Date;
    cdnUser?: number;

    constructor(data: AuthenticatoinResponseDTO) {
        this.token = data.token;
        this.timeOut = data.timeOut;
        this.timeAt = data.timeAt;
        this.cdnUser = data.cdnUser;
    }
}

export class AuthenticatoinResponseDTOBuilder {

    constructor(data: AuthenticatoinResponseDTOBuilder) {
    }
}

export class CustomerDTO implements Serializable {
    cdnCustomer?: number;
    cdnX3Customer?: string;
    razaoSocial?: string;
    status?: boolean;
    creationDate?: Date;
    creationUser?: string;
    updateDate?: Date;
    updateUser?: string;

    constructor(data: CustomerDTO) {
        this.cdnCustomer = data.cdnCustomer;
        this.cdnX3Customer = data.cdnX3Customer;
        this.razaoSocial = data.razaoSocial;
        this.status = data.status;
        this.creationDate = data.creationDate;
        this.creationUser = data.creationUser;
        this.updateDate = data.updateDate;
        this.updateUser = data.updateUser;
    }
}

export class CustomerDTOList implements Serializable {
    $resources?: CustomerDTO[];
    $links?: X3Link;

    constructor(data: CustomerDTOList) {
        this.$resources = data.$resources;
        this.$links = data.$links;
    }
}

export class ProductCustomerDTO implements Serializable {
    cdnProduct?: number;
    product?: ProductDTO;
    customer?: CustomerDTO;
    creationDate?: Date;
    creationUser?: string;
    updateDate?: Date;
    updateUser?: string;

    constructor(data: ProductCustomerDTO) {
        this.cdnProduct = data.cdnProduct;
        this.product = data.product;
        this.customer = data.customer;
        this.creationDate = data.creationDate;
        this.creationUser = data.creationUser;
        this.updateDate = data.updateDate;
        this.updateUser = data.updateUser;
    }
}

export class ProductDTO implements Serializable {
    cdnProduct?: number;
    cdnX3Product?: string;
    description?: string;
    productStatus?: string;
    creationDate?: Date;
    creationUser?: string;
    updateDate?: Date;
    updateUser?: string;

    constructor(data: ProductDTO) {
        this.cdnProduct = data.cdnProduct;
        this.cdnX3Product = data.cdnX3Product;
        this.description = data.description;
        this.productStatus = data.productStatus;
        this.creationDate = data.creationDate;
        this.creationUser = data.creationUser;
        this.updateDate = data.updateDate;
        this.updateUser = data.updateUser;
    }
}

export class ProductDTOList implements Serializable {
    $resources?: ProductDTO[];
    $links?: X3Link;

    constructor(data: ProductDTOList) {
        this.$resources = data.$resources;
        this.$links = data.$links;
    }
}

export class RegisterRequestDTO {
    firstName?: string;
    lastName?: string;
    email?: string;
    password?: string;
    dateCreated?: Date;
    dateUpdated?: Date;
    userCreate?: UserDTO;
    userUpdate?: UserDTO;

    constructor(data: RegisterRequestDTO) {
        this.firstName = data.firstName;
        this.lastName = data.lastName;
        this.email = data.email;
        this.password = data.password;
        this.dateCreated = data.dateCreated;
        this.dateUpdated = data.dateUpdated;
        this.userCreate = data.userCreate;
        this.userUpdate = data.userUpdate;
    }
}

export class RegisterRequestDTOBuilder {

    constructor(data: RegisterRequestDTOBuilder) {
    }
}

export class ResponseDTO<T> {
    code?: number;
    status?: Status;
    messagens?: string[];
    data?: T;

    constructor(data: ResponseDTO<T>) {
        this.code = data.code;
        this.status = data.status;
        this.messagens = data.messagens;
        this.data = data.data;
    }
}

export class SalesQuoteDTO implements Serializable {
    cdnSalesQuote?: number;
    cdnX3SalesQuote?: string;
    site?: SiteDTO;
    customer?: CustomerDTO;
    quoteDate?: Date;
    salesQuoteProducts?: SalesQuoteProductDTO[];
    logIntegration?: SalesQuoteX3LogDTO[];
    x3IntegrationStatus?: string;
    c3IntegrationMessage?: string;
    creationDate?: Date;
    creationUser?: string;
    updateDate?: Date;
    updateUser?: string;
    SQH0_1?: { [index: string]: any };

    constructor(data: SalesQuoteDTO) {
        this.cdnSalesQuote = data.cdnSalesQuote;
        this.cdnX3SalesQuote = data.cdnX3SalesQuote;
        this.site = data.site;
        this.customer = data.customer;
        this.quoteDate = data.quoteDate;
        this.salesQuoteProducts = data.salesQuoteProducts;
        this.logIntegration = data.logIntegration;
        this.x3IntegrationStatus = data.x3IntegrationStatus;
        this.c3IntegrationMessage = data.c3IntegrationMessage;
        this.creationDate = data.creationDate;
        this.creationUser = data.creationUser;
        this.updateDate = data.updateDate;
        this.updateUser = data.updateUser;
        this.SQH0_1 = data.SQH0_1;
    }
}

export class SalesQuoteProductDTO implements Serializable {
    cdnSalesQuoteProduct?: number;
    lineNumber?: number;
    salesOrder?: SalesQuoteDTO;
    product?: ProductDTO;
    qty?: number;
    netPrice?: number;
    netPriceToltal?: number;
    deliveryDate?: Date;
    creationUser?: string;
    updateUser?: string;

    constructor(data: SalesQuoteProductDTO) {
        this.cdnSalesQuoteProduct = data.cdnSalesQuoteProduct;
        this.lineNumber = data.lineNumber;
        this.salesOrder = data.salesOrder;
        this.product = data.product;
        this.qty = data.qty;
        this.netPrice = data.netPrice;
        this.netPriceToltal = data.netPriceToltal;
        this.deliveryDate = data.deliveryDate;
        this.creationUser = data.creationUser;
        this.updateUser = data.updateUser;
    }
}

export class SalesQuoteX3LogDTO implements Serializable {
    cdnSalesOrderlogIntegration?: number;
    salesOrder?: SalesQuoteDTO;
    x3SendBody?: string;
    x3ResponseBody?: string;
    x3ResposneStatusWs?: string;
    x3ResponseHeaders?: string;
    x3ErrorMessages?: string;
    creationDate?: Date;
    creationUser?: string;
    updateDate?: Date;
    updateUser?: string;

    constructor(data: SalesQuoteX3LogDTO) {
        this.cdnSalesOrderlogIntegration = data.cdnSalesOrderlogIntegration;
        this.salesOrder = data.salesOrder;
        this.x3SendBody = data.x3SendBody;
        this.x3ResponseBody = data.x3ResponseBody;
        this.x3ResposneStatusWs = data.x3ResposneStatusWs;
        this.x3ResponseHeaders = data.x3ResponseHeaders;
        this.x3ErrorMessages = data.x3ErrorMessages;
        this.creationDate = data.creationDate;
        this.creationUser = data.creationUser;
        this.updateDate = data.updateDate;
        this.updateUser = data.updateUser;
    }
}

export class SiteDTO implements Serializable {
    cdnSite?: number;
    cdnX3Site?: string;
    creationUser?: string;
    updateUser?: string;

    constructor(data: SiteDTO) {
        this.cdnSite = data.cdnSite;
        this.cdnX3Site = data.cdnX3Site;
        this.creationUser = data.creationUser;
        this.updateUser = data.updateUser;
    }
}

export class SiteDTOList implements Serializable {
    $resources?: SiteDTO[];
    $links?: X3Link;

    constructor(data: SiteDTOList) {
        this.$resources = data.$resources;
        this.$links = data.$links;
    }
}

export class UserDTO implements Serializable {
    cdnUser?: number;
    email?: string;
    password?: string;
    name?: string;
    lastName?: string;
    creationUser?: string;
    updateUser?: string;

    constructor(data: UserDTO) {
        this.cdnUser = data.cdnUser;
        this.email = data.email;
        this.password = data.password;
        this.name = data.name;
        this.lastName = data.lastName;
        this.creationUser = data.creationUser;
        this.updateUser = data.updateUser;
    }
}

export class BaseResource {

    constructor(data: BaseResource) {
    }
}

export interface Serializable {
}

export class X3Link implements Serializable {
    $next?: NextUrl;
    $previous?: PreviousUrl;
    $first?: FirstUrl;
    $last?: LastUrl;

    constructor(data: X3Link) {
        this.$next = data.$next;
        this.$previous = data.$previous;
        this.$first = data.$first;
        this.$last = data.$last;
    }
}

export class NextUrl {
    $url?: string;

    constructor(data: NextUrl) {
        this.$url = data.$url;
    }
}

export class PreviousUrl {
    $url?: string;

    constructor(data: PreviousUrl) {
        this.$url = data.$url;
    }
}

export class FirstUrl {
    $url?: string;

    constructor(data: FirstUrl) {
        this.$url = data.$url;
    }
}

export class LastUrl {
    $url?: string;

    constructor(data: LastUrl) {
        this.$url = data.$url;
    }
}

export interface Page<T> extends Slice<T> {
    totalPages?: number;
    totalElements?: number;
}

export interface Pageable {
    unpaged?: boolean;
    paged?: boolean;
    pageNumber?: number;
    pageSize?: number;
    offset?: number;
    sort?: Sort;
}

export class Sort implements Streamable<Order>, Serializable {
    empty?: boolean;
    sorted?: boolean;
    unsorted?: boolean;

    constructor(data: Sort) {
        this.empty = data.empty;
        this.sorted = data.sorted;
        this.unsorted = data.unsorted;
    }
}

export interface Slice<T> extends Streamable<T> {
    pageable?: Pageable;
    last?: boolean;
    numberOfElements?: number;
    size?: number;
    content?: T[];
    number?: number;
    sort?: Sort;
    first?: boolean;
}

export interface Streamable<T> extends Iterable<T>, Supplier<Stream<T>> {
    empty?: boolean;
}

export class Order implements Serializable {
    direction?: Direction;
    property?: string;
    ignoreCase?: boolean;
    nullHandling?: NullHandling;
    ascending?: boolean;
    descending?: boolean;

    constructor(data: Order) {
        this.direction = data.direction;
        this.property = data.property;
        this.ignoreCase = data.ignoreCase;
        this.nullHandling = data.nullHandling;
        this.ascending = data.ascending;
        this.descending = data.descending;
    }
}

export interface Iterable<T> {
}

export interface Supplier<T> {
}

export interface Stream<T> extends BaseStream<T, Stream<T>> {
}

export interface BaseStream<T, S> extends AutoCloseable {
    parallel?: boolean;
}

export interface AutoCloseable {
}

export interface HttpClient<O> {

    request<R>(requestConfig: { method: string; url: string; queryParams?: any; data?: any; copyFn?: (data: R) => R; options?: O; }): RestResponse<R>;
}

export class CustomerResourceClient<O> {

    constructor(protected httpClient: HttpClient<O>) {
    }

    /**
     * HTTP DELETE /api/v1/customer/delete
     * Java method: br.brn.x3Integrator.rest.CustomerResource.delete
     */
    delete(queryParams: { cdnCustomer: number; }, options?: O): RestResponse<ResponseDTO<any>> {
        return this.httpClient.request({ method: "DELETE", url: uriEncoding`api/v1/customer/delete`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP GET /api/v1/customer/getX3Customer
     * Java method: br.brn.x3Integrator.rest.CustomerResource.getX3Customer
     */
    getX3Customer(queryParams: { x3Cod: string; }, options?: O): RestResponse<ResponseDTO<CustomerDTO>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/customer/getX3Customer`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP POST /api/v1/customer/listAll
     * Java method: br.brn.x3Integrator.rest.CustomerResource.listAll
     */
    listAll(options?: O): RestResponse<ResponseDTO<CustomerDTO[]>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/customer/listAll`, options: options });
    }

    /**
     * HTTP POST /api/v1/customer/listExample
     * Java method: br.brn.x3Integrator.rest.CustomerResource.listExample
     */
    listExample(customerDTO: CustomerDTO, options?: O): RestResponse<ResponseDTO<CustomerDTO[]>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/customer/listExample`, data: customerDTO, options: options });
    }

    /**
     * HTTP GET /api/v1/customer/obtain
     * Java method: br.brn.x3Integrator.rest.CustomerResource.obtain
     */
    obtain(queryParams: { cdnCustomer: number; }, options?: O): RestResponse<ResponseDTO<CustomerDTO>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/customer/obtain`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP GET /api/v1/customer/pagination
     * Java method: br.brn.x3Integrator.rest.CustomerResource.pagination
     */
    pagination(queryParams?: { page?: number; pageSize?: number; sortBy?: string; direction?: string; filter?: number; }, options?: O): RestResponse<ResponseDTO<Page<CustomerDTO>>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/customer/pagination`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP POST /api/v1/customer/paginationFull
     * Java method: br.brn.x3Integrator.rest.CustomerResource.paginationFull
     */
    paginationFull(filterMap: { [index: string]: any }, queryParams?: { page?: number; pageSize?: number; sortBy?: string; direction?: string; }, options?: O): RestResponse<ResponseDTO<Page<CustomerDTO>>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/customer/paginationFull`, queryParams: queryParams, data: filterMap, options: options });
    }

    /**
     * HTTP POST /api/v1/customer/save
     * Java method: br.brn.x3Integrator.rest.CustomerResource.save
     */
    save(customerDTO: CustomerDTO, options?: O): RestResponse<ResponseDTO<CustomerDTO>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/customer/save`, data: customerDTO, options: options });
    }

    /**
     * HTTP GET /api/v1/customer/updateX3CustomerLot
     * Java method: br.brn.x3Integrator.rest.CustomerResource.updateX3CustomerLot
     */
    updateX3CustomerLot(options?: O): RestResponse<ResponseDTO<CustomerDTO[]>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/customer/updateX3CustomerLot`, options: options });
    }
}

export class ProductResourceClient<O> {

    constructor(protected httpClient: HttpClient<O>) {
    }

    /**
     * HTTP POST /api/v1/product/createX3Product
     * Java method: br.brn.x3Integrator.rest.ProductResource.createX3Product
     */
    createX3Product(options?: O): RestResponse<ResponseDTO<ProductDTO[]>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/product/createX3Product`, options: options });
    }

    /**
     * HTTP DELETE /api/v1/product/delete
     * Java method: br.brn.x3Integrator.rest.ProductResource.delete
     */
    delete(queryParams: { cdnProduct: number; }, options?: O): RestResponse<ResponseDTO<any>> {
        return this.httpClient.request({ method: "DELETE", url: uriEncoding`api/v1/product/delete`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP GET /api/v1/product/getX3Product
     * Java method: br.brn.x3Integrator.rest.ProductResource.getX3Product
     */
    getX3Product(queryParams: { x3Cod: string; }, options?: O): RestResponse<ResponseDTO<ProductDTO>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/product/getX3Product`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP POST /api/v1/product/listAll
     * Java method: br.brn.x3Integrator.rest.ProductResource.listAll
     */
    listAll(options?: O): RestResponse<ResponseDTO<ProductDTO[]>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/product/listAll`, options: options });
    }

    /**
     * HTTP POST /api/v1/product/listExample
     * Java method: br.brn.x3Integrator.rest.ProductResource.listExample
     */
    listExample(productDTO: ProductDTO, options?: O): RestResponse<ResponseDTO<ProductDTO[]>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/product/listExample`, data: productDTO, options: options });
    }

    /**
     * HTTP GET /api/v1/product/obtain
     * Java method: br.brn.x3Integrator.rest.ProductResource.obtain
     */
    obtain(queryParams: { cdnProduct: number; }, options?: O): RestResponse<ResponseDTO<ProductDTO>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/product/obtain`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP GET /api/v1/product/pagination
     * Java method: br.brn.x3Integrator.rest.ProductResource.pagination
     */
    pagination(queryParams?: { page?: number; pageSize?: number; sortBy?: string; direction?: string; filter?: number; }, options?: O): RestResponse<ResponseDTO<Page<ProductDTO>>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/product/pagination`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP POST /api/v1/product/paginationFull
     * Java method: br.brn.x3Integrator.rest.ProductResource.paginationFull
     */
    paginationFull(filterMap: { [index: string]: any }, queryParams?: { page?: number; pageSize?: number; sortBy?: string; direction?: string; }, options?: O): RestResponse<ResponseDTO<Page<ProductDTO>>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/product/paginationFull`, queryParams: queryParams, data: filterMap, options: options });
    }

    /**
     * HTTP POST /api/v1/product/save
     * Java method: br.brn.x3Integrator.rest.ProductResource.save
     */
    save(productDTO: ProductDTO, options?: O): RestResponse<ResponseDTO<ProductDTO>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/product/save`, data: productDTO, options: options });
    }

    /**
     * HTTP GET /api/v1/product/updateX3ProductLot
     * Java method: br.brn.x3Integrator.rest.ProductResource.updateX3ProductLot
     */
    updateX3ProductLot(options?: O): RestResponse<ResponseDTO<ProductDTO[]>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/product/updateX3ProductLot`, options: options });
    }
}

export class AuthenticationResourceClient<O> {

    constructor(protected httpClient: HttpClient<O>) {
    }

    /**
     * HTTP POST /api/v1/auth/authenticate
     * Java method: br.brn.x3Integrator.rest.AuthenticationResource.authenticate
     */
    authenticate(request: AuthenticationRequestDTO, options?: O): RestResponse<ResponseDTO<AuthenticatoinResponseDTO>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/auth/authenticate`, data: request, options: options });
    }

    /**
     * HTTP POST /api/v1/auth/reNewToken
     * Java method: br.brn.x3Integrator.rest.AuthenticationResource.reNewToken
     */
    reNewToken(queryParams: { email: string; }, options?: O): RestResponse<ResponseDTO<UserDTO>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/auth/reNewToken`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP POST /api/v1/auth/register
     * Java method: br.brn.x3Integrator.rest.AuthenticationResource.register
     */
    register(register: RegisterRequestDTO, options?: O): RestResponse<ResponseDTO<UserDTO>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/auth/register`, data: register, options: options });
    }
}

export class SalesQuoteResourceClient<O> {

    constructor(protected httpClient: HttpClient<O>) {
    }

    /**
     * HTTP DELETE /api/v1/salesquote/delete
     * Java method: br.brn.x3Integrator.rest.SalesQuoteResource.delete
     */
    delete(queryParams: { cdnSalesQuote: number; }, options?: O): RestResponse<ResponseDTO<any>> {
        return this.httpClient.request({ method: "DELETE", url: uriEncoding`api/v1/salesquote/delete`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP POST /api/v1/salesquote/listAll
     * Java method: br.brn.x3Integrator.rest.SalesQuoteResource.listAll
     */
    listAll(options?: O): RestResponse<ResponseDTO<SalesQuoteDTO[]>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/salesquote/listAll`, options: options });
    }

    /**
     * HTTP POST /api/v1/salesquote/listExample
     * Java method: br.brn.x3Integrator.rest.SalesQuoteResource.listExample
     */
    listExample(salesquoteDTO: SalesQuoteDTO, options?: O): RestResponse<ResponseDTO<SalesQuoteDTO[]>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/salesquote/listExample`, data: salesquoteDTO, options: options });
    }

    /**
     * HTTP GET /api/v1/salesquote/obtain
     * Java method: br.brn.x3Integrator.rest.SalesQuoteResource.obtain
     */
    obtain(queryParams: { cdnSalesQuote: number; }, options?: O): RestResponse<ResponseDTO<SalesQuoteDTO>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/salesquote/obtain`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP GET /api/v1/salesquote/pagination
     * Java method: br.brn.x3Integrator.rest.SalesQuoteResource.pagination
     */
    pagination(queryParams?: { page?: number; pageSize?: number; sortBy?: string; direction?: string; filter?: number; }, options?: O): RestResponse<ResponseDTO<Page<SalesQuoteDTO>>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/salesquote/pagination`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP POST /api/v1/salesquote/paginationFull
     * Java method: br.brn.x3Integrator.rest.SalesQuoteResource.paginationFull
     */
    paginationFull(filterMap: { [index: string]: any }, queryParams?: { page?: number; pageSize?: number; sortBy?: string; direction?: string; }, options?: O): RestResponse<ResponseDTO<Page<SalesQuoteDTO>>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/salesquote/paginationFull`, queryParams: queryParams, data: filterMap, options: options });
    }

    /**
     * HTTP GET /api/v1/salesquote/resendErp
     * Java method: br.brn.x3Integrator.rest.SalesQuoteResource.resendErp
     */
    resendErp(queryParams: { cdnSalesQuote: number; }, options?: O): RestResponse<ResponseDTO<SalesQuoteDTO>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/salesquote/resendErp`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP POST /api/v1/salesquote/save
     * Java method: br.brn.x3Integrator.rest.SalesQuoteResource.save
     */
    save(salesquoteDTO: SalesQuoteDTO, options?: O): RestResponse<ResponseDTO<SalesQuoteDTO>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/salesquote/save`, data: salesquoteDTO, options: options });
    }
}

export class SiteResourceClient<O> {

    constructor(protected httpClient: HttpClient<O>) {
    }

    /**
     * HTTP DELETE /api/v1/site/delete
     * Java method: br.brn.x3Integrator.rest.SiteResource.delete
     */
    delete(queryParams: { cdnSite: number; }, options?: O): RestResponse<ResponseDTO<any>> {
        return this.httpClient.request({ method: "DELETE", url: uriEncoding`api/v1/site/delete`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP POST /api/v1/site/listAll
     * Java method: br.brn.x3Integrator.rest.SiteResource.listAll
     */
    listAll(options?: O): RestResponse<ResponseDTO<SiteDTO[]>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/site/listAll`, options: options });
    }

    /**
     * HTTP POST /api/v1/site/listExample
     * Java method: br.brn.x3Integrator.rest.SiteResource.listExample
     */
    listExample(siteDTO: SiteDTO, options?: O): RestResponse<ResponseDTO<SiteDTO[]>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/site/listExample`, data: siteDTO, options: options });
    }

    /**
     * HTTP GET /api/v1/site/obtain
     * Java method: br.brn.x3Integrator.rest.SiteResource.obtain
     */
    obtain(queryParams: { cdnSite: number; }, options?: O): RestResponse<ResponseDTO<SiteDTO>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/site/obtain`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP GET /api/v1/site/pagination
     * Java method: br.brn.x3Integrator.rest.SiteResource.pagination
     */
    pagination(queryParams?: { page?: number; pageSize?: number; sortBy?: string; direction?: string; filter?: number; }, options?: O): RestResponse<ResponseDTO<Page<SiteDTO>>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/site/pagination`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP POST /api/v1/site/paginationFull
     * Java method: br.brn.x3Integrator.rest.SiteResource.paginationFull
     */
    paginationFull(filterMap: { [index: string]: any }, queryParams?: { page?: number; pageSize?: number; sortBy?: string; direction?: string; }, options?: O): RestResponse<ResponseDTO<Page<SiteDTO>>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/site/paginationFull`, queryParams: queryParams, data: filterMap, options: options });
    }

    /**
     * HTTP POST /api/v1/site/save
     * Java method: br.brn.x3Integrator.rest.SiteResource.save
     */
    save(siteDTO: SiteDTO, options?: O): RestResponse<ResponseDTO<SiteDTO>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/site/save`, data: siteDTO, options: options });
    }

    /**
     * HTTP GET /api/v1/site/updateX3SiteLot
     * Java method: br.brn.x3Integrator.rest.SiteResource.updateX3SiteLot
     */
    updateX3SiteLot(options?: O): RestResponse<ResponseDTO<SiteDTO[]>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/site/updateX3SiteLot`, options: options });
    }
}

export class ProductCustomerResourceClient<O> {

    constructor(protected httpClient: HttpClient<O>) {
    }

    /**
     * HTTP DELETE /api/v1/productcustomer/delete
     * Java method: br.brn.x3Integrator.rest.ProductCustomerResource.delete
     */
    delete(queryParams: { cdnProductCustomer: number; }, options?: O): RestResponse<ResponseDTO<any>> {
        return this.httpClient.request({ method: "DELETE", url: uriEncoding`api/v1/productcustomer/delete`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP POST /api/v1/productcustomer/listAll
     * Java method: br.brn.x3Integrator.rest.ProductCustomerResource.listAll
     */
    listAll(options?: O): RestResponse<ResponseDTO<ProductCustomerDTO[]>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/productcustomer/listAll`, options: options });
    }

    /**
     * HTTP POST /api/v1/productcustomer/listExample
     * Java method: br.brn.x3Integrator.rest.ProductCustomerResource.listExample
     */
    listExample(productcustomerDTO: ProductCustomerDTO, options?: O): RestResponse<ResponseDTO<ProductCustomerDTO[]>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/productcustomer/listExample`, data: productcustomerDTO, options: options });
    }

    /**
     * HTTP GET /api/v1/productcustomer/obtain
     * Java method: br.brn.x3Integrator.rest.ProductCustomerResource.obtain
     */
    obtain(queryParams: { cdnProductCustomer: number; }, options?: O): RestResponse<ResponseDTO<ProductCustomerDTO>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/productcustomer/obtain`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP GET /api/v1/productcustomer/pagination
     * Java method: br.brn.x3Integrator.rest.ProductCustomerResource.pagination
     */
    pagination(queryParams?: { page?: number; pageSize?: number; sortBy?: string; direction?: string; filter?: number; }, options?: O): RestResponse<ResponseDTO<Page<ProductCustomerDTO>>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/productcustomer/pagination`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP POST /api/v1/productcustomer/paginationFull
     * Java method: br.brn.x3Integrator.rest.ProductCustomerResource.paginationFull
     */
    paginationFull(filterMap: { [index: string]: any }, queryParams?: { page?: number; pageSize?: number; sortBy?: string; direction?: string; }, options?: O): RestResponse<ResponseDTO<Page<ProductCustomerDTO>>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/productcustomer/paginationFull`, queryParams: queryParams, data: filterMap, options: options });
    }

    /**
     * HTTP POST /api/v1/productcustomer/save
     * Java method: br.brn.x3Integrator.rest.ProductCustomerResource.save
     */
    save(productcustomerDTO: ProductCustomerDTO, options?: O): RestResponse<ResponseDTO<ProductCustomerDTO>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/productcustomer/save`, data: productcustomerDTO, options: options });
    }
}

export class SalesQuoteProductResourceClient<O> {

    constructor(protected httpClient: HttpClient<O>) {
    }

    /**
     * HTTP DELETE /api/v1/salesquoteproduct/delete
     * Java method: br.brn.x3Integrator.rest.SalesQuoteProductResource.delete
     */
    delete(queryParams: { cdnSalesQuoteProduct: number; }, options?: O): RestResponse<ResponseDTO<any>> {
        return this.httpClient.request({ method: "DELETE", url: uriEncoding`api/v1/salesquoteproduct/delete`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP POST /api/v1/salesquoteproduct/listAll
     * Java method: br.brn.x3Integrator.rest.SalesQuoteProductResource.listAll
     */
    listAll(options?: O): RestResponse<ResponseDTO<SalesQuoteProductDTO[]>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/salesquoteproduct/listAll`, options: options });
    }

    /**
     * HTTP POST /api/v1/salesquoteproduct/listExample
     * Java method: br.brn.x3Integrator.rest.SalesQuoteProductResource.listExample
     */
    listExample(salesquoteproductDTO: SalesQuoteProductDTO, options?: O): RestResponse<ResponseDTO<SalesQuoteProductDTO[]>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/salesquoteproduct/listExample`, data: salesquoteproductDTO, options: options });
    }

    /**
     * HTTP GET /api/v1/salesquoteproduct/obtain
     * Java method: br.brn.x3Integrator.rest.SalesQuoteProductResource.obtain
     */
    obtain(queryParams: { cdnSalesQuoteProduct: number; }, options?: O): RestResponse<ResponseDTO<SalesQuoteProductDTO>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/salesquoteproduct/obtain`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP GET /api/v1/salesquoteproduct/pagination
     * Java method: br.brn.x3Integrator.rest.SalesQuoteProductResource.pagination
     */
    pagination(queryParams?: { page?: number; pageSize?: number; sortBy?: string; direction?: string; filter?: number; }, options?: O): RestResponse<ResponseDTO<Page<SalesQuoteProductDTO>>> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`api/v1/salesquoteproduct/pagination`, queryParams: queryParams, options: options });
    }

    /**
     * HTTP POST /api/v1/salesquoteproduct/paginationFull
     * Java method: br.brn.x3Integrator.rest.SalesQuoteProductResource.paginationFull
     */
    paginationFull(filterMap: { [index: string]: any }, queryParams?: { page?: number; pageSize?: number; sortBy?: string; direction?: string; }, options?: O): RestResponse<ResponseDTO<Page<SalesQuoteProductDTO>>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/salesquoteproduct/paginationFull`, queryParams: queryParams, data: filterMap, options: options });
    }

    /**
     * HTTP POST /api/v1/salesquoteproduct/save
     * Java method: br.brn.x3Integrator.rest.SalesQuoteProductResource.save
     */
    save(salesquoteproductDTO: SalesQuoteProductDTO, options?: O): RestResponse<ResponseDTO<SalesQuoteProductDTO>> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`api/v1/salesquoteproduct/save`, data: salesquoteproductDTO, options: options });
    }
}

export type RestResponse<R> = Promise<Axios.GenericAxiosResponse<R>>;

export enum Code {
    ERROR_GENERIC = "ERROR_GENERIC",
    SUCCESS = "SUCCESS",
    ERROR_BUSINESS_RULE = "ERROR_BUSINESS_RULE",
}

export enum Status {
    SUCCESS = "SUCCESS",
    ERROR = "ERROR",
    WARN = "WARN",
}

export enum Role {
    USER = "USER",
    ADMIN = "ADMIN",
}

export enum X3Class {
    CUSTOMER = "CUSTOMER",
    PRODUCT = "PRODUCT",
    SALESORDER = "SALESORDER",
}

export enum X3Facet {
    QUERY = "QUERY",
    DATAILS = "DATAILS",
}

export enum X3PublicName {
    CUSTOMER = "CUSTOMER",
    PRODUCT = "PRODUCT",
    SALESORDER = "SALESORDER",
    SALESQUOTE = "SALESQUOTE",
}

export enum X3Representation {
    CUSTOMER = "CUSTOMER",
    PRODUCT = "PRODUCT",
    SALESORDER = "SALESORDER",
    SITE = "SITE",
}

export enum X3StatusIntegration {
    PENDING = "PENDING",
    ERROR_INTEGRACAO = "ERROR_INTEGRACAO",
    ERROR_X3 = "ERROR_X3",
    SUCCESS = "SUCCESS",
}

export enum Direction {
    ASC = "ASC",
    DESC = "DESC",
}

export enum NullHandling {
    NATIVE = "NATIVE",
    NULLS_FIRST = "NULLS_FIRST",
    NULLS_LAST = "NULLS_LAST",
}

function uriEncoding(template: TemplateStringsArray, ...substitutions: any[]): string {
    let result = "";
    for (let i = 0; i < substitutions.length; i++) {
        result += template[i];
        result += encodeURIComponent(substitutions[i]);
    }
    result += template[template.length - 1];
    return result;
}


// Added by 'AxiosClientExtension' extension

import axios from "axios";
import * as Axios from "axios";

declare module "axios" {
    export interface GenericAxiosResponse<R> extends Axios.AxiosResponse {
        data: R;
    }
}

class AxiosHttpClient implements HttpClient<Axios.AxiosRequestConfig> {

    constructor(private axios: Axios.AxiosInstance) {
    }

    request<R>(requestConfig: { method: string; url: string; queryParams?: any; data?: any; copyFn?: (data: R) => R; options?: Axios.AxiosRequestConfig; }): RestResponse<R> {
        function assign(target: any, source?: any) {
            if (source != undefined) {
                for (const key in source) {
                    if (source.hasOwnProperty(key)) {
                        target[key] = source[key];
                    }
                }
            }
            return target;
        }

        const config: Axios.AxiosRequestConfig = {};
        config.method = requestConfig.method as typeof config.method;  // `string` in axios 0.16.0, `Method` in axios 0.19.0
        config.url = requestConfig.url;
        config.params = requestConfig.queryParams;
        config.data = requestConfig.data;
        assign(config, requestConfig.options);
        const copyFn = requestConfig.copyFn;

        const axiosResponse = this.axios.request(config);
        return axiosResponse.then(axiosResponse => {
            if (copyFn && axiosResponse.data) {
                (axiosResponse as any).originalData = axiosResponse.data;
                axiosResponse.data = copyFn(axiosResponse.data);
            }
            return axiosResponse;
        });
    }
}

export class AxiosCustomerResourceClient extends CustomerResourceClient<Axios.AxiosRequestConfig> {

    constructor(baseURL: string, axiosInstance: Axios.AxiosInstance = axios.create()) {
        axiosInstance.defaults.baseURL = baseURL;
        super(new AxiosHttpClient(axiosInstance));
    }
}

export class AxiosProductResourceClient extends ProductResourceClient<Axios.AxiosRequestConfig> {

    constructor(baseURL: string, axiosInstance: Axios.AxiosInstance = axios.create()) {
        axiosInstance.defaults.baseURL = baseURL;
        super(new AxiosHttpClient(axiosInstance));
    }
}

export class AxiosAuthenticationResourceClient extends AuthenticationResourceClient<Axios.AxiosRequestConfig> {

    constructor(baseURL: string, axiosInstance: Axios.AxiosInstance = axios.create()) {
        axiosInstance.defaults.baseURL = baseURL;
        super(new AxiosHttpClient(axiosInstance));
    }
}

export class AxiosSalesQuoteResourceClient extends SalesQuoteResourceClient<Axios.AxiosRequestConfig> {

    constructor(baseURL: string, axiosInstance: Axios.AxiosInstance = axios.create()) {
        axiosInstance.defaults.baseURL = baseURL;
        super(new AxiosHttpClient(axiosInstance));
    }
}

export class AxiosSiteResourceClient extends SiteResourceClient<Axios.AxiosRequestConfig> {

    constructor(baseURL: string, axiosInstance: Axios.AxiosInstance = axios.create()) {
        axiosInstance.defaults.baseURL = baseURL;
        super(new AxiosHttpClient(axiosInstance));
    }
}

export class AxiosProductCustomerResourceClient extends ProductCustomerResourceClient<Axios.AxiosRequestConfig> {

    constructor(baseURL: string, axiosInstance: Axios.AxiosInstance = axios.create()) {
        axiosInstance.defaults.baseURL = baseURL;
        super(new AxiosHttpClient(axiosInstance));
    }
}

export class AxiosSalesQuoteProductResourceClient extends SalesQuoteProductResourceClient<Axios.AxiosRequestConfig> {

    constructor(baseURL: string, axiosInstance: Axios.AxiosInstance = axios.create()) {
        axiosInstance.defaults.baseURL = baseURL;
        super(new AxiosHttpClient(axiosInstance));
    }
}
