import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule,HttpTestingController} from '@angular/common/http/testing';
import { MatchesService } from './matches.service';
import { Matches } from 'src/app/modules/matches/Matches';
import { matchesElement } from '@angular/animations/browser/src/render/shared';

describe('MatchesService', () => {
  let matches = new Matches();
  matches.id =123;
  matches.date = "23-Jun-2019";
  matches.team1 ='India';
  matches.team2 = 'Pakisthan';
  matches.type = 'T20';
  matches.description = 'India vs Pakisthan';
  matches.count = 100;

  const backendApi = 'http://localhost:8085/favouriteservice/api/v1/userfavourites/users/';
  const recommendationService = 'http://localhost:8085/recommendationservice/api/v1/recommendationservice/recommendations';
  let matchesService:MatchesService;
  let httpTestingController:HttpTestingController;

  beforeEach(() => 
  {
    TestBed.configureTestingModule({
    imports:[HttpClientTestingModule],
      providers:[MatchesService]
    });
    matchesService = TestBed.get(MatchesService);
    httpTestingController= TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
  
    expect(matchesService).toBeTruthy();
  });

  it('should add the match to favourites', () =>{
    matchesService.addToFavourties(matches).subscribe(result =>{
      expect(result.body).toBe(matches);
    });
    const url = backendApi+"null/match";
    const httpMockRequest = httpTestingController.expectOne(url);
    console.log(url);
    expect(httpMockRequest.request.method).toBe('POST');
  });

  it('should get all matches from favourites', () =>{
    matchesService.getAllFavourites().subscribe(result =>{
      
    });
    const url = backendApi+"null/matches";
    const httpMockRequest = httpTestingController.expectOne(url);
    expect(httpMockRequest.request.method).toBe('GET');
  });

  it('should get all matches from Recommendation', () =>{
    matchesService.getRecommendations().subscribe(result =>{
      
    });
    const url = recommendationService;
    const httpMockRequest = httpTestingController.expectOne(url);
    expect(httpMockRequest.request.method).toBe('GET');
  });

  it('should delete the match from wishlist', () =>{
    matchesService.removeFavourties(matches.id).subscribe(result =>{
      //expect(result.body).toBe(track);
    });
    const url = backendApi+"null/match/"+matches.id;
    const httpMockRequest = httpTestingController.expectOne(url);
    expect(httpMockRequest.request.method).toBe('DELETE');
  });

});
