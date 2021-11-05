document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    timeZone: 'UTC',
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    editable: true, // 이거만 하면 되는듯
    dayMaxEvents: true, // when too many events in a day, show the popover
    events: 'https://fullcalendar.io/demo-events.json?overload-day'
  });

  calendar.render();
});