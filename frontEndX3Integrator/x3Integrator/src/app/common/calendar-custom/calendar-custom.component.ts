import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-calendar-custom',
  templateUrl: './calendar-custom.component.html',
  styleUrls: ['./calendar-custom.component.scss']
})
export class CalendarCustomComponent {
  _data: Date | undefined;

  @Input()
  set data(value: Date) {
    if (value)
      this._data = new Date(value.toString());
  }

  @Output() dataChange = new EventEmitter<Date>();

  ngOnInit() {
    console.log(this._data)
    // Inicialização se necessário
  }

  onDateChange(event: Date) {
    this.dataChange.emit(event);
  }
}
