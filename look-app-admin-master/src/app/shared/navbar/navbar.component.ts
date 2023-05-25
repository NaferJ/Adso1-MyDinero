import { Component, OnInit, Renderer, ViewChild, ElementRef } from '@angular/core';
import { ROUTES_ADMIN, ROUTES_OPERATIVO, ROUTES_CLIENTE, ROUTES_SUPER } from '../../sidebar/sidebar.component';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import Swal from 'sweetalert2';

const Toast = Swal.mixin({
  toast: true,
  position: 'top-end',
  showConfirmButton: false,
  timer: 3000
});


@Component({
  moduleId: module.id,
  // tslint:disable-next-line: component-selector
  selector: 'navbar-cmp',
  templateUrl: 'navbar.component.html'
})

export class NavbarComponent implements OnInit {
  private listTitles: any[];
  location: Location;
  private nativeElement: Node;
  private toggleButton;
  private sidebarVisible: boolean;
  usuario: any;
  roles: any;
  public isCollapsed = true;
  @ViewChild('navbar-cmp', { static: false }) button;

  // tslint:disable-next-line: max-line-length
  constructor(location: Location, private renderer: Renderer, private element: ElementRef, private router: Router, private auth: AuthService) {
    this.location = location;
    this.nativeElement = element.nativeElement;
    this.sidebarVisible = false;
    this.usuario = JSON.parse(sessionStorage.getItem('usuario'));
    this.roles = this.usuario.roles[0].nombre;
  }

  ngOnInit() {
    switch (this.roles) {
      case 'ROLE_ADMIN':
        this.listTitles = ROUTES_ADMIN.filter(listTitle => listTitle);
        break;
      case 'ROLE_OPERATIVO':
        this.listTitles = ROUTES_OPERATIVO.filter(listTitle => listTitle);
        break;
      case 'ROLE_CLIENTE':
        this.listTitles = ROUTES_CLIENTE.filter(listTitle => listTitle);
        break;
      case 'ROLE_SUPER':
        this.listTitles = ROUTES_SUPER.filter(listTitle => listTitle);
        break;
      default:
        break;
    }
    const navbar: HTMLElement = this.element.nativeElement;
    this.toggleButton = navbar.getElementsByClassName('navbar-toggle')[0];
    this.router.events.subscribe((event) => {
      this.sidebarClose();
    });
  }
  getTitle() {
    let titlee = this.location.prepareExternalUrl(this.location.path());
    if (titlee.charAt(0) === '#') {
      titlee = titlee.slice(1);
    }
    for (let item = 0; item < this.listTitles.length; item++) {
      if (this.listTitles[item].path === titlee) {
        return this.listTitles[item].title;
      }
    }
    return 'Dashboard';
  }
  sidebarToggle() {
    if (this.sidebarVisible === false) {
      this.sidebarOpen();
    } else {
      this.sidebarClose();
    }
  }
  sidebarOpen() {
    const toggleButton = this.toggleButton;
    const html = document.getElementsByTagName('html')[0];
    const mainPanel = <HTMLElement>document.getElementsByClassName('main-panel')[0];
    setTimeout(function () {
      toggleButton.classList.add('toggled');
    }, 500);

    html.classList.add('nav-open');
    if (window.innerWidth < 991) {
      mainPanel.style.position = 'fixed';
    }
    this.sidebarVisible = true;
  };
  sidebarClose() {
    const html = document.getElementsByTagName('html')[0];
    const mainPanel = <HTMLElement>document.getElementsByClassName('main-panel')[0];
    if (window.innerWidth < 991) {
      setTimeout(function () {
        mainPanel.style.position = '';
      }, 500);
    }
    this.toggleButton.classList.remove('toggled');
    this.sidebarVisible = false;
    html.classList.remove('nav-open');
  };
  collapse() {
    this.isCollapsed = !this.isCollapsed;
    const navbar = document.getElementsByTagName('nav')[0];
    if (!this.isCollapsed) {
      navbar.classList.remove('navbar-transparent');
      navbar.classList.add('bg-white');
    } else {
      navbar.classList.add('navbar-transparent');
      navbar.classList.remove('bg-white');
    }

  }

  logOut() {
    this.auth.logout();
    this.router.navigate(['/auth']);
    Toast.fire({
      type: 'info',
      title: 'Hasta Pronto!'
    });
  }
}
