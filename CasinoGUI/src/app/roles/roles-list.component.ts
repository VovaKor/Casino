import {Component, OnChanges, OnInit, SimpleChanges} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {Role} from "./role";
import {RoleService} from "./role.service";
import {Observable} from "rxjs";
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

                    {{role.roleName}}

                </li>
            </ul>
            <section *ngIf="errorMessage">
                {{errorMessage}}
            </section>
            <button (click)="showDialog = !showDialog" class="btn">Create new role</button>
        </section>
            <app-dialog [(visible)]="showDialog">
                <h4>New role</h4>
                <form (ngSubmit)="createRole($role)" #roleForm="ngForm">
                    <div>
                        <label for="id">Id: </label>
                        <input type="text" name="id" required [(ngModel)]="$role.roleId" #id="ngModel">
                        <div [hidden]="id.valid || id.pristine" class="error">
                            Id is required my good sir/lady!
                        </div>
                    </div>
                    <div>
                        <label for="name">Name: </label>
                        <input type="text" name="name" maxlength="20" required [(ngModel)]="$role.roleName" #name="ngModel">
                        <div [hidden]="name.valid || name.pristine" class="error">
                            Name is required my good sir/lady!
                        </div>
                    </div>
                    <div>
                        <label for="description">Description: </label>
                        <textarea name="description" rows="6" cols="35" maxlength="200" [(ngModel)]="$role.description" #description="ngModel">
                        </textarea>
                        <div [hidden]="description.valid || description.pristine" class="error">
                            Description is required my good sir/lady!
                        </div>
                    </div>


                    <button type="submit" [disabled]="!roleForm.form.valid">Create</button>
                </form>
                <button (click)="showDialog = !showDialog" class="btn">Cancel</button>
            </app-dialog>
        
        <router-outlet></router-outlet>
    `
})
export class RolesListComponent implements OnInit{
    showDialog = false;
    roles: Role[];
    $role: Role = <Role>{};
    errorMessage: string = '';
    isLoading: boolean = true;

    constructor(private roleService : RoleService,
                private route: ActivatedRoute,
                private router: Router){ }

    ngOnInit(){
       this.roleService.getAll()
            .subscribe(
                /* happy path */ r => this.roles = r,
                /* error path */ e => this.errorMessage = e,
                /* onComplete */ () => this.isLoading = false);
    }
    onSelect(role: Role) {
        // Navigate with relative link
        this.router.navigate([role.roleId], { relativeTo: this.route });
    }
    createRole(role: Role){
       this.roleService.add(role).subscribe(
           /* happy path */ r => this.roles.push(r),
           /* error path */ e => this.errorMessage = e);
       this.showDialog = false;
    }
}