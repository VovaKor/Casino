"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var http_1 = require("@angular/http");
require('rxjs/add/operator/toPromise');
var TransactionService = (function () {
    function TransactionService(http) {
        this.http = http;
        this.baseUrl = 'http://localhost:8080/transactions';
    }
    TransactionService.prototype.getAllTransactions = function () {
        return this.http.get(this.baseUrl + '/all', this.getHeaders())
            .toPromise()
            .then(function (response) { return response.json().transactions; })
            .catch(this.handleError);
    };
    TransactionService.prototype.getUsersTransactions = function (login) {
        if (login === 'all') {
            return this.getAllTransactions();
        }
        else {
            return this.http.get(this.baseUrl + '/byloginid/' + login, this.getHeaders())
                .toPromise()
                .then(function (response) { return response.json().transactions; })
                .catch(this.handleError);
        }
    };
    TransactionService.prototype.addTransaction = function (transaction) {
        return this.http.post(this.baseUrl + '/add', JSON.stringify(transaction), this.getHeaders())
            .toPromise()
            .then(function (response) { return response.json().transactions; })
            .catch(this.handleError);
    };
    TransactionService.prototype.handleError = function (error) {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    };
    TransactionService.prototype.getHeaders = function () {
        var headers = new http_1.Headers();
        headers.append('Accept', 'application/json');
        return headers;
    };
    TransactionService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], TransactionService);
    return TransactionService;
}());
exports.TransactionService = TransactionService;
//# sourceMappingURL=transaction.service.js.map