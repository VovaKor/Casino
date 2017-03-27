import { Injectable } from '@angular/core';
import {Http, Response, Headers} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {Automat} from "./automat";

@Injectable()
export class AutomatService {
  private baseUrl: string = 'http://localhost:8080/automats';

  constructor(private http : Http){
  }
  getAutomats(): Observable<Automat[]> {
      let automats$ = this.http
          .get(`${this.baseUrl}/all`, {headers: this.getHeaders()})
          .map(mapAutomats)
          .catch(handleError);
      return automats$;
  }

  getAutomat(id: number): Observable<Automat> {
      let automat$ = this.http
          .get(`${this.baseUrl}/byId/${id}`, {headers: this.getHeaders()})
          .map(mapAutomat)
          .catch(handleError);
      return automat$;
  }
  private getHeaders(){
        let headers = new Headers();
        headers.append('Accept', 'application/json');
        return headers;
  }
}
function mapAutomat(response:Response): Automat{
    let automats = mapAutomats(response);
    return automats.pop();
}
function mapAutomats(response:Response): Automat[]{
    // The response of the API has a results
    // property with the actual results
    return response.json().automats.map(toAutomat)
}
function toAutomat(a:any): Automat{
    let automat = <Automat>({
        id: a.automatId,
        name: a.automatName,
        description: a.description,
        slot1: a.slot1,
        slot2: a.slot2,
        slot3: a.slot3,
        isWon: a.isWon
    });
    console.log('Parsed automat:', automat);
    return automat;
}
function handleError (error: any) {
    // log error
    // could be something more sofisticated
    let errorMsg = error.message || `Yikes! There was a problem and we couldn't retrieve your data!`
    console.error(errorMsg);

    // throw an application level error
    return Observable.throw(errorMsg);
}
