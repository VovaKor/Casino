import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import { TransactionsRoutingModule } from "./transactions.routes";
import { TransactionsComponent }  from "./transactions.component";
import { TransactionService } from "./transaction.service";

@NgModule({
    imports: [
        CommonModule,
        TransactionsRoutingModule,
        FormsModule,
        HttpModule
    ],
    declarations: [
        TransactionsComponent
    ],
    providers: [ TransactionService ]
})
export class TransactionsModule { }