import {Component, Input} from '@angular/core';
import { Transaction } from './transaction';
import { TransactionService } from './transaction.service';
import { OnInit } from '@angular/core'
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';
import 'rxjs/add/operator/switchMap'

@Component({
    selector: 'transactions',
    moduleId: module.id,
    templateUrl: './transactions.component.html',
    styleUrls: [ 'transactions.component.css' ]
})

export class TransactionsComponent implements OnInit {

    @Input()
    login: string;

    transactions: Transaction[];
    selectedTransaction: Transaction;

    errorMessage: string = '';
    isLoading: boolean = true;

    constructor (
        private transactionService: TransactionService,
        private route: ActivatedRoute,
        private location: Location
    ) {}

    ngOnInit() {
        console.log('Transactions component load');
        this.route.params
            .switchMap((params: Params) => this.transactionService.getUsersTransactions(params['login']))
            .subscribe(transactions => this.transactions = transactions,
                e => this.errorMessage = e,
                /* onComplete */ () => this.isLoading = false);
        console.log('Transactions component load FINISHED')
    }

    onSelect(transaction: Transaction): void {
        if(this.selectedTransaction === transaction) {
            this.selectedTransaction = null;
        } else {
            this.selectedTransaction = transaction
        }
    }

    goBack() {
        this.location.back();
    }
}