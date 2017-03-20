import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {Role} from "./role";
import {RoleService} from "./role.service";
/**
 * Created by Вова on 20.03.2017.
 */
@Component({
    selector: 'roles-list',
    template: `
        <section>
            <section *ngIf="isLoading && !errorMessage">
                Loading roles!!! Retrieving data...
            </section>
            <ul class="items">
                <!-- this is the new syntax for ng-repeat -->
                <li *ngFor="let role of roles" (click)="onSelect(role)">

                    {{role.name}}

                </li>
            </ul>
            <section *ngIf="errorMessage">
                {{errorMessage}}
            </section>
        </section>
        <router-outlet></router-outlet>
    `
})
export class RolesListComponent implements OnInit{
    roles: Role[] = [];
    errorMessage: string = '';
    isLoading: boolean = true;

    constructor(private roleService : RoleService,
                private route: ActivatedRoute,
                private router: Router){ }

    ngOnInit(){
        this.roleService
            .getAll()
            .subscribe(
                /* happy path */ r => this.roles = r,
                /* error path */ e => this.errorMessage = e,
                /* onComplete */ () => this.isLoading = false);
    }
    onSelect(role: Role) {
        //this.selectedId = crisis.id;

        // Navigate with relative link
        this.router.navigate([role.id], { relativeTo: this.route });
    }
}