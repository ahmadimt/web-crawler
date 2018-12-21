import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class CrawlerService {
  userUrl: string = environment.apiBaseUrl;
  constructor(private httpClient: HttpClient) { }

  crawl(url: any): Observable<any> {
    return this.httpClient.post(this.userUrl.concat('crawler'), url);
  }

  showResults(url: any): Observable<any> {
    return this.httpClient.get(this.userUrl.concat('crawler').concat('/result?url=').concat(url));
  }
}
