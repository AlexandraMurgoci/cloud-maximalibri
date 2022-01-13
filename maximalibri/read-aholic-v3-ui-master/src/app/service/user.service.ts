import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  //TODO
  private baseUrl = "http://192.168.49.2:31982/";
  // private baseUrl = "http://localhost:8085/";

  constructor(
    private http: HttpClient
  ) { }

  login(email:string, password:string) {
    let loginData = {'email':email, 'password':password};
    return this.http.post(this.baseUrl+"login",loginData);
  }

  register(registerData) {
    return this.http.post(this.baseUrl+"register",registerData);
  }

  search(page: number, searchParam: string) {
    return searchParam && (searchParam+"")!="" ?
     this.http.get(this.baseUrl+"admin/user/search?page="+page+"&searchParam="+searchParam)
     : this.http.get(this.baseUrl+"admin/user/search?page="+page);
  }

  getUserDetails(id: number) {
    return this.http.get(this.baseUrl+"admin/user/"+id);
  }

  changeUserActivation(id: number, active: boolean) {
    return this.http.post(this.baseUrl+"admin/user/"+id+"/activation", {aBoolean: active})
  }

  makeAdmin(id: number) {
    return this.http.post(this.baseUrl+"admin/user/make-admin", {aLong: id})
  }

  getReviewsForUser(id) {
    return this.http.get(this.baseUrl+"admin/user/"+id+"/reviews");
  }

  deleteReviewById(id){
    return this.http.post(this.baseUrl+"admin/user/deleteReview", {aLong: id});
  }

  getRecomendationsForUser(userId) {
    return this.http.get(this.baseUrl+"/user/recommendation/"+userId);
  }

}
