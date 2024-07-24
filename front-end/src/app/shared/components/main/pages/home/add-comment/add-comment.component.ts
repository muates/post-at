import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css']
})
export class AddCommentComponent implements OnInit {

  @Input() postId!: number;

  constructor() { }

  ngOnInit(): void {
  }

  addComment() {
    throw new Error('Method not implemented.');
  }

}
