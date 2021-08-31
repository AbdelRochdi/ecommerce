import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { map } from 'rxjs/operators';
import { ProductCategory } from '../common/product-category';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private host = environment.host;

  private baseUrl = this.host + '/api/products';

  private categoryUrl = this.host + '/api/product-categories';

  constructor(private httpClient: HttpClient) { }

  getProductList(theCategoryId: number, thePage: number, thePageSize: number): Observable<GetResponseProducts> {

    const searchUrl = `${this.baseUrl}/searchByCategory?category=${theCategoryId}`
      + `&page=${thePage}&size=${thePageSize}`;

    return this.httpClient.get<GetResponseProducts>(searchUrl).pipe(
      map(response => response));
  }

  getProduct(theProductId: number): Observable<Product> {
    const searchUrl = `${this.baseUrl}/${theProductId}`;
    return this.httpClient.get<Product>(searchUrl);
  }

  getProductCategories(): Observable<ProductCategory[]> {
    return this.httpClient.get<ProductCategory[]>(this.categoryUrl).pipe(
      map(response => response));
  }

  searchProducts(theKeyword: string, thePage: number, thePageSize: number): Observable<GetResponseProducts> {
    const searchUrl = `${this.baseUrl}/searchByName?name=${theKeyword}`
      + `&page=${thePage}&size=${thePageSize}`;

    return this.httpClient.get<GetResponseProducts>(searchUrl).pipe(
      map(response => response));
  }
}

interface GetResponseProducts {
  productList: Product[];
  totalElements: number;
  pageNumber: number;
  pageSize: number;
}