import { Component, OnInit,ViewChild } from '@angular/core';
import {MatPaginator, MatTableDataSource} from '@angular/material';
import {MatchesService} from 'src/app/modules/matches/matches.service';
import {MatSnackBar} from '@angular/material';
import {Matches} from 'src/app/modules/matches/Matches';
import { ActivatedRoute } from '@angular/router';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {ScoreComponent} from '../score/score.component';
import { ArrayType } from '@angular/compiler/src/output/output_ast';


@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
  styleUrls: ['./matches.component.css']
})
export class MatchesComponent implements OnInit {
  displayedColumns: string[] = ['date', 'type', 'desc', 'action'];
  dataSource:MatTableDataSource<any>;

  matches: any;
  url:string;
  constructor(private matchService:MatchesService,
    private matSnackBar:MatSnackBar,
    private route:ActivatedRoute,private dialog: MatDialog) {
    this.matches = [];
    this.dataSource = new MatTableDataSource(this.matches);
   }


  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.route.url.subscribe( data => {
      this.url = data[0].path;
    });
    if("matches" === this.url)
      this.getMatchesFromThirdParty();
    else if("favourites" === this.url)
      this.getFavourites();
    else if("recommendations" === this.url)
      this.getRecommendations();
    
    
  }

  getRecommendations(){
    this.matchService.getRecommendations().subscribe( data => {
      let recommendations:any = data;
      recommendations.forEach(element => {
        let match:Matches = element.matches;
        match.count = element.count;
        this.matches.push(match);
        
      });
      console.log("ggggg", data);
    this.dataSource = new MatTableDataSource(this.matches);
    this.paginator.pageSize = 5;
    this.dataSource.paginator = this.paginator;
    });
    
  }

  getFavourites(){
    this.matchService.getAllFavourites().subscribe( data => {
      console.log(data);
      this.matches = data;
      this.dataSource = new MatTableDataSource(this.matches);
      this.paginator.pageSize = 5;
      this.dataSource.paginator = this.paginator;
    });
    
  }

  getMatchesFromThirdParty(){
    this.matchService.getMatches().subscribe(data => {
      console.log("result "+data.matches);
      this.matches = [];
      data.matches.forEach(element => {
        let match:Matches = new Matches();
        match.id = element.unique_id;
        match.date = element.date;
        match.description = element['team-1']+ " vs " + element['team-2'];
        match.type = element.type;
        this.matches.push(match);
        
      });
    this.dataSource = new MatTableDataSource(this.matches);
    this.paginator.pageSize = 5;
    this.dataSource.paginator = this.paginator;
    });
  }

  filterEvent(event){
    const inDate =`${event.value}`;
    const inDateObj = new Date(inDate);
    const inDateStr = inDateObj.getDate()+'-'+(inDateObj.getMonth()+1)+'-'+inDateObj.getFullYear();
    const result = this.matches.filter(match =>{
      //return match.date.this.artistName);
      let arrDate = new Date(match.date);
      let aDStr = arrDate.getDate()+'-'+(arrDate.getMonth()+1)+'-'+arrDate.getFullYear();
      return inDateStr.match(aDStr);
    });
    this.dataSource = new MatTableDataSource(result);
    this.paginator.pageSize = 5;
    this.dataSource.paginator = this.paginator;

  }

  getScore(matches:Matches){
    
    this.matchService.getScores(matches.id).subscribe(data =>{
      let score;
      if(data.score)
        score = data.score;
      else
        score = "score is not available";

      const dialogRef = this.dialog.open(ScoreComponent, {
          width: '250px',
          data: score
        });
    });
  }

  removeFromFavourites(matches:Matches){
    this.matchService.removeFavourties(matches.id).subscribe(response =>{
      this.matSnackBar.open("Match is removed from Favourites"," ",{duration:1000});
      
    });
    this.getFavourites();
  }

  addToFavourites(matches:Matches){
    
    let statusCode;
    this.matchService.addToFavourties(matches).subscribe(
      data =>{
        console.log("add to Favourites response ", data);
        statusCode = data.status;
        if(statusCode === 201){
          console.log("add to wish list response ", data);
          this.matSnackBar.open("Match is successfully Added to Favourites"," ",{duration:1000});
        }
      },
      error=>{
        
        statusCode = error.status;
        console.log("AddtoFavourites failed ",statusCode);
        if(statusCode == 409){
          this.matSnackBar.open(error.error.message," ",{duration:1000});
        }
      });
  }
}


