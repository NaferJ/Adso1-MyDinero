import { Component, OnInit, AfterViewInit  } from '@angular/core';
import * as mapboxgl from 'mapbox-gl';
import { MapServService } from '../../services/map-serv.service';
import { GeoJson, FeatureCollection } from './map';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-ubicacion',
  templateUrl: './ubicacion.component.html',
  styleUrls: ['./ubicacion.component.scss']
})
export class UbicacionComponent implements OnInit, AfterViewInit  {

  map: mapboxgl.Map;

  style = 'mapbox://styles/mapbox/streets-v11';
  lat = 10.963103;
  lng = -74.794470;
  message = 'Gts';

  source: any;
  markers: any;
  tanda: any;

  cobarMarker: any;

  mark: mapboxgl.Marker;

  constructor(private mapService: MapServService) { }

  ngOnInit() {
    this.markers = this.mapService.getMarkers();
    if (!mapboxgl.supported()) {
      Swal.fire('Alto ahí', 'Tu navegador no soporta la tecnología', 'warning');
    } else {
      this.initializeMap();
    }
  }

  initializeMap() {
    this.buildMap();
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(position => {
        this.lat = position.coords.latitude;
        this.lng = position.coords.longitude;
        this.map.flyTo({
          center: [this.lng, this.lat]
        });
      })
    }
  }

  buildMap() {
    this.map = new mapboxgl.Map({
      container: 'map',
      style: this.style,
      zoom: 14,
      center: [this.lng, this.lat]
    });

    this.map.addControl(new mapboxgl.NavigationControl());

    this.mark = new mapboxgl.Marker({
      draggable: true
    }).setLngLat([this.lng, this.lat]).addTo(this.map);

    this.mark.on('dranged', () => {
      this.lng = this.mark.getLngLat().lat;
      this.lat = this.mark.getLngLat().lng;
    });
    this.map.on('load', function() {
      this.map.addLayer({
        'id': 'places',
        'type': 'symbol',
        'source': {
          'type': FeatureCollection,
          'features': [{
            'type': 'Feature',
            'properties': {
              'description': '<strong>GTS</strong>',
              'icon': 'theatre'
            },
            'geometry': {
              'type': 'Point',
              'coordinates': [10.963103, -74.794470]
            }
          }]
        },
        'layout': {
          'icon-image': '{icon}-15',
          'icon-allow-overlap': true
        }
      });
    });

  }

  ngAfterViewInit () { }

}
