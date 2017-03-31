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
var core_1 = require("@angular/core");
var router_1 = require("@angular/router");
var role_service_1 = require("./role.service");
/**
 * Created by Вова on 20.03.2017.
 */
var RolesListComponent = (function () {
    function RolesListComponent(roleService, route, router) {
        this.roleService = roleService;
        this.route = route;
        this.router = router;
        this.showDialog = false;
        this.$role = {};
        this.errorMessage = '';
        this.isLoading = true;
    }
    RolesListComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.roleService.getAll()
            .subscribe(
        /* happy path */ function (r) { return _this.roles = r; }, 
        /* error path */ function (e) { return _this.errorMessage = e; }, 
        /* onComplete */ function () { return _this.isLoading = false; });
    };
    RolesListComponent.prototype.onSelect = function (role) {
        // Navigate with relative link
        this.router.navigate([role.roleId], { relativeTo: this.route });
    };
    RolesListComponent.prototype.createRole = function (role) {
        var _this = this;
        this.roleService.add(role).subscribe(
        /* happy path */ function (r) { return _this.roles.push(r); }, 
        /* error path */ function (e) { return _this.errorMessage = e; });
        this.showDialog = false;
    };
    return RolesListComponent;
}());
RolesListComponent = __decorate([
    core_1.Component({
        selector: 'roles-list',
        template: "\n        <section>\n            <section *ngIf=\"isLoading && !errorMessage\">\n                Loading roles!!! Retrieving data...\n            </section>\n            <ul class=\"items\">\n                <!-- this is the new syntax for ng-repeat -->\n                <li *ngFor=\"let role of roles\" (click)=\"onSelect(role)\">\n\n                    {{role.roleName}}\n\n                </li>\n            </ul>\n            <section *ngIf=\"errorMessage\">\n                {{errorMessage}}\n            </section>\n            <button (click)=\"showDialog = !showDialog\" class=\"btn\">Create new role</button>\n        </section>\n            <app-dialog [(visible)]=\"showDialog\">\n                <h4>New role</h4>\n                <form (ngSubmit)=\"createRole($role)\" #roleForm=\"ngForm\">\n                    <div>\n                        <label for=\"id\">Id: </label>\n                        <input type=\"text\" name=\"id\" required [(ngModel)]=\"$role.roleId\" #id=\"ngModel\">\n                        <div [hidden]=\"id.valid || id.pristine\" class=\"error\">\n                            Id is required my good sir/lady!\n                        </div>\n                    </div>\n                    <div>\n                        <label for=\"name\">Name: </label>\n                        <input type=\"text\" name=\"name\" maxlength=\"20\" required [(ngModel)]=\"$role.roleName\" #name=\"ngModel\">\n                        <div [hidden]=\"name.valid || name.pristine\" class=\"error\">\n                            Name is required my good sir/lady!\n                        </div>\n                    </div>\n                    <div>\n                        <label for=\"description\">Description: </label>\n                        <textarea name=\"description\" rows=\"6\" cols=\"35\" maxlength=\"200\" [(ngModel)]=\"$role.description\" #description=\"ngModel\">\n                        </textarea>\n                        <div [hidden]=\"description.valid || description.pristine\" class=\"error\">\n                            Description is required my good sir/lady!\n                        </div>\n                    </div>\n\n\n                    <button type=\"submit\" [disabled]=\"!roleForm.form.valid\">Create</button>\n                </form>\n                <button (click)=\"showDialog = !showDialog\" class=\"btn\">Cancel</button>\n            </app-dialog>\n        \n        <router-outlet></router-outlet>\n    "
    }),
    __metadata("design:paramtypes", [role_service_1.RoleService,
        router_1.ActivatedRoute,
        router_1.Router])
], RolesListComponent);
exports.RolesListComponent = RolesListComponent;
//# sourceMappingURL=roles-list.component.js.map