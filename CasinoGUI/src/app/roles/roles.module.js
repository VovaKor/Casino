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
var common_1 = require("@angular/common");
var forms_1 = require("@angular/forms");
var http_1 = require("@angular/http");
var roles_routes_1 = require("./roles.routes");
var roles_list_component_1 = require("./roles-list.component");
var role_service_1 = require("./role.service");
var role_details_component_1 = require("./role-details.component");
var RolesModule = (function () {
    function RolesModule() {
    }
    RolesModule = __decorate([
        core_1.NgModule({
            imports: [common_1.CommonModule, roles_routes_1.RolesRoutingModule, forms_1.FormsModule, http_1.HttpModule],
            declarations: [roles_list_component_1.RolesListComponent, role_details_component_1.RoleDetailsComponent],
            providers: [role_service_1.RoleService]
        }), 
        __metadata('design:paramtypes', [])
    ], RolesModule);
    return RolesModule;
}());
exports.RolesModule = RolesModule;
//# sourceMappingURL=roles.module.js.map