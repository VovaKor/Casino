/**
 * Created by Вова on 20.03.2017.
 */
import {Component, OnDestroy, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {RoleService} from "./role.service";
import {Role} from "./role";
import {RolesListComponent} from "./roles-list.component";

@Component({
    selector: 'role-details',
    template: `<!-- new syntax for ng-if -->
    <section *ngIf="role">
        <section>
            <h2>You selected: {{role.roleName}}</h2>
            <h3>Description</h3>
            <p>
                {{role.description}}
            </p>
        </section>
        <section>
            <form (ngSubmit)="updateRoleDetails(role)" #roleForm="ngForm">
                <div>
                    <label for="name">Name: </label>
                    <input type="text" name="name" required [(ngModel)]="role.roleName" #name="ngModel">
                    <div [hidden]="name.valid || name.pristine" class="error">
                        Name is required my good sir/lady!
                    </div>
                </div>
                <div>
                    <label for="description">Description: </label>
                    <input type="text" name="description" [(ngModel)]="role.description">
                </div>

                <button type="submit" [disabled]="!roleForm.form.valid">Update</button>
                
            </form>
        </section>
        <button (click)="delRole(role)">Delete role</button>
        <button (click)="gotoRolesList()">Back to roles list</button>
    </section>
    `
})

export class RoleDetailsComponent implements OnInit, OnDestroy {
    role: Role;
    sub: any;

    constructor(private rolesList:RolesListComponent,
                private roleService: RoleService,
                private route: ActivatedRoute,
                private router: Router){
    }

    ngOnInit(){
        this.sub = this.route.params.subscribe(params => {
            let id = Number.parseInt(params['id']);
            //console.log('getting role with roleId: ', id);
            this.roleService
                .get(id)
                .subscribe(r => this.role = r);
        });
    }

    ngOnDestroy(){
        this.sub.unsubscribe();
    }

    gotoRolesList(){
        this.router.navigate(['../'],{ relativeTo: this.route });
    }

    updateRoleDetails(role: Role){
        this.roleService
            .update(role)
            .subscribe(
                r => {this.role = r;
                this.rolesList.ngOnInit();
                }
            );

    }
    delRole(role: Role){
        this.roleService.deleteRole(role.roleId).then(
            () => {
                this.role = null;
                this.rolesList.ngOnInit();
            }
        );

    }
}