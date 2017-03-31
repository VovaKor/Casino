/**
 * Created by Вова on 20.03.2017.
 */
import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {RolesRoutingModule} from "./roles.routes";
import {RolesListComponent} from "./roles-list.component";
import {RoleService} from "./role.service";
import {RoleDetailsComponent} from "./role-details.component";
import {Popup2AddComponent} from "./popup2add.component";

@NgModule({
    imports: [ CommonModule, RolesRoutingModule, FormsModule, HttpModule],
    declarations: [ RolesListComponent, RoleDetailsComponent, Popup2AddComponent],
    providers: [RoleService]
})
export class RolesModule { }