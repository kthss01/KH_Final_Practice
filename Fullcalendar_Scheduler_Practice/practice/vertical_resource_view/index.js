document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    timeZone: 'UTC',
    initialView: 'resourceTimeGridDay',
    resources: [
      { id: 'a', title: 'Room A' },
      { id: 'b', title: 'Room B'},
      { id: 'c', title: 'Room C' },
      { id: 'd', title: 'Room D' }
    ],
    events: 'https://fullcalendar.io/demo-events.json?with-resources=4&single-day'
  });

  calendar.render();
});