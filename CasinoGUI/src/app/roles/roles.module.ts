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
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
    imports: [ CommonModule, RolesRoutingModule, FormsModule, HttpModule, BrowserAnimationsModule],
    declarations: [ RolesListComponent, RoleDetailsComponent],
    providers: [RoleService]
})
export class RolesModule { }