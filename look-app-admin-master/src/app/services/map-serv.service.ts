import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest, HttpEvent } from '@angular/common/http';

import * as mapboxgl from 'mapbox-gl';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MapServService {

  constructor(private http:HttpClient) {
    mapboxgl.accessToken = environment.mapboxgl.accessToken;
  }

  getMarkers(): any {
    return {
      lat : 10.963103,
      lng : -74.794470
    }
  }
}
