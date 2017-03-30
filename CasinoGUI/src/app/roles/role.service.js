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
/**
 * Created by Вова on 20.03.2017.
 */
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var Rx_1 = require("rxjs/Rx");
var RoleService = (function () {
    function RoleService(http) {
        this.http = http;
        this.baseUrl = 'http://localhost:8080/roles';
    }
    RoleService.prototype.getAll = function () {
        var roles$ = this.http
            .get(this.baseUrl + "/all", { headers: this.getHeaders() })
            .map(mapRoles)
            .catch(handleError);
        return roles$;
    };
    RoleService.prototype.get = function (id) {
        var role$ = this.http
            .get(this.baseUrl + "/byId/" + id, { headers: this.getHeaders() })
            .map(mapRole)
            .catch(handleError);
        return role$;
    };
    RoleService.prototype.update = function (role) {
        return this.http
            .post(this.baseUrl + "/update", JSON.stringify({ role: role }), { headers: this.getHeaders() })
            .map(mapRole)
            .catch(handleError);
    };
    RoleService.prototype.getHeaders = function () {
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        return headers;
    };
    RoleService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], RoleService);
    return RoleService;
}());
exports.RoleService = RoleService;
function mapRoles(response) {
    // uncomment to simulate error:
    // throw new Error('ups! Force choke!');
    // The response of the API has a results
    // property with the actual results
    return response.json().roles.map(toRole);
}
function mapRole(response) {
    var roles = mapRoles(response);
    return roles.pop();
}
function toRole(r) {
    var role = ({
        roleId: r.roleId,
        roleName: r.roleName,
        description: r.description
    });
    console.log('Parsed role:', role);
    return role;
}
// to avoid breaking the rest of our app
// I extract the id from the person url
// function extractId(roleData:any){
//     let extractedId = roleData.url.replace('localhost:8080/roles/byId/','').replace('/','');
//     return parseInt(extractedId);
// }
// this could also be a private method of the component class
function handleError(error) {
    // log error
    // could be something more sofisticated
    var errorMsg = error.message || "Yikes! There was a problem and we couldn't retrieve your data!";
    console.error(errorMsg);
    // throw an application level error
    return Rx_1.Observable.throw(errorMsg);
}
//# sourceMappingURL=role.service.js.map