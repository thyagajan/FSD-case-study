import { Component, OnInit ,Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';

@Component({
  selector: 'app-score',
  templateUrl: './score.component.html',
  styleUrls: ['./score.component.css']
})
export class ScoreComponent implements OnInit {

  score1:string;
  score2:string;
  constructor(public dialogRef: MatDialogRef<ScoreComponent>,
    @Inject(MAT_DIALOG_DATA) private score: string) {
      var res = score.split(" v ");
      this.score1 =res[0];
      this.score2 = res[1];
     }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
