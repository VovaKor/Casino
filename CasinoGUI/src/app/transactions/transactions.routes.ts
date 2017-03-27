import { TransactionsComponent } from './transactions.component'
import {TransactionService} from "./transaction.service";
import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
    { path: ':login', component: TransactionsComponent }
];
@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [
        RouterModule
    ],
    providers: [
        TransactionService
    ]
})
export class TransactionsRoutingModule { }