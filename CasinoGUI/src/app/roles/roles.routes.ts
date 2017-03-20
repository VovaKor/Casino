import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {RolesListComponent} from "./roles-list.component";
import {RoleDetailsComponent} from "./role-details.component";
/**
 * Created by Вова on 20.03.2017.
 */
const routes: Routes = [
    {
        path: '',
        component: RolesListComponent,
        children: [
            {
                path: ':id',
                component: RoleDetailsComponent,

            },
        ]
    }

];
@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class RolesRoutingModule { }