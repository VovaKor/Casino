/**
 * Created by Вова on 20.03.2017.
 */
import {Injectable} from "@angular/core";
import {Http, Response, Headers} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {Role} from "./role";

@Injectable()
export class RoleService{
    private baseUrl: string = 'http://localhost:8080/roles';

    constructor(private http : Http){
    }

    getAll(): Observable<Role[]>{
        let roles$ = this.http
            .get(`${this.baseUrl}/all`, {headers: this.getHeaders()})
            .map(mapRoles)
            .catch(handleError);
        return roles$;
    }

    get(id: number): Observable<Role> {
        let role$ = this.http
            .get(`${this.baseUrl}/byId/${id}`, {headers: this.getHeaders()})
            .map(mapRole)
            .catch(handleError);
        return role$;
    }

    update(role: Role): Observable<Role>{
        return this.http
            .post(`${this.baseUrl}/update`,JSON.stringify({role}), {headers: this.getHeaders()})
            .map(mapRole)
            .catch(handleError);
    }
    add(role: Role): Observable<Role>{
        return this.http
            .post(`${this.baseUrl}/add`,JSON.stringify({role}), {headers: this.getHeaders()})
            .map(mapRole)
            .catch(handleError);
    }
    deleteRole(id: number): Promise<number>{
        return this.http
            .get(`${this.baseUrl}/delete/${id}`, {headers: this.getHeaders()})
            .toPromise()
            .then(response => response.json().retcode as number)
            .catch(handleError);

    }

    private getHeaders(){
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return headers;
    }
}

function mapRoles(response:Response): Role[]{
    // uncomment to simulate error:
    // throw new Error('ups! Force choke!');

    // The response of the API has a results
    // property with the actual results
    return response.json().roles.map(toRole)
}

function mapRole(response:Response): Role{
    let roles = mapRoles(response);
    return roles.pop();
}
function toRole(r:any): Role{
    let role = <Role>({
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
function handleError (error: any) {
    // log error
    // could be something more sofisticated
    let errorMsg = error.message || `Yikes! There was a problem and we couldn't retrieve your data!`
    console.error(errorMsg);

    // throw an application level error
    return Observable.throw(errorMsg);
}