import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Matches} from 'src/app/modules/matches/Matches';
import  {USER_NAME} from 'src/app/modules/authentication/authentication.service';



@Injectable({
  providedIn: 'root'
})
export class MatchesService {
  thirdPartyApi:string;
  apiKey:string;
  backendApi:string;
  userName:string;
  recommendationService:string;
  constructor(private httpClient:HttpClient) {
    this.thirdPartyApi = 'https://cricapi.com/api/';
    this.apiKey = 'apikey=60sJnInd0LSBuEf37QKfIoli8032';
    this.backendApi = 'http://localhost:8085/favouriteservice/api/v1/userfavourites/users/';
    
    this.recommendationService = 'http://localhost:8085/recommendationservice/api/v1/recommendationservice/recommendations';
  }

  getMatches():Observable<any>{
    const url = this.thirdPartyApi+'matches?'+this.apiKey;
    return this.httpClient.get(url);
  }

  getScores(id):Observable<any>{
    const url = this.thirdPartyApi+'cricketScore?'+this.apiKey+'&unique_id='+id;
    return this.httpClient.get(url);
  }

  addToFavourties(matches: Matches){
    this.userName = sessionStorage.getItem(USER_NAME);
    const url = this.backendApi+this.userName+"/match";
    console.log("service :",url);
    return this.httpClient.post(url,matches,{
      observe:"response"
    });
  }

  removeFavourties(id:number){
    this.userName = sessionStorage.getItem(USER_NAME);
    const url = this.backendApi+this.userName+"/match/"+id;
    return this.httpClient.delete(url);
  }

  getAllFavourites(){
    this.userName = sessionStorage.getItem(USER_NAME);
    const url = this.backendApi+this.userName+"/matches";
    return this.httpClient.get(url);
  }

  getRecommendations(){
    const url = this.recommendationService;
    return this.httpClient.get(url);
  }

    
}
