document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    timeZone: 'UTC',
    initialView: 'resourceTimeGridFourDay',
    datesAboveResources: true,
    headerToolbar: {
      left: 'prev,next',
      center: 'title',
      right: 'resourceTimeGridDay,resourceTimeGridFourDay'
    },
    views: {
      resourceTimeGridFourDay: {
        type: 'resourceTimeGrid',
        duration: { days: 4 },
        buttonText: '4 days'
      }
    },
    resources: [
      { id: 'a', title: 'Room A' },
      { id: 'b', title: 'Room B' }
    ],
    events: 'https://fullcalendar.io/demo-events.json?with-resources=2'
  });

  calendar.render();
});