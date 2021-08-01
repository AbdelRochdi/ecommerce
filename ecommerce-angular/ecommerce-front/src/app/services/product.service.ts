import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { map } from 'rxjs/operators';
import { ProductCategory } from '../common/product-category';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8080/api/products';

  private categoryUrl = 'http://localhost:8080/api/product-categories';

  constructor(private httpClient: HttpClient) { }

  getProductList(theCategoryId: number, thePage: number, thePageSize: number): Observable<Product[]> {

    const searchUrl = `${this.baseUrl}/searchByCategory?category=${theCategoryId}`
                    + `&page=${thePage}&size=${thePageSize}`;

    return this.httpClient.get<Product[]>(searchUrl).pipe(
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

  searchProducts(theKeyword: string): Observable<Product[]> {
    const searchUrl = `${this.baseUrl}/searchByName?name=${theKeyword}`;

    return this.httpClient.get<Product[]>(searchUrl).pipe(
      map(response => response));
  }
}
