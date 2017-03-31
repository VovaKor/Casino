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
Object.defineProperty(exports, "__esModule", { value: true });
/**
 * Created by Вова on 20.03.2017.
 */
var core_1 = require("@angular/core");
var router_1 = require("@angular/router");
var role_service_1 = require("./role.service");
var roles_list_component_1 = require("./roles-list.component");
var RoleDetailsComponent = (function () {
    function RoleDetailsComponent(rolesList, roleService, route, router) {
        this.rolesList = rolesList;
        this.roleService = roleService;
        this.route = route;
        this.router = router;
    }
    RoleDetailsComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.sub = this.route.params.subscribe(function (params) {
            var id = Number.parseInt(params['id']);
            console.log('getting role with roleId: ', id);
            _this.roleService
                .get(id)
                .subscribe(function (r) { return _this.role = r; });
        });
    };
    RoleDetailsComponent.prototype.ngOnDestroy = function () {
        this.sub.unsubscribe();
    };
    RoleDetailsComponent.prototype.gotoRolesList = function () {
        this.router.navigate(['../'], { relativeTo: this.route });
    };
    RoleDetailsComponent.prototype.updateRoleDetails = function (role) {
        var _this = this;
        this.roleService
            .update(role)
            .subscribe(function (r) {
            _this.role = r;
            console.log('success');
            _this.rolesList.ngOnInit();
        });
    };
    RoleDetailsComponent.prototype.delRole = function (role) {
        var _this = this;
        this.roleService.deleteRole(role.roleId).then(function () {
            _this.role = null;
            _this.rolesList.ngOnInit();
        });
        //this.rolesList.roles = this.rolesList.roles.filter(h => h !== role);
        //
        //this.ngOnDestroy();
        //this.gotoRolesList();
    };
    return RoleDetailsComponent;
}());
RoleDetailsComponent = __decorate([
    core_1.Component({
        selector: 'role-details',
        template: "<!-- new syntax for ng-if -->\n    <section *ngIf=\"role\">\n        <section>\n            <h2>You selected: {{role.roleName}}</h2>\n            <h3>Description</h3>\n            <p>\n                {{role.description}}\n            </p>\n        </section>\n        <section>\n            <form (ngSubmit)=\"updateRoleDetails(role)\" #roleForm=\"ngForm\">\n                <div>\n                    <label for=\"name\">Name: </label>\n                    <input type=\"text\" name=\"name\" required [(ngModel)]=\"role.roleName\" #name=\"ngModel\">\n                    <div [hidden]=\"name.valid || name.pristine\" class=\"error\">\n                        Name is required my good sir/lady!\n                    </div>\n                </div>\n                <div>\n                    <label for=\"description\">Description: </label>\n                    <input type=\"text\" name=\"description\" [(ngModel)]=\"role.description\">\n                </div>\n\n                <button type=\"submit\" [disabled]=\"!roleForm.form.valid\">Update</button>\n                \n            </form>\n        </section>\n        <button (click)=\"delRole(role)\">Delete role</button>\n        <button (click)=\"gotoRolesList()\">Back to roles list</button>\n    </section>\n    "
    }),
    __metadata("design:paramtypes", [roles_list_component_1.RolesListComponent,
        role_service_1.RoleService,
        router_1.ActivatedRoute,
        router_1.Router])
], RoleDetailsComponent);
exports.RoleDetailsComponent = RoleDetailsComponent;
//# sourceMappingURL=role-details.component.js.map