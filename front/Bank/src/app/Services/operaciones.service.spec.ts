import { TestBed } from '@angular/core/testing';

import { OperacionesService } from '../Operaciones/Services/operaciones.service';

describe('OperacionesService', () => {
  let service: OperacionesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OperacionesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
