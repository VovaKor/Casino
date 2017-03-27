import { Injectable } from '@angular/core';
import { Transaction } from './transaction';
import { Http, Headers } from "@angular/http";

import 'rxjs/add/operator/toPromise';

@Injectable()
export class TransactionService {
    private baseUrl = 'http://localhost:8080/transactions';

    constructor(private http: Http) {}

    getAllTransactions(): Promise<Transaction[]> {
        return this.http.get(this.baseUrl + '/all', this.getHeaders())
            .toPromise()
            .then(response => response.json().transactions as Transaction[])
            .catch(this.handleError);
    }

    getUsersTransactions(login: string): Promise<Transaction[]> {
        if (login === 'all') {
            return this.getAllTransactions();
        } else {
            return this.http.get(this.baseUrl + '/byloginid/' + login, this.getHeaders())
                .toPromise()
                .then(response => response.json().transactions as Transaction[])
                .catch(this.handleError);
        }
    }

    addTransaction(transaction: Transaction): Promise<Transaction[]> {
        return this.http.post(this.baseUrl + '/add', JSON.stringify(transaction) ,this.getHeaders())
            .toPromise()
            .then(response => response.json().transactions as Transaction[])
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    private getHeaders(){
        let headers = new Headers();
        headers.append('Accept', 'application/json');
        return headers;
    }
}