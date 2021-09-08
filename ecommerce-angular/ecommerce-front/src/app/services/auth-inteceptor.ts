import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>,
        next: HttpHandler): Observable<HttpEvent<any>> {

        localStorage.setItem("id_token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2NoZGkuZWxtYWduaWZpY29AZ21haWwuY29tIiwiZXhwIjoxNjMxMjAxNzQ2LCJpYXQiOjE2MzExMTUzNDZ9.iMP2ZZ2ORLxV3gEQzA8rqDOHGKNMcWB7bYHGyVRdVOA");

        const idToken = localStorage.getItem("id_token");

        if (idToken) {
            const cloned = req.clone({
                headers: req.headers.set("Authorization",
                    "Bearer " + idToken)
            });

            return next.handle(cloned);
        }
        else {
            return next.handle(req);
        }
    }
}