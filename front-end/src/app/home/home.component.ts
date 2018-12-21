import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CrawlerService } from '../crawler.service';
import * as M from 'materialize-css';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  urlForm: FormGroup;
  data: any;
  error: any;
  result: any;
  urlRegx = '(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?';
  constructor(private crawlerService: CrawlerService) { }

  ngOnInit() {
    this.urlForm = new FormGroup({
      url: new FormControl('', Validators.compose([Validators.pattern(this.urlRegx), Validators.required]))
    });
  }

  crawl() {
    const url = this.urlForm.get('url').value;
    this.crawlerService.crawl(url).subscribe(
      data => this.data = data,
      err => this.error = err,
      () => {
        console.log(this.data);
        if (this.data.error !== null && this.data.error !== undefined) {
          M.toast({ html: this.data.error, classes: 'red' });
        } else if (this.data.message !== null && this.data.message !== undefined) {
          M.toast({ html: this.data.message, classes: 'green' });
        }
      });
  }

  showResults() {
    const url = this.urlForm.get('url').value;
    this.crawlerService.showResults(url).subscribe(
      data => this.result = data,
      err => this.error = err,
      () => {
        console.log(this.result);
      });
  }

}
